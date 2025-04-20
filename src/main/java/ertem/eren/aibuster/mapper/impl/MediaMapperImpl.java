package ertem.eren.aibuster.mapper.impl;

import ertem.eren.aibuster.domain.dto.MediaDto;
import ertem.eren.aibuster.domain.entities.Media;
import ertem.eren.aibuster.mapper.MediaMapper;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

@Component
public class MediaMapperImpl implements MediaMapper {
  
  @Override
  public Media fromDto(MediaDto mediaDto) {
    return new Media(
      mediaDto.id(),
      mediaDto.type(),
      mediaDto.status(),
      mediaDto.createdAt()
    );
  }
  
  @Override
  public MediaDto toDto(Media media) {
    return new MediaDto(
      media.getId(),
      media.getType(),
      media.getStatus(),
      media.getCreatedAt()
    );
  }
}
