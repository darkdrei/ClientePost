/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package origami2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import logica.Validadora;

/**
 *
 * @author dreicon
 */
public class practica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        logica.correo.EnvioCorreo.send();
        // TODO code application logic here
//        logica.correo.EnvioCorreo.send   f.anio="+calendar.get(Calendar.YEAR)+" and f.mes="+(calendar.get(Calendar.MONTH) + 1)+"
        
//        Calendar calendar = new GregorianCalendar();
//        int anio =calendar.get(Calendar.YEAR),dia=calendar.get(Calendar.DAY_OF_MONTH),mes=(calendar.get(Calendar.MONTH) + 1);
//        double total= anio*mes,t2=2015*11;
//        System.err.println(total+"   "+t2);
//        logica.db.Configuracion c = new logica.db.Configuracion();
//        String contenido="";
//        for (logica.Ingrediente arg : c.getCampos(c.get_sDato().getUnidades(), c.get_sDato().getIngrediente())) {
//            contenido+="<tr><td>"+arg.getNombre()+"</td><td>"+arg.getDescripcion()+"</td></tr>";
//        }
//        String body = "";
//        body += "<style>\n"
//                + "table {\n"
//                + "    width:100%;\n"
//                + "}\n"
//                + "table, th, td {\n"
//                + "    border: 1px solid black;\n"
//                + "    border-collapse: collapse;\n"
//                + "}\n"
//                + "th, td {\n"
//                + "    padding: 5px;\n"
//                + "    text-align: left;\n"
//                + "}\n"
//                + "table#t01 tr:nth-child(even) {\n"
//                + "    background-color: #eee;\n"
//                + "}\n"
//                + "table#t01 tr:nth-child(odd) {\n"
//                + "   background-color:#fff;\n"
//                + "}\n"
//                + "table#t01 th	{\n"
//                + "    background-color: black;\n"
//                + "    color: white;\n"
//                + "}\n"
//                + "</style>";
//        body += "<table id=\"t01\">\n"
//                + "  <tr>\n"
//                + "    <th>Nombre</th>\n"
//                + "    <th>Existencias</th>		\n"
//                + "  </tr>\n"
//                +contenido+"\n"
//                + "</table>";
//        logica.correo.Gmail.send("login", "darkdrei88@gmail.com",
//                "password", "mqyjykammndoxdrh",
//                "to", "darkdrei88@gmail.com",
//                "cc", "darkdrei88@gmail.com",
//                "bcc", "eupaal@hotmail.com",
//                "subject", "prueba desde origamis",
//                "body",body);
    }
    
    public static String generadorMD5(String m) throws NoSuchAlgorithmException {
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
