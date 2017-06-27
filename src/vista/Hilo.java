/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eugenio
 */
public class Hilo implements Runnable {

    String nombre;
    int tiempo;

    public Hilo(String nombre,int t) {
        this.nombre = nombre;
        this.tiempo =t;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            System.out.println("Hilo " + nombre + "   " + i);
            i++;
            try {
                Thread.sleep(this.tiempo);
            } catch (InterruptedException ex) {
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
