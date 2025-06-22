package ertem.eren.aibusterjend.controllers;

import ertem.eren.aibusterjend.dtos.MediaRequest;
import org.springframework.web.bind.annotation.*;
import ertem.eren.aibusterjend.services.MediaService;

@RestController
@RequestMapping("/media")
public class MediaController {
  
  private final MediaService service;
  
  public MediaController(MediaService service) {
    this.service = service;
  }
  
  @PostMapping
  public void postData(@RequestBody MediaRequest request) {
    service.saveData(request);
    //no response
  }
  
}

