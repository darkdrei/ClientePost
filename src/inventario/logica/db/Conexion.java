/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.logica.db;

import logica.db.*;
import java.sql.Statement;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.JOptionPane;

/**
 *
 * @author Carotech
 */
public class Conexion {
    public static int num=0;
    public static boolean BANDERA =false; 
    private static Conexion conexion = new Conexion();
    private static Connection conex;
    private static Statement stat;
    
    private Conexion(){
        abrirConexion();
    }
    
    public void abrirConexion(){
        try {
            System.out.println("-----------BASE DE DATOS PARA INVENTARIO");
            Class.forName("org.sqlite.JDBC"); //driver a utilizar
            String ruta ="C:\\Users\\eugenio\\Documents\\Origamis-Estable FIna-Terminadol\\inventario.db";
            File base = new File(ruta); //la declaramos como un archivo
            if (base.exists()) {       //si la base existe
                System.out.println("existe el archivo");
                conex = DriverManager.getConnection("jdbc:sqlite:" + ruta); //conexion con la base
                stat = conex.createStatement();              
                BANDERA = true;
            } else {
                JOptionPane.showMessageDialog(null, "La base de datos no existe o no se encuentra en la ruta especificada.");
                BANDERA = false;
            }
        } catch (Exception e) {
            BANDERA = false;
            JOptionPane.showMessageDialog(null, e+"  se exploto");//hubo un error
        }
    }
    
    public static Connection getConnecion(){
        return conex;
    }
    
    public static Statement getStamento(){
        return stat;
    }
    
    public static void setStamento(Statement t){
        stat=t;
    }
    
    public static void setBandera(boolean be){
        BANDERA=be;
    }
    
    public static boolean getBandera(){
        return BANDERA;
    }
    
    public static void cerrarConexion() throws SQLException{
        conex.close();
    }
    
    public static boolean existConexion(){
        try {
            URL u = new URL("https://www.google.es/");
            HttpsURLConnection huc = (HttpsURLConnection) u.openConnection();
            huc.connect();
            return true;
        } catch (Exception e) { 
            return false;
        }        
    }
    
    public static void openConexion(){
        try {
            System.out.println("-----------BASE DE DATOS PARA INVENTARIO");
            Class.forName("org.sqlite.JDBC"); //driver a utilizar
            String ruta ="C:\\Users\\eugenio\\Documents\\Origamis-Estable FIna-Terminadol\\inventario.db";
            File base = new File(ruta); //la declaramos como un archivo
            if (base.exists()) {       //si la base existe
                System.out.println("existe el archivo");
                conex = DriverManager.getConnection("jdbc:sqlite:" + ruta); //conexion con la base
                stat = conex.createStatement();              
                BANDERA = true;
            } else {
                JOptionPane.showMessageDialog(null, "La base de datos no existe o no se encuentra en la ruta especificada.");
                BANDERA = false;
            }
        } catch (Exception e) {
            BANDERA = false;
            JOptionPane.showMessageDialog(null, e+"  se exploto");//hubo un error
        }
    }

    
}
