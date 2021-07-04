package net.ufjnet.combinebackend.models;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "COMBINER")
public class Combiner implements Serializable {

    private static final long serialVersionUID = 1L;

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

    public Combiner() {

    }

    public Combiner(String id, String name, String password, String email, String username, Date createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.username = username;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Combiner combiner = (Combiner) o;
        return Objects.equals(id, combiner.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


