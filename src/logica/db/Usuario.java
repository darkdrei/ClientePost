/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.db;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dark
 */
public class Usuario extends BaseDao {

    @Override
    public void insertarDato(Object o) {
        logica.Usuario i = (logica.Usuario) o;
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            String sentencia = "insert into usuario (pass,nom) values('" + generadorMD5((String) i.getClave()) + "','" + i.getUser() + "');";
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('Eugenio',5,1000);";
            Conexion.getStamento().executeUpdate(sentencia);
        } catch (SQLException ex) {
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sateDato(ArrayList<Object> o, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateDato(Object o, int id) {

        logica.Usuario i = (logica.Usuario) o;
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "update usuario set nom='" + i.getUser() + "' ,pass='" + generadorMD5(i.getClave()) + "' where id=" + id + ";";
            Conexion.getStamento().executeUpdate(sentencia);
        } catch (SQLException ex) {
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteDato(Object o) {
        int i = (int) o;
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "update usuario set estado=0 where id=" + i + ";";
            Conexion.getStamento().executeQuery(sentencia);
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
            String sentencia = "select * from usuario ;";
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
    public Object getDato(int id) {
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select * from usuario ;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
//            try{
            while (r.next()) {
                return new logica.Usuario(r.getInt(1));
            }
            return null;
        } catch (SQLException ex) {

        }
        return null;
    }
    
    
    public Object getDato(Object o) throws NoSuchAlgorithmException {
        logica.Usuario u = (logica.Usuario)o;
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select tipo from usuario where nom='"+u.getUser()+"' and pass like '"+generadorMD5(u.getClave())+"';";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
//            try{
            while (r.next()) {
                return new logica.Usuario(r.getInt(1));
            }
            return null;
        } catch (SQLException ex) {

        }
        return null;
    }
    
    @Override
    public int getEndObjeto(int id) {
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select id from new_factura where mesa_id=" + id + " and estado=1 order by id desc limit 1;";
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
    public Object[][] getObjetoMatriz() {
        ArrayList<Object> o = getDatos();
        if (o.equals(null)) {
            return new String[1][3];
        }
        Object m[][] = new Object[o.size()][3];
        int i = 0;
        for (Object m1 : o) {
            logica.Usuario tm = (logica.Usuario) m1;
            m[i][0] = tm.getNombre();
            i++;
        }
        return m;
    }

    @Override
    public int getEndObjeto() {

        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select id from usuario order by id desc limit 1;";
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
            String sentencia = "select  count(nom)   from usuario where nom like '" + ((String) l.get(0)) + "' group by nom limit 1;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
            if (r.getInt(1) == 0) {
                o[0] = false;
            } else {
                o[0] = true;
                o[1] = "El nombre de usuario se encuentra registrado.";
            }
            return o;
        } catch (SQLException ex) {
            o[0] = false;
        }
        return o;
    }

    public boolean validarPass(logica.Usuario u) throws NoSuchAlgorithmException {
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            String sentencia = "select  count(nom)   from usuario where nom like '" + (u.getUser()) + "' and pass like '" + generadorMD5(u.getClave()) + "' and estado=1 group by nom limit 1;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
            if (r.getInt(1) == 0) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException ex) {
            return false;
        }

    }

    public String generadorMD5(String m) throws NoSuchAlgorithmException {
        String original = m;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(original.getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }
}
