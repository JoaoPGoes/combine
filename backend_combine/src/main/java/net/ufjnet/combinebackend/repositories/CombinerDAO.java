package net.ufjnet.combinebackend.repositories;

import net.ufjnet.combinebackend.models.Combiner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CombinerDAO extends JpaRepository<Combiner, Integer> {
	
	public Optional<Combiner> findByUsername (String username);
	public Optional<Combiner> findByEmail (String email);
	public Optional<Combiner> findById (String id);

	@Modifying
	@Query("delete from Combiner c where c.id=:id")
	void deleteCombiner(@Param("id") String id);
		
}

	 
