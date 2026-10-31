package org.example;

import java.util.Scanner;

public class Zel {
    private String name;
    private double x;
    private double y;
    private Scanner scanner = new Scanner(System.in);

    public Zel() {
        System.out.println("<< Zel >>");
        System.out.print("Ввести имя: ");
        this.name = scanner.nextLine();
        System.out.print("Ввести X: ");
        this.x = scanner.nextDouble();
        System.out.print("Ввести Y: ");
        this.y = scanner.nextDouble();
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

    @Override
    public String toString() {
        return "Zel: " +
                "name='" + name +
                ", x=" + x +
                ", y=" + y;
    }
}
