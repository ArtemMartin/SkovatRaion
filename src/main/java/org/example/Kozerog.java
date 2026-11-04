package org.example;

public class Kozerog extends OS {
    public Kozerog() {
        super();
        sistema = "'Kozerog'";
    }

    @Override
    public String toString() {
        return "sistema = '" + sistema +
                ", name = '" + name +
                ", x = " + x +
                ", y = " + y +
                ", ON = " + ON +
                ", dovLeft = " + dovLeft +
                ", dovRight = " + dovRight +
                ", dalnMax = " + dalnMax +
                ", dalnMin = " + dalnMin +
                ", kolSnaNaRab = " + kolSnaNaRab;
    }
}
