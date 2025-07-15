package com.example.demo.endpoint.storedInt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class StoredIntController {

  public static final ResponseEntity<String> OK = new ResponseEntity<>("OK", HttpStatus.OK);
  public static final ResponseEntity<String> KO =
      new ResponseEntity<>("KO", HttpStatus.INTERNAL_SERVER_ERROR);

  @GetMapping("/stored-int")
  public String storedIntApply() {
    File file = new File("/tmp/storedIntRandom.txt");
    Random random = new Random();
    if (file.exists()) {
      try {
        return "stored value :" + Files.readString(file.toPath());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    int randomInt = random.nextInt(1000); /* 0 to 999*/
    String valueRandom = String.valueOf(randomInt);
    try {
      Files.writeString(file.toPath(), valueRandom);
      return "random value :" + valueRandom;

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
