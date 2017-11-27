/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.db;

import java.sql.Statement;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.sqlite.SQLiteDataSource;
import org.sqlite.SQLiteJDBCLoader;

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
            Class.forName("org.sqlite.JDBC"); //driver a utilizar
            //String ruta = "/home/dreicon88/Dropbox/Restaurante/Restaurante-V2-2/origami.db"; //especificamos la ruta de la base
            //String ruta = "/home/dark/Dropbox/Restaurante/V2/Origami2/src/logica/db/origami.db";
            //String ruta = "H:\\windows 10\\Origami2\\src\\logica\\db\\origami.db"; //especificamos la ruta de la base
            //String ruta ="G:\\windows 10\\Origami2\\src\\logica\\\\db\\\\origami.db";
            //String ruta ="G:\\windows 10\\Origami2\\src\\logica\\db\\origami.db";
            //String ruta="C:\\Users\\CTesting\\Pictures\\Origami2\\src\\logica\\db\\origami.db";
            //String ruta ="/home/carotech/Descargas/Origami2(Backup f)/src/logica/db/origami.db";
            //String ruta ="C:\\Users\\dreicon\\Desktop\\Restaurante\\V2\\Origami2(Backup f)\\src\\logica\\db\\origami.db";
            //String ruta ="C:\\Users\\hp 4204LA\\Desktop\\origami\\origami.db";
            //String ruta ="C:\\Users\\Carotech\Desktop\\origam.db";
            String ruta ="C:\\Users\\eugenio\\Downloads\\origam.db";
            ruta="/home/dark/proyectos/ClientePost/origam.db";
            //ruta ="C:\\origamis\\origam.db";
            //ruta ="C:\\Users\\Carotech\\Desktop\\origam.db";
            //String ruta ="/home/dark/Dropbox/Restaurante/origami.db";
            System.out.println("Entrando en la ruta de creacin de db");
            File base = new File(ruta); //la declaramos como un archivo
                        boolean initialize = SQLiteJDBCLoader.initialize();
            SQLiteDataSource dataSource = new SQLiteDataSource();

            if (base.exists()) {       //si la base existe
                System.out.println("existe el archivo");
                conex = DriverManager.getConnection("jdbc:sqlite:" + ruta); //conexion con la base
                stat = conex.createStatement();              
                BANDERA = true;
               //JOptionPane.showMessageDialog(null, "La base existe, y se ha conectado exitosamente!!");
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
    
    public void openConexion(){
        try {
            Class.forName("org.sqlite.JDBC"); //driver a utilizar
            //String ruta = "/home/dreicon88/Dropbox/Restaurante/Restaurante-V2-2/origami.db"; //especificamos la ruta de la base
            //String ruta = "/home/dark/Dropbox/Restaurante/V2/Origami2/src/logica/db/origami.db";
            //String ruta = "H:\\windows 10\\Origami2\\src\\logica\\db\\origami.db"; //especificamos la ruta de la base
            //String ruta ="G:\\windows 10\\Origami2\\src\\logica\\\\db\\\\origami.db";
            //String ruta ="G:\\windows 10\\Origami2\\src\\logica\\db\\origami.db";
            //String ruta="C:\\Users\\CTesting\\Pictures\\Origami2\\src\\logica\\db\\origami.db";
            //String ruta ="/home/carotech/Descargas/Origami2(Backup f)/src/logica/db/origami.db";
            //String ruta ="C:\\Users\\dreicon\\Desktop\\Restaurante\\V2\\Origami2(Backup f)\\src\\logica\\db\\origami.db";
            //String ruta ="C:\\Users\\hp 4204LA\\Desktop\\origami\\origami.db";
            //String ruta ="C:\\Users\\Carotech\Desktop\\origam.db";
            String ruta ="C:\\Users\\eugenio\\Downloads\\origam.db";
            //ruta ="C:\\origamis\\origam.db";
            //ruta ="C:\\Users\\Carotech\\Desktop\\origam.db";
            //String ruta ="/home/dark/Dropbox/Restaurante/origami.db";
            System.out.println("Entrando en la ruta de creacin de db");
            File base = new File(ruta); //la declaramos como un archivo
            if (base.exists()) {       //si la base existe
                System.out.println("existe el archivo");
                conex = DriverManager.getConnection("jdbc:sqlite:" + ruta); //conexion con la base
                stat = conex.createStatement();              
                BANDERA = true;
               //JOptionPane.showMessageDialog(null, "La base existe, y se ha conectado exitosamente!!");
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
