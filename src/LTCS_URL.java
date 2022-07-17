import java.io.File;
import java.io.IOException;

import url.modelo.Configuration;
import url.modelo.ListOfTelescopes;
import url.modelo.Telescope;

import url.vista.FrmPrincipal;
import url.vista.DlgUrlData;
import url.vista.DlgConfiguration;
import url.vista.DlgAbout;
import url.vista.DlgListOfTelescopes;
import url.vista.DlgSetup;

import url.controlador.Control;


/**
 * @author Alberto J. Prieto Antunez
 * @version 17.01.2022
 */
public class LTCS_URL {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    ListOfTelescopes listOfTelescopes;

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        //VISTA
        FrmPrincipal frmPpal = new FrmPrincipal();
        DlgUrlData dlgUrlData = new DlgUrlData(frmPpal, false);
        DlgConfiguration dlgConfiguration = new DlgConfiguration(frmPpal, false);
        DlgAbout dlgAbout = new DlgAbout(frmPpal, false);
        DlgListOfTelescopes dlgListOfTelescopes = new DlgListOfTelescopes(frmPpal, false);
        DlgSetup dlgSetup = new DlgSetup(frmPpal, false);

        //CONTROLADOR
        Control ctrl = new Control();

        //ASOCIAMOS CONTROLADOR --> VISTA
        frmPpal.setControlador(ctrl);
        dlgUrlData.setControlador(ctrl);
        dlgConfiguration.setControlador(ctrl);
        dlgAbout.setControlador(ctrl);
        dlgListOfTelescopes.setControlador(ctrl);
        dlgSetup.setControlador(ctrl);

        //ASOCIAMOS VISTA --> CONTROLADOR
        ctrl.setFrmPpal(frmPpal);
        ctrl.setDlgUrlData(dlgUrlData);
        ctrl.setConfiguration(dlgConfiguration);
        ctrl.setDlgAbout(dlgAbout);
        ctrl.setDlgListOfTelescopes(dlgListOfTelescopes);
        ctrl.setDlgSetup(dlgSetup);

        //ASOCIAMOS MODELO --> CONTROLADOR
        // List of Telescope class and file.
        String listPath = "./ListOfTelescopes";
        File f2 = new File(listPath);
        ListOfTelescopes listOfTelescopes = new ListOfTelescopes();
        listOfTelescopes = f2.exists() ? ListOfTelescopes.loadList(listPath) : defaultTelescopes();
        ctrl.setListOfTelescopes(listOfTelescopes);

        //ASOCIAMOS VISTA --> VISTA
        frmPpal.setDlgUrlData(dlgUrlData);

        // Load Configuration class and config file.
        String path = "./config";
        File f = new File(path);
        Configuration configuration = Configuration.loadConfiguration(path);
        ctrl.setClassConfiguration(configuration);
        ctrl.setDataModify();

        //ASOCIAMOS MODELO --> VISTA
        frmPpal.setClassConfiguration(configuration);

        // Load ListModels, ComboBoxModels, etc.
        ctrl.setHayCambios(true);
        ctrl.refreshDatosVistas();
        frmPpal.setVisible(true);
    }

    /**
    * Default List of Telescopes. It returns a list of telescopes
    * in case there's no stored any telescopes list.
    * @return listOfTelescopes. 
    */
    private static ListOfTelescopes defaultTelescopes() {
        ListOfTelescopes listOfTelescopes = new ListOfTelescopes();
        try {
            Telescope IAC80 = new Telescope("IAC80", "0.235", "IAC80.dat", false);
            Telescope TCS = new Telescope("TCS", "0.236", "TCS.dat", false);
            Telescope ELRS = new Telescope("ELRS", "0.237", "ELRS.dat", true);
            Telescope OGS = new Telescope("OGS", "0.238", "OGS.dat", true);
            listOfTelescopes.addTelescope(IAC80);
            listOfTelescopes.addTelescope(TCS);
            listOfTelescopes.addTelescope(ELRS);
            listOfTelescopes.addTelescope(OGS);
        } catch (Exception e) {
        }
        return listOfTelescopes;
    }
}
