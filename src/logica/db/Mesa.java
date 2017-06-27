/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Objeto;

/**
 *
 * @author CTesting
 */
public class Mesa extends BaseDao {

    @Override
    public void insertarDato(Object o) {
        logica.Mesa i = (logica.Mesa) o;
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            String sentencia = "insert into mesa (nombre,descripcion) values('" + i.getNombre() + "','" + i.getDescripcion() + "');";
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('Eugenio',5,1000);";
            Conexion.getStamento().executeUpdate(sentencia);
        } catch (SQLException ex) {
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sateDato(ArrayList<Object> o, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateDato(Object o, int id) {

        logica.Mesa i = (logica.Mesa) o;
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "update mesa set nombre='" + i.getNombre() + "' ,descripcion='" + i.getDescripcion() + "' where id=" + id + ";";
            Conexion.getStamento().executeUpdate(sentencia);
        } catch (SQLException ex) {
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteDato(Object o) {
        int i = (int) o;
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "update mesa set estado=0 where id=" + i + ";";
            Conexion.getStamento().executeUpdate(sentencia);
        } catch (SQLException ex) {
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Object> getDatos() {
        ArrayList<Object> o = new ArrayList<>();
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select * from mesa where estado=1;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
//            try{
            while (r.next()) {
                o.add(new logica.Mesa(r.getInt(1), r.getString(2), r.getString(3)));
            }
            return o;
        } catch (SQLException ex) {

        }
        return null;
    }
    
    @Override
    public Object[][] getObjetoMatriz() {
        ArrayList<Object> o = getDatos();
        if (o.equals(null)) {
            return new String[1][3];
        }
        Object m[][] = new Object[o.size()][3];
        int i = 0;
        for (Object m1 : o) {
            logica.Mesa tm = (logica.Mesa) m1;
            m[i][0] = new Integer(tm.getId());
            m[i][1] = tm.getNombre();
            m[i][2] = tm.getDescripcion();
            i++;
        }
        return m;
    }

    @Override
    public int getEndObjeto() {

        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select id from mesa order by id desc limit 1;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
//            try{
            while (r.next()) {
                return r.getInt(1);
            }
            return 0;
        } catch (SQLException ex) {

        }
        return 0;
    }

    @Override
    public Object[] validInsert(ArrayList<Object> l) {
        Object o[] = new Object[2];
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            String sentencia = "select  count(nombre)   from mesa where nombre like '" + ((String) l.get(0)) + "' and estado=1 group by nombre limit 1;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
            r.getInt(1);
            o[0] = true;
            o[1] = "El nombre se encuentra registrado.";
            return o;
        } catch (SQLException ex) {
            o[0] = false;
        }
        return o;
    }
}
