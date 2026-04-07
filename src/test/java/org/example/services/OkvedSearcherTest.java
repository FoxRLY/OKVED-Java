package org.example.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import org.example.dtos.OkvedData;
import org.junit.jupiter.api.Test;

class OkvedSearcherTest {

  @Test
  public void testFindByPhone() {
    final List<OkvedData> data = List.of(
        new OkvedData("9", "TEST_9"),
        new OkvedData("99", "TEST_99"),
        new OkvedData("9999", "TEST_9999")
    );

    final OkvedSearcher searcher = new OkvedSearcher(data);

    assertEquals(Optional.empty(), searcher.findByPhone("+70000000000"));
    assertEquals(Optional.of(data.get(0)), searcher.findByPhone("+70000000009"));
    assertEquals(Optional.of(data.get(1)), searcher.findByPhone("+70000000099"));
    assertEquals(Optional.of(data.get(1)), searcher.findByPhone("+70000000999"));
    assertEquals(Optional.of(data.get(2)), searcher.findByPhone("+70000009999"));

  }

}