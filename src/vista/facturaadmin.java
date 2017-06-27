/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates *cas30032451c4q2t3
 * and open the template in the editor.  secreto del orgasmo femenino karla fuentes
 */
package vista;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import logica.Imprimir;
import logica.Objeto;
import logica.db.*;
import logica.Validadora;

/**
 *
 * @author dark
 */
public class facturaadmin extends javax.swing.JPanel {

    private String nom;
    private int id_mesa;
    private Vector<Integer> id_cat = new Vector<>();
    private Vector<Integer> id_tipo = new Vector<>(2);
    private Vector<Integer> id_plato = new Vector<>();
    private logica.db.BaseDao db;
    private String nom_tipo[];
    private String nom_plato[];
    Object m[][];
    private Vector<Integer> id_factura = new Vector<>();
    private boolean semaforo_voluntaria = false;

    public boolean getSemaforo_voluntaria() {
        return semaforo_voluntaria;
    }

    public void setSemaforo_voluntaria(boolean semaforo_voluntaria) {
        this.semaforo_voluntaria = semaforo_voluntaria;
    }

    public Vector<Integer> getId_factura() {
        return id_factura;
    }

    public void setId_factura(Vector<Integer> id_factura) {
        this.id_factura = id_factura;
    }   

    /**
     * Creates new form factura
     */
    public facturaadmin() {
        initComponents();
        actualizarLista(0, 0, 0, 0);
    }

    public BaseDao getDb() {
        return db;
    }

    public void setDb(BaseDao db) {
        this.db = db;
    }

    public ArrayList<Object> getLista() {
        return new ArrayList<Object>();
    }

    public facturaadmin(String nom) {
        initComponents();
        this.nom = nom;
    }

    public Vector<Integer> getId_cat() {
        return id_cat;
    }

    public void setId_cat(Vector<Integer> id_cat) {
        this.id_cat = id_cat;
    }

    public Vector<Integer> getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(Vector<Integer> id_tipo) {
        this.id_tipo = id_tipo;
    }

    public Vector<Integer> getId_plato() {
        return id_plato;
    }

    public void setId_plato(Vector<Integer> id_plato) {
        this.id_plato = id_plato;
    }

    public void actualizarLista(int id, int d, int m, int a) {
        logica.db.Factura f = new logica.db.Factura();
        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = f.getListaFactura(id, d, m, a);

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public Object getElementAt(int i) {
                return strings[i];
            }
        });
        this.setId_factura(f.getIdFactura(id, d, m, a));
        
    }

    public facturaadmin(String nom, int id) {
        initComponents();
        this.nom = nom;
        this.id_mesa = id;
        actualizarPrecio();
        setDb(FabricaDaos.producirPersistenia(2));
        ArrayList<Object> tem = getDb().getDatos();
        String cat_nom[] = new String[tem.size() + 1];
        int t = 1;
        cat_nom[0] = "-- Seleccione  --";
        for (Object id_cat1 : tem) {
            logica.Categoria c = (logica.Categoria) id_cat1;
            cat_nom[t] = c.getNombre();
            getId_cat().add(c.getId());
            t++;
        }
//        cbCategoria.setModel(new javax.swing.DefaultComboBoxModel(cat_nom));
        enableCbOtros();
        actualizarMatriz();
        actualizarTotalFactuta();
        identificadorOpciones();
        JOptionPane.showMessageDialog(null, "2");
        actualizarLista(0, 0, 0, 0);
    }

    public String numeroDecimales(double x) {
        DecimalFormat decimales = new DecimalFormat("0.000");
        return "" + decimales.format(x);
    }

    public int getIdFactura() {
        db = FabricaDaos.producirPersistenia(8);
        ArrayList<Object> o = new ArrayList<>();
        o.add(id_mesa);
        return (boolean) (db.validInsert(o)[0]) ? db.getEndObjeto(id_mesa) : 0;
    }

    public void actualizarMatriz() {
        int id_fac = getIdFactura();
        db = FabricaDaos.producirPersistenia(9);
        m = convertirMatriz(db.getObjetoMatriz(id_fac), 6);
        modificarTablaDescripcion();
        actualizarPrecio(id_fac);
    }

    public void actualizarPrecio(int id_fac) {

        txtSubTotal.setText("" + getPrecio(id_fac));
    }

    public float getPrecio(int id_fact) {
        setDb(FabricaDaos.producirPersistenia(9));
        return getDb().getTotal(id_fact);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        dateComponentFormatter1 = new org.jdatepicker.impl.DateComponentFormatter();
        jDatePickerUtil1 = new org.jdatepicker.util.JDatePickerUtil();
        jPanel1 = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtIdFactura = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        optVoluntaria = new javax.swing.JRadioButton();
        optDomicilio = new javax.swing.JRadioButton();
        opcSinPropina = new javax.swing.JRadioButton();
        opcConPropina = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lbTemporal = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        txtTemporal = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtCambio = new javax.swing.JTextField();
        txtEfectivo = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        btnCobro = new javax.swing.JButton();
        btnCerrarCuenta = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Fecha");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Num Factura");

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/searc.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtIdFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel5.setText("Descripcion Factura");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Cantidad", "Valor"
            }
        ));
        jTable1.setMinimumSize(new java.awt.Dimension(200, 100));
        jScrollPane1.setViewportView(jTable1);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel6.setText("Gestion de pago");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones"));

        buttonGroup1.add(optVoluntaria);
        optVoluntaria.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        optVoluntaria.setText("Voluntaria");
        optVoluntaria.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optVoluntariaStateChanged(evt);
            }
        });
        optVoluntaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optVoluntariaActionPerformed(evt);
            }
        });

        buttonGroup1.add(optDomicilio);
        optDomicilio.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        optDomicilio.setText("Domicilio");
        optDomicilio.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optDomicilioStateChanged(evt);
            }
        });
        optDomicilio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optDomicilioActionPerformed(evt);
            }
        });

        buttonGroup1.add(opcSinPropina);
        opcSinPropina.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        opcSinPropina.setText("Sin Propina");
        opcSinPropina.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                opcSinPropinaStateChanged(evt);
            }
        });
        opcSinPropina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcSinPropinaActionPerformed(evt);
            }
        });

        buttonGroup1.add(opcConPropina);
        opcConPropina.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        opcConPropina.setSelected(true);
        opcConPropina.setText("Con Propina");
        opcConPropina.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                opcConPropinaStateChanged(evt);
            }
        });
        opcConPropina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcConPropinaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(optVoluntaria)
                    .addComponent(opcConPropina)
                    .addComponent(opcSinPropina)
                    .addComponent(optDomicilio))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(opcConPropina)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(opcSinPropina)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(optDomicilio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(optVoluntaria))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion Factura"));

        jLabel7.setText("Sub Total");

        lbTemporal.setText("Propina ( 10 %)");

        jLabel9.setText("Total");

        txtSubTotal.setEditable(false);
        txtSubTotal.setFont(new java.awt.Font("Ubuntu", 0, 20)); // NOI18N
        txtSubTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSubTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubTotalActionPerformed(evt);
            }
        });

        txtTemporal.setEditable(false);
        txtTemporal.setFont(new java.awt.Font("Ubuntu", 0, 20)); // NOI18N
        txtTemporal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Ubuntu", 0, 20)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbTemporal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTemporal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTotal))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lbTemporal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTemporal, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Pago"));

        jLabel10.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel10.setText("Efectivo");

        jLabel11.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel11.setText("Cambio");

        txtCambio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtEfectivo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(txtCambio, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEfectivo)))
                .addGap(25, 25, 25))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 15, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones"));

        btnCobro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/1437697978_Dollar.png"))); // NOI18N
        btnCobro.setText("Cobro ");
        btnCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobroActionPerformed(evt);
            }
        });

        btnCerrarCuenta.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btnCerrarCuenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/1437698487_Delete.png"))); // NOI18N
        btnCerrarCuenta.setText("Cerrar Cuenta");
        btnCerrarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarCuentaActionPerformed(evt);
            }
        });

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/1437952978_print.png"))); // NOI18N
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCerrarCuenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnCobro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCobro)
                    .addComponent(btnImprimir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerrarCuenta))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        btnEliminar.setText("Eliminar");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(4, 4, 4))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(281, 281, 281)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(12, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void opcConPropinaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_opcConPropinaStateChanged
        if (opcConPropina.isSelected()) {
            setDb(FabricaDaos.producirPersistenia(8));
            int id_fact = getIdFactura();
            if (id_fact > 0) {
                getDb().updateDato(new logica.Factura(1, getPrecio(id_fact)), id_fact);
                deshabilitarTemporal(true);
                txtTemporal.setText("" + numeroDecimales(this.getPrecio(id_fact) * 0.1));
                lbTemporal.setText("Propina ( 10 %)");
                actualizarTotalFactuta();
            }
        }
//        activitiOption(opcConPropina);
    }//GEN-LAST:event_opcConPropinaStateChanged

    public void deshabilitarTemporal(boolean r) {
        lbTemporal.setVisible(r);
        txtTemporal.setVisible(r);
    }

    private void opcSinPropinaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_opcSinPropinaStateChanged
        // TODO add your handling code here:
//        activitiOption(opcSinPropina);
        if (opcSinPropina.isSelected()) {
            int id_fact = getIdFactura();
            if (id_fact > 0) {
                setDb(FabricaDaos.producirPersistenia(8));
                getDb().updateDato(new logica.Factura(2, new Float(0)), id_fact);
                txtTemporal.setText("0");
                deshabilitarTemporal(false);
            }
        }
    }//GEN-LAST:event_opcSinPropinaStateChanged

    private void optDomicilioStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optDomicilioStateChanged
        // TODO add your handling code here:
        if (optDomicilio.isSelected()) {
            if (!getSemaforo_voluntaria()) {
                this.setEnabled(false);
                setSemaforo_voluntaria(true);
                setDb(FabricaDaos.producirPersistenia(8));
                int id_fact = getIdFactura();
                if (id_fact > 0) {//            String mesage, String titulo, factura base, int id_fact, int opcion, JRadioButton option    
                    Thread h1 = new Thread(
                            new ConvertirMensage("Digite el valor para el domicio", "Domiciolio", this, id_fact, 3, optDomicilio)
                    );
                    h1.start();
                }
            }
        }

    }//GEN-LAST:event_optDomicilioStateChanged

    private void optVoluntariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optVoluntariaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optVoluntariaActionPerformed

    private void optVoluntariaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optVoluntariaStateChanged
        // TODO add your handling code here:
////        activitiOption(optVoluntaria);
        if (optVoluntaria.isSelected()) {
            if (!getSemaforo_voluntaria()) {
                this.setEnabled(false);
                setSemaforo_voluntaria(true);
                this.setEnabled(false);
                setDb(FabricaDaos.producirPersistenia(8));
                int id_fact = getIdFactura();
                if (id_fact > 0) {//                
                    Thread h1 = new Thread( //String mesage, String titulo, factura base, int id_fact, int opcion, JRadioButton option
                            new ConvertirMensage("Digite la cantidad voluntaria", "Propina Voluntaria", this, id_fact, 4, optVoluntaria)
                    );
                    h1.start();
                }
            }
        }
    }//GEN-LAST:event_optVoluntariaStateChanged

    public JTextField getTxtTemporal() {
        return txtTemporal;
    }

    public void setTxtTemporal(JTextField txtTemporal) {
        this.txtTemporal = txtTemporal;
    }

    public void enableCbOtros() {

//        cbCategoria.requestFocusInWindow();
    }

    class ProducirMensaje implements Runnable {

        private String titulo;
        private String men;
        private int icono;
        private facturaadmin f;

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getMen() {
            return men;
        }

        public void setMen(String men) {
            this.men = men;
        }

        public int getIcono() {
            return icono;
        }

        public void setIcono(int icono) {
            this.icono = icono;
        }

        public facturaadmin getF() {
            return f;
        }

        public void setF(facturaadmin f) {
            this.f = f;
        }

        public ProducirMensaje(String titulo, String men, int icono, facturaadmin f) {
            this.titulo = titulo;
            this.men = men;
            this.icono = icono;
            this.f = f;
        }

        @Override
        public void run() {
            JOptionPane.showMessageDialog(null, men, titulo, icono);
            this.getF().setEnabled(true);
        }

    }

    class ConvertirMensage implements Runnable {

        private float valor;
        private String mesage;
        private String titulo;
        private facturaadmin base;
        private int id_fact;
        private int opcion;
        private javax.swing.JRadioButton option;

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public int getOpcion() {
            return opcion;
        }

        public void setOpcion(int opcion) {
            this.opcion = opcion;
        }

        public ConvertirMensage(float valor, String mesage, String titulo, facturaadmin base, int id_fact, int opcion, JRadioButton option) {
            this.valor = valor;
            this.mesage = mesage;
            this.titulo = titulo;
            this.base = base;
            this.id_fact = id_fact;
            this.opcion = opcion;
            this.option = option;
        }

        public ConvertirMensage(String mesage, String titulo, facturaadmin base, int id_fact, int opcion, JRadioButton option) {
            this.mesage = mesage;
            this.titulo = titulo;
            this.base = base;
            this.id_fact = id_fact;
            this.opcion = opcion;
            this.option = option;
        }

        public JRadioButton getOption() {
            return option;
        }

        public void setOption(JRadioButton option) {
            this.option = option;
        }

        public float getValor() {
            return valor;
        }

        public void setValor(float valor) {
            this.valor = valor;
        }

        public String getMesage() {
            return mesage;
        }

        public void setMesage(String mesage) {
            this.mesage = mesage;
        }

        public facturaadmin getBase() {
            return base;
        }

        public int getId_fact() {
            return id_fact;
        }

        public void setId_fact(int id_fact) {
            this.id_fact = id_fact;
        }

        public ConvertirMensage(String mesage, facturaadmin base, int id_fact) {
            this.mesage = mesage;
            this.base = base;
            this.id_fact = id_fact;
        }

        public void setBase(facturaadmin base) {
            this.base = base;
        }

        @Override
        public void run() {
            boolean tem_res = false;
            float valor = 0;
            setDb(FabricaDaos.producirPersistenia(8));
            uno:
            do {
                try {
                    String men = JOptionPane.showInputDialog(null, mesage, titulo, JOptionPane.INFORMATION_MESSAGE);
                    if (men != null) {
                        valor = Float.parseFloat(men);
                        if (valor >= 0) {
                            getDb().updateDato(new logica.Factura(opcion, valor), id_fact);
                            option.setSelected(true);
                            this.getBase().getTxtTemporal().setText(numeroDecimales(valor));
                            this.getBase().deshabilitarTemporal(true);
                            this.getBase().lbTemporal.setText(opcion == 3 ? "Domicilio" : "Propina Voluntaria");
                            tem_res = true;
                            break uno;
                        } else {

                        }
                    } else {
                        Object o = getDb().getDato(id_fact);
                        if (o instanceof logica.Factura) {
                            logica.Factura f = (logica.Factura) o;
                            if (f.getTipo() == 1) {
                                this.getBase().getOpcConPropina().setSelected(true);
                            } else if (f.getTipo() == 2) {
                                this.getBase().getOpcSinPropina().setSelected(true);
                            } else if (f.getTipo() == 3) {
                                this.getBase().getOptDomicilio().setSelected(true);
                            } else if (f.getTipo() == 4) {
                                this.getBase().optVoluntaria.setSelected(true);
                            }
                            if (f.getTipo() != 2 && f.getTipo() != 1) {
                                txtTemporal.setText(numeroDecimales(f.getTotal()));
                            } else if (f.getTipo() == 2) {
                                this.getBase().deshabilitarTemporal(false);
                            } else if (f.getTipo() == 1) {
                                txtTemporal.setText(numeroDecimales(f.getTotal() * 0.1));
                            }
                        }
                        Thread.sleep(500);
                        tem_res = true;
                        break uno;

                    }
                } catch (NumberFormatException n) {
                    tem_res = false;
                } catch (InterruptedException ex) {
                    Logger.getLogger(facturaadmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (!tem_res);
            this.getBase().setEnabled(true);
            this.getBase().actualizarTotalFactuta();
            setSemaforo_voluntaria(false);
        }

    }
    private void opcSinPropinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcSinPropinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcSinPropinaActionPerformed

    private void optDomicilioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optDomicilioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optDomicilioActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        Vector<Integer> ids = new Vector<>();
        String r = "(";
        int t = jTable1.getModel().getRowCount();
        for (int i = 0; i < t; i++) {
            r += (boolean) jTable1.getModel().getValueAt(i, 5) ? ("" + (int) jTable1.getModel().getValueAt(i, 0)) + "," : "";
        }
        setDb(FabricaDaos.producirPersistenia(9));
        getDb().deleteDato((r + ")").replaceAll(",\\)", "\\)"), getIdFactura());
        actualizarFactura();
    }//GEN-LAST:event_btnEliminarActionPerformed

    public void actualizarFactura() {
        actualizarMatriz();
        actualizarPrecio();
        actualizarTotalFactuta();
        btnEliminar.setEnabled(false);
    }

    public void newFactura() {
        int id_fact = getIdFactura();
        if (id_fact == 0) {
            return;
        }
        setDb(FabricaDaos.producirPersistenia(9));
        getDb().deleteDato(id_fact);
        setDb(FabricaDaos.producirPersistenia(8));
        getDb().sateDato(id_fact);
        txtTemporal.setText("0");
        enableCbOtros();
        actualizarFactura();
//        cbCategoria.requestFocusInWindow();
    }

    public void limpiarResultados() {
        txtEfectivo.setText("0.0");
        txtCambio.setText("0.0");
    }

    public void finalizarFactura() {
        int id_fac = getIdFactura();
        if (id_fac == 0) {
            JOptionPane.showMessageDialog(null, "No Existe factura que cobrar.", "Cobro Cuenta", JOptionPane.INFORMATION_MESSAGE);
//            cbCategoria.requestFocusInWindow();
            return;
        }
        setDb(FabricaDaos.producirPersistenia(9));
        if (getDb().getTotal(id_fac) == 0) {
            JOptionPane.showMessageDialog(null, "No Existe contenido en la factura que cobrar.", "Cobro Cuenta", JOptionPane.INFORMATION_MESSAGE);
//            cbCategoria.requestFocusInWindow();
            return;
        }
        if (!txtEfectivo.getText().equalsIgnoreCase("")) {
            if (Validadora.validarCadena(4, txtEfectivo.getText())) {
                float total_p = Float.parseFloat(txtEfectivo.getText());
                setDb(FabricaDaos.producirPersistenia(8));
                float total_f = getDb().getTotal(id_fac);
                float r = total_p - total_f;
                if (r < 0) {
                    JOptionPane.showMessageDialog(null, "El dinero no es suficiente para el pago de la factura.\nFaltan " + (-r) + " pesos.", "Cobro Cuenta", JOptionPane.INFORMATION_MESSAGE);
                    txtEfectivo.setText("");
                    return;
                }
                txtCambio.setText(numeroDecimales(r));
                int seleccion;
                seleccion = JOptionPane.showOptionDialog(
                        this,
                        "Su cambio son : " + r + "pesos.\n\tSeleccione opcion",
                        "Factura",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, // null para icono por defecto.
                        new Object[]{"Cancelar", "Imprimir", "Cerrar Factura"}, // null para YES, NO y CANCEL
                        "opcion 1");
                if (seleccion == 2) {
                    System.out.println("Factura cerrada");
                    newFactura();
                    limpiarResultados();
                };
                return;
            } else {
                JOptionPane.showMessageDialog(null, "Digite un valor numerico en el campo Efectivo.", "Cobro Cuenta", JOptionPane.INFORMATION_MESSAGE);
                txtEfectivo.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Digite un valor en el campo Efectivo.", "Cobro Cuenta", JOptionPane.INFORMATION_MESSAGE);
        }
        txtEfectivo.requestFocusInWindow();
    }

    private void btnCobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobroActionPerformed
        // TODO add your handling code here:
        finalizarFactura();
    }//GEN-LAST:event_btnCobroActionPerformed

    private void btnCerrarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarCuentaActionPerformed
        // TODO add your handling code here:
        finalizarFactura();
    }//GEN-LAST:event_btnCerrarCuentaActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
        imprimirFactura();
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void opcConPropinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcConPropinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcConPropinaActionPerformed

    private void txtSubTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubTotalActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarActionPerformed

    public void cerrarFactura() {
        newFactura();
        limpiarResultados();
    }

    public void activitiOption(JRadioButton r) {
        optDomicilio.setSelected(false);
        opcConPropina.setSelected(false);
        opcSinPropina.setSelected(false);
        optVoluntaria.setEnabled(false);
        r.setSelected(true);
    }

    public void deshabilitarCantidad(boolean r) {
//        cbPlato.setEnabled(r);
//        cbPlato.setSelectedIndex(0);
    }

    public void deshabilitarCant(boolean r) {
//        jSCantidad.setValue(0);
//        jSCantidad.setEnabled(r);
//        btnAgregar.setEnabled(r);

    }

    public void actualizarTotalFactuta() {
        int id_fac = getIdFactura();
        if (id_fac != 0) {
            setDb(FabricaDaos.producirPersistenia(8));
            txtTotal.setText(numeroDecimales(getDb().getTotal(id_fac)));
        } else {
            txtTotal.setText("0.0");
        }

    }

    public void deshabilitarCant() {
//        jSCantidad.setValue(0);
//        jSCantidad.setEnabled(false);
//        btnAgregar.setEnabled(false);
    }

    public Object[][] convertirMatriz(Object[][] o, int n) {

        Object m[][] = new Object[o.length][n];
        for (int i = 0; i < o.length; i++) {
            for (int j = 0; j < o[i].length; j++) {
                m[i][j] = o[i][j];
            }
            m[i][n - 1] = new Boolean(false);
        }
        return m;
    }

    public void modificarTablaDescripcion() {

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                m,
                new String[]{
                    "Id", "Plato", "Cantidad", "Precio", "Total", "Eliminar"

                }
        ) {

            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Float.class, java.lang.Boolean.class

            };

            boolean[] canEdit = new boolean[]{
                false, false, true, false, false, true
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

            @Override
            public void setValueAt(Object value, int row, int col) {
                db = FabricaDaos.producirPersistenia(3);
                if (col == 5) {
                    m[row][col] = (boolean) value;
                    btnEliminar.setEnabled(true);
                    modificarTablaDescripcion();
                    return;
                } else if (col == 2) {
                    int valor = (int) value;
                    if (valor >= 0) {
                        setDb(FabricaDaos.producirPersistenia(9));
                        getDb().updateDato(new logica.ConteFac(valor), (int) jTable1.getValueAt(row, 0));
                        actualizarMatriz();
                        modificarTablaDescripcion();
                    } else {
                        JOptionPane.showMessageDialog(null, "Los valores deben ser mayores a cero.");
                    }
                }
                /*if (col == 1) {
                 ArrayList<Object> o = new ArrayList<>();
                 o.add((String) value);
                 Object r[] = db.validInsert(o);
                 if (!(boolean) r[0]) {
                 db.updateDato(new logica.Categoria((String) value, (String) jtMesa.getValueAt(row, 2)), (int) jtMesa.getValueAt(row, 0));
                 modificarTablaCategoria();
                 limpiarCategoria();
                 return;
                 }
                 } else if (col == 2) {
                 db.updateDato(new logica.Categoria((String) jtCategoria.getValueAt(row, 1), (String) value), (int) jtCategoria.getValueAt(row, 0));
                 modificarTablaCategoria();
                 limpiarCategoria();
                 return;
                 }*/
            }

        });
    }

    public JRadioButton getOpcConPropina() {
        return opcConPropina;
    }

    public void setOpcConPropina(JRadioButton opcConPropina) {
        this.opcConPropina = opcConPropina;
    }

    public JRadioButton getOpcSinPropina() {
        return opcSinPropina;
    }

    public void setOpcSinPropina(JRadioButton opcSinPropina) {
        this.opcSinPropina = opcSinPropina;
    }

    public JRadioButton getOptDomicilio() {
        return optDomicilio;
    }

    public void setOptDomicilio(JRadioButton optDomicilio) {
        this.optDomicilio = optDomicilio;
    }

    public void actualizarPrecio() {
        int id_fact = getIdFactura();
        if (id_fact != 0) {
            setDb(FabricaDaos.producirPersistenia(8));
            Object f = getDb().getDato(id_fact);
            if (f instanceof logica.Factura) {
                this.setSemaforo_voluntaria(true);
                logica.Factura fa = (logica.Factura) f;
                if (fa.getTipo() == 2) {
                    deshabilitarTemporal(false);
                } else {
                    lbTemporal.setText(fa.getTipo() == 1 ? "Propina ( 10 %)" : fa.getTipo() == 3 ? "Domicilio" : "Propina Voluntaria");
                    txtTemporal.setText(numeroDecimales(fa.getTipo() == 1 ? ((float) getPrecio(id_fact) * 0.1) : (fa.getTotal())));
                    if (fa.getTipo() == 1) {
                        opcConPropina.setSelected(true);
                    } else if (fa.getTipo() == 3) {
                        optDomicilio.setSelected(true);
                    } else if (fa.getTipo() == 4) {
                        optVoluntaria.setSelected(true);
                    }
                    this.setSemaforo_voluntaria(false);
                }
            }
        }
    }

    public void identificadorOpciones() {
        int id_fact = getIdFactura();
        if (id_fact == 0) {
            return;
        }
        setDb(FabricaDaos.producirPersistenia(8));
        logica.Factura f = (logica.Factura) getDb().getDato(id_fact);
        actualizarOpciones(f.getTipo());
    }

    public void actualizarOpciones(int o) {
        if (o == 1) {
            getOpcConPropina().setSelected(true);
        } else if (o == 2) {
            getOpcSinPropina().setSelected(true);
        } else if (o == 3) {
            getOptDomicilio().setSelected(true);
        } else if (o == 4) {
            optVoluntaria.setSelected(true);
        }
    }

    public void imprimirFactura() {
        this.setDb(FabricaDaos.producirPersistenia(8));
        int id_fac = getIdFactura();
        if (id_fac > 0) {
            if (this.getDb().getTotal(id_fac) > 0) {
                /*logica.impresion.Imprimir im = new logica.impresion.Imprimir(this);
                 im.main();*/
                logica.impresion.Imprimir im = new logica.impresion.Imprimir();
                im.main(id_fac);
            } else {
                JOptionPane.showMessageDialog(null, "No existe en la factura para imprimir.", "Imprimir Factura", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existe factura para imprimir.", "Imprimir Factura", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrarCuenta;
    private javax.swing.JButton btnCobro;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private org.jdatepicker.impl.DateComponentFormatter dateComponentFormatter1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private org.jdatepicker.util.JDatePickerUtil jDatePickerUtil1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbTemporal;
    private javax.swing.JRadioButton opcConPropina;
    private javax.swing.JRadioButton opcSinPropina;
    private javax.swing.JRadioButton optDomicilio;
    private javax.swing.JRadioButton optVoluntaria;
    private javax.swing.JTextField txtCambio;
    private javax.swing.JTextField txtEfectivo;
    private javax.swing.JTextField txtIdFactura;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTemporal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
