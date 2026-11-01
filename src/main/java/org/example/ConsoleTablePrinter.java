package org.example;

import java.time.LocalTime;
import java.util.*;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;


public class ConsoleTablePrinter {
    public static void printTable(Map<LocalTime, ZelNameRash> dataMap, List<String> categories) {
        if (dataMap.isEmpty()) {
            System.out.println("Нет данных для отображения.");
            return;
        }

        // 1. Расчет сумм по категориям (ключ категории -> сумма значений)
        Map<String, Integer> sums = dataMap.values().stream()
                .collect(Collectors.groupingBy(
                        new Function<ZelNameRash, String>() {
                            @Override
                            public String apply(ZelNameRash zelNameRash) {
                                return zelNameRash.getZel();
                            }
                        },
                        Collectors.summingInt(new ToIntFunction<ZelNameRash>() {
                            @Override
                            public int applyAsInt(ZelNameRash zelNameRash) {
                                return zelNameRash.getRashod();
                            }
                        })
                ));

        // 2. Формируем заголовок
        String timeHeader = "Время/Цель";
        List<String> headers = new ArrayList<>();
        headers.add(timeHeader);
        headers.addAll(categories);

        // 3. Определяем ширину каждого столбца
        Map<Integer, Integer> colWidths = new HashMap<>();
        for (int i = 0; i < headers.size(); i++) {
            colWidths.put(i, headers.get(i).length());
        }

        // Собираем строки данных
        List<List<String>> rows = new ArrayList<>();
        for (Map.Entry<LocalTime, ZelNameRash> entry : dataMap.entrySet()) {
            LocalTime time = entry.getKey();
            ZelNameRash obj = entry.getValue();

            List<String> row = new ArrayList<>();
            row.add(time.toString());

            for (String cat : categories) {
                if (cat.equals(obj.getZel())) {
                    row.add(obj.getName() + "(" + obj.getRashod() + ")");
                } else {
                    row.add("-");
                }
            }
            rows.add(row);

            // Обновляем ширину столбцов
            for (int i = 0; i < row.size(); i++) {
                int currentLen = colWidths.getOrDefault(i, 0);
                if (row.get(i).length() > currentLen) {
                    colWidths.put(i, row.get(i).length());
                }
            }
        }

        // Проверяем ширину столбцов для строки сумм
        for (int i = 0; i < categories.size(); i++) {
            String cat = categories.get(i);
            int sum = sums.getOrDefault(cat, 0);
            int colIndex = i + 1;
            int currentLen = colWidths.getOrDefault(colIndex, 0);
            if (String.valueOf(sum).length() > currentLen) {
                colWidths.put(colIndex, String.valueOf(sum).length());
            }
        }

        // 4. Строим разделитель
        StringBuilder separator = new StringBuilder();
        separator.append("+");
        for (int i = 0; i < headers.size(); i++) {
            int width = colWidths.get(i);
            separator.append("-".repeat(width + 2));
            separator.append("+");
        }

        // 5. Печать таблицы
        printRow(headers, colWidths, String.valueOf(separator));
        for (List<String> row : rows) {
            printRow(row, colWidths, String.valueOf(separator));
        }

        // 6. Печать строки сумм
        System.out.println(separator);
        List<String> sumRow = new ArrayList<>();
        sumRow.add("РАСХОД:");
        for (String cat : categories) {
            int sum = sums.getOrDefault(cat, 0);
            sumRow.add(String.valueOf(sum));
        }
        printRow(sumRow, colWidths, String.valueOf(separator));
        System.out.println(separator);
    }

    private static void printRow(List<String> row, Map<Integer, Integer> colWidths, String separator) {
        System.out.println(separator);
        StringBuilder line = new StringBuilder();
        line.append("|");
        for (int i = 0; i < row.size(); i++) {
            String cell = row.get(i);
            int width = colWidths.get(i);
            line.append(" ").append(String.format("%-" + width + "s", cell)).append(" |");
        }
        System.out.println(line);
    }
}
