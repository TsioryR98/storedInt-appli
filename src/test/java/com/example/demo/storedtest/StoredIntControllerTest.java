package com.example.demo.storedtest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.demo.endpoint.storedInt.StoredIntController;
import java.io.File;
import java.nio.file.Files;
import java.util.Random;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StoredIntControllerTest {
  StoredIntController subject = new StoredIntController();

  @BeforeEach
  void setUp() {
    // file does not exist before each test
    File file = new File("/tmp/storedIntRandom.txt");
    if (file.exists()) {
      file.delete();
    }
  }

  @AfterEach
  void setDown() {
    // file does not exist after each test
    File file = new File("/tmp/storedIntRandom.txt");
    if (file.exists()) {
      file.delete();
    }
  }

  @SneakyThrows
  @Test
  void storedIntApply_when_file_exist() {
    File file = new File("/tmp/storedIntRandom.txt");
    String number = "123";
    Files.writeString(file.toPath(), number);
    String result = subject.storedIntApply();
    assertTrue(file.exists());
    assertTrue(result.contains("123"));
  }

  // Java
  @SneakyThrows
  @Test
  void storedIntApply_when_file_not_exist() {
    File file = new File("/tmp/storedIntRandom.txt");
    if (file.exists()) {
      file.delete();
    }
    String result = subject.storedIntApply();
    Random mockRandom = mock(Random.class);
    when(mockRandom.nextInt(1000)).thenReturn(42); // force random value to 42

    assertTrue(file.exists());
    assertTrue(result.equals("random value :42"));
  }
}
