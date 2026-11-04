package org.example;

public class D30 extends OS {


    public D30() {
        super();
        sistema = "'D30'";
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
                ", kolSnaNaRab = " +
                "" + kolSnaNaRab;
    }
}
