package net.ufjnet.combinebackend.controllers;

import net.ufjnet.combinebackend.dtos.CombinerDTO;
import net.ufjnet.combinebackend.models.Combiner;
import net.ufjnet.combinebackend.services.CombinerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/combine/user")
public class CombinerController {
	
	@Autowired
	private CombinerService service;
	
	@GetMapping
	public ResponseEntity<Page<CombinerDTO>> buscarTodos(Pageable pageable) {
		Page<CombinerDTO> result = service.findAll(pageable);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/create")
	public ResponseEntity<CombinerDTO> create(@RequestBody Combiner obj) {
		CombinerDTO objDTO = service.save(obj);
		return ResponseEntity.created(null).body(objDTO);
	}

	@PostMapping("/searchById/{id}")
	public ResponseEntity<CombinerDTO> searchById(@PathVariable String id) {
		return service.findById(id)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/searchByUsername/{username}")
	public ResponseEntity<CombinerDTO> searchByUsername(@PathVariable String username) {
		return service.findByUsername(username)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<CombinerDTO> update(@PathVariable String id, @RequestBody Combiner obj) {
		if (!service.verifyId(id)) {
			return ResponseEntity.notFound().build();
		}
		obj.setId(id);
		CombinerDTO objDTO = service.update(obj);
		return ResponseEntity.ok(objDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		if (!service.verifyId(id)) {
			return ResponseEntity.notFound().build();
		}
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
