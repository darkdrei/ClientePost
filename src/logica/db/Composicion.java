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
import logica.TemporalComposicion;

/**
 *
 * @author dreicon
 */
public class Composicion extends BaseDao {

    @Override
    public void insertarDato(Object o) {
        Vector<Object> info = (Vector<Object>) o;
        int id_plato = (int) info.get(0);
        ArrayList<logica.TemporalComposicion> i = (ArrayList<logica.TemporalComposicion>) info.get(1);
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            for (TemporalComposicion i1 : i) {
                String sentencia = "insert into comp_plato (id_plato,id_ingrediente,gramo) "
                        + "values(" + id_plato + "," + i1.getId() + "," + i1.getCantidad() + ");";
                //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('Eugenio',5,1000);";
                Conexion.getStamento().executeUpdate(sentencia);
            }

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
        ArrayList<TemporalComposicion> i = (ArrayList<TemporalComposicion>) o;
        ArrayList<Object> tem = getDatos(id);//id de los q tenia
        Vector<Integer> intervalo = intervaloIngredientesPlatoSeleccionados(tem);//id de los q llegan
        Vector<Integer> eliminar = new Vector<>();
        ArrayList<TemporalComposicion> contenedor = new ArrayList<>();
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            for (int k = 0; k < i.size(); k++) {
                TemporalComposicion intervalo1 = i.get(k);
                if (intervalo.contains(((TemporalComposicion) intervalo1).getId())) {
                    String sentencia = "update comp_plato set gramo=" + ((TemporalComposicion) intervalo1).getCantidad() + " where id_plato=" + id + " and id_ingrediente=" + ((TemporalComposicion) intervalo1).getId() + " and estado=1;";
                    Conexion.getStamento().executeUpdate(sentencia);
                    eliminar.add(k);
                    intervalo.remove((Integer) intervalo1.getId());
                }else{
                    contenedor.add(intervalo1);
                }
            }
            for (Object eliminar1 : tem) {
                
                TemporalComposicion tr = (TemporalComposicion) eliminar1;
                if (intervalo.contains((Integer) tr.getId())) {
                    String sentencia = "update comp_plato set estado=0 where id_plato=" + id + " and id_ingrediente=" + tr.getId() + " and estado=1;";
                    Conexion.getStamento().executeUpdate(sentencia);
                }
            }
            for (Integer eliminar1 : intervalo) {
                int pos = -1;
                uno:
                for (int j = 0; j < tem.size(); j++) {
                    if (((TemporalComposicion) tem.get(j)).getId() == eliminar1) {
                        pos = j;
                        break uno;
                    }
                }
                if (pos != -1) {
                    tem.remove(pos);
                }
            }
            for (Integer eliminar1 : eliminar) {
                i.remove(eliminar1);
            }
            Vector<Object> obj = new Vector<>();
            obj.add(id);
            obj.add(contenedor);
            this.insertarDato(obj);
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
            String sentencia = "update comp_plato set estado=0 where id_plato=" + id + ";";
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
            String sentencia = "select  distinct p.id,p.nombre from (select * from comp_plato where estado=1 ) as cp inner join plato as p on (cp.id_plato=p.id and p.estado=1 and cp.estado=1);";
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
    public ArrayList<Object> getDatos(int id) {
        ArrayList<Object> o = new ArrayList<>();
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select i.id,i.nombre,cp.gramo from (select * from comp_plato where id_plato=" + id + " and estado=1) as cp inner  join ingrediente as i on(i.estado=1 and i.id=cp.id_ingrediente);";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
//            try{
            while (r.next()) {
                o.add(new TemporalComposicion(r.getInt(1), r.getString(2), r.getFloat(3)));
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
            return new String[1][2];
        }
        Object m[][] = new Object[o.size()][2];
        int i = 0;
        for (Object m1 : o) {
            logica.Plato tm = (logica.Plato) m1;
            m[i][0] = new Integer(tm.getId());
            m[i][1] = tm.getNombre();
            i++;
        }
        return m;
    }

    @Override
    public Object[][] getObjetoMatriz(int id) {
        ArrayList<Object> o = getDatos(id);
        if (o.equals(null)) {
            return new String[1][3];
        }
        Object m[][] = new Object[o.size()][3];
        int i = 0;
        for (Object m1 : o) {
            logica.TemporalComposicion tm = (logica.TemporalComposicion) m1;
            m[i][0] = tm.getId();
            m[i][1] = tm.getNombre();
            m[i][2] = tm.getCantidad();
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

    public Vector<Integer> intervaloIngredientesPlato(ArrayList<TemporalComposicion> l) {
        Vector<Integer> res = new Vector<>();
        for (TemporalComposicion l1 : l) {
            res.add(l1.getId());
        }
        return res;
    }

    public Vector<Integer> intervaloIngredientesPlatoSeleccionados(ArrayList<Object> l) {
        Vector<Integer> res = new Vector<>();
        for (Object l1 : l) {
            res.add(((TemporalComposicion) l1).getId());
        }
        return res;
    }
}
