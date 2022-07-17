package url.modelo;

import java.io.Serializable;

/**
 * @author Alberto J. Prieto Antunez
 * @version 17.01.2022
 */
public class Telescope implements Serializable {

    private String name, fov, FileName;
    private boolean laser;

    /**
     * Standard telescope constructor.
     */
    public Telescope() {
    }

    /**
     * Telescope constructor.
     *
     * @param name telescope name
     * @param fov telescope field of view as string
     * @param FileName URL file name of the telescope
     * @param laser true if the telescope is laser equipped, false otherwise
     */
    public Telescope(String name, String fov, String FileName, boolean laser) {
        this.name = name;
        this.fov = fov;
        this.FileName = FileName;
        this.laser = laser;
    }

    /**
     * Getter of the telescope name.
     *
     * @return telescope name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of the telescope name.
     *
     * @param name telescope name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of the telescope field of view.
     *
     * @return telescope field of view as string.
     */
    public String getFov() {
        return fov;
    }

    /**
     * Setter of the telescope field of view.
     *
     * @param fov field of view.
     */
    public void setFov(String fov) {
        this.fov = fov;
    }

    /**
     * Getter of the telescope URL file name.
     *
     * @return telescope URL file name.
     */
    public String getFileName() {
        return FileName;
    }

    /**
     * Setter of the telescope URL file name.
     *
     * @param FileName telescope URL file name.
     */
    public void setFileName(String FileName) {
        this.FileName = FileName;
    }

    /**
     * Getter of the telescope laser configuration.
     *
     * @return true if the telescope is laser configured, false otherwise.
     */
    public boolean getLaser() {
        return laser;
    }

    /**
     * Setter of the telescope laser configured.
     *
     * @param laser laser configured parameter.
     */
    public void setLaser(boolean laser) {
        this.laser = laser;
    }
}
