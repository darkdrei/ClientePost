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
import logica.db.DaoFactura;

/**
 *
 * @author eugenio
 */
public class Descripcion extends Dao {

    @Override
    public void insertarDato(Object o) {
        inventario.logica.Descripcion r = (inventario.logica.Descripcion) o;
        try {
            inventario.logica.db.Conexion.setStamento(inventario.logica.db.Conexion.getConnecion().createStatement());
            String sentencia = "insert into descripcion (id_fact,articulo_id,cantidad) values (" + r.getFactura_id() + "," + r.getArticulo_id() + "," + r.getCantidad() + ");";
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('Eugenio',5,1000);";
            inventario.logica.db.Conexion.getStamento().executeUpdate(sentencia);
        } catch (SQLException ex) {
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Object> getDatos(int id) {
        ArrayList<Object> o = new ArrayList<>();
        try {
            inventario.logica.db.Conexion.setStamento(inventario.logica.db.Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select d.id,case when a.codigo is  null or a.codigo='null' then ' ' else a.codigo end as codigo,a.nombre,d.cantidad,a.precio, d.cantidad*a.precio from factura as f inner join descripcion as d on (f.id=d.id_fact) inner join articulo as a on (a.id=d.articulo_id) where f.id=" + id + ";";
            ResultSet r = inventario.logica.db.Conexion.getStamento().executeQuery(sentencia);
//            try{
            while (r.next()) {
                o.add(new inventario.logica.Descripcion(r.getInt(1), r.getString(2), r.getString(3), r.getFloat(4), r.getFloat(5), r.getFloat(6)));
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
            String sentencia = "select  id_fact, articulo_id, cantidad from factura as f inner join descripcion as d on (f.id=d.id_fact) inner join articulo as a on (a.id=d.articulo_id) where f.id=" + id[0] + ";";
            ResultSet r = inventario.logica.db.Conexion.getStamento().executeQuery(sentencia);
            while (r.next()) {
                o.add(new inventario.logica.Descripcion(r.getInt(1),r.getInt(2),r.getFloat(3)));
            }
            return o;
        } catch (SQLException ex) {

        }
        return null;
    }

    @Override
    public Object getDato(int id) {
        System.out.println("este es el id del precio------->-----> "+id);
        try {
            inventario.logica.db.Conexion.setStamento(inventario.logica.db.Conexion.getConnecion().createStatement());
            String sentencia = "select  sum(d.cantidad*a.precio) as total from factura as f inner join descripcion as d on (f.id=d.id_fact) inner join articulo as a on (a.id=d.articulo_id) where f.id=" + id + ";";
            ResultSet r = inventario.logica.db.Conexion.getStamento().executeQuery(sentencia);
            System.out.println(r.getFloat(1) + "  COLUMAN DE LA VALIDCION INSERT");
            return r.getFloat(1) ;

        } catch (SQLException ex) {
            System.err.println("en el valid de empleado 1");
        }
        return 0;
    }

    @Override
    public Object[][] getObjetoMatriz(int id) {
        ArrayList<Object> o = this.getDatos(id);
        if (o.equals(null)) {
            return new String[1][7];
        }
        Object m[][] = new Object[o.size()][7];
        int i = 0;
        for (Object m1 : o) {
            inventario.logica.Descripcion tm = (inventario.logica.Descripcion) m1;
            m[i][0] = tm.getId();
            m[i][1] = tm.getCodigo();
            m[i][2] = tm.getNombre();
            m[i][3] = tm.getCantidad();
            m[i][4] = tm.getValor_unitario();
            m[i][5] = tm.getTotal();
            m[i][6] = false;
            i++;
        }
        return m;
    }
    @Override
    public void deleteDato(Object o) {
        int i = (int) o;
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "delete from descripcion where  id=" + i + ";";
            Conexion.getStamento().executeUpdate(sentencia);
        } catch (SQLException ex) {
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void updateDato(Object o,int id){
        float valor = (float)o;
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "update descripcion set cantidad="+valor+" where  id=" + id + ";";
            Conexion.getStamento().executeUpdate(sentencia);
        } catch (SQLException ex) {
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
