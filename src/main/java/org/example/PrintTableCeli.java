package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrintTableCeli {

    public static void printTable(List<Zel> listZelei) {
        if (listZelei == null || listZelei.isEmpty()) {
            System.out.println("Список пуст.");
            return;
        }

        // 2. Заголовки столбцов
        List<String> headers = List.of("№ Celi", "X", "Y");

        // 3. Вычисляем ширину каждого столбца
        Map<Integer, Integer> colWidths = new HashMap<>(Map.of(
                0, headers.get(0).length(),
                1, headers.get(1).length(),
                2, headers.get(2).length()
        ));

        for (Zel zel : listZelei) {
            if (zel.getName().length() > colWidths.get(0))
                colWidths.put(0, zel.getName().length());
            if (String.format("%.0f", zel.getX()).length() > colWidths.get(1))
                colWidths.put(1, String.format("%.0f", zel.getX()).length());
            if (String.format("%.0f", zel.getY()).length() > colWidths.get(2))
                colWidths.put(2, String.format("%.0f", zel.getY()).length());
        }

        // 4. Строим разделитель (например, +-----+-----------+-------+)
        StringBuilder separator = new StringBuilder();
        separator.append("+");
        for (int i = 0; i < 3; i++) {
            separator.append("-".repeat(colWidths.get(i) + 2));
            separator.append("+");
        }

        // 5. Печать
        printRow(headers, colWidths, String.valueOf(separator));
        for (Zel zel : listZelei) {
            List<String> row = List.of(zel.getName(),
                    String.format("%.0f", zel.getX()),
                    String.format("%.0f", zel.getY()));
            printRow(row, colWidths, String.valueOf(separator));
        }
        System.out.println(separator);
    }

    private static void printRow(List<String> row, Map<Integer, Integer> colWidths, String separator) {
        System.out.println(separator);
        StringBuilder line = new StringBuilder();
        line.append("|");
        for (int i = 0; i < row.size(); i++) {
            String cell = row.get(i);
            int width = colWidths.get(i);
            // Выравнивание по левому краю
            line.append(" ").append(String.format("%-" + width + "s", cell)).append(" |");
        }
        System.out.println(line);
    }
}
