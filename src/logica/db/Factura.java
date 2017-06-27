/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.  secreto del orgasmo femenino karla fuentes
 */
package logica.db;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dark
 */
public class Factura extends BaseDao {

    @Override
    public void insertarDato(Object o) {
        logica.Factura i = (logica.Factura) o;
        try {
            Calendar calendar = new GregorianCalendar();
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            String sentencia = "insert into new_factura (dia,mes,anio,mesa_id) "
                    + "values(" + calendar.get(Calendar.DAY_OF_MONTH) + ","
                    + "" + (calendar.get(Calendar.MONTH) + 1) + ","
                    + "" + calendar.get(Calendar.YEAR) + "," + i.getMesa_id() + ");";
            Conexion.getStamento().executeUpdate(sentencia);
        } catch (SQLException ex) {
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateDato(Object o, int id) {

        logica.Factura i = (logica.Factura) o;
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "update new_factura set opcion=" + i.getTipo() + ",add_p=" + i.getTotal() + " where id=" + id + ";";
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
    public void deleteDato(Object o) {
        int i = (int) o;
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "update new_factura set estado=0 where id=" + i + ";";
            Conexion.getStamento().executeUpdate(sentencia);
        } catch (SQLException ex) {
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateDato(Object o){
        int i = (int) o;
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "update new_factura set enviada=1 where id=" + i + ";";
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
            String sentencia = "select * from new_factura where mesa_id=" + id + " and estado=1 order by id desc limit 1;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
//            try{
            while (r.next()) {
                o.add(new logica.Factura(r.getInt(1)));
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
                o.add(new logica.Tipo(r.getInt(1), r.getString(2), r.getString(3), r.getString(4), new Color(r.getInt(5), r.getInt(6), r.getInt(7))));
            }
            return o;
        } catch (SQLException ex) {

        }
        return null;
    }

    @Override
    public void sateDato(int id) {
        Calendar calendar = new GregorianCalendar();
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "update new_factura set estado=2,anio="+calendar.get(Calendar.YEAR)+",mes="+(calendar.get(Calendar.MONTH) + 1)+",dia="+calendar.get(Calendar.DAY_OF_MONTH)+" where id=" + id + ";";
            Conexion.getStamento().executeUpdate(sentencia);
        } catch (SQLException ex) {
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public float getTotal(int id_fac) {
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = " select  case when opcion=1 then 1.1 *(select sum(p.precio*c.cantidad) as total from conten_facnew as c  "
                    + "inner join plato as p on (c.plato_id=p.id and c.estado=1 and p.estado=1 and c.factura_id= f.f.id))"
                    + "else add_p+(select sum(p.precio*c.cantidad) as total "
                    + "from conten_facnew as c  "
                    + "inner join plato as p on (c.plato_id=p.id and c.estado=1 and p.estado=1 and c.factura_id= f.f.id)) end as "
                    + "total from new_factura as f where f.id=" + id_fac + " and   estado in (1,2) order by id desc;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
            while (r.next()) {
                return r.getFloat(1);
            }

        } catch (SQLException ex) {
            System.err.println("////Explote total factura ");
        }
        return 0;
    }

    @Override
    public Object getDato(int id) {
        Object o = new Object();
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = " select opcion,add_p from new_factura  where id=" + id + ";";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
            while (r.next()) {
                return new logica.Factura(r.getInt(1), r.getFloat(2));
            }

            return o;
        } catch (SQLException ex) {

        }
        return o;
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
            logica.Tipo tm = (logica.Tipo) m1;
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
    }@Override
    public Object[][] getObjetoMatriz(int id_fact) {
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            String sentencia = "select id,(select sum(p.precio*c.cantidad) as total from conten_facnew as c  inner join plato as p on "
                    + "(c.plato_id=p.id and c.estado=1 and p.estado=1 and c.factura_id= fact.id)),"
                    + "dia||'\\'||mes||'\\'||anio as fecha,(select opc_pag.nombre  from opcion_pago as opc_pag where opc_pag.id=fact.opcion  limit 1)"
                    + " as nom_opc,( case when fact.opcion=1 then  "
                    + "0.1 *(select sum(p.precio*c.cantidad) as total from conten_facnew as c  inner join plato as p on "
                    + "(c.plato_id=p.id and c.estado=1 and p.estado=1 and c.factura_id= fact.id))  when fact.opcion = 2 then 0 else fact.add_p end  )"
                    + " as resp_opc,(select  case when opcion=1 then 1.1 *(select sum(p.precio*c.cantidad) as total from conten_facnew as c  "
                    + "inner join plato as p on (c.plato_id=p.id and c.estado=1 and p.estado=1 and c.factura_id= f.id)) "
                    + "else add_p+(select sum(p.precio*c.cantidad) as total from conten_facnew as c "
                    + "inner join plato as p on (c.plato_id=p.id and c.estado=1 and p.estado=1 and c.factura_id= f.id)) "
                    + "end as  total from new_factura as f where f.id=fact.id and estado=1 order by id desc limit 1)  "
                    + "as total  from new_factura as fact where fact.id="+id_fact+";";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
            return new Object[][]{{r.getInt(1), r.getFloat(2), r.getString(3), r.getString(4), r.getFloat(5), r.getFloat(6)}};
        } catch (SQLException ex) {
            System.err.println("Se exploto esta vaina");
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public Object[] validInsert(ArrayList<Object> l) {
        Object o[] = new Object[2];
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            String sentencia = " select count(id) from new_factura where estado=1 and mesa_id=" + l.get(0) + " order by id desc limit 1";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
            if (r.getInt(1) == 0) {
                o[0] = false;;
            } else {
                o[0] = true;
                o[1] = "Existe factura.";
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
            String sentencia = "select  count(nombre)   from tipo where nombre like '" + ((String) l.get(0)) + "' group by nombre limit 1;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
            o[0] = true;
            o[1] = "El nombre se encuentra registrado.";
            return o;
        } catch (SQLException ex) {
            o[0] = false;
        }
        return o;
    }
    
    public String[] getListaFactura(int id,int d,int m,int a){
        try {
            String res ="";
            String res1=(id==0?"":"id="+id+" ");
            String res2=(a==0||d==0||a==0?"":" dia="+d+" and mes="+m+" and anio="+a);
            res= res1.length() > 0?res2.length() > 0 ?" where "+res1+" and "+res2:" where "+res1:res2.length() > 0?"where "+res2:"";
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select id,dia||\"/\"||mes||\"/\"||anio||\" -- Numero de factura = \"||id from  new_factura "+res+" order by anio desc,mes desc,dia desc, id desc;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
            ArrayList<String> l = new ArrayList<>();
            while (r.next()) {
                l.add(r.getString(2));
            }
            String tr[] = new String[l.size()];
            return l.toArray(tr);
        } catch (SQLException ex) {

        }
        return (new String[]{});
    }
    
    public Vector<Integer> getIdFactura(int id,int d,int m,int a){
        Vector<Integer> o = new Vector<>();
        try {
            String res ="";
            String res1=(id==0?"":"id="+id+" ");
            String res2=(a==0||d==0||a==0?"":" dia="+d+" and mes="+m+" and anio="+a);
            res= res1.length() > 0?res2.length() > 0 ?" where "+res1+" and "+res2:" where "+res1:res2.length() > 0?"where "+res2:"";
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select id,dia||\"/\"||mes||\"/\"||anio from  new_factura "+res+" order by anio desc,mes desc,dia desc, id desc;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
            while (r.next()) {
                o.add(r.getInt(1));
            }
            return o;
        } catch (SQLException ex) {

        }
        return o;
    }
}
