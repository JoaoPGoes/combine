package net.ufjnet.combinebackend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import net.ufjnet.combinebackend.models.Combiner;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@JsonPropertyOrder({"id", "name_combiner", "username_combiner", "created_at", "updated_at"})
@JsonIgnoreProperties({"password_combiner", "email_combiner"})
public class CombinerDTO extends RepresentationModel<CombinerDTO> implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@JsonProperty("id")
	private String id;

	@NotBlank
	@Size(max=60)
	@JsonProperty("name_combiner")
	private String name;

	@NotBlank
	@Size(max=16)
	@JsonProperty("password_combiner")
	private String password;

	@Email
	@JsonProperty("email_combiner")
	private String email;

	@NotBlank
	@Size(max=28)
	@JsonProperty("username_combiner")
	private String username;

	@JsonProperty("created_at")
	private LocalDateTime createdAt;

	@JsonProperty("updated_at")
	private LocalDateTime updatedAt;

	public CombinerDTO(Combiner obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.password = obj.getPassword();
		this.email = obj.getEmail();
		this.username = obj.getUsername();
		this.createdAt = obj.getCreatedAt();
		this.updatedAt = obj.getUpdatedAt();
	}
}
