package net.ufjnet.combinebackend.models;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COMBINER")
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

    @Column(name = "email_combiner", nullable = false)
    private String email;

    @Column(name = "username_combiner", nullable = false)
    private String username;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
  
}


