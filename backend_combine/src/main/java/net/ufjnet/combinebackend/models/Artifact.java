package net.ufjnet.combinebackend.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ARTIFACTS")
public class Artifact implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id_artifact")
    @GenericGenerator(name = "uuid", strategy = "uuid4")
    private String id;

    @NotBlank
		@Column(name = "name_artifact", nullable = false)
		private String name;

		@NotBlank
    @Column(name = "categoria_artifact", nullable = false)
    private String categoria;

		@NotBlank
    @Size(max=120)
		@Column(name = "descricao_artifact", nullable = false)
		private String descricao;
		
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne()
		@JoinColumn(name="combiner_id")
		private Combiner combiner;
}


