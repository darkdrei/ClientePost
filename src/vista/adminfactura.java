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
import javax.swing.JList;
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
public class adminfactura extends javax.swing.JPanel {

    private String nom;
    private int id_mesa;
    private Vector<Integer> id_cat = new Vector<>();
    private Vector<Integer> id_tipo = new Vector<>(2);
    private Vector<Integer> id_plato = new Vector<>();
    private logica.db.BaseDao db;
    private String nom_tipo[];
    private String nom_plato[];
    Object m[][];
    private boolean semaforo_voluntaria = false;

    public boolean getSemaforo_voluntaria() {
        return semaforo_voluntaria;
    }

    public void setSemaforo_voluntaria(boolean semaforo_voluntaria) {
        this.semaforo_voluntaria = semaforo_voluntaria;
    }

    public void actualizarLista(int id, int d, int m, int a) {
        logica.db.Factura f = new logica.db.Factura();
        lista.setModel(new javax.swing.AbstractListModel() {
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
        logica.db.Factura fuh = new Factura();
        fuh.getIdFactura(0, 0, 9, 2015);
        this.setId_factura(f.getIdFactura(id, d, m, a));

    }

    private Vector<Integer> id_factura = new Vector<>();

    public Vector<Integer> getId_factura() {
        return id_factura;
    }

    public void setId_factura(Vector<Integer> id_factura) {
        this.id_factura = id_factura;
    }

    /**
     * Creates new form factura
     */
    public adminfactura() {
        initComponents();
        actualizarLista(0, 0, 0, 0);
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
        cbCategoria.setModel(new javax.swing.DefaultComboBoxModel(cat_nom));

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

    public adminfactura(String nom) {
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

    public adminfactura(String nom, int id) {
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
        cbCategoria.setModel(new javax.swing.DefaultComboBoxModel(cat_nom));
        enableCbOtros();
        actualizarMatriz();
        actualizarTotalFactuta();
        identificadorOpciones();
    }

    public String numeroDecimales(double x) {
        DecimalFormat decimales = new DecimalFormat("0.000");
        return "" + decimales.format(x);
    }

    public int getIdFactura() {
        return id_factura.get(lista.getSelectedIndex());
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
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbCategoria = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cbPlato = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jSCantidad = new javax.swing.JSpinner();
        btnAgregar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnNewFactura = new javax.swing.JButton();
        btnEraseFactura = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        fecha = new com.toedter.calendar.JDateChooser();
        txtIdFactura = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lista = new javax.swing.JList();
        rdFecha = new javax.swing.JRadioButton();
        rdFactura = new javax.swing.JRadioButton();
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

        jLabel1.setText("Categoria");

        cbCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCategoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCategoriaItemStateChanged(evt);
            }
        });
        cbCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCategoriaActionPerformed(evt);
            }
        });

        jLabel2.setText("Tipo");

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--  Seleccione  --" }));
        cbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoActionPerformed(evt);
            }
        });

        jLabel3.setText("Plato");

        cbPlato.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--  Seleccione  --" }));
        cbPlato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPlatoActionPerformed(evt);
            }
        });

        jLabel4.setText("Cantidad");

        jSCantidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSCantidadStateChanged(evt);
            }
        });
        jSCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jSCantidadKeyPressed(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, null, new java.awt.Color(70, 78, 96)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 256, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btnNewFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/new.png"))); // NOI18N
        btnNewFactura.setText("Nueva Factura");
        btnNewFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewFacturaActionPerformed(evt);
            }
        });

        btnEraseFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/delete.png"))); // NOI18N
        btnEraseFactura.setText("Borrar Factura");
        btnEraseFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEraseFacturaActionPerformed(evt);
            }
        });

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(70, 78, 96)));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 261, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/searc.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        lista.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaMouseClicked(evt);
            }
        });
        lista.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(lista);

        buttonGroup3.add(rdFecha);
        rdFecha.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        rdFecha.setText("Fecha");
        rdFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdFechaActionPerformed(evt);
            }
        });

        buttonGroup3.add(rdFactura);
        rdFactura.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        rdFactura.setText("#  Factura");
        rdFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdFacturaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(18, 18, 18))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2))
                                    .addGap(21, 21, 21)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnAgregar)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cbCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbPlato, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(5, 5, 5)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnEraseFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnNewFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rdFecha))
                            .addGap(10, 10, 10)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(rdFactura)
                                .addComponent(txtIdFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, 0)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdFecha)
                            .addComponent(rdFactura))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbPlato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jSCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(btnAgregar)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnNewFactura)
                .addGap(0, 0, 0)
                .addComponent(btnEraseFactura)
                .addGap(5, 5, 5))
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
                "Title 1", "Title 2", "Title 3", "Title 4"
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
                        .addGap(46, 46, 46)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                        .addGap(8, 8, 8))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lbTemporal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(txtTemporal)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSubTotal)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lbTemporal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(9, 9, 9))
                    .addComponent(txtTemporal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                        .addComponent(txtCambio))
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
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
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
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addGap(4, 4, 4)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

    private void jSCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSCantidadKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jSCantidadKeyPressed

    private void jSCantidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSCantidadStateChanged
        // TODO add your handling code here:
        if (((int) jSCantidad.getValue()) < 1 && cbPlato.getSelectedIndex() > 0) {
            jSCantidad.setValue(1);
        } else if (cbPlato.getSelectedIndex() == 0) {
            jSCantidad.setValue(0);
        }
    }//GEN-LAST:event_jSCantidadStateChanged

    private void opcConPropinaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_opcConPropinaStateChanged
        if (opcConPropina.isSelected()) {
            setDb(FabricaDaos.producirPersistenia(8));
            if (this.getId_factura().size() == 0 || list.getSelectedIndices().length <= 0) {                            
//            JOptionPane.showMessageDialog(this, "Llego  "+this.getId_factura().size()+"   "+list.getSelectedIndices().length);
                return;
            }
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
            if (this.getId_factura().size() == 0) {
                return;
            }
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
        cbPlato.setEnabled(false);
        cbPlato.setSelectedIndex(0);
        cbTipo.setSelectedIndex(0);
        cbTipo.setEnabled(false);
        jSCantidad.setValue(0);
        jSCantidad.setEnabled(false);
        btnAgregar.setEnabled(false);
        cbCategoria.requestFocusInWindow();
    }
    private void cbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCategoriaActionPerformed
        // TODO add your handling code here:
        if (cbCategoria.getSelectedIndex() == 0) {
            cbTipo.setEnabled(false);
        } else {
            setDb(FabricaDaos.producirPersistenia(3));
            ArrayList<Object> tipos = getDb().getDatos(getId_cat().get(cbCategoria.getSelectedIndex() - 1));
            if (tipos.size() > 0) {
                nom_tipo = new String[tipos.size() + 1];
                nom_tipo[0] = "-- Seleccione --";
                id_tipo.clear();
                int i = 1;
                for (Object tipo : tipos) {
                    logica.Tipo tem_tipo = (logica.Tipo) tipo;
                    nom_tipo[i] = tem_tipo.getNombre();
                    id_tipo.add(tem_tipo.getId());
                    i++;
                }
                cbTipo.setModel(new javax.swing.DefaultComboBoxModel(nom_tipo));
                cbTipo.setEnabled(true);
            } else {
                this.setEnabled(false);
                //JOptionPane.showMessageDialog(null, "No existen tipos de platos para esta categoria");
                Thread h1 = new Thread(new ProducirMensaje("Seleccion Categoria", "No existen tipos de platos para esta categoria", JOptionPane.INFORMATION_MESSAGE, this));
                h1.start();
                cbTipo.setEnabled(false);
                cbTipo.setSelectedIndex(0);
                enableCbOtros();
            }
            cbPlato.setEnabled(false);
            cbPlato.setSelectedIndex(0);
        }
    }//GEN-LAST:event_cbCategoriaActionPerformed

    class ProducirMensaje implements Runnable {

        private String titulo;
        private String men;
        private int icono;
        private adminfactura f;

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

        public adminfactura getF() {
            return f;
        }

        public void setF(adminfactura f) {
            this.f = f;
        }

        public ProducirMensaje(String titulo, String men, int icono, adminfactura f) {
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
        private adminfactura base;
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

        public ConvertirMensage(float valor, String mesage, String titulo, adminfactura base, int id_fact, int opcion, JRadioButton option) {
            this.valor = valor;
            this.mesage = mesage;
            this.titulo = titulo;
            this.base = base;
            this.id_fact = id_fact;
            this.opcion = opcion;
            this.option = option;
        }

        public ConvertirMensage(String mesage, String titulo, adminfactura base, int id_fact, int opcion, JRadioButton option) {
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

        public adminfactura getBase() {
            return base;
        }

        public int getId_fact() {
            return id_fact;
        }

        public void setId_fact(int id_fact) {
            this.id_fact = id_fact;
        }

        public ConvertirMensage(String mesage, adminfactura base, int id_fact) {
            this.mesage = mesage;
            this.base = base;
            this.id_fact = id_fact;
        }

        public void setBase(adminfactura base) {
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
                    Logger.getLogger(adminfactura.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (!tem_res);
            this.getBase().setEnabled(true);
            this.getBase().actualizarTotalFactuta();
            setSemaforo_voluntaria(false);
        }

    }
    private void cbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoActionPerformed
        // TODO add your handling code here:
        if (cbTipo.getSelectedIndex() == 0) {
            deshabilitarCantidad(false);
            cbPlato.requestFocusInWindow();
        } else {
            setDb(FabricaDaos.producirPersistenia(4));
            ArrayList<Object> pla = getDb().getDatos(getId_tipo().get(cbTipo.getSelectedIndex() - 1));
            if (pla.size() > 0) {
                nom_plato = new String[pla.size() + 1];
                nom_plato[0] = "-- Seleccione --";
                id_plato.clear();
                int i = 1;
                for (Object tipo : pla) {
                    logica.Plato tem_plato = (logica.Plato) tipo;
                    nom_plato[i] = tem_plato.getNombre();
                    id_plato.add(tem_plato.getId());
                    i++;
                }
                cbPlato.setModel(new javax.swing.DefaultComboBoxModel(nom_plato));
                cbPlato.setEnabled(true);
            } else {
                this.setEnabled(false);
                Thread h2 = new Thread(new ProducirMensaje("Seleccionar Tipo", "No existen platos relacionados a este tipo.", JOptionPane.INFORMATION_MESSAGE, this));
                h2.start();
                deshabilitarCantidad(false);
                cbPlato.requestFocusInWindow();
            }
        }
    }//GEN-LAST:event_cbTipoActionPerformed

    private void cbCategoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCategoriaItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCategoriaItemStateChanged

    private void cbPlatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPlatoActionPerformed
        // TODO add your handling code here:
        if (cbPlato.getSelectedIndex() > 0) {
            deshabilitarCant(true);
            jSCantidad.setValue(1);
            jSCantidad.requestFocusInWindow();
        } else {
            jSCantidad.setValue(0);
            deshabilitarCant();
        }
    }//GEN-LAST:event_cbPlatoActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        this.setDb(FabricaDaos.producirPersistenia(8));
        System.out.println("1");
        ArrayList<Object> f = getDb().getDatos(id_mesa);
        int id_fac;
        if (f.size() > 0) {
            ArrayList<Object> o = new ArrayList<>();
            o.add(id_mesa);
            id_fac = (boolean) (db.validInsert(o)[0]) ? db.getEndObjeto(id_mesa) : 0;
        } else {
            this.getDb().insertarDato(new logica.Factura(id_mesa, 0));
            id_fac = this.getDb().getEndObjeto(id_mesa);
        }
        System.out.println("2 "+id_fac);
        this.setDb(FabricaDaos.producirPersistenia(9));
        this.getDb().insertarDato(new logica.ConteFac(id_plato.get(cbPlato.getSelectedIndex() - 1), (int) jSCantidad.getValue(), getId_factura().get(lista.getSelectedIndex())));
        actualizarMatriz();
        modificarTablaDescripcion();
        deshabilitarCant();
        actualizarPrecio();
        actualizarTotalFactuta();
        cbPlato.setSelectedIndex(0);
        jSCantidad.setValue(0);
        System.out.println("3");
    }//GEN-LAST:event_btnAgregarActionPerformed

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

    private void btnEraseFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEraseFacturaActionPerformed
        // TODO add your handling code here:
        int r = JOptionPane.showConfirmDialog(null, "Esta seguro que desea borrar el contenido de la factura?", "Contenido Factura", JOptionPane.YES_NO_OPTION);
        if (r != JOptionPane.YES_OPTION) {
            return;
        }
        setDb(FabricaDaos.producirPersistenia(9));
        getDb().deleteDato(getIdFactura());
        enableCbOtros();
        actualizarFactura();
    }//GEN-LAST:event_btnEraseFacturaActionPerformed

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
        cbCategoria.requestFocusInWindow();
    }

    private void btnNewFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewFacturaActionPerformed
        // TODO add your handling code here:
        int r = JOptionPane.showConfirmDialog(null, "Esta seguro que desea borrar la factura?", "Factura", JOptionPane.YES_NO_OPTION);
        if (r != JOptionPane.YES_OPTION) {
            return;
        }
        newFactura();
    }//GEN-LAST:event_btnNewFacturaActionPerformed

    public void limpiarResultados() {
        txtEfectivo.setText("0.0");
        txtCambio.setText("0.0");
    }

    public void finalizarFactura() {
        int id_fac = getIdFactura();
        if (id_fac == 0) {
            JOptionPane.showMessageDialog(null, "No Existe factura que cobrar.", "Cobro Cuenta", JOptionPane.INFORMATION_MESSAGE);
            cbCategoria.requestFocusInWindow();
            return;
        }
        setDb(FabricaDaos.producirPersistenia(9));
        if (getDb().getTotal(id_fac) == 0) {
            JOptionPane.showMessageDialog(null, "No Existe contenido en la factura que cobrar.", "Cobro Cuenta", JOptionPane.INFORMATION_MESSAGE);
            cbCategoria.requestFocusInWindow();
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
        cobroFactura();
    }//GEN-LAST:event_btnCobroActionPerformed
public void cerrarPagoFactura() {
        int id_fac = getIdFactura();
        if (id_fac == 0) {
            JOptionPane.showMessageDialog(null, "No Existe factura que cobrar.", "Cobro Cuenta", JOptionPane.INFORMATION_MESSAGE);
            cbCategoria.requestFocusInWindow();
            return;
        }
        setDb(FabricaDaos.producirPersistenia(9));
        if (getDb().getTotal(id_fac) == 0) {
            JOptionPane.showMessageDialog(null, "No Existe contenido en la factura que cobrar.", "Cobro Cuenta", JOptionPane.INFORMATION_MESSAGE);
            cbCategoria.requestFocusInWindow();
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
                    finalizacionCerrarFactura();
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
    private void btnCerrarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarCuentaActionPerformed
        // TODO add your handling code here:
        cerrarPagoFactura();
    }//GEN-LAST:event_btnCerrarCuentaActionPerformed
public void cobroFactura() {
        int id_fac = getIdFactura();
        if (id_fac == 0) {
            JOptionPane.showMessageDialog(null, "No Existe factura que cobrar.", "Cobro Cuenta", JOptionPane.INFORMATION_MESSAGE);
            cbCategoria.requestFocusInWindow();
            return;
        }
        setDb(FabricaDaos.producirPersistenia(9));
        if (getDb().getTotal(id_fac) == 0) {
            JOptionPane.showMessageDialog(null, "No Existe contenido en la factura que cobrar.", "Cobro Cuenta", JOptionPane.INFORMATION_MESSAGE);
            cbCategoria.requestFocusInWindow();
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
    public void finalizacionCerrarFactura() {
        int id_fact = getIdFactura();
        if (id_fact == 0)
            return;
        setDb(FabricaDaos.producirPersistenia(8));
        getDb().sateDato(id_fact);
        txtTemporal.setText("0");
        enableCbOtros();
        actualizarFactura();
        cbCategoria.requestFocusInWindow();
    }
    
    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
        imprimirFactura();
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void opcConPropinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcConPropinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcConPropinaActionPerformed

    private void listaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_listaMouseClicked
private JList list;
    private void listaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaValueChanged
        // TODO add your handling code here:
        list = (JList) evt.getSource();
        if (list.getSelectedIndices().length == 1) {
            actualizarPrecio();
            enableCbOtros();
            actualizarMatriz();
            actualizarTotalFactuta();
            identificadorOpciones();
        } else if(list.getSelectedIndices().length > 0) {
            int index[] = list.getSelectedIndices();
            for (int j : index) {
                lista.setSelectedIndex(j);
            }
            JOptionPane.showMessageDialog(this, "Debe seleccionar un solo elemento de la lista.", "MODIFICAR FACTURA", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_listaValueChanged

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        if (rdFactura.isSelected()) {
            if (txtIdFactura.getText().length() > 0) {
                if (!Validadora.validarCadena(5, txtIdFactura.getText())) {
                    txtIdFactura.setText("");
                    txtIdFactura.requestFocus();
                    JOptionPane.showMessageDialog(this, "Debe digitar solo numeros.", "MODIFICAR FACTURA", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
            actualizarLista(txtIdFactura.getText().length() == 0 ? 0 : Integer.parseInt(txtIdFactura.getText()),
                    0,
                    0,
                    0);
            logica.db.Factura fac = new logica.db.Factura();
            this.setId_factura(fac.getIdFactura(txtIdFactura.getText().length() == 0 ? 0 : Integer.parseInt(txtIdFactura.getText()), 0, 0, 0));
        } else if (rdFecha.isSelected()) {
            actualizarLista(0,
                    fecha.getJCalendar().getDayChooser().getDay(),
                    fecha.getJCalendar().getMonthChooser().getMonth() + 1,
                    fecha.getJCalendar().getYearChooser().getYear());
            logica.db.Factura fac = new logica.db.Factura();
            this.setId_factura(fac.getIdFactura(0, fecha.getJCalendar().getDayChooser().getDay(),
                    fecha.getJCalendar().getMonthChooser().getMonth() + 1,
                    fecha.getJCalendar().getYearChooser().getYear())
            );
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void rdFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdFacturaActionPerformed
        actualizarLista(0, 0, 0, 0);
        logica.db.Factura fac = new logica.db.Factura();
        this.setId_factura(fac.getIdFactura(0, 0, 0, 0));
    }//GEN-LAST:event_rdFacturaActionPerformed

    private void rdFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdFechaActionPerformed
        // TODO add your handling code here:
        actualizarLista(0, 0, 0, 0);
        logica.db.Factura fac = new logica.db.Factura();
        this.setId_factura(fac.getIdFactura(0, 0, 0, 0));
        rdFactura.setText("");
    }//GEN-LAST:event_rdFechaActionPerformed

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
        cbPlato.setEnabled(r);
        cbPlato.setSelectedIndex(0);
    }

    public void deshabilitarCant(boolean r) {
        jSCantidad.setValue(0);
        jSCantidad.setEnabled(r);
        btnAgregar.setEnabled(r);

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
        jSCantidad.setValue(0);
        jSCantidad.setEnabled(false);
        btnAgregar.setEnabled(false);
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
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrarCuenta;
    private javax.swing.JButton btnCobro;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEraseFactura;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnNewFactura;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox cbCategoria;
    private javax.swing.JComboBox cbPlato;
    private javax.swing.JComboBox cbTipo;
    private com.toedter.calendar.JDateChooser fecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JSpinner jSCantidad;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbTemporal;
    private javax.swing.JList lista;
    private javax.swing.JRadioButton opcConPropina;
    private javax.swing.JRadioButton opcSinPropina;
    private javax.swing.JRadioButton optDomicilio;
    private javax.swing.JRadioButton optVoluntaria;
    private javax.swing.JRadioButton rdFactura;
    private javax.swing.JRadioButton rdFecha;
    private javax.swing.JTextField txtCambio;
    private javax.swing.JTextField txtEfectivo;
    private javax.swing.JTextField txtIdFactura;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTemporal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
