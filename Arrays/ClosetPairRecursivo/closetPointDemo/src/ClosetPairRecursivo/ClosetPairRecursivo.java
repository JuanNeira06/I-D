/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClosetPairRecursivo;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author jtucan
 */
public class ClosetPairRecursivo {

    public static void GetRandoms(ArrayList numeros, int n) {
        int numero;
        for (int i = 1; i <= n; i++) {
            numero = (int) (Math.random() * 255 + 1);
            if (numeros.contains(numero)) {
                i--;
            } else {
                numeros.add(numero);
            }
        }
    }

    /**`
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //create Point[] object with capacity of 100 
        for (int i = 1; i < 10; i++) {
            int n = (i * 2) * 10;
            int indice=0;
            int indice2=0;
            ArrayList<Integer> numeros = new ArrayList();
            GetRandoms(numeros,n);
            Punto p[] = new Punto[n/2];
            for (int j = 0; j < numeros.size()-2; j=j+2) {
                indice = indice +1;
                p[indice]= new Punto(numeros.get(j),numeros.get(j+1));
                System.out.println("asdasdasdsadasd"+p[indice]);
            }
            double[][] p1 = new double[numeros.size()/2][2];
            for (int j = 0; j < numeros.size()/2; j++) {
                for (int k = 0; k < 2; k++) {
                    int num = numeros.get(indice2);
                    double b = (double) num;
                    p1[j][k]= b;
                    indice2 = indice2 +1;
                }
            }
            DecimalFormat format = new DecimalFormat("###.00");
            Par pair = Par.getClosestPair(p);
            System.out.println("Test with 2-dimentional array: ");
            for (int fila = 0; fila < p1.length; fila++) {
                System.out.println("(" + p1[fila][0] + " , " + p1[fila][1] + ")");
            }
            Par pair1 = Par.getClosestPair(p1);
            System.out.println("El par mas cercano");
            System.out.println(pair1.getp1());
            System.out.println(pair1.getp2());
            System.out.println("Distancia: " + format.format(pair1.getDistancia()));
        }
    }

}
