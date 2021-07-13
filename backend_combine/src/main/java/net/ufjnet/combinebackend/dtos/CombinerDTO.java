package net.ufjnet.combinebackend.dtos;

import net.ufjnet.combinebackend.models.Combiner;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class CombinerDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include

	private String id;

	private String name;
	
	private String password;

	private String email;
	
	private String username;

	private LocalDateTime createdAt;
	
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
