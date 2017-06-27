/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dark
 */
public class Trabajador extends BaseDao {

    @Override
    public void insertarDato(Object o) {
        logica.Trabajador i = (logica.Trabajador) o;
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            Usuario u = new Usuario();
            u.insertarDato(new logica.Usuario(i.getUser(),i.getClave()));
            String sentencia = "insert into trabajador "
                    + "(cedula,nombre,apellidos,correo,telefono,user_id,dir) "
                    + "values"
                    + "('" + i.getIdentificacion() + "','" + i.getNombre() + "','" + i.getApellidos() + "',"
                    + "'" + i.getCorreo() + "','" + i.getTel() + "',"+u.getEndObjeto()+",'"+i.getDireccion()+"');";
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
        Vector<Object> v = (Vector<Object>)o; 
        logica.Trabajador i = (logica.Trabajador) v.get(0);
        try {
            // cedula,nombre,apellidos,correo,telefono
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "update trabajador set nombre='" + i.getNombre() + "' ,"
                    + "cedula='" + i.getIdentificacion() + "',apellidos='" + i.getApellidos() + "',"
                    + "correo='" + i.getCorreo() + "',telefono='" + i.getTel() + "',dir='"+i.getDireccion()+"', where id=" + id + ";";
            sentencia+="update usuario set nom='"+i.getUser()+"' where id ="+((int)v.get(1))+";";
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
            String sentencia = "update usuario set estado=0 where id=  (select user_id from trabajador where id = "+i+" limit 1);";
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
            String sentencia = "select t.id,u.nom,t.cedula,t.nombre,t.apellidos,t.cedula from trabajador as t inner join usuario as u on (t.user_id=u.id) where u.estado=1";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
//            try{
            while (r.next()) {
                o.add(new logica.Trabajador(r.getInt(1), r.getString(2), r.getString(3),r.getString(4),r.getString(5)));
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
            logica.Trabajador tm = (logica.Trabajador) m1;
            m[i][0] = new Integer(tm.getId_trabajador());
            m[i][2] = tm.getNombre();
            m[i][3] = tm.getApellidos() ;
            m[i][1] = tm.getIdentificacion() ;
            m[i][4] = tm.getUser() ;
            i++;
        }
        return m;
    }

    @Override
    public int getEndObjeto() {

        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select id from trabajador order by id desc limit 1;";
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
            String sentencia = "select  count(nombre)   from trabajador where cedula like '" + ((String) l.get(0)) + "' group by nombre limit 1;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
            if (r.getInt(1) == 0) {
                o[0]=false;
            } else {
                o[0] = true;
                o[1] = "La cedula se encuentra registrada.";
            }
            return o;
        } catch (SQLException ex) {
            o[0] = false;
        }
        return o;
    }
    
    @Override
    public Object getDato(int id) {
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select t.id,u.id,u.nom,t.nombre,t.apellidos,t.cedula,t.telefono,t.correo,t.dir from trabajador as t inner join usuario as u on(t.user_id=u.id and u.estado=1) where t.id="+id+";";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
//            try{
            while (r.next()) {
                return (new logica.Trabajador(r.getInt(1),r.getInt(2),r.getString(3),r.getString(4),r.getString(5),r.getString(6),r.getString(9),r.getString(7),r.getString(8)));
            }
        } catch (SQLException ex) {

        }
        return null;
    }
}
