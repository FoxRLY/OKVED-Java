package org.example.exceptions;

/**
 * Ошибка парсинга номера телефона.
 */
public class PhoneParsingException extends Exception {

  public PhoneParsingException(String message) {
    super(message);
  }

}
