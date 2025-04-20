package ertem.eren.aibuster.repositories;


import ertem.eren.aibuster.domain.entities.Media;
import ertem.eren.aibuster.domain.entities.MediaStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MediaRepository extends JpaRepository<Media, UUID> {
  
  List<Media> findAllById(UUID mediaId);
  
}
