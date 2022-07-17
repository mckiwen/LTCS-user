package url.modelo;

import java.io.Serializable;

/**
 * @author Alberto J. Prieto Antunez
 * @version 17.01.2022
 */
public class URL implements Serializable {

    private String telescope, ra, dec, equinox, fov, laser_impacted, laser_state, log_data;

    /**
     * URL file constructor. This object is used to generate the URL file of the
     * selected telescope in order to upload the pointing data to the LTCS
     * server.
     *
     * @param telescope telescope name as string
     * @param ra right ascension decimal system as string
     * @param dec declination decimal system as string
     * @param equinox equinox as string
     * @param fov telescope field of view as string
     * @param laser_impacted YES/NO
     * @param laser_state ON-SKY, ON or OFF
     * @param log_data ON/OFF
     */
    public URL(String telescope, String ra, String dec, String equinox, String fov, String laser_impacted, String laser_state, String log_data) {
        this.telescope = telescope;
        this.ra = ra;
        this.dec = dec;
        this.equinox = equinox;
        this.fov = fov;
        this.laser_impacted = laser_impacted;
        this.laser_state = laser_state;
        this.log_data = log_data;
    }

    /**
     * Getter of the telescope name.
     *
     * @return telescope name
     */
    public String getTelescope() {
        return telescope;
    }

    /**
     * Setter of the telescope name.
     *
     * @param telescope telescope name
     */
    public void setTelescope(String telescope) {
        this.telescope = telescope;
    }

    /**
     * Getter of the telescope right ascension.
     *
     * @return telescope right ascension
     */
    public String getRa() {
        return ra;
    }

    /**
     * Setter of the telescope right ascension.
     *
     * @param ra telescope right ascension
     */
    public void setRa(String ra) {
        this.ra = ra;
    }

    /**
     * Getter of the telescope declination.
     *
     * @return telescope declination
     */
    public String getDec() {
        return dec;
    }

    /**
     * Setter of the telescope delclination
     *
     * @param dec telescope delclination
     */
    public void setDec(String dec) {
        this.dec = dec;
    }

    /**
     * Getter of the telescope coordinates equinox.
     *
     * @return telescope coordinates equinox
     */
    public String getEquinox() {
        return equinox;
    }

    /**
     * Setter of the telescope coordinates equinox
     *
     * @param equinox telescope coordinates equinox
     */
    public void setEquinox(String equinox) {
        this.equinox = equinox;
    }

    /**
     * Getter of the telescope field of view.
     *
     * @return telescope field of view.
     */
    public String getFov() {
        return fov;
    }

    /**
     * Setter of the telescope field of view.
     *
     * @param fov telescope field of view.
     */
    public void setFov(String fov) {
        this.fov = fov;
    }

    /**
     * Getter of the telescope laser sensitivity flag
     *
     * @return telescope laser sensitivity flag
     */
    public String getLaser_impacted() {
        return laser_impacted;
    }

    /**
     * Setter of the telescope laser sensitivity flag
     *
     * @param laser_impacted laser sensitivity flag
     */
    public void setLaser_impacted(String laser_impacted) {
        this.laser_impacted = laser_impacted;
    }

    /**
     * Getter of the telescope laser state
     *
     * @return telescope laser state
     */
    public String getLaser_state() {
        return laser_state;
    }

    /**
     * Setter of the telescope laser state
     *
     * @param laser_state telescope laser state
     */
    public void setLaser_state(String laser_state) {
        this.laser_state = laser_state;
    }

    /**
     * Getter of the telescope log data flag
     *
     * @return telescope log data flag
     */
    public String getLog_data() {
        return log_data;
    }

    /**
     * Setter of the telescope log data flag
     *
     * @param log_data log data flag
     */
    public void setLog_data(String log_data) {
        this.log_data = log_data;
    }

    /**
     * Standard URL constructor.
     *
     */
    public URL() {
    }
}
