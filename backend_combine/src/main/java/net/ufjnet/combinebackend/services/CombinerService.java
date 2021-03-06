package net.ufjnet.combinebackend.services;

import lombok.AllArgsConstructor;
import net.ufjnet.combinebackend.dtos.CombinerDTO;
import net.ufjnet.combinebackend.models.Combiner;
import net.ufjnet.combinebackend.repositories.CombinerDAO;
import net.ufjnet.combinebackend.services.exceptions.BusinessException;
import org.apache.tomcat.jni.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CombinerService {
	
	private final CombinerDAO dao;

	@Transactional(readOnly = true)
	public Page<CombinerDTO> findAll(Pageable pageable) {
		Page<Combiner> result = dao.findAll(pageable);
		return result.map(CombinerDTO::new);
	}

	@Transactional(readOnly = true)
	public CombinerDTO findById(String id) {
		Combiner result = dao.findById(id)
			.orElseThrow(() -> new BusinessException("ID não encontrado!"));
		return new CombinerDTO(result);
		
	}
	
	@Transactional(readOnly = true)
	public boolean verifyId(String id) {
		Optional<Combiner> result = dao.findById(id);
		return result.isPresent();
	}

	@Transactional(readOnly = true)
	public CombinerDTO findByUsername(String username) {
		Combiner result = dao.findByUsername(username)
		.orElseThrow(() -> new BusinessException("Nome não encontrado!"));
		return new CombinerDTO(result);
	}

	@Transactional(readOnly = true)
	public CombinerDTO findByEmail(String email) {
		Combiner result = dao.findByEmail(email)
			.orElseThrow(() -> new BusinessException("Email não encontrado!"));
		
		return new CombinerDTO(result);
	}
	
	@Transactional
	public CombinerDTO update(String id, Combiner obj) {
		Combiner entity = dao.findById(id)
			.orElseThrow(() -> new BusinessException(("Registro nao encontrado")));
		
		entity.setName(obj.getName());
		entity.setUsername(obj.getUsername());
		entity.setPassword(obj.getPassword());
		entity.setEmail(obj.getEmail());
		entity.setUpdatedAt(LocalDateTime.now());
		
		return new CombinerDTO(dao.save(entity));
	}
	
	@Transactional
	public CombinerDTO save(CombinerDTO obj) {
		final String uuid = UUID.randomUUID().toString();

		Combiner entity = new Combiner(uuid, obj.getName(), obj.getUsername(), obj.getPassword(), obj.getEmail(), obj.getCreatedAt(), obj.getCreatedAt());
		
		boolean usernameExists = dao.findByUsername(entity.getUsername())
			.stream()
			.anyMatch(objResult -> !objResult.equals(entity));

		if (usernameExists) {
			throw new BusinessException("Username já está em uso. Tente novamente!");
		}

		boolean emailExists = dao.findByEmail(entity.getEmail())
			.stream()
			.anyMatch(objResult -> !objResult.equals(entity));

		if (emailExists) {
			throw new BusinessException("E-mail já está em uso. Tente novamente!");
		}
		
		obj.setCreatedAt(LocalDateTime.now());
		obj.setUpdatedAt(LocalDateTime.now());
		
		return new CombinerDTO(dao.save(entity));
	}
	
	@Transactional
	public void deleteById(String id){
		dao.deleteCombiner(id);
	}
	
}
