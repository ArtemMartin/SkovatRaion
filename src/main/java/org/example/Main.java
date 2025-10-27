package org.example;

import java.time.LocalTime;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int intervalTime;
    static int kolSnarNaRaboty;
    static int chas;
    static int minyt;
    static Map<String, ZelRash> mapZad = new LinkedHashMap<>();
    static LocalTime time;

    public static void main(String[] args) {
        ListZelei listZelei = new ListZelei();
        listZelei.setList();

        System.out.print("Ввести интервал времени для Огн Возд: ");
        intervalTime = scanner.nextInt();
        System.out.print("Ввести кол снар на работу: ");
        kolSnarNaRaboty = scanner.nextInt();
        //определяем расход на цель по количеству задач
        RashNaZel rashNaZel = new RashNaZel(kolSnarNaRaboty);
        rashNaZel.setList();

        System.out.println("Время начала работы: ");
        System.out.print("Часы - ");
        chas = scanner.nextInt();
        System.out.print("Минуты - ");
        minyt = scanner.nextInt();
        time = LocalTime.parse(chas + ":" + minyt);

        int schetchik;
        do {
            //получаем время для каждой задачи
            int[] timeZad = getTimeZadMeth(rashNaZel);

            for (int i = 0; i < timeZad.length; i++) {
                mapZad.put(getTime(timeZad[i]), new ZelRash(getNZeli(listZelei.getList()), rashNaZel.getList().get(i)));
            }

            for (Map.Entry<String, ZelRash> entry : mapZad.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
            System.out.print("Прокрутить время задач ещё раз - 1, НЕТ - 0:");
            schetchik = scanner.nextInt();
            mapZad.clear();
        } while (schetchik != 0);
    }

    private static int[] getTimeZadMeth(RashNaZel rashNaZel) {
        ManagerIntervala managerIntervala = new ManagerIntervala(rashNaZel.getList().size(), intervalTime);
        int[] timeZad = managerIntervala.getSpisVremen();
        Arrays.sort(timeZad);
        return timeZad;
    }

    public static String getNZeli(List<String> list) {
        Random random = new Random();
        return list.get(random.nextInt((list.size() + 1) - 1));
    }

    public static String getTime(int minytu) {
        LocalTime timeLoc = time.plusMinutes(minytu);
        return timeLoc.toString();
    }
}