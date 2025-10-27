package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RashNaZel {
    List<Integer> list = new ArrayList<>();
    Random random = new Random();
    int rashNaZad;

    public RashNaZel(int rashNaZad) {
        this.rashNaZad = rashNaZad;
    }

    public List<Integer> getList() {
        return list;
    }

    //формируем кол огн задач в зависимости от расх на цель
    public void setList() {
        int schetchik = rashNaZad;
        int rashNaZel;
        while (schetchik > 0) {
            rashNaZel = getRashNaZel();
            list.add(rashNaZel);
            schetchik -= rashNaZel;
        }
        System.out.println("Список создан...");
        System.out.println("Размер списка - " + list.size());
    }

    //генерируем расход на цель
    int getRashNaZel() {
        return random.nextInt(4 - 1) + 1;
    }


}
