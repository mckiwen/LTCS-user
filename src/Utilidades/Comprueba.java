package Utilidades;

import java.io.Serializable;

/**
 * @author Alberto J. Prieto Antunez
 * @version 17.01.2022
 */
public class Comprueba implements Serializable {

    /**
     * This function checks if a string number can be parsed into a Float.
     *
     * @param cadena string number
     * @return true in case of a float number, false otherwise
     */
    public static boolean isFloat(String cadena) {
        boolean isfloat = false;
        try {
            float cadenaFloat = Float.parseFloat(cadena);
            isfloat = true;
        } catch (NumberFormatException e) {
        }
        return isfloat;
    }

    /**
     * This function checks the right ascension provided in a string format can
     * be parsed into float and is not out of limits.
     *
     * @param ra right ascension in string format
     * @return true in case of a float number and is in the limits allowed,
     * false otherwise
     */
    public static boolean raLimits(String ra) {
        boolean limitsRa = false;
        try {
            float ra_float = Float.parseFloat(ra);
            if (ra_float >= 0 && ra_float < 24) {
                limitsRa = true;
            }
        } catch (NumberFormatException e) {
        }
        return limitsRa;

    }

    /**
     * This function checks the declination provided in a string format can be
     * parsed into float and is not out of limits.
     *
     * @param dec right ascension in string format
     * @return true in case of a float number and is in the limits allowed,
     * false otherwise
     */
    public static boolean DecLimits(String dec) {
        boolean limitsDec = false;
        try {
            float dec_float = Float.parseFloat(dec);
            if (dec_float >= -90 && dec_float <= 90) {
                limitsDec = true;
            }
        } catch (NumberFormatException e) {
        }
        return limitsDec;
    }

    /**
     * This function checks the equinox provided in a string format can be
     * parsed into float and is not out of limits.
     *
     * @param equinox equinox in string format
     * @return true in case of a float number and is in the limits allowed,
     * false otherwise
     */
    public static boolean EquinoxLimits(String equinox) {
        boolean limitsEquinox = false;
        try {
            float equinox_float = Float.parseFloat(equinox);
            if (equinox_float >= 1900.0) {
                limitsEquinox = true;
            }
        } catch (NumberFormatException e) {
        }
        return limitsEquinox;
    }

    /**
     * This function checks the equinox provided in a string format matches to
     * an spepcific format.
     *
     * @param equinox equinox in string format
     * @return true in case of a match of the format specified, false otherwise
     */
    public static boolean EquinoxFormat(String equinox) {
        return equinox.matches("^[0-9]{4}[.][0-9]{1,4}$");
    }

    /**
     * This function checks the field of view provided in a string format can be
     * parsed into float and is not out of limits.
     *
     * @param fov field of view in string format
     * @return true in case of a float number and is in the limits allowed,
     * false otherwise
     */
    public static boolean FovLimits(String fov) {
        boolean limitsFov = false;
        try {
            float fov_float = Float.parseFloat(fov);
            if (fov_float > 0) {
                limitsFov = true;
            }
        } catch (NumberFormatException e) {
        }
        return limitsFov;
    }

    /**
     * This function checks the fov provided in a string format matches to an
     * spepcific format.
     *
     * @param fov fov in string format
     * @return true in case of a match of the format specified, false otherwise
     */
    public static boolean FovFormat(String fov) {
        return fov.matches("^[0-9]{1,3}[.][0-9]{2,}$");
    }

    /**
     * This function checks the hour provided in a string format can be parsed
     * into integer and is not out of limits.
     *
     * @param hour hour in string format
     * @return true in case of a integer number and is in the limits allowed,
     * false otherwise
     */
    public static boolean hourCheck(String hour) {
        boolean result = false;
        try {
            int hour_int = Integer.parseInt(hour);
            if (hour_int < 24 && hour_int >= 0) {
                result = true;
            }
        } catch (NumberFormatException e) {
        }
        return result;
    }

    /**
     * This function checks the minutes provided in a string format can be
     * parsed into integer and is not out of limits.
     *
     * @param min minutes in string format
     * @return true in case of a integer number and is in the limits allowed,
     * false otherwise
     */
    public static boolean minCheck(String min) {
        boolean result = false;
        try {
            int min_int = Integer.parseInt(min);
            if (min_int < 60 && min_int >= 0) {
                result = true;
            }
        } catch (NumberFormatException e) {
        }
        return result;
    }

    /**
     * This function checks the seconds provided in a string format can be
     * parsed into integer and is not out of limits.
     *
     * @param sec seconds in string format
     * @return true in case of a integer number and is in the limits allowed,
     * false otherwise
     */
    public static boolean secCheck(String sec) {
        boolean result = false;
        try {
            float sec_float = Float.parseFloat(sec);
            if (sec_float < 60 && sec_float >= 0) {
                result = true;
            }
        } catch (NumberFormatException e) {
        }
        return result;
    }

    /**
     * This function checks the degrees provided in a string format can be
     * parsed into integer and is not out of limits.
     *
     * @param deg degrees in string format
     * @return true in case of a integer number and is in the limits allowed,
     * false otherwise
     */
    public static boolean degCheck(String deg) {
        boolean result = false;
        try {
            int deg_int = Integer.parseInt(deg);
            if (deg_int < 90 && deg_int >= -90) {
                result = true;
            }
        } catch (NumberFormatException e) {
        }
        return result;
    }
}
