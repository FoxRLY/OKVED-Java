package org.example.dtos;

import java.util.List;

/**
 * ОКВЭД в древовидном представлении.
 *
 * @param code код
 * @param name название
 * @param items список дочерних ОКВЭД
 */
public record OkvedDataTree(
    String code, String name, List<OkvedDataTree> items
) {
}
