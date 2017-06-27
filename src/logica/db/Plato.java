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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CTesting
 */
public class Plato extends BaseDao {

    @Override
    public void insertarDato(Object o) {
        logica.Plato i = (logica.Plato) o;
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            String sentencia = "insert into plato (nombre,descripcion,tipo_id,precio) values('" + i.getNombre() + "','" + i.getDescripcion() + "'," + i.getTipo_id() + "," + i.getPrecio() + ");";
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

        logica.Plato i = (logica.Plato) o;
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "update plato set nombre='" + i.getNombre() + "' ,descripcion='" + i.getDescripcion() + "', precio =" + i.getPrecio() + ",tipo_id="+i.getTipo_id()+" where id=" + id + ";";
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
            String sentencia = "update plato set estado=0 where id=" + i + ";";
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
            String sentencia = "select p.id,p.nombre from plato as p inner join tipo as t on (p.tipo_id=t.id and p.estado=1 and t.estado=1 and t.id="+id+") ;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
//            try{
            while (r.next()) {
                o.add(new logica.Plato(r.getInt(1), r.getString(2)));
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
            String sentencia = "select  p.id, p.nombre,p.descripcion,t.nombre,p.precio from  plato as p inner join tipo as t on (p.tipo_id=t.id and p.estado=1 and t.estado=1) ;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
//            try{
            while (r.next()) {
                o.add(new logica.Plato(r.getInt(1), r.getString(2), r.getString(3), r.getString(4), r.getFloat(5)));
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
            return new String[1][5];
        }
        Object m[][] = new Object[o.size()][5];
        int i = 0;
        for (Object m1 : o) {
            logica.Plato tm = (logica.Plato) m1;
            m[i][0] = new Integer(tm.getId());
            m[i][1] = tm.getNombre();
            m[i][2] = tm.getDescripcion();
            m[i][3] = tm.getTipo_nom();
            m[i][4] = tm.getPrecio();
            i++;
        }
        return m;
    }

    @Override
    public int getEndObjeto() {

        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select id from plato order by id desc limit 1;";
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
            String sentencia = "select  count(nombre)   from plato where nombre like '" + ((String) l.get(0)) + "' group by nombre limit 1;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
            if (r.getInt(1) == 0) {
                o[0] = false;
            } else {
                o[0] = true;
                o[1] = "El nombre se encuentra registrado.";
            }
            return o;
        } catch (SQLException ex) {
            o[0] = false;
        }
        return o;
    }
    
    @Override
    public Vector<Integer> getIds() {
        Vector<Integer> o = new Vector<>();
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select p.id from plato  as p left join  comp_plato as cp on (p.estado=1 and cp.estado=1 and p.id=cp.id_plato) where cp.id is null order by p.nombre;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
            while (r.next()) {
                o.add(r.getInt(1));
            }
            return o;
        } catch (SQLException ex) {

        }
        return o;
    }

    @Override
    public String[] getNombres() {
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select p.nombre from plato  as p left join  comp_plato as cp on (p.estado=1 and cp.estado=1 and p.id=cp.id_plato) where cp.id is null order by p.nombre;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
            ArrayList<String> l = new ArrayList<>();
            l.add(new String("-- Seleccione --"));
            while (r.next()) {
                l.add(r.getString(1));
            }
            String tr[] = new String[l.size()];
            return l.toArray(tr);
        } catch (SQLException ex) {

        }
        return (new String[]{});
    }
    
    
    @Override
    public Object getDato(int id) {
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select id,tipo_id,nombre,descripcion,precio from plato where id="+id+" and estado=1 limit 1";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
//            try{
            while (r.next()) {
                return (new logica.Plato(r.getInt(1),r.getString(3),r.getString(4),r.getInt(2),r.getFloat(5)));
            }
        } catch (SQLException ex) {

        }
        return null;
    }
}
