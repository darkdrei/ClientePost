/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import inventario.logica.db.Postgres;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eugenio
 */
public class InventarioManejadorSincronizador extends Thread{
    private boolean bandera = false;
    private int t;
    private int negocio;

        public InventarioManejadorSincronizador(int t, int n) {
        this.t = t;
        this.negocio = n;
    }

    public synchronized boolean isBandera() {
        return bandera;
    }

    public synchronized void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    public synchronized int getT() {
        return t;
    }

    public synchronized void setT(int t) {
        this.t = t;
    }

    @Override
    public void run() {
        inventario.logica.db.ReloadServer server = new inventario.logica.db.ReloadServer();
        boolean b = true;
        while (!this.isBandera()) {
            System.err.println("entro en el hilo");
            if (Postgres.existConexion()) {
                if (Postgres.getConnecion() != null) {
                    server.actualizarArticulos(negocio);
                    
                    System.err.println("salto actualizacion");
                } else {
                    Postgres.openConexion();
                }
            }
            try {
                Thread.sleep(t);
            } catch (InterruptedException ex) {
                Logger.getLogger(cajero.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
