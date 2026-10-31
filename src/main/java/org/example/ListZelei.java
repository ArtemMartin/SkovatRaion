package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ListZelei {
    List<Zel> list = new ArrayList<>();

    public List<Zel> getList() {
        return list;
    }

    //создаем список целей
    public void setList() {
        String name;
        String[] names;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Создать список целей:");
        int vvod = 1;
        do {
            list.add(new Zel());
            System.out.print("Продолжить - 1, нет - 0: ");
            vvod = scanner.nextInt();
            scanner.nextLine();
        } while (vvod == 1);
        System.out.println("Список создан...");
        System.out.println("Размер - " + list.size());
    }

    //посмотреть список целей
    public void watchList() {
        System.out.println("<< Список целей >>");
        for (Zel zel : list) {
            System.out.println(zel);
        }
    }
}
