package ertem.eren.aibuster.services.impl;

import ertem.eren.aibuster.repositories.MediaRepository;
import ertem.eren.aibuster.services.MediaServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MediaServiceImplTest {
  
  @Mock
  private MediaRepository mediaRepository;
  
  @InjectMocks
  private MediaServiceImpl underTest;
  
  public void testThatMediaIsSaved() {
  
  }
}
