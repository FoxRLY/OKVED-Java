package org.example.tree;

import java.util.Optional;
import org.example.dtos.OkvedData;

/**
 * Древо поиска ОКВЭД.
 */
public class OkvedSearchTree {

  private final OkvedSearchTreeNode startNode = new OkvedSearchTreeNode();

  /**
   * Добавить данные в древо.
   *
   * @param okved ОКВЭД
   */
  public void add(OkvedData okved) {
    final String path = okved.code().replace(".", "");
    if (isNumeric(path)) {
      startNode.add(okved, path);
    }
  }

  /**
   * Найти данные в древе по указанному пути.
   *
   * @param path адрес ноды для поиска
   * @return ОКВЭД, если нашли
   */
  public Optional<OkvedData> find(String path) {
    return startNode.find(path, null);
  }

  /**
   * Проверка строки на парсинг в число.
   *
   * @param str строка
   * @return флаг проверки
   */
  private static boolean isNumeric(String str) {
    if (str == null || str.isEmpty()) {
      return false;
    }
    try {
      Integer.parseInt(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

}
