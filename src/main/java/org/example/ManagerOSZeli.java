package org.example;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ManagerOSZeli {
    Map<String, List<String>> mapOSZeli;
    private List<Zel> listZelei;
    private List<OS> listOS;

    public ManagerOSZeli(List<Zel> listZelei, List<OS> listOS) {
        this.listZelei = listZelei;
        this.listOS = listOS;
    }

    public Map<OS, List<String>> getMapOSZeli() {
        double dct;
        double ygct;
        double[] ogz;
        Map<OS, List<String>> mapOSZeli = new LinkedHashMap<>();
        List<String> list = new ArrayList<>();
        for (OS listO : listOS) {
            for (Zel zel : listZelei) {
                ogz = OGZ(listO.x, listO.y, zel.getX(), zel.getY());
                dct = ogz[0];
                ygct = ogz[1];
                if (proverkaSootvetstvia(dct, ygct, listO.dalnMax, listO.dovLeft, listO.dovRight, listO.ON)) {
                    list.add(zel.getName());
                }
            }
            if (!list.isEmpty()) {

                mapOSZeli.put(listO, new ArrayList<>(list));
            }
            list.clear();
        }
        return mapOSZeli;
    }

    public boolean proverkaSootvetstvia(double dc, double ygolc, double dalOP, double dovLeft, double dovRight, double OH) {
        boolean propyskPoDal;
        boolean propyskPoYgly;

        propyskPoDal = dc < dalOP;

        double ygolLeft = OH - dovLeft;
        double ygolRight = OH + dovRight;
        if (ygolLeft > 4500 && ygolRight < 1500) {
            ygolRight += 6000;
        }

        if (ygolc < 1500 && ygolLeft > 4500) {
            ygolc += 6000;
        }
        propyskPoYgly = ygolc > ygolLeft && ygolc < ygolRight;

        return propyskPoDal && propyskPoYgly;
    }

    public double[] OGZ(double xop, double yop, double xc, double yc) {
        double[] ogz;
        double dx = xc - xop;
        double dy = yc - yop;

        double dalTopo = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        double a = Math.abs(Math.atan(dy / dx) / Math.PI * 30 * 100);
        double ygolTopo = 0;
        if (dx > 0 && dy > 0) {
            ygolTopo = a;
        } else if (dx < 0 && dy > 0) {
            ygolTopo = 3000 - a;
        } else if (dx < 0 && dy < 0) {
            ygolTopo = 3000 + a;
        } else if (dx > 0 && dy < 0) {
            ygolTopo = 6000 - a;
        }
        ogz = new double[]{dalTopo, ygolTopo};
        return ogz;
    }

}
