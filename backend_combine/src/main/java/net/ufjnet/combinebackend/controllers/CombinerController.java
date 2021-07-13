package net.ufjnet.combinebackend.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.ufjnet.combinebackend.dtos.CombinerDTO;
import net.ufjnet.combinebackend.models.Combiner;
import net.ufjnet.combinebackend.services.CombinerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/combine/user")
@Tag(name = "Endpoint de Combiner(Users)")
public class CombinerController {
	
	@Autowired
	private CombinerService service;

	@GetMapping
	@Operation(summary = "Busca todos os usuarios")
	public ResponseEntity<CollectionModel<CombinerDTO>> buscarTodos(
		@RequestParam(value="page", defaultValue = "0") int page,
		@RequestParam(value="limit", defaultValue = "12") int limit,
		@RequestParam(value="direction", defaultValue = "asc") String direction) {

		Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "name"));

		Page<CombinerDTO> pages = service.findAll(pageable);
		pages
			.stream()
			.forEach(p -> p.add(
				linkTo(methodOn(CombinerController.class).searchById(p.getId())).withSelfRel()
				)
			);

		return ResponseEntity.ok(CollectionModel.of(pages));
	}

	@GetMapping("/searchById/{id}")
	@Operation(summary = "Busca usuario por ID")
	public ResponseEntity<CombinerDTO> searchById(@PathVariable String id) {
		CombinerDTO objDTO = service.findById(id);
		objDTO.add(linkTo(methodOn(CombinerController.class).searchById(id)).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}

	@GetMapping("/searchByUsername/{username}")
	@Operation(summary = "Busca usuario por USERNAME")
	public ResponseEntity<CombinerDTO> searchByUsername(@PathVariable String username) {
		CombinerDTO objDTO = service.findByUsername(username);
		objDTO.add(linkTo(methodOn(CombinerController.class).searchByUsername(username)).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}

	@GetMapping("/searchByEmail/{email}")
	@Operation(summary = "Busca usuario por EMAIL")
	public ResponseEntity<CombinerDTO> searchByEmail(@PathVariable String email) {
		CombinerDTO objDTO = service.findByEmail(email);
		objDTO.add(linkTo(methodOn(CombinerController.class).searchByEmail(email)).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Insere um usuario")
	public ResponseEntity<CombinerDTO> insert(@RequestBody CombinerDTO objBody) {
		CombinerDTO objDTO = service.save(objBody);
		objDTO.add(linkTo(methodOn(CombinerController.class).service.findById(objDTO.getId())).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}

	@PutMapping("/edit/{id}")
	@Operation(summary = "Altera um usuario por ID")
	public ResponseEntity<CombinerDTO> update(@PathVariable String id, @RequestBody Combiner obj) {
		CombinerDTO objDTO = service.update(obj);
		objDTO.add(linkTo(methodOn(CombinerController.class).searchById(id)).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um usuario por ID")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		if (!service.verifyId(id)) {
			return ResponseEntity.notFound().build();
		}
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
