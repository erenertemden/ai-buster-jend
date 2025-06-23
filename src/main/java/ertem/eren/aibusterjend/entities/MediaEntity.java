package ertem.eren.aibusterjend.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MediaEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private String path;
  private double rate;
  private boolean flag;
  private LocalDateTime createdAt;
  
  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
  }
  
 
  public Long getId() {
    return id;
  }
  
  public String getPath() {
    return path;
  }
  
  public void setPath(String path) {
    this.path = path;
  }
  
  public double getRate() {
    return rate;
  }
  
  public void setRate(double rate) {
    this.rate = rate;
  }
  
  public boolean isFlag() {
    return flag;
  }
  
  public void setFlag(boolean flag) {
    this.flag = flag;
  }
  
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }
}
