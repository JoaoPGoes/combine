package net.ufjnet.combinebackend.services;

import lombok.AllArgsConstructor;
import net.ufjnet.combinebackend.dtos.ArtifactDTO;
import net.ufjnet.combinebackend.models.Artifact;
import net.ufjnet.combinebackend.models.Combiner;
import net.ufjnet.combinebackend.repositories.ArtifactDAO;
import net.ufjnet.combinebackend.services.exceptions.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ArtifactService {
	
	private  ArtifactDAO dao;
	
	@Transactional(readOnly = true)
	public Page<ArtifactDTO> findAll(Pageable pageable) {
		Page<Artifact> result = dao.findAll(pageable);
		return result.map(ArtifactDTO::new);
	}

	@Transactional(readOnly = true)
	public ArtifactDTO findById(String id) {
		Artifact result = dao.findById(id)
			.orElseThrow(() -> new BusinessException("ID não encontrado!"));
		return new ArtifactDTO(result);
		
	}
	
	@Transactional(readOnly = true)
	public boolean verifyId(String id) {
		Optional<Artifact> result = dao.findById(id);
		return result.isPresent();
	}
	
	@Transactional
	public ArtifactDTO update(Artifact obj) {
		Artifact entity = dao.findById(obj.getId())
			.orElseThrow(() -> new BusinessException(("Artefato nao encontrado")));
		
		entity.setName(obj.getName());
		entity.setCategoria(obj.getCategoria().toUpperCase(Locale.ROOT));
		entity.setDescricao(obj.getDescricao());
		entity.setUpdatedAt(LocalDateTime.now());
		
		return new ArtifactDTO(dao.save(entity));
	}
	
	@Transactional
	public ArtifactDTO save(ArtifactDTO obj) {
		final String uuid = UUID.randomUUID().toString();
		LocalDateTime dateNow = LocalDateTime.now();
		
		obj.setId(uuid);
		obj.setCreatedAt(dateNow);
		obj.setUpdatedAt(dateNow);
		
		Artifact entity = new Artifact(
			obj.getId(),
			obj.getName(),
			obj.getCategoria(),
			obj.getDescricao(),
			obj.getCreatedAt(),
			obj.getUpdatedAt(),
			new Combiner(
				obj.getCombiner().getId(),
				obj.getCombiner().getName(),
				obj.getCombiner().getUsername(),
				obj.getCombiner().getEmail(),
				obj.getCombiner().getPassword(),
				obj.getCombiner().getCreatedAt(),
				obj.getCombiner().getUpdatedAt()));
		
		return new ArtifactDTO(dao.save(entity));
	}
	
	@Transactional
	public void deleteById(String id){
		dao.deleteArtifact(id);
	}
	
}
