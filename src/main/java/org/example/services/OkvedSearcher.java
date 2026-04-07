package org.example.services;

import java.util.List;
import java.util.Optional;
import org.example.dtos.OkvedData;
import org.example.tree.OkvedSearchTree;

/**
 * Сервис поиска ОКВЭД.
 */
public class OkvedSearcher {

  private final OkvedSearchTree searchTree = new OkvedSearchTree();

  public OkvedSearcher(List<OkvedData> okveds) {
    loadData(okveds);
  }

  /**
   * Найти ОКВЭД по номеру телефона.
   *
   * @param phone нормер телефона
   * @return ОКВЭД, если нашли
   */
  public Optional<OkvedData> findByPhone(String phone) {
    return searchTree.find(phone.substring(1));
  }

  /**
   * Получить длину совпадения по ОКВЭД.
   *
   * @param okved ОКВЭД
   * @return длина совпадения
   */
  public static int getMatchLength(OkvedData okved) {
    return okved.code().replace(".", "").length();
  }

  /**
   * Загрузить данные для поиска в древо.
   *
   * @param data данные
   */
  private void loadData(List<OkvedData> data) {
    for (OkvedData okved : data) {
      searchTree.add(okved);
    }
  }

}
