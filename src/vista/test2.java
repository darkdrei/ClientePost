/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import inventario.logica.db.ReloadServer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author eugenio
 */
public class test2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        Thread h1 = new Thread(new Hilo("primero",1000));
//        h1.start();
//        Thread h2 = new Thread(new Hilo("segundo",2000));
//        h2.start();
//        2017-06-22 13:06:22-04
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss-SS");
//        Date date = new Date();
//        System.out.println(dateFormat.format(date));
        ReloadServer r = new ReloadServer();
        
        r.actualizarArticulos();
    }
    
    
    
}
