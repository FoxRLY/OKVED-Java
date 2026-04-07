package org.example;

import java.io.IOException;
import org.example.exceptions.PhoneParsingException;
import org.example.services.OkvedDownloader;
import org.example.services.OkvedSearcher;
import org.example.services.PhoneParser;

public class Main {

  public static void main(String[] args) {
    try {
      final String phone = PhoneParser.tryParsePhone(args);

      final OkvedSearcher searcher = new OkvedSearcher(OkvedDownloader.getOkveds());

      searcher.findByPhone(phone).ifPresentOrElse(okved -> {
        System.out.println("Телефон: " + phone);
        System.out.println("Код ОКВЭД: " + okved.code());
        System.out.println("Название: " + okved.name());
        System.out.println("Совпадение: " + OkvedSearcher.getMatchLength(okved));
      }, () -> System.out.println("Не повезло!"));

    } catch (IOException e) {
      System.out.println("Ошибка получения списка ОКВЭД: " + e.getMessage());
    } catch (PhoneParsingException e) {
      System.out.println(e.getMessage());
    }
  }

}