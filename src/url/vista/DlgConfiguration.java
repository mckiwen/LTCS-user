package url.vista;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import url.controlador.Control;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.IllegalBlockSizeException;

import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Alberto J. Prieto Antunez
 * @version 17.01.2022
 */
public class DlgConfiguration extends javax.swing.JDialog implements Serializable {

    /**
     * Creates new form dlgConfiguration
     *
     */
    Timer t;
    public Control ctrl;

    /**
     * This function launch the components of the DlgConfiguration dialog.
     *
     * @param parent owner the {@code Frame} from which the dialog is displayed
     * @param modal specifies whether dialog blocks user input to other
     * top-level windows when shown. If {@code true}, the modality type property
     * is set to {@code DEFAULT_MODALITY_TYPE}, otherwise the dialog is
     * modeless.
     */
    public DlgConfiguration(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();
    }

    /**
     * This function sets the controller of the system to DlgConfiguration.
     *
     * @param ctrl controller object.
     */
    public void setControlador(Control ctrl) {
        this.ctrl = ctrl;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgProtocol = new javax.swing.ButtonGroup();
        pnlDatos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCoordFilePath = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtFtpSever = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtUpdateSec = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtFtpPath = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtFtpUser = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        pwdPassword = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        txtUrlFile = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtPort = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jrbFTPS = new javax.swing.JRadioButton();
        jrbSFTP = new javax.swing.JRadioButton();
        btnFileChooser = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtAutoCoordsTime = new javax.swing.JTextField();
        pnlBotones = new javax.swing.JPanel();
        btnConfirm = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configuration");
        setResizable(false);
        setSize(new java.awt.Dimension(1000, 1000));

        pnlDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("Configuration"));

        jLabel1.setText("Coordinates File Path:");

        jLabel2.setText("Server: ");

        txtFtpSever.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setText("Semi-Automatic Mode Update Rate (seconds):");

        txtUpdateSec.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUpdateSec.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel4.setText("Upload Path: ");

        txtFtpPath.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel5.setText("User: ");

        txtFtpUser.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setText("Password:");

        pwdPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setText("Output File Name:");

        txtUrlFile.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setText("Port:");

        txtPort.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel9.setText("Protocol:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel10.setText("2. SFTP: Port 1001");

        jLabel11.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel11.setText("1. FTPS: Port 21");

        btgProtocol.add(jrbFTPS);
        jrbFTPS.setSelected(true);
        jrbFTPS.setText("FTPS");
        jrbFTPS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbFTPSActionPerformed(evt);
            }
        });

        btgProtocol.add(jrbSFTP);
        jrbSFTP.setText("SFTP");
        jrbSFTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbSFTPActionPerformed(evt);
            }
        });

        btnFileChooser.setText("Browse..");
        btnFileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFileChooserActionPerformed(evt);
            }
        });

        jLabel12.setText("Coordinates reading time (seconds):");

        txtAutoCoordsTime.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAutoCoordsTime.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout pnlDatosLayout = new javax.swing.GroupLayout(pnlDatos);
        pnlDatos.setLayout(pnlDatosLayout);
        pnlDatosLayout.setHorizontalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtAutoCoordsTime, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtUrlFile, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtUpdateSec, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDatosLayout.createSequentialGroup()
                                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addGap(22, 22, 22)
                                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtFtpSever)
                                    .addComponent(txtFtpPath, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlDatosLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtFtpUser, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(pwdPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(pnlDatosLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlDatosLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(26, 26, 26)
                                .addComponent(jrbFTPS)
                                .addGap(18, 18, 18)
                                .addComponent(jrbSFTP))
                            .addComponent(jLabel11)))
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addComponent(txtCoordFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnFileChooser)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDatosLayout.setVerticalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtUrlFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(txtAutoCoordsTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCoordFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFileChooser))
                .addGap(45, 45, 45)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtFtpSever, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtFtpPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jrbFTPS)
                    .addComponent(jrbSFTP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtFtpUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(pwdPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtUpdateSec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        pnlBotones.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        btnConfirm.setText("Confirm");
        btnConfirm.setPreferredSize(new java.awt.Dimension(100, 30));
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });
        pnlBotones.add(btnConfirm);

        btnClose.setText("Close");
        btnClose.setPreferredSize(new java.awt.Dimension(100, 30));
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        pnlBotones.add(btnClose);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBotones, javax.swing.GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE)
            .addComponent(pnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * This function close the DlgConfiguration in case of confirmation.
     *
     * @param evt event on click.
     */
    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:

        int resultado = JOptionPane.showConfirmDialog(this, "Are you sure you want to close this window?", "Information", JOptionPane.YES_NO_OPTION);
        if (resultado == JOptionPane.YES_OPTION) {
            dispose();
        }
    }//GEN-LAST:event_btnCloseActionPerformed

    /**
     * This function confirm the configuration to save it into a file.
     *
     * @param evt on click event.
     */
    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        int resultado = JOptionPane.showConfirmDialog(this, "Are you sure you want to confirm?", "Information", JOptionPane.YES_NO_OPTION);
        if (resultado == JOptionPane.YES_OPTION) {
            ctrl.ConfirmConfig();
            try {
                ctrl.saveData();
            } catch (IOException | IllegalBlockSizeException ex) {
                Logger.getLogger(DlgConfiguration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnConfirmActionPerformed
    /**
     * This function set the txtPort to 1001 in case of SFTP protocol is
     * selected.
     *
     * @param evt on click event.
     */
    private void jrbSFTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbSFTPActionPerformed
        txtPort.setText("1001");
    }//GEN-LAST:event_jrbSFTPActionPerformed

    /**
     * This function set the txtPort to 21 in case of FTPS protocol is selected.
     *
     * @param evt on click event.
     */
    private void jrbFTPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbFTPSActionPerformed
        txtPort.setText("21");
    }//GEN-LAST:event_jrbFTPSActionPerformed

    /**
     * This function write the path of the coordinates.txt to the txt
     * UrlFilePath for a file chosen at the JFileChooser. The coordinates.txt
     * file is used for the automation of the telescope coordinates in
     * generating the URL file.
     *
     * @param evt file chosen event.
     */
    private void btnFileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFileChooserActionPerformed
        // Create JFileChooser Object
        JFileChooser fileChooser = new JFileChooser();
        // Set file filter .txt
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
        fileChooser.setFileFilter(filter);

        // Save selected option
        int seleccion = fileChooser.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            // Save File Path
            File fichero = fileChooser.getSelectedFile();
            this.txtCoordFilePath.setText(fichero.getAbsolutePath());
        }
    }//GEN-LAST:event_btnFileChooserActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgProtocol;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnFileChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JRadioButton jrbFTPS;
    public javax.swing.JRadioButton jrbSFTP;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlDatos;
    public javax.swing.JPasswordField pwdPassword;
    public javax.swing.JTextField txtAutoCoordsTime;
    public javax.swing.JTextField txtCoordFilePath;
    public javax.swing.JTextField txtFtpPath;
    public javax.swing.JTextField txtFtpSever;
    public javax.swing.JTextField txtFtpUser;
    public javax.swing.JTextField txtPort;
    public javax.swing.JTextField txtUpdateSec;
    public javax.swing.JTextField txtUrlFile;
    // End of variables declaration//GEN-END:variables
}
