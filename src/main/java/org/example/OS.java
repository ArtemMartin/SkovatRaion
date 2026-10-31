package org.example;

import java.util.Scanner;

public  class OS {
    protected String name;
    protected double x;
    protected double y;
    protected int ON;
    protected int dovLeft;
    protected int dovRight;
    protected double dalnMax;
    protected double dalnMin;
    protected int kolSnaNaRab;
    protected Scanner scanner = new Scanner(System.in);

    public OS() {
        System.out.println("<<< Записываем ОС >>>");
        System.out.print("Позывной: ");
        this.name = scanner.nextLine();
        System.out.print("Х: ");
        this.x = scanner.nextDouble();
        System.out.print("Y: ");
        this.y = scanner.nextDouble();
        System.out.print("OH: ");
        this.ON = scanner.nextInt();
        System.out.print("Доворот влево: ");
        this.dovLeft = scanner.nextInt();
        System.out.print("Доворот вправо: ");
        this.dovRight = scanner.nextInt();
        System.out.print("Дальность Макс: ");
        this.dalnMax = scanner.nextInt();
        System.out.print("Дальность Мин: ");
        this.dalnMin = scanner.nextInt();
        System.out.print("К-во снарядов на работу: ");
        this.kolSnaNaRab = scanner.nextInt();
        scanner.nextLine();
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getON() {
        return ON;
    }

    public int getDovLeft() {
        return dovLeft;
    }

    public int getDovRight() {
        return dovRight;
    }

    public double getDalnMax() {
        return dalnMax;
    }

    public double getDalnMin() {
        return dalnMin;
    }

    public int getKolSnaNaRab() {
        return kolSnaNaRab;
    }

    public Scanner getScanner() {
        return scanner;
    }
}
