package org.example.services;

import org.example.exceptions.PhoneParsingException;

/**
 * Сервис парсинга телефонного номера из аргументов.
 */
public class PhoneParser {

  /**
   * Попытаться спарсить телефон.
   *
   * @param args аргументы командной строки
   * @return номер телефона
   * @throws PhoneParsingException ошибка парсинга
   */
  public static String tryParsePhone(String[] args) throws PhoneParsingException {
    final String parsedPhone = parsePhone(String.join("", args));

    validatePhone(parsedPhone);

    if (parsedPhone.charAt(0) == '8') {
      return "+7" + parsedPhone.substring(1);
    } else {
      return "+" + parsedPhone;
    }

  }

  /**
   * Убрать лишние символы из номера телефона.
   *
   * @param supposedPhone неотформатированный номер
   * @return отформатированный номер
   */
  private static String parsePhone(String supposedPhone) {
    return supposedPhone.replace("-", "")
        .replace("+7", "7")
        .replace("(", "")
        .replace(")", "")
        .replaceAll("\\s+", "");
  }

  /**
   * Провести валидацию номера.
   *
   * @param parsedPhone полученный номер
   * @throws PhoneParsingException ошибка парсинга
   */
  private static void validatePhone(String parsedPhone) throws PhoneParsingException {
    if (!parsedPhone.matches("[78]\\d{10}")) {
      throw new PhoneParsingException(
          "Телефон " + parsedPhone + " не подходит под паттерн [78]XXXXXXXXXX");
    }
  }

}
