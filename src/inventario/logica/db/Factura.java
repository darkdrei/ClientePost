/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.logica.db;

import inventario.logica.Articulo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.db.DaoFactura;

/**
 *
 * @author eugenio
 */
public class Factura extends Dao {

    @Override
    public void insertarDato(Object o) {
        inventario.logica.Articulo r = (inventario.logica.Articulo) o;
        try {
//            inventario.logica.db.Conexion.cerrarConexion();
//            inventario.logica.db.Conexion.openConexion();
            inventario.logica.db.Conexion.setStamento(inventario.logica.db.Conexion.getConnecion().createStatement());
            String sentencia = "insert  into factura (paga,estado,enviada,cargada) values(0,1,0,0);";
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('Eugenio',5,1000);";
            inventario.logica.db.Conexion.getStamento().executeUpdate(sentencia);
//            inventario.logica.db.Conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Object> getDatos() {
        ArrayList<Object> o = new ArrayList<>();
        try {
//            inventario.logica.db.Conexion.cerrarConexion();
//            inventario.logica.db.Conexion.openConexion();
            inventario.logica.db.Conexion.setStamento(inventario.logica.db.Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select id,nombre,precio,existencias,tipo,negocio,codigo from articulo where  existencias>0;";
            ResultSet r = inventario.logica.db.Conexion.getStamento().executeQuery(sentencia);
//            inventario.logica.db.Conexion.cerrarConexion();
            while (r.next()) {
                o.add(new Articulo(r.getInt(1), r.getString(2), r.getFloat(3), r.getFloat(4), r.getInt(5), r.getInt(6), r.getString(7)));
            }
            return o;
        } catch (SQLException ex) {

        }
        return o;
    }

    public ArrayList<Object> getDatos(int id) {
        ArrayList<Object> o = new ArrayList<>();
        try {
//            inventario.logica.db.Conexion.cerrarConexion();
//            inventario.logica.db.Conexion.openConexion();
            inventario.logica.db.Conexion.setStamento(inventario.logica.db.Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select id,nombre,precio,existencias,tipo,negocio from articulo where  existencias>0 and negocio = " + id + ";";
            ResultSet r = inventario.logica.db.Conexion.getStamento().executeQuery(sentencia);
//            inventario.logica.db.Conexion.cerrarConexion();
            while (r.next()) {
                o.add(new Articulo(r.getInt(1), r.getString(2), r.getFloat(3), r.getFloat(4), r.getInt(5), r.getInt(6), r.getString(7)));
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
//            inventario.logica.db.Conexion.cerrarConexion();
//            inventario.logica.db.Conexion.openConexion();
            inventario.logica.db.Conexion.setStamento(inventario.logica.db.Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
//            String sentencia = "select id,nombre,precio,existencias,tipo,negocio,codigo from articulo where (nombre like '%"+id[0]+"%' or codigo like '%"+id[0]+"%' )and  existencias>0;";
            String sentencia = "select id,nombre,precio,existencias,tipo,negocio,codigo from articulo where (nombre like '%" + id[0] + "%' or codigo like '%" + id[0] + "%' )and  existencias>0;";
            ResultSet r = inventario.logica.db.Conexion.getStamento().executeQuery(sentencia);
//            inventario.logica.db.Conexion.cerrarConexion();
            while (r.next()) {
                o.add(new Articulo(r.getInt(1), r.getString(2), r.getFloat(3), r.getFloat(4), r.getInt(5), r.getInt(6), r.getString(7)));
            }
            return o;
        } catch (SQLException ex) {

        }
        return o;
    }

    @Override
    public Object validInsert(int id) {
        System.out.println("/////////////////////////////////////");
        try {
//            inventario.logica.db.Conexion.cerrarConexion();
//            inventario.logica.db.Conexion.openConexion();
            inventario.logica.db.Conexion.setStamento(inventario.logica.db.Conexion.getConnecion().createStatement());
            String sentencia = "select count(id) from articulo where id=" + id + " limit 1;";
            ResultSet r = inventario.logica.db.Conexion.getStamento().executeQuery(sentencia);
            System.out.println(r.getInt(1) + "  COLUMAN DE LA VALIDCION INSERT");
//            inventario.logica.db.Conexion.cerrarConexion();
            if (r.getInt(1) != 0) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            System.err.println("en el valid de empleado 1");
        }
        return false;
    }

    @Override
    public void updateDato(Object o) {
        inventario.logica.Articulo i = (inventario.logica.Articulo) o;
        try {
//            inventario.logica.db.Conexion.cerrarConexion();
//            inventario.logica.db.Conexion.openConexion();
            inventario.logica.db.Conexion.setStamento(inventario.logica.db.Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "update articulo set existencias=" + i.getExistencias() + " where id =" + i.getId() + ";";
            inventario.logica.db.Conexion.getStamento().executeUpdate(sentencia);
//            inventario.logica.db.Conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Object get_sDato() {
        try {
//            inventario.logica.db.Conexion.cerrarConexion();
//            inventario.logica.db.Conexion.openConexion();
            inventario.logica.db.Conexion.setStamento(inventario.logica.db.Conexion.getConnecion().createStatement());
            String sentencia = "select id,case when nombre is null then '' else nombre end as nom ,case when apellidos is null then '' else apellidos end as ape,case when direccion is null then '' else direccion end as dir,case when fecha is null then '' else fecha end as fecha,estado,paga,enviada from factura where  estado=1 and enviada=0 limit 1;";
            ResultSet r = inventario.logica.db.Conexion.getStamento().executeQuery(sentencia);
            System.out.println(r.getInt(1) + "  COLUMAN DE LA VALIDCION INSERT");
//            inventario.logica.db.Conexion.cerrarConexion();
            bucle:
            while (r.next()) {
                return (new inventario.logica.Factura(r.getInt(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5), r.getBoolean(6), r.getBoolean(7), r.getBoolean(8)));
            }
        } catch (SQLException ex) {
            System.err.println("en el valid de empleado 1");
        }
        return null;
    }

    public boolean validInsert() {
        try {
//            inventario.logica.db.Conexion.cerrarConexion();
//            inventario.logica.db.Conexion.openConexion();
            inventario.logica.db.Conexion.setStamento(inventario.logica.db.Conexion.getConnecion().createStatement());
            String sentencia = "select count(id) from factura where  estado=1 and enviada=0 limit 1;";
            ResultSet r = inventario.logica.db.Conexion.getStamento().executeQuery(sentencia);
            System.out.println(r.getInt(1) + "  COLUMAN DE LA VALIDCION INSERT");
//            inventario.logica.db.Conexion.cerrarConexion();
            if (r.getInt(1) != 0) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            System.err.println("en el valid de empleado 1");
        }
        return false;
    }

    @Override
    public void updateDato(int o) {
        System.out.println("este es el id ---->  " + 0);
        try {
//            inventario.logica.db.Conexion.cerrarConexion();
//            inventario.logica.db.Conexion.openConexion();
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String sentencia = "update factura set paga=1, fecha='"+dateFormat.format(date)+"' where  id=" + o + ";";
            Conexion.getStamento().executeUpdate(sentencia);
//            inventario.logica.db.Conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateDatoEnvio(int o) {
        int i = (int) o;
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "update factura set enviada=1 where id=" + i + ";";
            Conexion.getStamento().executeUpdate(sentencia);
        } catch (SQLException ex) {
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateDato(Object o, int id) {
        try {
//            inventario.logica.db.Conexion.cerrarConexion();
//            inventario.logica.db.Conexion.openConexion();
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "update factura set estado=0 where  id=" + id + ";";
            Conexion.getStamento().executeUpdate(sentencia);
//            inventario.logica.db.Conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
