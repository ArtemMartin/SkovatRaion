package org.example;

import java.util.ArrayList;
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
        int a;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Создать список целей:");
        do {
            System.out.print("Ввести номер цели -> ");
            name = scanner.next();
            if (!name.isEmpty()) list.add(name);
            System.out.print("Продолжить - 1, НЕТ - 0:");
            a = scanner.nextInt();
            System.out.println();
        } while (a != 0);
        System.out.println("Список создан...");
        System.out.println("Размер - " + list.size());
    }
}
