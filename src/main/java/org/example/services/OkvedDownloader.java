package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Stream;
import org.example.dtos.OkvedData;
import org.example.dtos.OkvedDataTree;

/**
 * Сервис для скачивания ОКВЭД.
 */
public class OkvedDownloader {

  private static final String FILE_URL =
      "https://raw.githubusercontent.com/bergstar/testcase/refs/heads/master/okved.json";
  private static final ObjectMapper MAPPER = new ObjectMapper();

  /**
   * Получить список ОКВЭД
   *
   * @return список ОКВЭД
   * @throws IOException ошибка получениия
   */
  public static List<OkvedData> getOkveds() throws IOException {
    final List<OkvedDataTree> data = MAPPER.readValue(URI.create(FILE_URL).toURL(),
        new TypeReference<>() {
        });

    return okvedTreeToData(data);
  }

  private static List<OkvedData> okvedTreeToData(List<OkvedDataTree> tree) {
    return flatten(tree.stream()).toList();
  }

  private static Stream<OkvedData> flatten(Stream<OkvedDataTree> tree) {
    return tree
        .flatMap(OkvedDownloader::toData);
  }

  private static Stream<OkvedData> toData(OkvedDataTree tree) {
    Stream<OkvedData> result = Stream.of(new OkvedData(tree.code(), tree.name()));

    if (tree.items() != null) {
      result = Stream.concat(result, flatten(tree.items().stream()));
    }

    return result;
  }

}
