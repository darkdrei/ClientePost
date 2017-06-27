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
import static origami2.Origami2.generadorMD5;

/**
 *
 * @author dreicon
 */
public class Configuracion extends BaseDao {

    @Override
    public void updateDato(Object o, int id) {

        logica.Configuracion i = (logica.Configuracion) o;
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = i.getPass().length() > 0 ? "update usuario set nom='" + i.getNom() + "' ,pass='" + generadorMD5(i.getPass()) + "' where id=( select id_user from configuracion where id=1  limit 1);" : "update usuario set nom='" + i.getNom() + "' where id=  (select id_user from configuracion where id=1 limit 1);";
            sentencia += ("update configuracion set uni=" + i.getUnidades() + ",ing=" + i.getIngrediente() + ",correo='"+i.getCorreo()+"' where id=" + id + ";");
            Conexion.getStamento().executeUpdate(sentencia);
        } catch (SQLException ex) {
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public logica.Configuracion get_sDato() {
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = " select c.id,u.nom,c.correo,c.uni,c.ing from configuracion as c inner join usuario as u on(c.id_user=u.id) limit 1;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
//            try{
            while (r.next()) {
                return (new logica.Configuracion(r.getString(2), r.getInt(1), r.getString(3), r.getInt(4), r.getFloat(5)));
            }
        } catch (SQLException ex) {

        }
        return null;
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

    /**
     *
     * @return
     */
    public ArrayList<logica.Ingrediente> getCampos(int uni, float ing) {
        ArrayList<logica.Ingrediente> i = new ArrayList<>();
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select nombre,existencias||case when categoria=0 then \"  Kg\"  else \"  Unidas\"end from ingrediente where estado=1  and  existencias <= case when categoria= 1 then 5 else 500 end order by categoria desc,nombre asc;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
//            try{
            while (r.next()) {
                i.add(new logica.Ingrediente(r.getString(1), r.getString(2)));
            }
            return i;
        } catch (SQLException ex) {

        }
        return null;
    }

}
