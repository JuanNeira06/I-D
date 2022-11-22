/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClosetPairRecursivo;

/**
 *
 * @author jtucan
 */
import java.text.*;

public class Punto implements Comparable<Punto>, java.io.Serializable {

    private double x;
    private double y;

    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int compareTo(Punto p) {
        if (this.getX() < p.getX()) {
            return -1;
        } else if (this.getX() == p.getX()) {
            if (this.getY() < p.getY()) {
                return -1;
            } else if (this.getY() == p.getY()) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 1;
        }

    }

    public String toString() {
        DecimalFormat format = new DecimalFormat("###.00");
        String str = "(" + format.format(x) + " , " + format.format(y) + ")";
        return str;
    }
}
