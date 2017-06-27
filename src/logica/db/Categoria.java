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
import javax.swing.JOptionPane;

/**
 *
 * @author dreicon
 */
public class Categoria extends BaseDao {
    @Override
    public void insertarDato(Object o) {
        logica.Categoria i = (logica.Categoria) o;
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            String sentencia = "insert into categoria (nombre,descripcion) values('" + i.getNombre() + "','" + i.getDescripcion() + "');";
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('Eugenio',5,1000);";
            Conexion.getStamento().executeUpdate(sentencia);
        } catch (SQLException ex) {
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void updateDato(Object o, int id) {

        logica.Categoria i = (logica.Categoria) o;
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "update categoria set nombre='" + i.getNombre() + "' ,descripcion='" + i.getDescripcion() + "' where id=" + id + ";";
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
            String sentencia = "update categoria set estado=0 where id=" + i + ";";
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
            String sentencia = "select * from categoria where estado = 1 ;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
//            try{
            while (r.next()) {
                o.add(new logica.Categoria(r.getInt(1), r.getString(2), r.getString(3)));
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
            logica.Categoria tm = (logica.Categoria) m1;
            m[i][0] = new Integer(tm.getId());
            m[i][1] = tm.getNombre();
            m[i][2] = tm.getDescripcion();
            i++;
        }
        return m;
    }
    
    @Override
    public Object[] validInsert(ArrayList<Object> l) {
        Object o[] = new Object[2];
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            String sentencia = "select  count(nombre)   from categoria where nombre like '" + ((String) l.get(0)) + "' and estado=1 group by nombre limit 1;";
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
    
    @Override
    public int size() {
        try {
            int rp=0;
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            String sentencia = "select  count(nombre) from categoria where estado=1;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
            return  r.getInt(1);
        } catch (SQLException ex) {
            return 0;
        }
    }
}
