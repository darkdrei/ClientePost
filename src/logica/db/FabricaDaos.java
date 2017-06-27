/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.db;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author CTesting
 */
public final class FabricaDaos {
    private static final FabricaDaos f = new FabricaDaos();
    private static Calendar calendar = new GregorianCalendar();
    private FabricaDaos(){}
    
    public static BaseDao producirPersistenia(int i){
        
            if (i== 1)
                return new Mesa();
            else if (i == 2)
                return new Categoria();
            else if (i == 3)
                return new Tipo();
            else if (i == 4)
                return new Plato();
            else if (i == 5)
                return new Adicion();
            else if (i == 6)
                return new Usuario();
            else if(i == 7)
                return new Trabajador();
            else if(i == 8)
                return new Factura();
            else if(i == 9)
                return new ConteFac();
            else if (i == 10)
                return new Ingrediente();
            else if (i == 11)
                return new Composicion();
            return null;

    }
}
