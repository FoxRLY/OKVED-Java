package org.example.services;

import static org.junit.jupiter.api.Assertions.*;

import org.example.exceptions.PhoneParsingException;
import org.junit.jupiter.api.Test;

class PhoneParserTest {

  @Test
  void tryParsePhone() throws PhoneParsingException {
    assertEquals("+79999999999", PhoneParser.tryParsePhone(new String[] {"+7", "(999)", "999", "99-99"}));
    assertEquals("+79919999999", PhoneParser.tryParsePhone(new String[] {"+7 (991) 999 99-99"}));
    assertEquals("+79999999999", PhoneParser.tryParsePhone(new String[] {"89999999999"}));
    assertEquals("+79919999999", PhoneParser.tryParsePhone(new String[] {"8", "991", "999", "99-99" }));
  }
}