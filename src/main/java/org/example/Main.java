package org.example;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int intervalTime;
    static int kolSnarNaRaboty;
    static int chas;
    static int minyt;
    static Map<String, ZelRash> mapZad = new LinkedHashMap<>();
    static LocalTime time;
    static List<OS> listOS = new ArrayList<>();
    static Map<OS, List<String>> mapOSZeli;
    static ListZelei listZelei = new ListZelei();
    static Map<String, Map<String, ZelRash>> mapOSMapTimeExpenditure = new LinkedHashMap<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("<<< Сковать район >>>");
            System.out.println("0 - Создать список целей     1 - Создать список огневых средств");
            System.out.println("2 - Создать список задач     3 - Посмотреть список целей");
            System.out.println("4 - Посмотреть список ОП     5 - Показать план ОПП");
            System.out.println("9 - EXIT");
            System.out.println("---------------------------------------------------------------");
            int v;
            int sk;
            sk = scanner.nextInt();
            scanner.nextLine();
            switch (sk) {
                case 0:
                    do {
                        //создаём список целей
                        listZelei.setList();
                        System.out.print("Создать список заново - 1, НЕТ - 0: ");
                        v = scanner.nextInt();
                        scanner.nextLine();
                    } while (v == 1);
                    break;
                case 1:
                    do {
                        //Создаём список огневых средств
                        listOS = makeListOS();
                        System.out.print("Создать список заново - 1, НЕТ - 0: ");
                        v = scanner.nextInt();
                        scanner.nextLine();
                    } while (v == 1);
                    break;
                case 2:
                    //чистим мап ОС список задач
                    mapOSMapTimeExpenditure.clear();
                    do {
                        //создаём Мап ОС - цели
                        ManagerOSZeli managerOSZeli = new ManagerOSZeli(listZelei.getList(), listOS);
                        mapOSZeli = managerOSZeli.getMapOSZeli();

                        System.out.print("Ввести интервал времени для Огн Возд: ");
                        intervalTime = scanner.nextInt();
                        System.out.println("Время начала работы: ");
                        System.out.print("Часы - ");
                        chas = scanner.nextInt();
                        System.out.print("Минуты - ");
                        minyt = scanner.nextInt();
                        if (minyt < 10 && chas < 10) {
                            time = LocalTime.parse("0" + chas + ":" + "0" + minyt);
                        } else if (chas < 10) {
                            time = LocalTime.parse("0" + chas + ":" + minyt);
                        } else if (minyt < 10) {
                            time = LocalTime.parse(chas + ":" + "0" + minyt);
                        } else {
                            time = LocalTime.parse(chas + ":" + minyt);
                        }

                        //проходим по мапу ОС и целей
                        for (Map.Entry<OS, List<String>> entry2 : mapOSZeli.entrySet()) {
                            System.out.println("<<< План на " + entry2.getKey().getName() + " >>>");
                            kolSnarNaRaboty = entry2.getKey().getKolSnaNaRab();
                            //определяем расход на цель по количеству задач
                            RashNaZel rashNaZel = getRashNaZel();

                            int schetchik;
                            do {
                                //получаем время для каждой задачи
                                int[] timeZad = getTimeZadMeth(rashNaZel);

                                for (int i = 0; i < timeZad.length; i++) {
                                    mapZad.put(getTime(timeZad[i]), new ZelRash(getNZeli(entry2.getValue()), rashNaZel.getList().get(i)));
                                }

                                for (Map.Entry<String, ZelRash> entry : mapZad.entrySet()) {
                                    System.out.println(entry.getKey() + " " + entry.getValue());
                                }
                                System.out.print("Прокрутить время задач и расход ещё раз - 1, НЕТ - 0: ");
                                schetchik = scanner.nextInt();
                                if (schetchik == 1) rashNaZel = getRashNaZel();
                                //добавляем ОС и список задач
                                mapOSMapTimeExpenditure.put(entry2.getKey().getName(), new LinkedHashMap<>(mapZad));
                                //чистим список задач
                                mapZad.clear();
                            } while (schetchik != 0);
                        }
                        System.out.print("Создать список заново - 1, НЕТ - 0: ");
                        v = scanner.nextInt();
                        scanner.nextLine();
                    } while (v == 1);
                    break;
                case 3:
                    if (!listZelei.getList().isEmpty()) {
                        listZelei.watchList();
                    }
                    break;
                case 4:
                    if (!listOS.isEmpty()) {
                        for (OS listO : listOS) {
                            System.out.println(listO);
                        }
                    }
                    break;
                case 5:
                    if (mapOSMapTimeExpenditure.isEmpty()) {
                        System.out.println("Список задач пуст!!!");
                        break;
                    }
                    // Получаем текущую дату и время
                    LocalDateTime now = LocalDateTime.now();

                    // Создаём форматер с нужным шаблоном
                    // Шаблон: HH:MM dd:MM:yyyy
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd:MM:yyyy");

                    // Форматируем объект в строку
                    String formattedDateTime = formatter.format(now);
                    System.out.println("<<< План ОПП на " + formattedDateTime + " >>>");
                    watchTableTask();
                    System.out.println("В скобках за позывным расход на цель!!!");
                    break;
                case 9:
                    System.exit(0);
            }
        }
    }

    //показать план ОПП
    private static void watchTableTask() {
        String name;
        String zel;
        LocalTime time;
        int rashod;
        //для прреобразования времени
        String[] masTime;
        //для получения времени цели и расхода
        Map<String, ZelRash> mapTimeZelRash;
        //для дальнейше работы с мапом время цель позывной и расход
        Map<LocalTime, ZelNameRash> mapTimZlNamRash = new LinkedHashMap<>();
        for (Map.Entry<String, Map<String, ZelRash>> entry : mapOSMapTimeExpenditure.entrySet()) {
            name = entry.getKey();
            mapTimeZelRash = entry.getValue();
            for (Map.Entry<String, ZelRash> entry1 : mapTimeZelRash.entrySet()) {
                masTime = entry1.getKey().split(":");
                time = LocalTime.of(Integer.parseInt(masTime[0]), Integer.parseInt(masTime[1]));
                zel = entry1.getValue().getZel();
                rashod = entry1.getValue().getRash();
                mapTimZlNamRash.put(time, new ZelNameRash(zel, name, rashod));
            }
        }
        //сортируем задачи по времени
        Map<LocalTime, ZelNameRash> sortedMap = mapTimZlNamRash.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // Сортировка по ключам (LocalTime)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> {
                            return oldValue;
                        }, // Если ключи дублируются, оставляем старое значение
                        LinkedHashMap::new // Новая карта — LinkedHashMap сохраняет порядок
                ));

        List<String> list = new ArrayList<>();
        for (Zel zel1 : listZelei.getList()) {
            list.add(zel1.getName());
        }
        ConsoleTablePrinter.printTable(sortedMap, list);
    }

    private static List<OS> makeListOS() {
        List<OS> list = new ArrayList<>();
        System.out.println("<< Создаём список ОП >>");
        int vvod;
        int vvod2;
        do {
            System.out.print("Выбрать систему: Д30 - 0: ");
            vvod2 = scanner.nextInt();
            scanner.nextLine();
            if (vvod2 == 0) {
                list.add(new D30());
            }
            System.out.print("Продолжить - 1, нет - 0: ");
            vvod = scanner.nextInt();
            scanner.nextLine();
        } while (vvod == 1);
        return list;
    }

    private static RashNaZel getRashNaZel() {
        RashNaZel rashNaZel = new RashNaZel(kolSnarNaRaboty);
        rashNaZel.setList();
        return rashNaZel;
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