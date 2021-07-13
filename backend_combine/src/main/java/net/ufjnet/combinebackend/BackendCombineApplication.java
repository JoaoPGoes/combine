package net.ufjnet.combinebackend;

import net.ufjnet.combinebackend.models.Artifact;
import net.ufjnet.combinebackend.models.Combiner;
import net.ufjnet.combinebackend.repositories.ArtifactDAO;
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

@SpringBootApplication
public class BackendCombineApplication implements CommandLineRunner {
	
	@Autowired
	private CombinerDAO combinerDAO;

	@Autowired
	private ArtifactDAO artifactDAO;

	public static void main(String[] args) {
		SpringApplication.run(BackendCombineApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Combiner user = new Combiner(
			UUID.randomUUID().toString(),
			"Joao Pedro Goes",
			"123456",
			"joaopedro.goes13@gmail.com",
			"jpgoes",
			LocalDateTime.now(),
			LocalDateTime.now()
		);

		Combiner user2 = new Combiner(
			UUID.randomUUID().toString(),
			"Cleitom",
			"123456",
			"cleitim@gmail.com",
			"cleitu",
			LocalDateTime.now(),
			LocalDateTime.now()
		);
		

		Artifact artifact = new Artifact(
			UUID.randomUUID().toString(),
			"Coleção de ilustrações",
			"ILUSTRAÇÕES",
			"Diversas ilustrações de referências",
			LocalDateTime.now(),
			LocalDateTime.now(),
			user
		);

		Artifact artifact2 = new Artifact(
			UUID.randomUUID().toString(),
			"Coleção de fotografias",
			"FOTOGRAFIA",
			"Fotos renascentistas",
			LocalDateTime.now(),
			LocalDateTime.now(),
			user
		);
		
		Artifact artifact3 = new Artifact(
			UUID.randomUUID().toString(),
			"Arte da guerra",
			"RECORTES",
			"Imagens recortadas de jornais/revistas",
			LocalDateTime.now(),
			LocalDateTime.now(),
			user2
		);

		Artifact artifact4 = new Artifact(
			UUID.randomUUID().toString(),
			"Coleção de ilustrações",
			"casas",
			"Casas com paredes bonitas",
			LocalDateTime.now(),
			LocalDateTime.now(),
			user2
		);

		combinerDAO.saveAll(Arrays.asList(user,user2));
		artifactDAO.saveAll(Arrays.asList(artifact, artifact2, artifact3, artifact4));
	}
}
