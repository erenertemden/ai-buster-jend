package ertem.eren.aibuster.domain.entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table( name = "medias")

public class Media {
  @Id
  @GeneratedValue( strategy = GenerationType.UUID)
  @Column( name = "id", nullable = false, updatable = false)
  private UUID id;
  
  @Column( name = "type", nullable = false, updatable = false)
  private String type;
  
  @Column( name = "description")
  private String description;
  
  @Column( name = "status", nullable = false)
  private MediaStatus status;
  
  @Column( name = "created", nullable = false, updatable = false)
  private LocalDateTime createdAt; //jpa ve json serialization ile uyumlu
  
  @Column( name = "updated", nullable = false, updatable = false)
  private LocalDateTime updatedAt;
  
  
  //no arg constructor
  public Media() {
  }
  
  //all arg constructor
  public Media(UUID id, String type, MediaStatus status, LocalDateTime createdAt, LocalDateTime updatedAt, String description) {
    this.updatedAt = updatedAt;
    this.createdAt = createdAt;
    this.status = status;
    this.description = description;
    this.type = type;
    this.id = id;
  }
  
  public UUID getId() {
    return id;
  }
  
  public void setId(UUID id) {
    this.id = id;
  }
  
  public String getType() {
    return type;
  }
  
  public void setType(String type) {
    this.type = type;
  }
  
  public String getDescription() {
    return description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public MediaStatus getStatus() {
    return status;
  }
  
  public void setStatus(MediaStatus status) {
    this.status = status;
  }
  
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }
  
  public void setCreatedAt(LocalDateTime createdAt) {
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
    if (o == null || getClass() != o.getClass()) return false;
    Media media = (Media) o;
    return Objects.equals(id, media.id) && Objects.equals(type, media.type) && Objects.equals(description, media.description) && status == media.status && Objects.equals(createdAt, media.createdAt) && Objects.equals(updatedAt, media.updatedAt);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(id, type, description, status, createdAt, updatedAt);
  }
  
  @Override
  public String toString() {
    return "Media{" +
      "id=" + id +
      ", type='" + type + '\'' +
      ", description='" + description + '\'' +
      ", status=" + status +
      ", createdAt=" + createdAt +
      ", updatedAt=" + updatedAt +
      '}';
  }
}
