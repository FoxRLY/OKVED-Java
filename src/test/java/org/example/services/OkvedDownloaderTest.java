package org.example.services;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import org.example.dtos.OkvedData;
import org.junit.jupiter.api.Test;

class OkvedDownloaderTest {

  @Test
  public void testDownloading() throws IOException {
    final List<OkvedData> data = OkvedDownloader.getOkveds();

    assertFalse(data.isEmpty());
  }

}