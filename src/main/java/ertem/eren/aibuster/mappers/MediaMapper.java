package ertem.eren.aibuster.mappers;

import ertem.eren.aibuster.domain.dto.MediaDto;
import ertem.eren.aibuster.domain.entities.MediaEntity;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component

public class MediaMapper {
  
  public MediaDto toDto(MediaEntity entity) {
    if (entity == null) return null;
    
    MediaDto dto = new MediaDto();
    dto.setId(entity.getId());
    dto.setMediaPath(entity.getMediaPath());
    dto.setMediaFormat(entity.getMediaFormat());
    dto.setMediaStatus(entity.getMediaStatus());
    dto.setCreatedAt(entity.getCreatedAt());
    return dto;
  }
  
  public MediaEntity toEntity(MediaDto dto) {
    if (dto == null) return null;
    
    return MediaEntity.builder()
      .id(dto.getId())
      .mediaPath(dto.getMediaPath())
      .mediaFormat(dto.getMediaFormat())
      .mediaStatus(dto.getMediaStatus())
      .createdAt(dto.getCreatedAt())
      .build();
  }
}
