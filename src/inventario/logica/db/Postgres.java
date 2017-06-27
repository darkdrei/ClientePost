/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.logica.db;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.JOptionPane;

/**
 *
 * @author eugenio
 */
public class Postgres {

    public static int num = 0;
    public static boolean BANDERA = false;
    private static Postgres conexion = new Postgres();
    private static Connection conex;
    private static Statement stat;

    private Postgres() {
        if(this.existConexion()){
            try {
                abrirConexion();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Postgres.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Postgres.class.getName()).log(Level.SEVERE, null, ex);
            }
            setBandera(true);
        }
    }

    public void abrirConexion() throws ClassNotFoundException, SQLException {

        try {
            Class.forName("org.postgresql.Driver"); //driver a utilizar
            conex = DriverManager.getConnection(
                    "jdbc:postgresql://104.236.33.228:5432/inventario2", "postgres",
                    "Exile*74522547");
        } catch (org.postgresql.util.PSQLException e) {
            BANDERA = false;
            JOptionPane.showMessageDialog(null, e + "  se exploto");//hubo un error
        }
    }

    public static void openConexion() {
        try {
            Class.forName("org.postgresql.Driver"); //driver a utilizar
            conex = DriverManager.getConnection(
                    "jdbc:postgresql://104.236.33.228:5432/inventario2", "postgres",
                    "Exile*74522547");
        } catch (Exception e) {
            BANDERA = false;
            JOptionPane.showMessageDialog(null, e + "  se exploto");//hubo un error
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

}
