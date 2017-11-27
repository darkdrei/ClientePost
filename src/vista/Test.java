/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.sqlite.SQLiteDataSource;
import org.sqlite.SQLiteJDBCLoader;

/**
 *
 * @author dark
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        boolean initialize = SQLiteJDBCLoader.initialize();

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:/home/dark/proyectos/ClientePost/inventario.db");
        int i = 0;
        try {
            ResultSet executeQuery = dataSource.getConnection()
                    .createStatement().executeQuery("select * from articulo");
            while (executeQuery.next()) {
                i++;
                System.out.println("out: " + executeQuery.getMetaData().getColumnLabel(i));

            }
            dataSource.getConnection().close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

}
