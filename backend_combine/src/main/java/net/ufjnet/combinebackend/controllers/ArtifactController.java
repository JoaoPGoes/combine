package net.ufjnet.combinebackend.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.ufjnet.combinebackend.dtos.ArtifactDTO;
import net.ufjnet.combinebackend.models.Artifact;
import net.ufjnet.combinebackend.services.ArtifactService;
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
@RequestMapping("/v1/combine/artifact")
@Tag(name = "Endpoint de Artifact(Coleções)")
public class ArtifactController {
	
	@Autowired
	private ArtifactService service;

	@GetMapping
	@Operation(summary = "Busca todos os artifacts")
	public ResponseEntity<CollectionModel<ArtifactDTO>> buscarTodos(
		@RequestParam(value="page", defaultValue = "0") int page,
		@RequestParam(value="limit", defaultValue = "12") int limit,
		@RequestParam(value="direction", defaultValue = "asc") String direction) {

		Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "name"));

		Page<ArtifactDTO> pages = service.findAll(pageable);
		pages
			.stream()
			.forEach(p -> p.add(
				linkTo(methodOn(ArtifactController.class).searchById(p.getId())).withSelfRel()
				)
			);

		return ResponseEntity.ok(CollectionModel.of(pages));
	}

	@GetMapping("/searchById/{id}")
	@Operation(summary = "Busca artifact por ID")
	public ResponseEntity<ArtifactDTO> searchById(@PathVariable String id) {
		ArtifactDTO objDTO = service.findById(id);
		objDTO.add(linkTo(methodOn(ArtifactController.class).searchById(id)).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Insere um artifact")
	public ResponseEntity<ArtifactDTO> insert(@RequestBody ArtifactDTO objBody) {
		ArtifactDTO objDTO = service.save(objBody);
		objDTO.add(linkTo(methodOn(ArtifactController.class).service.findById(objDTO.getId())).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}

	@PutMapping("/edit/{id}")
	@Operation(summary = "Altera um artifact por ID")
	public ResponseEntity<ArtifactDTO> update(@PathVariable String id, @RequestBody Artifact obj) {
		ArtifactDTO objDTO = service.update(obj);
		objDTO.add(linkTo(methodOn(ArtifactController.class).searchById(id)).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um artifact por ID")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		if (!service.verifyId(id)) {
			return ResponseEntity.notFound().build();
		}
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
