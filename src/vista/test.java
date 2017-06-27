/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import inventario.logica.db.ReloadServer;
import java.net.URL;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author eugenio
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String conStatus = null;
//        try {
//            URL u = new URL("https://www.google.es/");
//            HttpsURLConnection huc = (HttpsURLConnection) u.openConnection();
//            huc.connect();
//            conStatus = "Online";
//        } catch (Exception e) { 
//            conStatus = "Offline";
//        }        
//        System.out.println(""+conStatus);
        ReloadServer r = new ReloadServer();
//        r.actualizarEmpleados();
//        r.actualizarArticulos();
//        parametros(new String[] { "7", "8"," 9" });
System.out.println(Pattern.matches("\\d+?.\\d+", "01234567.25"));
    }
    
    public static void parametros(String... t){
        System.out.println(t[0]+"  "+t.length);
    }
    
}
