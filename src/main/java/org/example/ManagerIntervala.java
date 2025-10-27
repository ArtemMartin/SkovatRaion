package org.example;

import java.util.Arrays;
import java.util.Random;

public class ManagerIntervala {
    int intervalNaZadachy;
    int colZadach;
    Random random = new Random();
    int[] spisVremen;

    public ManagerIntervala(int colZadach, int intervalNaZadachy) {
        this.colZadach = colZadach;
        this.intervalNaZadachy = intervalNaZadachy;
        spisVremen = new int[colZadach];
        getTimeZad();
    }

    public int[] getSpisVremen() {
        return spisVremen;
    }

    //генерируем масив времен выполнения задач
    public void getTimeZad() {
        int time;
        for (int i = 0; i < colZadach; i++) {
            time = random.nextInt(intervalNaZadachy - 1) + 1;
            if (provSpisVremNaSovp(time)) {
                i--;
            } else {
                spisVremen[i] = time;
            }
        }
    }

    public boolean provSpisVremNaSovp(int time) {
        for (int spisVreman : spisVremen) {
            if (spisVreman == time) return true;
        }
        return false;
    }
}
