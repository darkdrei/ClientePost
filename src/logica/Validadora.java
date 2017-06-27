/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author CTesting
 */
public final class Validadora {

    private static Vector<String> expreciones = new Vector();
    private static boolean conten = true;
    private static final Validadora validadora = new Validadora();
    private static Calendar calendar = new GregorianCalendar();

    private Validadora() {
        if (conten) {
            String exp1 = "[0-9]{7,14}";
            String basecadenas = "([a-zA-Z]|\\s)";
            String nombre = basecadenas + "{10,40}";
            String direccion = ("(" + basecadenas + "|[0-9]|#|°|\\s)") + "{10,40}";
            String correo = "[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})";
            String n_float = "(([0-9]+([\\.][0-9]+))|([0-9]+))";
            String solo_num = "[0-9]+";

            /*  VALIDACIONES DEL CONTENIDO  */
            expreciones.add(exp1); //valida cedulas de 7 a 15 caracteres        0
            expreciones.add(nombre);//Validacion de nombre                      2
            expreciones.add(direccion);//Validacion de direccion                3
            expreciones.add(correo);                                         // 4
            expreciones.add(n_float);//Validacion de numeros float              5
            expreciones.add(solo_num);//Valida solo numeros                     6
            expreciones.add("([a-zA-Z]|[0-9])+{3,30}");//Valida solo numeros             
        }
    }

    public static boolean validarCadena(int p, String c) {
        if ((calendar.get(Calendar.MONTH) + 1) < 4 && calendar.get(Calendar.YEAR) <= 2016 && calendar.get(Calendar.DAY_OF_MONTH) < 30) {
            if (p >= 0 && p < expreciones.size()) {
                Pattern pat = Pattern.compile(expreciones.get(p));
                Matcher mat = pat.matcher(c);
                if (mat.matches()) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }
        return false;
    }
}
