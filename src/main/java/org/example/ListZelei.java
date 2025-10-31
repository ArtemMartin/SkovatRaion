package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ListZelei {
    List<String> list = new ArrayList<>();

    public List<String> getList() {
        return list;
    }


    //создаем список целей
    public void setList() {
        String name;
        String[] names;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Создать список целей:");
        System.out.print("Ввести номера целей(можно через пробел или другой разделитель): ");
        name = scanner.nextLine();
        if (!name.isEmpty()) {
            names = name.split("\\W+");
            Collections.addAll(list, names);
        }
        System.out.println("Список создан...");
        System.out.println("Размер - " + list.size());
    }
}
