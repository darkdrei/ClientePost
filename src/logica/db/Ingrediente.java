/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.db;

/**
 *
 * @author dreicon
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Ingrediente extends BaseDao {

    @Override
    public void insertarDato(Object o) {
        logica.Ingrediente i = (logica.Ingrediente) o;
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            String sentencia = "insert into ingrediente (nombre,descripcion,categoria) "
                    + "values('" + i.getNombre() + "','" + i.getDescripcion() + "',"+i.getCategoria()+");";
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
            String sentencia = "update ingrediente set estado =0 where id=" + id + ";";
            Conexion.getStamento().executeUpdate(sentencia);
        } catch (SQLException ex) {
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateDato(Object o, int id) {

        logica.Ingrediente i = (logica.Ingrediente) o;
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "update ingrediente set existencias=" + i.getExistencias() + " where id=" + id + ";";
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
            String sentencia = "update ingrediente set estado=0 where id=" + i + ";";
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
    public ArrayList<Object> getDatos() {
        ArrayList<Object> o = new ArrayList<>();
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select id,nombre,descripcion from ingrediente where estado=1;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
//            try{
            while (r.next()) {
                o.add(new logica.Ingrediente(r.getInt(1), r.getString(2), r.getString(3)));
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
            logica.Ingrediente tm = (logica.Ingrediente) m1;
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
            String sentencia = "select  count(nombre)   from ingrediente where nombre like '" + ((String) l.get(0)) + "' group by nombre limit 1;";
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
            String sentencia = "select id from ingrediente where estado=1  order by nombre ;";
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
            String sentencia = "select nombre from ingrediente where estado=1 order by nombre ;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
            ArrayList<String> l = new ArrayList<>();
            l.add(new String("-- Seleccione --"));
            while (r.next()) {
                l.add(new String(r.getString(1)));
            }
            String tr[] = new String[l.size()];
           
            return l.toArray(tr);
        } catch (SQLException ex) {

        }
        return (new String[]{});
    }
    
    public String[] getNombresIngredientes(String b) {
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select nombre||\" -- \"||existencias||case when categoria=0 then \" Kg\" else \" Unids\" end from ingrediente where  nombre like '%"+b+"%'  or existencias like '%"+b+"%' and estado=1 order by nombre ;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
            ArrayList<String> l = new ArrayList<>();
            while (r.next()) {
                l.add(new String(r.getString(1)));
            }
            String tr[] = new String[l.size()];
           
            return l.toArray(tr);
        } catch (SQLException ex) {

        }
        return (new String[]{});
    }
    
    public Vector<Integer> getIdsIngredientes(String b) {
        Vector<Integer> o = new Vector<>();
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select id from ingrediente where nombre like '%"+b+"%'  or existencias like '%"+b+"%' and  estado=1 order by nombre ;";
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
    public logica.Ingrediente getDato(int b) {
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select id ,nombre,existencias,categoria from ingrediente where estado=1 and id = "+b+" ;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
            while (r.next()) {
                return new logica.Ingrediente(r.getInt(4),r.getFloat(3),r.getInt(1),r.getString(2));
            }
        } catch (SQLException ex) {
            System.err.println("explote en el catch get Dato");
            return null;
        }
        return null;
    }
    
    /******/
    public ArrayList<logica.Ingrediente> getDatosInventarioIngredientes(String b) {
        ArrayList<logica.Ingrediente> o = new ArrayList<>();
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select nombre,existencias||case when categoria=0 then \"  Kg\"  else \"  Unidas\" end from ingrediente where nombre like '%"+b+"%'  or existencias like '%"+b+"%' and estado=1 order by nombre asc;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
//            try{
            while (r.next()) {
                o.add(new logica.Ingrediente(r.getString(1),r.getString(2)));
            }
            return o;
        } catch (SQLException ex) {
            System.err.println("%%%%%%%%%%%%%se exploto");
        }
        return null;
    }
    public Object[][] getObjetoMatrizInventarioIngredientes(String b) {
        ArrayList<logica.Ingrediente> o = getDatosInventarioIngredientes(b);
        if (o.equals(null)) {
            return new String[1][2];
        }
        Object m[][] = new Object[o.size()][2];
        int i = 0;
        for (logica.Ingrediente tm : o) {
            m[i][0] = tm.getNombre();
            m[i][1] = tm.getDescripcion();
            i++;
        }
        return m;
    }
}
