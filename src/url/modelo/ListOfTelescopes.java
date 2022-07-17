package url.modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.ArrayList;

/**
 * @author Alberto J. Prieto Antunez
 * @version 17.01.2022
 */
public class ListOfTelescopes implements Serializable {

    private final ArrayList<Telescope> listOfTelescopes;
    private int totalTelescopes;

    /**
     * List of telescopes constructor for the telescopes provided at the setup
     * screen.
     */
    public ListOfTelescopes() {
        listOfTelescopes = new ArrayList();
    }

    /**
     * This getter provides an arrayList with the telescopes of the system.
     *
     * @return telescopes list
     */
    public ArrayList<Telescope> getArrayTelescopes() {
        return this.listOfTelescopes;
    }

    /**
     * This function adds a new telescope at the arrayList with the parameters
     * entered at the setup screen
     *
     * @param telescope telescope object
     */
    public void addTelescope(Telescope telescope) {
        totalTelescopes++;
        listOfTelescopes.add(telescope);
    }

    /**
     * This function removes a telescope at the arrayList
     *
     * @param telescope telescope object
     */
    public void removeTelescope(Telescope telescope) {
        totalTelescopes--;
        this.listOfTelescopes.remove(telescope);
    }

    /**
     * This function saves the telescopes list object without encryiption at the
     * file path specified.
     *
     * @param path file path
     */
    public void saveList(String path) {
        ObjectOutputStream output = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            output = new ObjectOutputStream(fos);
            output.writeObject(this);
        } catch (IOException ex) {
            System.out.println("Error closing the file");
            System.out.println(ex.toString());
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex) {
                System.out.println("Error closing the file");
            }
            try {
                if (output != null) {
                    output.close();
                }
            } catch (IOException ex) {
                System.out.println("Error closing the file");
            }
        }
    }

    /**
     * This function loads the telescopes list object without encryiption at the
     * file path specified.
     *
     * @param path file path
     * @return arrayList with a list of the telescopes
     */
    public static ListOfTelescopes loadList(String path) {

        FileInputStream fis = null;
        ObjectInputStream input = null;

        ListOfTelescopes listOfTelescopes = null;
        try {
            File file = new File(path);
            if (file.exists()) {
                fis = new FileInputStream(path);
                input = new ObjectInputStream(fis);
                listOfTelescopes = (ListOfTelescopes) input.readObject();
            } else {
                listOfTelescopes = new ListOfTelescopes();
            }
        } catch (IOException ex) {
            System.out.println("Error loading the file 1-> " + ex.getMessage());

        } catch (ClassNotFoundException ex) {
            System.out.println("Error loading the file 2-> " + ex.getMessage());
        }

        return listOfTelescopes;
    }
}
