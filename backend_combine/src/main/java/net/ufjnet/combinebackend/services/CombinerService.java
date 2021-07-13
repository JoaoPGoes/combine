package net.ufjnet.combinebackend.services;

import lombok.AllArgsConstructor;
import net.ufjnet.combinebackend.dtos.CombinerDTO;
import net.ufjnet.combinebackend.models.Combiner;
import net.ufjnet.combinebackend.repositories.CombinerDAO;
import net.ufjnet.combinebackend.services.exceptions.BusinessException;
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
		return result
			.map(CombinerDTO::new);
	}

	@Transactional(readOnly = true)
	public Optional<CombinerDTO> findById(String id) {
		Optional<Combiner> result = dao.findById(id);
		return result
			.map(CombinerDTO::new);
	}
	
	@Transactional(readOnly = true)
	public boolean verifyId(String id) {
		Optional<Combiner> result = dao.findById(id);
		return result.isPresent();
	}

	@Transactional(readOnly = true)
	public Optional<CombinerDTO> findByUsername(String username) {
		Optional<Combiner> result = dao.findByUsername(username);
		return result
			.map(CombinerDTO::new);
	}

	@Transactional(readOnly = true)
	public Optional<CombinerDTO> findByEmail(String email) {
		Optional<Combiner> result = dao.findByEmail(email);
		return result
			.map(CombinerDTO::new);
	}
	
	@Transactional
	public CombinerDTO save(Combiner obj) {
		final String uuid = UUID.randomUUID().toString();

		boolean usernameExists = dao.findByUsername(obj.getUsername())
			.stream()
			.anyMatch(objResult -> !objResult.equals(obj));

		if (usernameExists) {
			throw new BusinessException("Username já está em uso!");
		}
		
		boolean emailExists = dao.findByEmail(obj.getEmail())
			.stream()
			.anyMatch(objResult -> !objResult.equals(obj));
		
		if (emailExists) {
			throw new BusinessException("Email já está em uso!");
		}
		
		obj.setId(uuid);
		obj.setCreatedAt(LocalDateTime.now());
		obj.setUpdatedAt(LocalDateTime.now());
		
		return new CombinerDTO(dao.save(obj));
	}

	@Transactional
	public CombinerDTO update(Combiner obj) {
		boolean usernameExists = dao.findByUsername(obj.getUsername())
			.stream()
			.anyMatch(objResult -> !objResult.equals(obj));

		if (usernameExists) {
			throw new BusinessException("Username já está em uso!");
		}

		boolean emailExists = dao.findByEmail(obj.getEmail())
			.stream()
			.anyMatch(objResult -> !objResult.equals(obj));

		if (emailExists) {
			throw new BusinessException("Email já está em uso!");
		}
		
		obj.setUpdatedAt(LocalDateTime.now());

		return new CombinerDTO(dao.save(obj));
	}
	
	@Transactional
	public void deleteById(String id){
		dao.deleteCombiner(id);
	}
	
}
