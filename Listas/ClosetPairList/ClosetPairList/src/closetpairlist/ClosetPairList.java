/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closetpairlist;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

/**
 *
 * @author jtucan
 */
public class ClosetPairList {
    
  public static int comparaciones = 0;
  
    public static class Punto {

        private int x;
        private int y;

        public Punto(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
     public List<Punto> mindistance( LinkedList<Punto> list) {
        comparaciones = 0;
        if (list == null || list.size() == 0) {
            return new LinkedList<>();
        }
         LinkedList<Punto> result = new LinkedList();
        int n = list.size();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int distance = getDistance(list.get(i), list.get(j));
                if (min > distance || result.size() == 0) {
                    result.clear();
                    result.add(list.get(i));
                    result.add(list.get(j));
                    min = distance;

                }
                comparaciones = comparaciones + 1;
            }
        }
        return result;
    }

    public int getDistance(Punto a, Punto b) {
        int x = a.x - b.x;
        int y = a.y - b.y;
        return x * x + y * y;
    }

    public void display(List<Punto> list) {
        Punto a = list.get(0);
        Punto b = list.get(1);
        System.out.print("{" + a.x + " " + a.y + "}");
        System.out.print("{" + b.x + " " + b.y + "}");
        System.out.println(" ");
    }

    public static void GetRandoms(LinkedList numeros,int n) {
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
  
    public static void main(String[] args)throws IOException  {
        // TODO code application logic here
         FileWriter txt = new FileWriter ("C:\\Users\\jtucan\\Documents\\AvarageList.txt");
        for (int i = 1; i < 13; i++) {
            int n = (i*2)*10;
           
            LinkedList<Integer> numeros = new LinkedList();
            LinkedList puntos = new LinkedList();
            GetRandoms(numeros,n);
            ClosetPairList m = new ClosetPairList();
            LinkedList <Punto> list = new LinkedList<>();
            for (int j = 0; j < numeros.size(); j = j + 2) {
                Punto p1 = new Punto(numeros.get(j), numeros.get(j + 1));
                list.add(p1);
            }
            int startTime = (int) System.nanoTime();
            List<Punto> result = m.mindistance(list);
            int endTime = (int) System.nanoTime();
            m.display(result);
            System.out.println("Las  coordenadas son: ");
            for (int j = 0; j < numeros.size(); j = j + 2) {
                System.out.println("" + numeros.get(j) + "," + numeros.get(j + 1));
            }
            System.out.println("El numero de puntos son: " + n / 2);
            System.out.println("The distance between the closest points is " + Math.sqrt(m.getDistance(result.get(0), result.get(1))));
            
            System.out.println("Duración: " + (endTime - startTime) + " Nanosegundos");
            int tiempo = (endTime - startTime);
            System.out.println("El numero de compraciones son: " + comparaciones);
            txt.write(""+n/2+" "+comparaciones+" "+tiempo+"\n");
        }
        txt.close();
    }
    
}
