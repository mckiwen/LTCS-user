package url.controlador;

import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPSClient;

import com.jcraft.jsch.*;

import Utilidades.Comprueba;
import Utilidades.Utils;

import java.awt.Color;

import url.modelo.URL;
import url.modelo.Configuration;
import url.modelo.ListOfTelescopes;
import url.modelo.Telescope;

import url.vista.DlgUrlData;
import url.vista.FrmPrincipal;
import url.vista.DlgAbout;
import url.vista.DlgConfiguration;
import url.vista.DlgListOfTelescopes;
import url.vista.DlgSetup;

import java.text.SimpleDateFormat;

import java.time.Instant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.crypto.IllegalBlockSizeException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author Alberto J. Prieto Antunez
 * @version 17.01.2022
 */
public class Control implements Serializable {

    FrmPrincipal frmPpal;
    DlgUrlData dlgUrlData;
    DlgConfiguration dlgConfiguration;
    DlgAbout dlgAbout;
    Configuration configuration;
    DlgListOfTelescopes dlgListOfTelescopes;

    Boolean hayCambios;
    private ListOfTelescopes listOfTelescopes;
    private DlgSetup dlgSetup;

    /**
     * If there are any changes, this sets the parameter "hayCambios" to true.
     *
     * @param hayCambios true if there are any change, false otherwise.
     */
    public void setHayCambios(Boolean hayCambios) {
        this.hayCambios = hayCambios;
    }

    /**
     * It sets the Frame Principal of the GUI.
     *
     * @param frmPpal the Frame Principal class.
     */
    public void setFrmPpal(FrmPrincipal frmPpal) {
        this.frmPpal = frmPpal;
    }

    /**
     * It sets the Dialog for the URL Data of the GUI class.
     *
     * @param dlgUrlData URL Data Dialog.
     */
    public void setDlgUrlData(DlgUrlData dlgUrlData) {
        this.dlgUrlData = dlgUrlData;
    }

    /**
     * It sets the Dialog for the configuration class.
     *
     * @param dlgConfiguration Configuration Dialog.
     */
    public void setConfiguration(DlgConfiguration dlgConfiguration) {
        this.dlgConfiguration = dlgConfiguration;
    }

    /**
     * It sets the configuration class.
     *
     * @param configuration Configuration object.
     */
    public void setClassConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * It sets the About Dialog class.
     *
     * @param dlgAbout About Dialog.
     */
    public void setDlgAbout(DlgAbout dlgAbout) {
        this.dlgAbout = dlgAbout;
    }

    /**
     * It sets the List of Telescope Dialog class.
     *
     * @param dlgListOfTelescopes List of Telescope Dialog.
     */
    public void setDlgListOfTelescopes(DlgListOfTelescopes dlgListOfTelescopes) {
        this.dlgListOfTelescopes = dlgListOfTelescopes;
    }

    /**
     * It shows the URL Data Dialog to the user.
     */
    public void mostrarDlgUrlData() {
        this.dlgUrlData.setVisible(true);
    }

    /**
     * It shows the Configuration Dialog to the user.
     */
    public void mostrarDlgConfiguration() {
        this.dlgConfiguration.setVisible(true);
    }

    /**
     * It shows the About Dialog to the user.
     */
    public void mostrarDlgAbout() {
        this.dlgAbout.setVisible(true);
    }

    /**
     * It shows the List of Telescope Dialog to the user.
     */
    public void mostrarDlgListOfTelescopes() {
        this.dlgListOfTelescopes.setVisible(true);
    }

    /**
     * It sets the List of Telescope class.
     *
     * @param listOfTelescopes Telescope list.
     */
    public void setListOfTelescopes(ListOfTelescopes listOfTelescopes) {
        this.listOfTelescopes = listOfTelescopes;
    }

    /**
     * It sets the setup Dialog class.
     *
     * @param dlgSetup setup Dialog.
     */
    public void setDlgSetup(DlgSetup dlgSetup) {
        this.dlgSetup = dlgSetup;
    }

    /**
     * It shows the setup Dialog to the user.
     */
    public void mostrarDlgSetup() {
        this.dlgSetup.setVisible(true);
    }

    /**
     * Main method to refresh ListModels, ComboBoxModels, etc.
     */
    public void refreshDatosVistas() {
        if (hayCambios) {

            // List of Telescope JTable
            JTable tblTelescopes = dlgListOfTelescopes.getTblTelescopes();
            limpiarDtmTelescopesTable(tblTelescopes);
            loadTelescopeTable(tblTelescopes);

            // URL Data Dialog Combobox.
            DefaultComboBoxModel cbmUrl = this.dlgUrlData.getCbm();
            limpiarCbmTelescopesUrl(cbmUrl);
            cargarTelescopesCbmUrl(cbmUrl);

            // URL Setup Dialog Combobox.
            DefaultComboBoxModel cbmSetup = this.dlgSetup.getCbm();
            limpiarCbmTelescopesSetup(cbmSetup);
            cargarTelescopesCbmSetup(cbmSetup);

        }
        hayCambios = false;
    }

    /**
     * createURL checks all the parameters needed by the URL file of the LTCS,
     * it writes them into a local URL file and uploads it to a server throught
     * FPTS or SFTP protocol.The protocol is choosen by the user at the
     * Configuration Dialog.
     *
     * @param telescope telescope name
     * @param raHour Right Ascension hours
     * @param raMin Right Ascension Minutes
     * @param raSec Right Ascension Seconds
     * @param DecDeg Declination Degrees
     * @param DecMin Declination Minutes
     * @param DecSec Declination Seconds
     * @param equinox Epoch of the coordinates
     * @param fov Field of view of the instrument
     * @param laserSensitive whether the telescope is laser sensitive or not
     * @param laserState state of the laser
     * @param logData it allows the LTCS to log pointing data during a
     * collision.
     * @throws java.io.IOException
     */
    public void createUrl(String telescope, String raHour, String raMin, String raSec, String DecDeg, String DecMin, String DecSec, String equinox, String fov, String laserSensitive, String laserState, String logData) throws IOException, Exception {

        //RA
        String flagRa = "0";
        String ra = Utils.coord2float(raHour, raMin, raSec);
        if (ra == null) {
        } else if (Comprueba.hourCheck(raHour) && Comprueba.minCheck(raMin) && Comprueba.secCheck(raSec)) {
            flagRa = "1";
        }

        if ("1".equals(flagRa)) {
            dlgUrlData.lblRaStatus.setText("OK");
            dlgUrlData.lblRaStatus.setForeground(Color.GREEN.darker());
        } else {
            dlgUrlData.lblRaStatus.setText("WRONG INPUT");
            dlgUrlData.lblRaStatus.setForeground(Color.RED);
        }

        //DEC
        String flagDec = "0";
        String dec = Utils.coord2float(DecDeg, DecMin, DecSec);
        if (dec == null) {
        } else if (Comprueba.degCheck(DecDeg) && Comprueba.minCheck(DecMin) && Comprueba.secCheck(DecSec)) {
            flagDec = "1";
        }

        if ("1".equals(flagDec)) {
            dlgUrlData.lblDecStatus.setText("OK");
            dlgUrlData.lblDecStatus.setForeground(Color.GREEN.darker());
        } else {
            dlgUrlData.lblDecStatus.setText("WRONG INPUT");
            dlgUrlData.lblDecStatus.setForeground(Color.RED);
        }

        //EQUINOX
        String flagEquinox = "0";
        if (Comprueba.EquinoxLimits(equinox) && Comprueba.EquinoxFormat(equinox)) {
            flagEquinox = "1";
            dlgUrlData.lblEquinoxStatus.setText("OK");
            dlgUrlData.lblEquinoxStatus.setForeground(Color.GREEN.darker());
        } else {
            dlgUrlData.lblEquinoxStatus.setText("WRONG INPUT");
            dlgUrlData.lblEquinoxStatus.setForeground(Color.RED);
        }

        //FOV
        String flagFov = "0";
        if (Comprueba.FovLimits(fov) && Comprueba.FovFormat(fov)) {
            flagFov = "1";
            dlgUrlData.lblFovStatus.setText("OK");
            dlgUrlData.lblFovStatus.setForeground(Color.GREEN.darker());
        } else {
            dlgUrlData.lblFovStatus.setText("WRONG INPUT");
            dlgUrlData.lblFovStatus.setForeground(Color.RED);
        }

        //LASER IMPACTED
        String laserImpacted = laserSensitive;
        if ("YES".equals(laserImpacted)) {
            dlgUrlData.lblLaserImpactedStatus.setText("YES");
            dlgUrlData.lblLaserImpactedStatus.setForeground(Color.GREEN.darker());
        } else {
            dlgUrlData.lblLaserImpactedStatus.setText("NO");
            dlgUrlData.lblLaserImpactedStatus.setForeground(Color.RED);
        }

        //LASER STATE
        String laser_state = laserState;
        if (null != laser_state) {
            switch (laser_state) {
                case "OFF":
                    dlgUrlData.lblLaserStateStatus.setText("OFF");
                    dlgUrlData.lblLaserStateStatus.setForeground(Color.RED);
                    break;
                case "ON":
                    dlgUrlData.lblLaserStateStatus.setText("ON");
                    dlgUrlData.lblLaserStateStatus.setForeground(Color.GREEN.darker());
                    break;
                case "ON-SKY":
                    dlgUrlData.lblLaserStateStatus.setText("ON-SKY");
                    dlgUrlData.lblLaserStateStatus.setForeground(Color.ORANGE.darker());
                    break;
                default:
                    break;
            }
        }

        //LOG DATA
        String log_data = logData;
        if ("ON".equals(log_data)) {
            dlgUrlData.lblLogDataStatus.setText("ON");
            dlgUrlData.lblLogDataStatus.setForeground(Color.GREEN.darker());
        } else {
            dlgUrlData.lblLogDataStatus.setText("OFF");
            dlgUrlData.lblLogDataStatus.setForeground(Color.RED);
        }

        //TIMESTAMP
        Instant instant = Instant.now();
        long timestampLong = instant.toEpochMilli() / 1000;
        String timestamp = Long.toString(timestampLong);

        if ("1".equals(flagRa) && "1".equals(flagDec) && "1".equals(flagEquinox) && "1".equals(flagFov)) {
            // URL FILE
            URL url = new URL(telescope, ra, dec, equinox, fov, laserImpacted, laser_state, log_data);
            File fichero = new File(this.configuration.getFileName());
            try {
                fichero.createNewFile();
            } catch (IOException ex) {
            }

            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(fichero));
            } catch (IOException ex) {
            }

            bw.write("TIMESTAMP1 = " + timestamp);
            bw.write("\nTELESCOPE = " + url.getTelescope());
            bw.write("\nRA = " + url.getRa());
            bw.write("\nDEC = " + url.getDec());
            bw.write("\nEQUINOX = " + equinox);
            bw.write("\nFOV = " + fov);
            bw.write("\nLASER_IMPACTED = " + laserImpacted);
            bw.write("\nLASER_STATE = " + url.getLaser_state());
            bw.write("\nLOG_DATA = " + log_data);
            bw.write("\nTIMESTAMP2 = " + timestamp);
            bw.close();

            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            dlgUrlData.lblUpdate.setText("Last Update: " + formatter.format(date));
            if (dlgUrlData.lblUploadFile.getText().equals("ON")) {
                boolean protocol = dlgConfiguration.jrbSFTP.isSelected();
                if (protocol) {
                    uploadSftpFile();
                } else {
                    //uploadFtpFile();
                    uploadFtpsFile();
                }

            }
        }
    }

    /**
     * ConfirmConfig gets the configuration data provided at the configuration
     * dialog and updates the configuration object.
     */
    public void ConfirmConfig() {
        try {
            Configuration configuration = new Configuration(dlgConfiguration.txtCoordFilePath.getText(), dlgConfiguration.txtUrlFile.getText(), dlgConfiguration.txtFtpSever.getText().replaceAll(" ", ""), dlgConfiguration.txtFtpPath.getText(), dlgConfiguration.txtFtpUser.getText(), dlgConfiguration.pwdPassword.getText(), dlgConfiguration.txtUpdateSec.getText().replaceAll(" ", ""), dlgConfiguration.txtPort.getText().replaceAll(" ", ""), dlgConfiguration.txtAutoCoordsTime.getText().replaceAll(" ", ""));
            setClassConfiguration(configuration);
        } catch (Exception e) {
        }
    }

    /**
     * Config Time gets the URL updating time from the configuration object.
     *
     * @return the time of the updates.
     */
    public float configTime() {
        float time = 10;
        try {
            String timeString = this.configuration.getUpdateRate().replace(",", ".");
            time = Float.valueOf(timeString);
        } catch (NumberFormatException e) {
        }
        return time;
    }

    /**
     * Config Time2 gets the Auto Coordinates reading time from the
     * configuration object.
     *
     * @return the time rate of each reading.
     */
    public float configTime2() {
        float time = 10;
        try {
            String timeString = this.configuration.getReadingTime().replace(",", ".");
            time = Float.valueOf(timeString);
        } catch (NumberFormatException e) {

        }
        return time;
    }

    /**
     * uploadFtpFile uploads the URL file generated to the FTP server specified
     * at the Configuration screen.
     */
    public void uploadFtpFile() {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(this.configuration.getFtpServer(), 21);
            ftpClient.login((this.configuration.getUser()), (this.configuration.getPassword()));
            ftpClient.enterLocalPassiveMode();

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            File LocalFile = new File(this.configuration.getFileName());

            String remoteFile = this.configuration.getUploadPath() + this.configuration.getFileName();
            InputStream inputStream = new FileInputStream(LocalFile);

            System.out.println("Start uploading first file");
            boolean done = ftpClient.storeFile(remoteFile, inputStream);
            inputStream.close();

            if (done) {
                System.out.println("The first file is uploaded using FTP successfully.");
                dlgUrlData.lblServerStatus.setText("OK");
                dlgUrlData.lblServerStatus.setForeground(Color.GREEN.darker());
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                dlgUrlData.lblUpdateServer1.setText("Last Update to Sever: " + formatter.format(date));
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            dlgUrlData.lblServerStatus.setText("CONNECTION FAILED");
            dlgUrlData.lblServerStatus.setForeground(Color.RED);
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
            }
        }
    }

    /**
     * uploadFtpsFile uploads the URL file generated to the FTPS server
     * specified at the Configuration screen.
     */
    public void uploadFtpsFile() {
        FTPSClient ftpsClient = new FTPSClient();
        try {
            int port = Integer.parseInt(dlgConfiguration.txtPort.getText());
            ftpsClient.connect(this.configuration.getFtpServer(), port);
            ftpsClient.execPROT("P"); // encrypt data channel
            ftpsClient.login((this.configuration.getUser()), (this.configuration.getPassword()));
            ftpsClient.enterLocalPassiveMode();

            ftpsClient.setFileType(FTP.BINARY_FILE_TYPE);

            File LocalFile = new File(this.configuration.getFileName());

            String remoteFile = this.configuration.getUploadPath() + this.configuration.getFileName();
            boolean done;
            try ( InputStream inputStream = new FileInputStream(LocalFile)) {
                System.out.println("Start uploading URL file");
                done = ftpsClient.storeFile(remoteFile, inputStream);
                //System.out.println(ftpsClient.getReplyCode());
                System.out.print(ftpsClient.getReplyString());
                if (ftpsClient.getReplyCode() != 226) {
                    String arr[] = ftpsClient.getReplyString().split(" ", 2);
                    String errorMessage = arr[1];
                    dlgUrlData.lblServerStatus.setText(errorMessage);
                    dlgUrlData.lblServerStatus.setForeground(Color.RED);
                }
            }
            if (done) {
                System.out.println("The URL file is uploaded using FTPS successfully.");
                String arr2[] = ftpsClient.getReplyString().split(" ", 2);
                String message = arr2[1];
                dlgUrlData.lblServerStatus.setText(message);
                dlgUrlData.lblServerStatus.setForeground(Color.GREEN.darker());
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                dlgUrlData.lblUpdateServer1.setText("Last Update to Sever: " + formatter.format(date));
            }
        } catch (IOException ex) {
            System.out.println("[ERROR] " + ex.getMessage());
            dlgUrlData.lblServerStatus.setText("[ERROR] " + ex.getMessage());
            dlgUrlData.lblServerStatus.setForeground(Color.RED);
        } finally {
            try {
                if (ftpsClient.isConnected()) {
                    ftpsClient.logout();
                    ftpsClient.disconnect();
                }
            } catch (IOException ex) {
            }
        }
    }

    /**
     * Whenever the user selects a telescope at the URL screen combobox, the URL
     * file name generated must have the corresponding telescope name selected.
     * This function performs this automatically.
     */
    public void changeFileName() {
        String newName = dlgUrlData.cboTelescope.getSelectedItem().toString() + ".dat";
        this.configuration.setFileName(newName);
        this.dlgConfiguration.txtUrlFile.setText(newName);
    }

    /**
     * Whenever the user selects a telescope at the URL screen combobox, the FOV
     * field is written by the field of view configurated for this specific
     * telescope. This function gets the FOV configurated for the telescope from
     * the listOfTelescopes object.
     */
    public void changeFovField() {
        String telescopeSelected = dlgUrlData.cboTelescope.getSelectedItem().toString();
        ArrayList<Telescope> arrayTelescope = this.listOfTelescopes.getArrayTelescopes();
        for (Telescope t : arrayTelescope) {
            if (t.getName().equals(telescopeSelected)) {
                dlgUrlData.txtFov.setText(t.getFov());
            }
        }
    }

    /**
     * This function runs a function of the configuration class that save the
     * configuration parameters submitted at the configuration screen.
     *
     * @throws java.io.IOException
     * @throws javax.crypto.IllegalBlockSizeException
     */
    public void saveData() throws IOException, IllegalBlockSizeException {
        this.configuration.saveData("./config");
    }

    /**
     * This function writes all the parameters of the configuration object to
     * the fields of the configuration screen. This function is used when the
     * software is starting.
     */
    public void setDataModify() {
        dlgConfiguration.txtCoordFilePath.setText(this.configuration.getFileLocation());
        dlgConfiguration.txtUrlFile.setText(this.configuration.getFileName());
        dlgConfiguration.txtAutoCoordsTime.setText(this.configuration.getReadingTime());
        dlgConfiguration.txtFtpSever.setText(this.configuration.getFtpServer());
        dlgConfiguration.txtFtpPath.setText(this.configuration.getUploadPath());
        dlgConfiguration.txtFtpUser.setText(this.configuration.getUser());
        dlgConfiguration.pwdPassword.setText(this.configuration.getPassword());
        dlgConfiguration.txtUpdateSec.setText(this.configuration.getUpdateRate());
        dlgConfiguration.txtPort.setText(this.configuration.getHostPort());
    }

    /**
     * This function gets the coordinates of a file, which path is specified at
     * the configuration screen.It validates the coordinates and write them into
     * the RA, DEC fields of the URL screen. This function is called from a
     * timer to perform the full automation of the ponting data of the
     * telescopes.
     *
     * @throws java.io.UnsupportedEncodingException
     */
    public void AutoCoord() throws UnsupportedEncodingException {
        // Read TXT Coordeinates from Control PC
        try ( BufferedReader br = new BufferedReader(
                new FileReader(this.configuration.getFileLocation()))) {
            String line = "";
            boolean check1, check2;
            check1 = false;
            check2 = false;
            while ((line = br.readLine()) != null) {
                String[] parts = line.replaceAll(" ", "").split("=");
                if (parts[0].equals("RA") && Comprueba.raLimits(parts[1])) {
                    String[] coordinates = Utils.coord2strings(parts[1]);
                    dlgUrlData.txtRaHour.setText(coordinates[0].replaceAll(" ", ""));
                    dlgUrlData.txtRaMin.setText(coordinates[1].replaceAll(" ", ""));
                    dlgUrlData.txtRaSec.setText(coordinates[2].replaceAll(" ", ""));
                    check1 = true;
                }
                if (parts[0].equals("DEC") && Comprueba.DecLimits(parts[1])) {
                    String[] coordinates = Utils.coord2strings(parts[1]);
                    dlgUrlData.txtDecDeg.setText(coordinates[0].replaceAll(" ", ""));
                    dlgUrlData.txtDecMin.setText(coordinates[1].replaceAll(" ", ""));
                    dlgUrlData.txtDecSec.setText(coordinates[2].replaceAll(" ", ""));
                    check2 = true;
                }
            }
            if (check1 == true && check2 == true) {
                dlgUrlData.lblAutoCoordsStatus.setText("OK");
                dlgUrlData.lblAutoCoordsStatus.setForeground(Color.GREEN.darker());
            } else {
                dlgUrlData.lblAutoCoordsStatus.setText("ERROR");
                dlgUrlData.lblAutoCoordsStatus.setForeground(Color.RED);
                dlgUrlData.txtRaHour.setText("");
                dlgUrlData.txtRaMin.setText("");
                dlgUrlData.txtRaSec.setText("");
                dlgUrlData.txtDecDeg.setText("");
                dlgUrlData.txtDecMin.setText("");
                dlgUrlData.txtDecSec.setText("");
            }
        } catch (IOException ex) {
            dlgUrlData.lblAutoCoordsStatus.setText("ERROR");
            dlgUrlData.lblAutoCoordsStatus.setForeground(Color.RED);
            dlgUrlData.txtRaHour.setText("");
            dlgUrlData.txtRaMin.setText("");
            dlgUrlData.txtRaSec.setText("");
            dlgUrlData.txtDecDeg.setText("");
            dlgUrlData.txtDecMin.setText("");
            dlgUrlData.txtDecSec.setText("");
        }
    }

    /**
     * uploadSftpFile uploads the URL file to the server specified at the
     * configuration screen throught the SFTP protocol.
     */
    private void uploadSftpFile() throws SftpException, FileNotFoundException {
        JSch jsch = new JSch();
        Session session = null;
        try {
            String portString = this.configuration.getHostPort();
            int port = Integer.parseInt(portString);
            session = jsch.getSession(this.configuration.getUser(), this.configuration.getFtpServer(), port);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(this.configuration.getPassword());
            session.setConfig("PreferredAuthentications",
                    "publickey,keyboard-interactive,password");
            session.connect();
            System.out.println("user info :" + session.getUserInfo());

            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.cd(this.configuration.getUploadPath());
            int mode = ChannelSftp.OVERWRITE;
            File LocalFile = new File(this.configuration.getFileName());
            String remoteFile = this.configuration.getUploadPath() + this.configuration.getFileName();
            InputStream inputStream = new FileInputStream(LocalFile);
            sftpChannel.put(inputStream, this.configuration.getFileName(), null, mode);
            sftpChannel.exit();
            session.disconnect();
            dlgUrlData.lblServerStatus.setText("OK");
            dlgUrlData.lblServerStatus.setForeground(Color.GREEN.darker());
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            dlgUrlData.lblUpdateServer1.setText("Last Update to Sever: " + formatter.format(date));
        } catch (JSchException e) {
            dlgUrlData.lblServerStatus.setText("CONNECTION FAILED");
            dlgUrlData.lblServerStatus.setForeground(Color.RED);
            String updateRate = this.configuration.getUpdateRate();
            float upR = Float.parseFloat(updateRate);
            if (upR < 5.0) {
                JOptionPane.showMessageDialog(null, "SFTP connection failed!" + "\n" + "Settting Semi-Automatic update rate to more than 4s at the configuration window could solve the issue.");
            }
            this.dlgUrlData.btnStop.doClick();
        }
    }

    /**
     * This function clean the Telescope Table. This function is called to
     * refresh the telescope data.
     *
     * @param tblTelescopes telescopes table.
     */
    private void limpiarDtmTelescopesTable(JTable tblTelescopes) {
        DefaultTableModel dtm = (DefaultTableModel) tblTelescopes.getModel();
        dtm.setRowCount(0);
        tblTelescopes.revalidate();
    }

    /**
     * This updates the telescope data at the Telescope List.
     *
     * @param tblTelescopes telescopes table.
     */
    private void loadTelescopeTable(JTable tblTelescopes) {
        Telescope telescope;
        String[] rowData = new String[4];
        ArrayList<Telescope> arrayTelescopes = this.listOfTelescopes.getArrayTelescopes();
        DefaultTableModel dtm = (DefaultTableModel) tblTelescopes.getModel();
        for (int cont = 0; cont < arrayTelescopes.size(); cont++) {
            telescope = arrayTelescopes.get(cont);
            rowData[0] = telescope.getName();
            rowData[1] = telescope.getFov();
            rowData[2] = telescope.getFileName();
            rowData[3] = telescope.getLaser() ? "YES" : "NO";
            dtm.addRow(rowData);
        }
    }

    /**
     * This function remove the telescopes at the URL screen telescopes
     * combobox.
     *
     * @param cbmUrl URL screen combobox.
     */
    public void limpiarCbmTelescopesUrl(DefaultComboBoxModel cbmUrl) {
        cbmUrl.removeAllElements();
    }

    /**
     * This function updates the telescopes list at the URL screen telescopes
     * combobox.
     *
     * @param cbmUrl URL screen combobox.
     */
    private void cargarTelescopesCbmUrl(DefaultComboBoxModel cbmUrl) {
        DefaultComboBoxModel dlModel = this.dlgUrlData.getCbm();

        ArrayList<Telescope> arrayTelescope = this.listOfTelescopes.getArrayTelescopes();
        Telescope telescope;
        for (int i = 0; i < arrayTelescope.size(); i++) {
            telescope = arrayTelescope.get(i);
            dlModel.addElement(telescope.getName());
        }
    }

    /**
     * This function saves the list of the telescope as an object into a file.
     */
    public void saveListData() {
        this.listOfTelescopes.saveList("./ListOfTelescopes");
    }

    /**
     * This function removes the telescope list at the setup screen combobox.
     */
    private void limpiarCbmTelescopesSetup(DefaultComboBoxModel cbmSetup) {
        cbmSetup.removeAllElements();
    }

    /**
     * This function reloads the telescope list at the setup screen combobox.
     */
    private void cargarTelescopesCbmSetup(DefaultComboBoxModel cbmSetup) {
        DefaultComboBoxModel dlModel = this.dlgSetup.getCbm();

        ArrayList<Telescope> arrayTelescope = this.listOfTelescopes.getArrayTelescopes();
        Telescope telescope;
        for (int i = 0; i < arrayTelescope.size(); i++) {
            telescope = arrayTelescope.get(i);
            dlModel.addElement(telescope.getName());
        }
    }

    /**
     * Whenever the user select a telescope in the setup combobox, it writes the
     * corresponding telescopes data in every field of the setup screen.
     *
     * @param selectedIndex telescope index at the setup combobox.
     */
    public void loadTelescopeData(int selectedIndex) {
        DefaultComboBoxModel cbm = this.dlgSetup.getCbm();
        ArrayList<Telescope> arrayTelescope = this.listOfTelescopes.getArrayTelescopes();
        Telescope telescope = arrayTelescope.get(selectedIndex);

        dlgSetup.txtTelescopeName.setText(telescope.getName());
        dlgSetup.txtFov.setText(telescope.getFov());
        dlgSetup.txtOutputFile.setText(telescope.getFileName());
        if (telescope.getLaser()) {
            dlgSetup.btnYes.doClick();
        } else {
            dlgSetup.btnNo.doClick();
        }
    }

    /**
     * This function removes a selected telescope in the setup combobox. This
     * telescope will be removed of the system.
     *
     * @param selectedIndex telescope index at the setup combobox.
     */
    public void removeTel(int selectedIndex) {
        int resultado = JOptionPane.showConfirmDialog(null, "Are you sure to remove the telescope?", "Aviso", JOptionPane.YES_NO_OPTION);
        if (resultado == JOptionPane.YES_OPTION) {
            Object telescope = this.dlgSetup.getCbm().getSelectedItem();
            DefaultComboBoxModel cbm = this.dlgSetup.getCbm();
            ArrayList<Telescope> arrayTelescope = this.listOfTelescopes.getArrayTelescopes();

            arrayTelescope.remove(selectedIndex);
            cbm.removeElement(telescope);
            setHayCambios(true);
            refreshDatosVistas();
        }

    }

    /**
     * This function updates the data of a telescope selected at the setup
     * screen combobox.
     *
     * @param index index of the telescope combobox.
     * @param telescopeString name of the telescope at the combobox.
     * @param telName name of the telescope.
     * @param fov field of view.
     * @param outFile name of the output file.
     * @param laser boolean which indicates if the telescope is laser equipped.
     */
    public void ModifyTelescope(int index, String telescopeString, String telName, String fov, String outFile, boolean laser) {
        int resultado = JOptionPane.showConfirmDialog(null, "Are you sure to save the telescope data?", "Aviso", JOptionPane.YES_NO_OPTION);
        if (resultado == JOptionPane.YES_OPTION) {
            ArrayList<Telescope> arrayTelescope = this.listOfTelescopes.getArrayTelescopes();
            for (Telescope t : arrayTelescope) {
                if (telescopeString.equals(t.getName())) {
                    t.setName(telName);
                    t.setFov(fov);
                    t.setFileName(outFile);
                    t.setLaser(laser);
                    setHayCambios(true);
                    refreshDatosVistas();
                    dlgSetup.cboTelescopes.setSelectedIndex(index);
                    JOptionPane.showMessageDialog(null, "Telescope data successfully updated");
                }
            }
        }
    }

    /**
     * This function add a new telescope at the system with the data specified
     * at the fields of the setup screen.
     *
     * @param telName name of the telescope.
     * @param fov field of view.
     * @param outFile name of the output file.
     * @param laser boolean which indicates if the telescope is laser equipped.
     */
    public void addTelescope(String telName, String fov, String outFile, boolean laser) {
        boolean repeated = false;
        ArrayList<Telescope> arrayTelescope = this.listOfTelescopes.getArrayTelescopes();
        for (Telescope t : arrayTelescope) {
            if (t.getName().equals(telName)) {
                repeated = true;
            }
        }
        if (!repeated) {
            Telescope telescope = new Telescope(telName, fov, outFile, laser);
            this.listOfTelescopes.addTelescope(telescope);
            setHayCambios(true);
            refreshDatosVistas();
            JOptionPane.showMessageDialog(null, "Telescope correctly added");
        } else {
            JOptionPane.showMessageDialog(null, "The telescope "+telName+" already exists");
        }

    }
}
