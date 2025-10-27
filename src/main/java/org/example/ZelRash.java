package org.example;

public class ZelRash {
    String zel;
    int rash;

    public ZelRash(String zel, int rash) {
        this.zel = zel;
        this.rash = rash;
    }

    public String getZel() {
        return zel;
    }

    public int getRash() {
        return rash;
    }

    @Override
    public String toString() {
        return "цель = " + zel + ", расход = " + rash;
    }
}
