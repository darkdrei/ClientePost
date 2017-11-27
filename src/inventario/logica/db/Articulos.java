/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.logica.db;

import inventario.logica.Articulo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import logica.db.DaoFactura;
import inventario.logica.db.Conexion;

/**
 *
 * @author eugenio
 */
public class Articulos extends Dao{
    
    @Override
    public void insertarDato(Object o) {
        inventario.logica.Articulo r=(inventario.logica.Articulo)o;
        try {
            inventario.logica.db.Conexion.setStamento(inventario.logica.db.Conexion.getConnecion().createStatement());
            String sentencia = "insert into articulo (id,nombre,precio,existencias,tipo,negocio,codigo ) "
                    + "values("+r.getId()+",'" + r.getNombre() + "','" + r.getPrecio() + "','" + r.getExistencias() + "','" + r.getTipo()+ "',"+r.getNegocio()+",'" + r.getCodigo()+ "');";
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('Eugenio',5,1000);";
            inventario.logica.db.Conexion.getStamento().executeUpdate(sentencia);
        } catch (SQLException ex) {
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public ArrayList<Object> getDatos(){
        ArrayList<Object> o = new ArrayList<>();
        try {
            inventario.logica.db.Conexion.setStamento(inventario.logica.db.Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select id,nombre,precio,existencias,tipo,negocio,codigo from articulo where  existencias>0;";
            ResultSet r = inventario.logica.db.Conexion.getStamento().executeQuery(sentencia);
//            try{
            while (r.next()) {
                o.add(new Articulo(r.getInt(1), r.getString(2), r.getFloat(3),r.getFloat(4),r.getInt(5),r.getInt(6),r.getString(7)));
            }
            return o;
        } catch (SQLException ex) {

        }
        return o;
    }
    
    @Override
    public ArrayList<Object> getDatos(int id){
        ArrayList<Object> o = new ArrayList<>();
        try {
            System.out.println("esto es la conexio--> "+Conexion.existConexion());
            inventario.logica.db.Conexion.setStamento(inventario.logica.db.Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select id,nombre,precio,existencias,tipo,negocio from articulo where  existencias>0 and negocio = "+id+";";
            ResultSet r = inventario.logica.db.Conexion.getStamento().executeQuery(sentencia);
//            try{
            while (r.next()) {
                o.add(new Articulo(r.getInt(1), r.getString(2), r.getFloat(3),r.getFloat(4),r.getInt(5),r.getInt(6), r.getString(7)));
            }
            return o;
        } catch (SQLException ex) {

        }
        return null;
    }
    
    @Override
    public ArrayList<Object> getDatos(String... id) {
        ArrayList<Object> o = new ArrayList<>();
        try {
            inventario.logica.db.Conexion.setStamento(inventario.logica.db.Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
//            String sentencia = "select id,nombre,precio,existencias,tipo,negocio,codigo from articulo where (nombre like '%"+id[0]+"%' or codigo like '%"+id[0]+"%' )and  existencias>0;";
            String sentencia = "select id,nombre,precio,existencias,tipo,negocio,codigo from articulo where (nombre like '%"+id[0]+"%' or codigo like '%"+id[0]+"%' )and  existencias>0 and negocio="+id[1]+";";
            ResultSet r = inventario.logica.db.Conexion.getStamento().executeQuery(sentencia);
//            try{
            while (r.next()) {
                o.add(new Articulo(r.getInt(1), r.getString(2), r.getFloat(3),r.getFloat(4),r.getInt(5),r.getInt(6), r.getString(7)));
            }
            return o;
        } catch (SQLException ex) {

        }
        return o;
    }
    
    @Override
    public Object validInsert( int id) {
        System.out.println("/////////////////////////////////////");
        try {
            inventario.logica.db.Conexion.setStamento(inventario.logica.db.Conexion.getConnecion().createStatement());
            String sentencia = "select count(id) from articulo where id="+id+" limit 1;";
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
    public void updateDato(Object o){
    inventario.logica.Articulo i = (inventario.logica.Articulo) o;
        System.out.println("************************************ "+i.toString());
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "update articulo set existencias="+i.getExistencias()+" where id =" + i.getId() + ";";
            System.out.println(sentencia);
            Conexion.getStamento().executeUpdate(sentencia);
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, "e exploto");
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Object getDato(int id) {
        System.out.println("este es el id del precio------->-----> "+id);
        try {
            inventario.logica.db.Conexion.setStamento(inventario.logica.db.Conexion.getConnecion().createStatement());
            String sentencia = "select id,nombre,precio,existencias,tipo,negocio,codigo from articulo where id="+id+";";
            ResultSet r = inventario.logica.db.Conexion.getStamento().executeQuery(sentencia);
            while (r.next()) {
                return (new Articulo(r.getInt(1), r.getString(2), r.getFloat(3),r.getFloat(4),r.getInt(5),r.getInt(6), r.getString(7)));
            }
        } catch (SQLException ex) {
            System.err.println("en el valid de empleado 1");
        }
        return null;
    }
}
