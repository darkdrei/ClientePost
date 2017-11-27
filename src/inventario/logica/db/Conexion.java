/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.logica.db;

import com.sun.net.ssl.HttpsURLConnection;
import logica.db.*;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.sqlite.SQLiteDataSource;
import org.sqlite.SQLiteJDBCLoader;

/**
 *
 * @author Carotech
 */
public class Conexion {

    public static int num = 0;
    public static boolean BANDERA = false;
    private static Conexion conexion = new Conexion();
    private static Connection conex;
    private static Statement stat;

    private Conexion() {
        try {
            boolean initialize = SQLiteJDBCLoader.initialize();
            abrirConexion();
        } catch (Exception ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void abrirConexion() {
        try {
            System.out.println("-----------BASE DE DATOS PARA INVENTARIO");
            System.out.println("antes de llamar a el driver");
            String ruta = "C:\\Users\\eugenio\\Documents\\Origamis-Estable FIna-Terminadol\\inventario.db";
            ruta = "/home/dark/proyectos/ClientePost/inventario.db";
            System.out.println("antes del archivo");
            File base = new File(ruta); //la declaramos como un archivo
            System.out.println("Paso abrir el archivo");
            if (base.exists()) {       //si la base existe
                System.out.println("existe el archivo");
                SQLiteDataSource dataSource = new SQLiteDataSource();
                dataSource.setUrl("jdbc:sqlite:/home/dark/proyectos/ClientePost/inventario.db");
                conex = dataSource.getConnection();
                stat = conex.createStatement();
                BANDERA = true;
            } else {
                JOptionPane.showMessageDialog(null, "La base de datos no existe o no se encuentra en la ruta especificada.");
                BANDERA = false;
            }
        } catch (Exception e) {
            BANDERA = false;
            JOptionPane.showMessageDialog(null, e + "  se exploto la vaina");//hubo un error
        }
    }

    public static Connection getConnecion() {
        return conex;
    }

    public static Statement getStamento() {
        return stat;
    }

    public static void setStamento(Statement t) {
        stat = t;
    }

    public static void setBandera(boolean be) {
        BANDERA = be;
    }

    public static boolean getBandera() {
        return BANDERA;
    }

    public static void cerrarConexion() throws SQLException {
        conex.close();
    }

    public static boolean existConexion() {
        try {
            URL u = new URL("https://www.google.es/");
            HttpsURLConnection huc = (HttpsURLConnection) u.openConnection();
            huc.connect();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void openConexion() {
        try {            
            SQLiteDataSource dataSource = new SQLiteDataSource();
            dataSource.setUrl("jdbc:sqlite:/home/dark/proyectos/ClientePost/inventario.db");
            conex = dataSource.getConnection();
            stat = conex.createStatement();
        } catch (Exception ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
