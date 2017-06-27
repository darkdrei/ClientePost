/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.logica.db;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.db.DaoFactura;

/**
 *
 * @author eugenio
 */
public class Empleado extends Dao {
    @Override
    public void insertarDato(Object o) {
        inventario.logica.Empleado r=(inventario.logica.Empleado)o;
        try {
            inventario.logica.db.Conexion.setStamento(inventario.logica.db.Conexion.getConnecion().createStatement());
            String sentencia = "insert into empleado (id , nombre , apellidos , usuario , clave , negocio_id ) values("+r.getId()+",'" + r.getNombre() + "','" + r.getApellidos() + "','" + r.getUsuario() + "','" + r.getClave()+ "',"+r.getNegocio()+");";
            //String sentencia = "insert into empleado (id , nombre , apellidos , usuario , clave , negocio_id ) values(1,'dssd','dsd','dsds','dssd',9);";
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('Eugenio',5,1000);";
            inventario.logica.db.Conexion.getStamento().executeUpdate(sentencia);
        } catch (SQLException ex) {
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sateDato(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateDato(Object o, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   @Override
    public Object validInsert( int id) {
        System.out.println("/////////////////////////////////////");
        try {
            inventario.logica.db.Conexion.setStamento(inventario.logica.db.Conexion.getConnecion().createStatement());
            String sentencia = "select count(id) from empleado where id="+id+" limit 1;";
            ResultSet r = inventario.logica.db.Conexion.getStamento().executeQuery(sentencia);
            System.out.println(r.getInt(1)+"  COLUMAN DE LA VALIDCION INSERT");
            if (r.getInt(1) != 0)
                return true;
            return false;
        } catch (SQLException ex) {
            System.err.println("en el valid de empleado 1");
        }
        return false;
    }

    @Override
    public Object validInsert(Object l, String n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean validarPass(String user, String pass) throws NoSuchAlgorithmException {
        System.out.println(" "+user+"  "+pass);
        try {
            inventario.logica.db.Conexion.setStamento(inventario.logica.db.Conexion.getConnecion().createStatement());
            String sentencia = "select  count(nombre)   from empleado where usuario like '" + user + "' and clave like '" + pass + "' limit 1;";
            System.out.println(sentencia);
            ResultSet r = inventario.logica.db.Conexion.getStamento().executeQuery(sentencia);
            if (r.getInt(1) == 0) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException ex) {
            return false;
        }

    }
    
    @Override
    public Object getDato(String... cad) {
        if(cad.length < 2)
            return null;
        try {
            inventario.logica.db.Conexion.setStamento(inventario.logica.db.Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select id,nombre,apellidos,usuario,clave,negocio_id from empleado where usuario=\""+cad[0]+"\" and clave=\""+cad[1]+"\" limit 1;";
            ResultSet r = inventario.logica.db.Conexion.getStamento().executeQuery(sentencia);
//            try{
            while (r.next()) {
                return  (new inventario.logica.Empleado(r.getInt(1), r.getString(2), r.getString(3), r.getString(4),r.getString(5),r.getInt(6)));
            }
            return 0;
        } catch (SQLException ex) {

        }
        return null;
    }
    
}
