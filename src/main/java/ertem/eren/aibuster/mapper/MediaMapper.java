package ertem.eren.aibuster.mapper;

import ertem.eren.aibuster.domain.dto.MediaDto;
import ertem.eren.aibuster.domain.entities.Media;

public interface MediaMapper {
  
  Media fromDto(MediaDto mediaDto);
  
  MediaDto toDto(Media media);
}
