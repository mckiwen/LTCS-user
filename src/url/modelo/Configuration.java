package url.modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;

/**
 * @author Alberto J. Prieto Antunez
 * @version 17.01.2022
 */
public class Configuration implements Serializable {

    private String fileLocation, fileName, ftpServer, uploadPath, user, password, updateRate, hostPort, readingTime;

    /**
     * Configuration constructor for the parameters provided at the
     * configuration screen.
     *
     * @param fileLocation string with the file location of the coordinates for
     * automation with the telescope
     * @param fileName output URL file name
     * @param ftpServer URL or IP of the FTP (FTPS) server
     * @param uploadPath upload path of the URL file at the FTPS sever
     * @param user user of the FTPS credentials
     * @param password password of the FTPS credentials
     * @param updateRate updating time rate
     * @param hostPort opened port where this software is uploading the URL to
     * the FTPS server (21 - FTP or FTPS, 1001 - SFTP)
     * @param readingTime time of the reading time rate of the file provided by
     * the telescope for the automation
     */
    public Configuration(String fileLocation, String fileName, String ftpServer, String uploadPath, String user, String password, String updateRate, String hostPort, String readingTime) {
        this.fileLocation = fileLocation;
        this.fileName = fileName;
        this.ftpServer = ftpServer;
        this.uploadPath = uploadPath;
        this.user = user;
        this.password = password;
        this.updateRate = updateRate;
        this.hostPort = hostPort;
        this.readingTime = readingTime;
    }

    /**
     * This getter provides the reading time rate.
     *
     * @return reading time rate
     */
    public String getReadingTime() {
        return readingTime;
    }

    /**
     * This setter sets the reading time rate.
     *
     * @param readingTime reading time rate.
     */
    public void setReadingTime(String readingTime) {
        this.readingTime = readingTime;
    }

    /**
     * This getter provides the host port
     *
     * @return host port
     */
    public String getHostPort() {
        return hostPort;
    }

    /**
     * This setter sets the host port.
     *
     * @param hostPort host port
     */
    public void setHostPort(String hostPort) {
        this.hostPort = hostPort;
    }

    /**
     * Another constructor for the configuration.
     */
    public Configuration() {
    }

    /**
     * This getter provides the file location of the coordinates provided by the
     * telescope.
     *
     * @return file location
     */
    public String getFileLocation() {
        return fileLocation;
    }

    /**
     * This setter sets the file location of the coordinates provided by the
     * telescope.
     *
     * @param fileLocation file location
     */
    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    /**
     * This getter provides the URL file name
     *
     * @return URL file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * This setter sets URL file name.
     *
     * @param fileName URL file name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * This getter provides the URL or IP of the FTP (either FTPS or SFTP)
     * server
     *
     * @return the URL or IP of the FTP (either FTPS or SFTP) server
     */
    public String getFtpServer() {
        return ftpServer;
    }

    /**
     * This setter sets the URL or IP of the FTP (either FTPS or SFTP) server
     *
     * @param ftpServer the URL or IP of the FTP (either FTPS or SFTP) server
     */
    public void setFtpServer(String ftpServer) {
        this.ftpServer = ftpServer;
    }

    /**
     * This getter provides the upload URL file path in the LTCS server
     *
     * @return the upload URL file path in the LTCS server
     */
    public String getUploadPath() {
        return uploadPath;
    }

    /**
     * This setter sets the upload URL file path in the LTCS server
     *
     * @param uploadPath the upload URL file path in the LTCS server
     */
    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    /**
     * This getter provides the user of the LTCS server.
     *
     * @return the user of the LTCS server.
     */
    public String getUser() {
        return user;
    }

    /**
     * This setter sets the user of the LTCS server.
     *
     * @param user the user of the LTCS server.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * This getter provides the pasword of the user of the LTCS server.
     *
     * @return the pasword of the user of the LTCS server.
     */
    public String getPassword() {
        return password;
    }

    /**
     * This setter sets the pasword of the user of the LTCS server.
     *
     * @param password the pasword of the user of the LTCS server.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This getter provides the upload rate time of the URL file to the LTCS
     * server.
     *
     * @return the upload rate time of the URL file to the LTCS server.
     */
    public String getUpdateRate() {
        return updateRate;
    }

    /**
     * This setter sets the upload rate time of the URL file to the LTCS server.
     *
     * @param updateRate the upload rate time of the URL file to the LTCS
     * server.
     */
    public void setUpdateRate(String updateRate) {
        this.updateRate = updateRate;
    }

    /**
     * This function saves the configuration object with encryiption at the file
     * path specified.
     *
     * @param config file path
     * @throws java.io.IOException
     * @throws javax.crypto.IllegalBlockSizeException
     */
    public void saveData(String config) throws IOException, IllegalBlockSizeException {
        try {
            //Generate a key
            KeyGenerator gen = KeyGenerator.getInstance("AES");
            gen.init(128);
            Key sKey = gen.generateKey();

            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, sKey);
            // do the sealing
            SealedObject so = new SealedObject(this, c);

            FileOutputStream out = new FileOutputStream(config);
            //Save the key
            try ( ObjectOutputStream oOut = new ObjectOutputStream(out)) {
                //Save the key
                oOut.writeObject(sKey);

                //Save the sealed object
                oOut.writeObject(so);
            }
            System.out.println("Configuration data has been saved to "+config+" file");

        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * This function loads the configuration object with encryiption at the file
     * path specified.
     *
     * @param file file path with file name
     * @return configuration object
     */
    public static Configuration loadConfiguration(String file) {
        Configuration configuration = null;
        try {
            File fichero = new File(file);
            if (fichero.exists()) {
                FileInputStream in = new FileInputStream(file);
                ObjectInputStream oIn = new ObjectInputStream(in);

                //Read the key
                Key sKey = (Key) oIn.readObject();

                //Read the sealed object
                SealedObject so = (SealedObject) oIn.readObject();
                //unsealded the object
                configuration = (Configuration) so.getObject(sKey);
            } else {
                configuration = new Configuration();
            }
        } catch (IOException | ClassNotFoundException | InvalidKeyException | NoSuchAlgorithmException e) {
            System.out.println(e);
        }

        return configuration;
    }

    /**
     * This function saves the configuration object without encryiption at the
     * file path specified.
     *
     * @param config file path
     */
    /* Without encrypting
    public void saveData(String config) {
        ObjectOutputStream output = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(config);
            output = new ObjectOutputStream(fos);
            output.writeObject(this);
        } catch (IOException ex) {
            System.out.println("Config file did not close properly");
            System.out.println(ex.toString());
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex) {
                System.out.println("Config file did not close properly");
            }
            try {
                if (output != null) {
                    output.close();
                }
            } catch (IOException ex) {
                System.out.println("Config file did not close properly");
            }
        }
    }

     /**
     * This function loads the configuration object without encryiption at the file
     * path specified.
     *
     * @param file file path with file name
     * @return configuration object
     *
    public static Configuration loadConfiguration(String file) {
        FileInputStream fis = null;
        ObjectInputStream input = null;
        Configuration configuration = null;
        try {
            File fichero = new File(file);
            if (fichero.exists()) {
                fis = new FileInputStream(file);
                input = new ObjectInputStream(fis);
                configuration = (Configuration) input.readObject();
            } else {
                configuration = new Configuration();
            }

        } catch (IOException ex) {
            System.out.println("Error loading the file 1-> " + ex.getMessage());

        } catch (ClassNotFoundException ex) {
            System.out.println("Error loading the file 2-> " + ex.getMessage());
        }

        return configuration;
    }

*/
}
