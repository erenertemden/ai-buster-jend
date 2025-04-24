package ertem.eren.aibuster.repositories;

import ertem.eren.aibuster.domain.entities.MediaEntity;
import ertem.eren.aibuster.domain.entities.MediaFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// repository/MediaRepository.java
@Repository
public interface MediaRepository extends JpaRepository<MediaEntity, Long> {
  void deleteByMediaFormatNotIn(List<MediaFormat> allowedFormats);
}