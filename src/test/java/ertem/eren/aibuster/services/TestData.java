package ertem.eren.aibuster.services;

import ertem.eren.aibuster.domain.Media;
import ertem.eren.aibuster.domain.MediaEntity;
import ertem.eren.aibuster.domain.MediaStatus;
import ertem.eren.aibuster.domain.MediaType;

import java.time.LocalDateTime;
import java.util.UUID;

public final class TestData {
  
  UUID id = UUID.randomUUID();
  LocalDateTime createdAt = LocalDateTime.now();
  
  private TestData() {}
  
  private static final UUID FIXED_ID = UUID.fromString("00000000-0000-0000-0000-000000000001");
  private static final LocalDateTime FIXED_DATE = LocalDateTime.of(2024, 1, 1, 0, 0);
  
  public static Media testMedia() {
    return Media.builder()
      .id(FIXED_ID)
      .mediaType(MediaType.PHOTO)
      .mediaPath("/test/t")
      .mediaStatus(MediaStatus.ORIGINAL)
      .createdAt(FIXED_DATE)
      .build();
  }
  
  public static MediaEntity testMediaEntity() {
    return MediaEntity.builder()
      .id(FIXED_ID)
      .mediaType(MediaType.PHOTO)
      .mediaPath("/test/t")
      .mediaStatus(MediaStatus.ORIGINAL)
      .createdAt(FIXED_DATE)
      .build();
  }
  
}
