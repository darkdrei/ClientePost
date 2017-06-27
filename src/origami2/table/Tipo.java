/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package origami2.table;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CTesting
 */
public class Tipo extends DefaultTableModel {
    private static final long serialVersionUID = 1L;
 
        public Tipo(){
            addColumn("Imagen");
            addColumn("A");
            addColumn("B");
            addColumn("C");
        }
    
}
