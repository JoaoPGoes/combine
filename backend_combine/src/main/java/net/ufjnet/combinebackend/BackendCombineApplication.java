package net.ufjnet.combinebackend;

import net.ufjnet.combinebackend.models.Combiner;
import net.ufjnet.combinebackend.repositories.CombinerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class BackendCombineApplication implements CommandLineRunner {
	
	@Autowired
	private CombinerDAO combinerDAO;

	public static void main(String[] args) {
		SpringApplication.run(BackendCombineApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		final String uuid = UUID.randomUUID().toString();
		
		Combiner user = new Combiner(
			uuid,
			"Joao Pedro Goes",
			"123456",
			"joaopedro.goes13@gmail.com",
			"jpgoes",
			LocalDateTime.now()
			, LocalDateTime.now()
		);
		
		combinerDAO.saveAll(Arrays.asList(user));
	}
}
