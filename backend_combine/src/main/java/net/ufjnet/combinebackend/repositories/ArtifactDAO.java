package net.ufjnet.combinebackend.repositories;

import net.ufjnet.combinebackend.models.Artifact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ArtifactDAO extends JpaRepository<Artifact, Integer> {

	public Optional<Artifact> findById (String id);

	@Modifying
	@Query("delete from Artifact b where b.id=:id")
	void deleteArtifact(@Param("id") String id);
}

	 
