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
import java.util.Comparator;

public class Comparador implements Comparator<Punto>, java.io.Serializable {

    public int compare(Punto p1, Punto p2) {
        if (p1.getY() < p2.getY()) {
            return -1;
        } else if (p1.getY() == p2.getY()) {
            if (p1.getX() < p2.getX()) {
                return -1;
            } else if (p1.getX() == p2.getX()) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }
}
