package net.ufjnet.combinebackend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import net.ufjnet.combinebackend.models.Artifact;
import net.ufjnet.combinebackend.models.Combiner;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@JsonPropertyOrder({"id", "name_artifact", "categoria_artifact", "descricao_artifact", "created_at", "updated_at"})
public class ArtifactDTO extends RepresentationModel<ArtifactDTO> implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@JsonProperty("id")
	private String id;

	@JsonProperty("name_artifact")
	private String name;
	
	@JsonProperty("categoria_artifact")@CreatedDate
	private String categoria;

	@JsonProperty("descricao_artifact")
	private String descricao;
	
	@JsonProperty("created_at")
	private LocalDateTime createdAt;
	
	@JsonProperty("updated_at")
	private LocalDateTime updatedAt;
	
	private CombinerDTO combiner;

	public ArtifactDTO (Artifact obj) {
		id = obj.getId();
		name = obj.getName();
		categoria = obj.getCategoria();
		descricao = obj.getDescricao();
		createdAt = obj.getCreatedAt();
		updatedAt = obj.getUpdatedAt();
		combiner = new CombinerDTO(obj.getCombiner());
	}
}
