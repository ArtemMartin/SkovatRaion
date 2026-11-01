package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ZelNameRash {
    String zel;
    String name;
    int rashod;

    public ZelNameRash(String zel, String name, int rashod) {
        this.zel = zel;
        this.name = name;
        this.rashod = rashod;
    }

    public String getZel() {
        return zel;
    }

    public String getName() {
        return name;
    }

    public int getRashod() {
        return rashod;
    }
}
