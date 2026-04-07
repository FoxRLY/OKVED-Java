package org.example.tree;

import java.util.Arrays;
import java.util.Optional;
import org.example.dtos.OkvedData;

/**
 * Нода древа поиска.
 */
public class OkvedSearchTreeNode {

  private final OkvedSearchTreeNode[] links = new OkvedSearchTreeNode[10];
  private OkvedData data = null;

  public OkvedSearchTreeNode() {
    Arrays.fill(links, null);
  }

  /**
   * Добавить данные в древо.
   *
   * @param okved ОКВЭД
   * @param path адрес ноды для вставки
   */
  public void add(OkvedData okved, String path) {

    if (path.isEmpty()) {
      data = okved;
      return;
    }

    final int linkIndex = Character.getNumericValue(path.charAt(path.length() - 1));
    final String pathRemainder = path.substring(0, path.length() - 1);

    if (links[linkIndex] == null) {
      links[linkIndex] = new OkvedSearchTreeNode();
    }

    links[linkIndex].add(okved, pathRemainder);
  }

  /**
   * Найти данные в древе.
   *
   * @param path адрес ноды для поиска
   * @param lastFound последние найденные данные
   * @return ОКВЭД, если нашли
   */
  public Optional<OkvedData> find(String path, OkvedData lastFound) {
    if (data != null) {
      lastFound = data;
    }

    if (path.isEmpty()) {
      return Optional.ofNullable(lastFound);
    }

    final int linkIndex = Character.getNumericValue(path.charAt(path.length() - 1));
    final String pathRemainder = path.substring(0, path.length() - 1);

    if (links[linkIndex] == null) {
      return Optional.ofNullable(lastFound);
    } else {
      return links[linkIndex].find(pathRemainder, lastFound);
    }

  }

}
