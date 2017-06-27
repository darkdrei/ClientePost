/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates.
 * and open the template in the editor.
 * Calle primera de mayo  47-95 yohana 47-97.
 */
package logica.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author dark
 */
public class Reporte extends BaseDao {

    public void getReporteDias() {
        String r = "select o.opcion, sum( case when f.id  is  null then  0 when f.opcion=1 then 1.1 "
                + "*(select sum(p.precio*c.cantidad) as total from conten_facnew as c  "
                + "inner join plato as p on (c.plato_id=p.id and c.estado=2 and p.estado=1 "
                + "and c.factura_id= f. id) )   else add_p+(select sum(p.precio*c.cantidad) as total "
                + "from conten_facnew as c  inner join plato as p on "
                + "(c.plato_id=p.id and c.estado=2 and p.estado=1 and c.factura_id= f. id)) end )"
                + " as total  ,  sum(case when f.id is null then 0 when f.opcion=1 then 0.1 *"
                + "(select sum(p.precio*c.cantidad) as total  "
                + "from conten_facnew as c  inner join plato as p on "
                + "(c.plato_id=p.id and c.estado=2 and p.estado=1 and c.factura_id= f. id) )  "
                + " f.add_p end ) as resul_opcion from opcion_pago as o "
                + " left join new_factura as f on (f.anio="+calendar.get(Calendar.YEAR)+" and f.mes="+(calendar.get(Calendar.MONTH) + 1)+" and f.dia="+calendar.get(Calendar.DAY_OF_MONTH)+" and f.opcion=o.opcion)"
                + " group by o.opcion";
    }
Calendar calendar = new GregorianCalendar();
    public ArrayList<logica.DescripcionFactura> getReporteDia() {
        ArrayList<logica.DescripcionFactura> f = new ArrayList<>();
        
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select o.nombre,sum(case \n" +
"	when (select count(id) from new_factura as f where f.estado=2 and f.opcion=o.id and f.anio="+calendar.get(Calendar.YEAR)+" and f.mes="+(calendar.get(Calendar.MONTH) + 1)+" and f.dia="+calendar.get(Calendar.DAY_OF_MONTH)+") > 0 and o.id=1 then\n" +
"				(       select sum(case when ct.cantidad is not null and p.precio is not null then ct.cantidad*p.precio*0.1 else 0 end) \n" +
"					from new_factura as ft left join conten_facnew as ct on\n" +
"					(ft.opcion=o.id and ft.id=ct.factura_id and ft.anio="+calendar.get(Calendar.YEAR)+" and ft.mes="+(calendar.get(Calendar.MONTH) + 1)+" and ft.dia="+calendar.get(Calendar.DAY_OF_MONTH)+" and ft.estado=2 and ct.estado=1 )\n" +
"					left join plato as p on (p.id=ct.plato_id and p.estado=1)\n" +
"				)\n" +
"	when o.id != 1 and (select count(id) from new_factura as f where f.estado=2 and f.opcion=o.id and f.anio="+calendar.get(Calendar.YEAR)+" and f.mes="+(calendar.get(Calendar.MONTH) + 1)+" and f.dia="+calendar.get(Calendar.DAY_OF_MONTH)+") > 0 then\n" +
"		(select sum (fo.add_p) from new_factura as fo where fo.estado=2 and fo.opcion=o.id and fo.anio="+calendar.get(Calendar.YEAR)+" and fo.mes="+(calendar.get(Calendar.MONTH) + 1)+" and fo.dia="+calendar.get(Calendar.DAY_OF_MONTH)+")\n" +
"	else 0 end\n" +
"				   ) as total from opcion_pago as o group by o.nombre  union select 'z',sum(case when ct.cantidad is not null and p.precio is not null then ct.cantidad*p.precio else 0 end) \n" +
"from new_factura as ft left join conten_facnew as ct on(ft.id=ct.factura_id and ft.anio="+calendar.get(Calendar.YEAR)+" and ft.mes="+(calendar.get(Calendar.MONTH) + 1)+" and ft.dia="+calendar.get(Calendar.DAY_OF_MONTH)+" and ft.estado=2 and ct.estado=1 )\n" +
" left join plato as p on (p.id=ct.plato_id and p.estado=1)\n" +
" union \n" +
"select 'zz',sum(case when ct.cantidad is not null and p.precio is not null then ct.cantidad*p.precio else 0 end) \n" +
"+(select sum (fo.add_p) from new_factura as fo where fo.estado=2 and fo.opcion!=1 and fo.anio="+calendar.get(Calendar.YEAR)+" and fo.mes="+(calendar.get(Calendar.MONTH) + 1)+" and fo.dia="+calendar.get(Calendar.DAY_OF_MONTH)+")\n" +
"from new_factura as ft left join conten_facnew as ct on(ft.id=ct.factura_id and ft.anio="+calendar.get(Calendar.YEAR)+" and ft.mes="+(calendar.get(Calendar.MONTH) + 1)+" and ft.dia="+calendar.get(Calendar.DAY_OF_MONTH)+" and ft.estado=2 and ct.estado=1 )\n" +
" left join plato as p on (p.id=ct.plato_id and p.estado=1) ;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
//            try{
            while (r.next()) {
                f.add(new logica.DescripcionFactura(
                        r.getString(1).equalsIgnoreCase("zz")?"Total Producido":r.getString(1).equalsIgnoreCase("z")?"Platos y Bebidas": r.getString(1)
                        , r.getFloat(2)));
            }
            return f;
        } catch (SQLException ex) {
            System.out.println("Se exploto");
        }
        return null;

    }

    public ArrayList<logica.DescripcionFactura> getReporteDiaTipo() {
        ArrayList<logica.DescripcionFactura> f = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            //String sentencia ="insert into contenido_tem (plato,cantidad,precio) values('"+i.getNombre()+"',"+i.getCantidad()+","+i.getPrecio()+");";
            String sentencia = "select * from (select tp.nombre,"
                    + "sum(case when f.cant is not null and f.precio is not null then "
                    + "f.cant*f.precio else 0 end) as total from tipo as tp  "
                    + "left join (select c.cantidad as cant, p.precio,t.id as tipo  from  "
                    + "(select * from new_factura as tf where tf.estado=2 and tf.anio="+calendar.get(Calendar.YEAR)+" and tf.mes="+(calendar.get(Calendar.MONTH) + 1)+" and tf.dia="+calendar.get(Calendar.DAY_OF_MONTH)+") as f   left join conten_facnew as c on (c.estado=1 and c.factura_id=f.id) left join plato as p on(p.estado=1 and p.id=c.plato_id) left join tipo as t on (t.id=p.tipo_id and t.estado=1)) as f on (f.tipo=tp.id) group by tp.nombre) as d order by d.total desc;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
//            try{
            while (r.next()) {
                f.add(new logica.DescripcionFactura(r.getString(1), r.getFloat(2)));
            }
            return f;
        } catch (SQLException ex) {
            System.out.println("Se exploto");
        }
        return null;

    }

    public Object[][] getObjetoMatriz(ArrayList<logica.DescripcionFactura> d) {
        if (d.equals(null)) {
            return new String[1][2];
        }
        Object m[][] = new Object[d.size() + 2][2];
        float r = 0;
        int i = 0;
        for (logica.DescripcionFactura m1 : d) {
            m[i][0] = new String(m1.getPlato());
            m[i][1] = m1.getTotal();
            r += m1.getTotal();
            i++;
        }
        /*logica.DescripcionFactura des = getTotalDia();
        m[i][0] = des.getPlato();
        m[i][1] = des.getTotal();
        i++;
        r += des.getTotal();
        m[i][0] = "Total Jornada";
        m[i][1] = r;*/
        return m;
    }

    public Object[][] getObjetoMatrizNormal(ArrayList<logica.DescripcionFactura> d) {
        if (d == null) {
            return new String[1][2];
        }
        Object m[][] = new Object[d.size() + 2][2];
        float r = 0;
        int i = 0;
        for (logica.DescripcionFactura m1 : d) {
            m[i][0] = m1.getPlato();
            m[i][1] = m1.getTotal();
            i++;
        }
        return m;
    }

    public ArrayList<logica.DescripcionFactura> getTotalDiaPlato() {
        ArrayList<logica.DescripcionFactura> f = new ArrayList<logica.DescripcionFactura>();
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            String sentencia = "select * from "
                    + "(select p.nombre,sum(case when f.cant is not null and "
                    + "f.precio is not null then f.cant*f.precio else 0 end) as total "
                    + "from plato as p  left join "
                    + "(select c.cantidad as cant, p.precio,c.plato_id as plato  from  "
                    + "(select * from new_factura as tf where tf.estado=2 and tf.anio="+calendar.get(Calendar.YEAR)+" and tf.mes="+(calendar.get(Calendar.MONTH) + 1)+" and tf.dia="+calendar.get(Calendar.DAY_OF_MONTH)+") as f  left join conten_facnew as c on (c.estado=1 and c.factura_id=f.id) left join plato as p on(p.estado=1 and p.id=c.plato_id) left join tipo as t on (t.id=p.tipo_id and t.estado=1)) as f on (f.plato=p.id and p.estado=1) group by p.nombre) as tp order by tp.total desc;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
            while (r.next()) {
                f.add(new logica.DescripcionFactura(r.getString(1), r.getFloat(2)));
            }
            return f;
        } catch (SQLException ex) {
            System.out.println("Se exploto getTotal");
            return f;
        }
    }

    public ArrayList<logica.DescripcionFactura> getTotalDiaCategoria() {
        ArrayList<logica.DescripcionFactura> f = new ArrayList<logica.DescripcionFactura>();
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            String sentencia = "select * from (select c.nombre,"
                    + "sum(case when f.cant is not null and f.precio is not null then "
                    + "f.cant*f.precio else 0 end) as total from categoria as c  "
                    + "left join tipo as tp on (c.id=tp.categoria_id and tp.estado=1 and c.estado=1) "
                    + "left join (select c.cantidad as cant, p.precio,t.id as tipo  from "
                    + " (select * from new_factura as tf where tf.estado=2 and tf.anio="+calendar.get(Calendar.YEAR)+" and tf.mes="+(calendar.get(Calendar.MONTH) + 1)+" and tf.dia="+calendar.get(Calendar.DAY_OF_MONTH)+") as f   left join conten_facnew as c on (c.estado=1 and c.factura_id=f.id) left join plato as p on(p.estado=1 and p.id=c.plato_id) left join tipo as t on (t.id=p.tipo_id and t.estado=1)) as f on (f.tipo=tp.id) group by c.nombre) as fin order by total desc;";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
            while (r.next()) {
                f.add(new logica.DescripcionFactura(r.getString(1), r.getFloat(2)));
            }
            return f;
        } catch (SQLException ex) {
            System.out.println("Se exploto getTotal");
            return f;
        }
    }

    public logica.DescripcionFactura getTotalDia() {
        try {
            Conexion.setStamento(Conexion.getConnecion().createStatement());
            String sentencia = "select 'total',sum(p.precio*c.cantidad) as total from (select * from  new_factura as f where (f.anio="+calendar.get(Calendar.YEAR)+" and f.mes="+(calendar.get(Calendar.MONTH) + 1)+" and f.dia="+calendar.get(Calendar.DAY_OF_MONTH)+" ) and f.estado=2) as fa \n"
                    + "inner join conten_facnew as c on(fa.id=c.factura_id and c.estado=2 ) inner join plato as p on(p.id=c.plato_id)";
            ResultSet r = Conexion.getStamento().executeQuery(sentencia);
            while (r.next()) {
                return (new logica.DescripcionFactura(r.getString(1), r.getFloat(2)));
            }
        } catch (SQLException ex) {
            return (new logica.DescripcionFactura("pongase en contacto admin", 0));
        }
        return (new logica.DescripcionFactura("Esperdi", 0));
    }

    public String[] cabecerasTablas(int op) {
        if (op == 1) {
            return new String[]{"Pago", "Total"};
        } else if (op == 2) {
            return new String[]{"Categoria", "Total"};
        } else if (op == 3) {
            return new String[]{"Tipo", "Total"};
        } else if (op == 4) {
            return new String[]{"Plato", "Total"};
        }
        return new String[]{"*****", "*****"};
    }

}
