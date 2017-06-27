/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.logica.db;

import inventario.logica.Articulo;
import inventario.logica.db.Postgres;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logica.db.Conexion;

/**
 *
 * @author eugenio
 */
public class ReloadServer {

    public ReloadServer() {
    }

    public void actualizarEmpleados() {
        ArrayList<Object> o = new ArrayList<>();
        try {
            Postgres.setStamento(Postgres.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select a.id,\n"
                    + "		a.first_name as nombre,\n"
                    + "        a.last_name,a.username,\n"
                    + "        c.autenticate,c.negocio_id from empleados_cajero as c inner join auth_user as a on (a.id=c.persona_ptr_id and a.is_active=true)";
            ResultSet r = Postgres.getStamento().executeQuery(sentencia);
//            try{
            //BLOQUE DE CONEXION DEL SQLITE
            Empleado tem_empleado = new Empleado();
            //****************************
            while (r.next()) {
                //o.add(new logica.Trabajador(r.getInt(1), r.getString(2), r.getString(3),r.getString(4),r.getString(5)));
                System.out.println(" " + r.getInt(1) + " " + r.getString(2) + " " + r.getString(3) + " " + r.getString(4) + " " + r.getString(5) + " " + r.getInt(1));
                inventario.logica.Empleado rec_empleado = new inventario.logica.Empleado(r.getInt(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5), r.getInt(6));
                if (!(boolean) tem_empleado.validInsert(rec_empleado.getId())) {
                    tem_empleado.insertarDato(rec_empleado);
                }
            }
        } catch (SQLException ex) {

        }

    }

    public void actualizarArticulos() {
        
        ArrayList<Object> o = new ArrayList<>();
        try {
            Postgres.setStamento(Postgres.getConnecion().createStatement());
            String sentencia = "select id,nombre,precio_venta,existencias,tipo,negocio_id,codigo from inventario_activo where estado=true and existencias>0;";
            ResultSet r = Postgres.getStamento().executeQuery(sentencia);
            Articulos tem_articulo = new Articulos();
            //****************************
            String men ="";
            while (r.next()) {
                System.out.println(" " + r.getInt(1) + " " + r.getString(2) + " " + r.getString(3) + " " + r.getString(4) + " " + r.getString(5) + " " + r.getInt(6) + " " + r.getString(7));
                inventario.logica.Articulo tem_a = new inventario.logica.Articulo(r.getInt(1), r.getString(2), r.getFloat(3), r.getFloat(4), r.getInt(5), r.getInt(6), r.getString(7));
                if (!(boolean) tem_articulo.validInsert(tem_a.getId())) {
                    tem_articulo.insertarDato(tem_a);
                } else {
                    tem_articulo.updateDato(tem_a);
                    men+=""+tem_a.getId()+",";
                }
            }
            System.out.println(":::::::::::::::: "+men+"  "+men);
            if(men.length() > 0){
                sentencia ="delete from articulo where id not in ("+men.substring(0, men.length()-1)+");";
                inventario.logica.db.Conexion.setStamento(inventario.logica.db.Conexion.getConnecion().createStatement());
                ResultSet r2 = inventario.logica.db.Conexion.getStamento().executeQuery(sentencia);
            }
            
        } catch (SQLException ex) {

        }

    }

    public void actualizarArticulos(int negocio) {
        System.out.println("-----------------> " + negocio);
        ArrayList<Object> o = new ArrayList<>();
        try {
            Postgres.setStamento(Postgres.getConnecion().createStatement());
            String sentencia = "select id,nombre,precio_venta,existencias,tipo,negocio_id,codigo from inventario_activo where estado=true and existencias>0 and negocio_id=" + negocio + ";";
            System.out.println(sentencia);
            ResultSet r = Postgres.getStamento().executeQuery(sentencia);
            Articulos tem_articulo = new Articulos();
            //****************************
            while (r.next()) {
                System.out.println(" " + r.getInt(1) + " " + r.getString(2) + " " + r.getString(3) + " " + r.getString(4) + " " + r.getString(5) + " " + r.getInt(6) + " " + r.getString(7));
                inventario.logica.Articulo tem_a = new inventario.logica.Articulo(r.getInt(1), r.getString(2), r.getFloat(3), r.getFloat(4), r.getInt(5), r.getInt(6), r.getString(7));
                if (!(boolean) tem_articulo.validInsert(tem_a.getId())) {
                    tem_articulo.insertarDato(tem_a);
                } else {
                    tem_articulo.updateDato(tem_a);
                }
            }
        } catch (SQLException ex) {

        }

    }

    public void sincronizarFacturas() {
        try {
            inventario.logica.db.Conexion.setStamento(inventario.logica.db.Conexion.getConnecion().createStatement());
            String sentencia = "select id,fecha from factura where enviada=1 and paga=1 and cargada=0;";
            ResultSet r = inventario.logica.db.Conexion.getStamento().executeQuery(sentencia);
            Descripcion des = new Descripcion();
            //{"a":{"factura":1,"fecha":"","cliente":"false","descripcion":[{"articulo":2,"cantidad":45}]}}
            String info_factura = "";
            String info_des = "";
            while (r.next()) {
                String descrip = "";
                ArrayList<Object> datos = des.getDatos(new String[]{"" + r.getInt(1)});
                int i = 0;
                for (i = 0; i < datos.size(); i++) {
                    inventario.logica.Descripcion tem_des = (inventario.logica.Descripcion) datos.get(i);
                    descrip += "{\"articulo\":" + tem_des.getArticulo_id() + ",\"cantidad\":" + tem_des.getCantidad() + "}";
                    descrip += !(i == datos.size() - 1) ? "," : "";
                }
                info_factura += "\"a\":{\"factura\":1,\"fecha\":\"" + r.getString(2) + "\",\"cliente\":\"false\",\"descripcion\":[" + descrip + "]}";
                info_factura += r.next() ? "," : "";
            }
            System.out.println("//////////////////////////////////////////////////////////////////////////");
            String envio = info_factura.substring(0, info_factura.length() - 1);
            if (envio.length() > 0) {
                try {
                    Postgres.setStamento(Postgres.getConnecion().createStatement());
                    sentencia = "select sincronizar_cliente('{"+envio+"}'::json)";
                    System.out.println(sentencia);
                    r = Postgres.getStamento().executeQuery(sentencia);
                } catch (SQLException ex) {
                    System.out.println("en el 1 factura"+ex);
                }
            }
        } catch (SQLException ex) {
            System.out.println("en el 1 evio factura" + ex);
        }

    }

}
