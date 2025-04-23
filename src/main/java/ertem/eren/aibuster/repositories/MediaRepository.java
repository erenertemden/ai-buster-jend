package ertem.eren.aibuster.repositories;

import ertem.eren.aibuster.domain.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface MediaRepository extends JpaRepository<MediaEntity, UUID> {
}
