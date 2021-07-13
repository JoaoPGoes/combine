package net.ufjnet.combinebackend.models;

import org.hibernate.annotations.GenericGenerator;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "COMBINERS")
public class Combiner implements Serializable {

    private static final long serialVersionUID = 1L;

		@EqualsAndHashCode.Include
    @Id
    @Column(name = "id_combiner")
    @GenericGenerator(name = "uuid", strategy = "uuid4")
    private String id;
		
    @Column(name = "name_combiner", nullable = false)
    private String name;
		
    @Column(name = "password_combiner", nullable = false)
    private String password;
    
    @Column(name = "email_combiner")
    private String email;
    
    @Column(name = "username_combiner", nullable = false)
    private String username;

		@CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

		@LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "combiner")
    private List<Artifact> artifacts = new ArrayList<>();

	public Combiner(String id, String name, String password, String email, String username, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.username = username;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}


