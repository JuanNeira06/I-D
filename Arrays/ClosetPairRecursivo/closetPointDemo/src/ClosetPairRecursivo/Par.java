/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClosetPairRecursivo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author jtucan
 */
public class Par {

    private Punto p1;
    private Punto p2;

    public Par(Punto p1, Punto p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void setp1(Punto p1) {
        this.p1 = p1;
    }

    public void setp2(Punto p2) {
        this.p2 = p2;
    }

    public Punto getp1() {
        return p1;
    }

    public Punto getp2() {
        return p2;
    }

    public double getDistancia() {
        return Par.distancia(p1, p2);
    }

    public static double distancia(Punto p1, Punto p2) {
        return Par.distancia(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    public static double distancia(double x1, double y1, double x2, double y2) {

        return Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));

    }

    public static Par getClosestPair(Punto[] points) {
        Arrays.sort(points);
        Punto[] pointsOrderedOnY = points.clone();
        Arrays.sort(pointsOrderedOnY, new Comparador());
        return distancia(points, 0, points.length - 1, pointsOrderedOnY);
    }

    public static Par getClosestPair(double[][] points) {
        Punto[] points2 = new Punto[points.length];
        for (int i = 0; i < points.length; i++) {
            points2[i] = new Punto(points[i][0], points[i][1]);
        }
        return getClosestPair(points2);
    }

    public static Par distancia(Punto[] pointsOrderedOnX, int low, int high, Punto[] pointsOrderedOnY) {
        if (low >= high) {
            return null;
        } else if (low + 1 == high) {
            return new Par(pointsOrderedOnX[low], pointsOrderedOnX[high]);
        }

        int midPoint = (low + high) / 2;

        Par leftPair = distancia(pointsOrderedOnX, low, midPoint, pointsOrderedOnY);

        Par rightPair = distancia(pointsOrderedOnX, midPoint + 1, high, pointsOrderedOnY);

        double d = 0;
        Par p = null;

        if (leftPair == null && rightPair == null) {
            d = 0;
            p = null;
        } else if (leftPair == null) {
            d = rightPair.getDistancia();
            p = rightPair;
        } else if (rightPair == null) {
            d = leftPair.getDistancia();
            p = leftPair;
        } else {

            d = Math.min(leftPair.getDistancia(), rightPair.getDistancia());

            if (leftPair.getDistancia() <= rightPair.getDistancia()) {
                p = leftPair;
            } else {
                p = rightPair;
            }

        }

        ArrayList<Punto> stripL = new ArrayList<Punto>();
        ArrayList<Punto> stripR = new ArrayList<Punto>();
        for (int i = 0; i < pointsOrderedOnY.length; i++) {
            if ((pointsOrderedOnY[i].getX() <= pointsOrderedOnX[midPoint].getX())
                    && (pointsOrderedOnX[midPoint].getX() - pointsOrderedOnY[i].getX() <= d)) {
                stripL.add(pointsOrderedOnY[i]);
            } else if ((pointsOrderedOnY[i].getX() > pointsOrderedOnX[midPoint].getX())
                    && (pointsOrderedOnY[i].getX() - pointsOrderedOnX[midPoint].getX() <= d)) {
                stripR.add(pointsOrderedOnY[i]);
            }
        }

        int r = 0;
        for (int i = 0; i < stripL.size(); i++) {
            while (r < stripR.size() && stripR.get(r).getY() <= stripL.get(i).getY() - d) {
                r++;
            }
            int r1 = r;
            while (r1 < stripR.size() && Math.abs(stripR.get(r1).getY() - stripL.get(i).getY()) <= d) {
                if (Par.distancia(stripL.get(i), stripR.get(r1)) < d) {
                    d = Par.distancia(stripL.get(i), stripR.get(r1));
                    p.p1 = stripL.get(i);
                    p.p2 = stripR.get(r1);
                }
                r1++;
            }
        }

        return p;
    }
}
