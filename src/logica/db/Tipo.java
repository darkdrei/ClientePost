/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.db;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dreicon
 */
public class Tipo extends BaseDao {

    @Override
    public void insertarDato(Object o) {
        logica.Tipo i = (logica.Tipo) o;
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            String sentencia = "insert into tipo (categoria_id,nombre,descripcion,rojo,verde,azul) "
                    + "values(" + i.getCategoria_id() + ",'" + i.getNombre() + "','" + i.getDescripcion() + "'," + i.getColo().getRed() + "," + i.getColo().getGreen() + "," + i.getColo().getBlue() + ");";
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

        logica.Tipo i = (logica.Tipo) o;
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "update tipo set nombre='" + i.getNombre() + "' ,descripcion='" + i.getDescripcion() + "'"
                    + ",categoria_id="+i.getCategoria_id()+", azul="+i.getColo().getBlue()+","
                    + "rojo="+i.getColo().getRed()+",verde="+i.getColo().getGreen()+" where id=" + id + ";";
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
            String sentencia = "update tipo set estado=0 where id=" + i + ";";
            Conexion.getStamento().executeUpdate(sentencia);
        } catch (SQLException ex) {
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public ArrayList<Object> getDatos(int id) {
        ArrayList<Object> o = new ArrayList<>();
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select t.id,t.nombre from tipo as t inner join  categoria as c on (t.categoria_id=c.id and t.estado=1 and c.estado=1 and c.id="+id+")  ;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
//            try{
            while (r.next()) {
                o.add(new logica.Tipo(r.getInt(1), r.getString(2)));
            }
            return o;
        } catch (SQLException ex) {

        }
        return null;
    }
    

    @Override
    public ArrayList<Object> getDatos() {
        ArrayList<Object> o = new ArrayList<>();
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select t.id,c.nombre as nom_cat,t.nombre as nom_tipo,t.descripcion,t.rojo,t.azul,t.verde from tipo as t inner join categoria as c on(t.categoria_id=c.id and c.estado=1 and t.estado=1);";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
//            try{
            while (r.next()) {
                o.add(new logica.Tipo(r.getInt(1), r.getString(2),r.getString(3),r.getString(4),new Color(r.getInt(5),r.getInt(6),r.getInt(7))));
            }
            return o;
        } catch (SQLException ex) {

        }
        return null;
    }
    
    @Override
    public Object getDato(int id) {
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select id,categoria_id,nombre,descripcion,azul,rojo,verde from tipo where id="+id+" and estado=1 limit 1;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
//            try{
            while (r.next()) {
                return (new logica.Tipo(new Color(r.getInt(5),r.getInt(6),r.getInt(7)),r.getInt(1),r.getInt(2),r.getString(3),r.getString(4)));
            }
        } catch (SQLException ex) {

        }
        return null;
    }

    @Override
    public Object[][] getObjetoMatriz() {
        ArrayList<Object> o = getDatos();
        if (o.equals(null)) {
            return new String[1][4];
        }
        Object m[][] = new Object[o.size()][4];
        int i = 0;
        for (Object m1 : o) {
            logica.Tipo tm = (logica.Tipo) m1;
            m[i][0] = new Integer(tm.getId());
            m[i][1] = tm.getCategoria_nom();
            m[i][2] = tm.getNombre();
            m[i][3] = tm.getDescripcion();
            i++;
        }
        return m;
    }

    @Override
    public int getEndObjeto() {

        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select id from mesa tipo by id desc limit 1;";
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
            String sentencia = "select  count(nombre)   from tipo "
                    + "where rojo = " + ((int) l.get(0)) + " and verde = " + ((int) l.get(1)) + " and azul = " + ((int) l.get(2)) + " limit 1;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
            if (r.getInt(1) == 0) {
                o[0] = false;;
            } else {
                o[0] = true;
                o[1] = "El Color se encuentra registrado.";
            }
            return o;
        } catch (SQLException ex) {
            o[0] = false;
        }
        return o;
    }

    @Override
    public Object[] validInsert(ArrayList<Object> l, int n) {
        Object o[] = new Object[2];
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            String sentencia = "select  count(nombre) from tipo where nombre like '" + ((String) l.get(0)) + "' group by nombre limit 1;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
            r.getInt(0);
            o[0] = true;
            o[1] = "El nombre se encuentra registrado.";
            return o;
        } catch (SQLException ex) {
            o[0] = false;
        }
        return o;
    }
}
