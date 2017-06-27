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

/**
 *
 * @author dark
 */
public class ConteFac extends BaseDao {

    @Override
    public void insertarDato(Object o) {
        logica.ConteFac i = (logica.ConteFac) o;
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            String sentencia = "insert into conten_facnew (plato_id,cantidad,factura_id) "
                    + "values(" + i.getPlato_id() + "," + i.getCantidad() + "," + i.getFactura_id() + ");";
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
    public void deleteDato(Object o, int id) {
        String i = (String) o;
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            String sentencia = "update conten_facnew set estado =0 where factura_id=" + id + " and id in " + i + ";";
            Conexion.getStamento().executeUpdate(sentencia);
        } catch (SQLException ex) {
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateDato(Object o, int id) {

        logica.ConteFac i = (logica.ConteFac) o;
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "update conten_facnew set cantidad=" + i.getCantidad() + " where id=" + id + ";";
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
            String sentencia = "update conten_facnew set estado=0 where factura_id=" + i + ";";
            Conexion.getStamento().executeUpdate(sentencia);
        } catch (SQLException ex) {
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sateDato(int id) {
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "update conten_facnew set estado=2 where factura_id=" + id + ";";
            Conexion.getStamento().executeUpdate(sentencia);
        } catch (SQLException ex) {
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteAll(int id) {
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "update conten_facnew set estado=2 where factura_id=" + id + " and estado=1;";
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
            String sentencia = "select * from conten_facnew where estado=1;";
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
    public ArrayList<Object> getDatos(int id) {
        ArrayList<Object> o = new ArrayList<>();
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select c.id,p.nombre,p.precio,c.cantidad,p.precio*c.cantidad as total from conten_facnew as c  inner join plato as p on (c.plato_id=p.id and c.estado=1 and p.estado=1 and c.factura_id=" + id + ");";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
//            try{
            while (r.next()) {
                logica.DescripcionFactura d = new logica.DescripcionFactura(r.getInt(1), r.getString(2), r.getFloat(3), r.getInt(4), r.getFloat(5));
                o.add(d);
            }
            return o;
        } catch (SQLException ex) {
            System.err.println("%%%%%%%%%%%%%se exploto");
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
    public Object[][] getObjetoMatriz(int id) {
        ArrayList<Object> o = getDatos(id);
        if (o.equals(null)) {
            return new String[1][5];
        }
        Object m[][] = new Object[o.size()][5];
        int i = 0;
        for (Object m1 : o) {
            logica.DescripcionFactura tm = (logica.DescripcionFactura) m1;
            m[i][0] = tm.getId();
            m[i][1] = tm.getPlato();
            m[i][2] = tm.getCantidad();
            m[i][3] = tm.getPrecio();
            m[i][4] = tm.getTotal();
            i++;
        }
        return m;
    }

    @Override
    public float getTotal(int id_fact) {
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select sum(p.precio*c.cantidad) as total "
                    + "from conten_facnew as c  inner join plato as p on "
                    + "(c.plato_id=p.id and c.estado=1 and p.estado=1 and c.factura_id=" + id_fact + ");";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
//            try{
            while (r.next()) {
                return r.getFloat(1);
            }
            return 0;
        } catch (SQLException ex) {

        }
        return 0;
    }
}
