package Utilidades;

import java.io.Serializable;

/**
 * @author Alberto J. Prieto Antunez
 * @version 17.01.2022
 */
public class Utils implements Serializable {

    /**
     * This function gets the coordinates Hours (or degrees), minutes and
     * seconds in a string format, checks that all of them can be parsed into
     * float and calculates the decimal system of the coordinate provided. The
     * coordinate is parsed into a specific string format.
     *
     * @param coordHour hours (or degrees) in a string format
     * @param coordMin minutes in a string format
     * @param coordSec seconds in a string format
     * @return coordinate in decimal system with specific string format
     */
    public static String coord2float(String coordHour, String coordMin, String coordSec) {
        String coord = null;
        float coordFloat;
        if (Comprueba.isFloat(coordHour) == true && Comprueba.isFloat(coordMin) == true && Comprueba.isFloat(coordSec) == true) {
            float coordHourFloat = Float.parseFloat(coordHour);
            float coordMinFloat = Float.parseFloat(coordMin);
            float coordSecFloat = Float.parseFloat(coordSec);
            if (coordHour.startsWith("-")) {
                coordFloat = coordHourFloat - (coordMinFloat / 60) - (coordSecFloat / 3600);
            } else {
                coordFloat = coordHourFloat + coordMinFloat / 60 + (coordSecFloat / 3600);
            }
            coord = String.format("%.4f", coordFloat);
            coord = coord.replace(",", ".");
        }
        return coord;
    }

    /**
     * This function gets the decimal system and string format coordinate and
     * transform it into an array of string hour, minutes and seconds.
     *
     * @param coord coordinate in decimal system and string format
     * @return array of hours, minutes and seconds of the coordinate provided in
     * string format.
     */
    public static String[] coord2strings(String coord) {
        String[] coordinates = null;
        if (Comprueba.isFloat(coord)) {
            float coordFloat = Float.parseFloat(coord);
            if (coordFloat < 0) {
                int coordIntHour = (int) coordFloat;
                String hour = String.format("%2d", coordIntHour);

                float coordMin = (coordFloat % 1) * (-60);
                int coordIntMin = (int) coordMin;
                String min = String.format("%02d", coordIntMin);

                float coordSec = coordMin % 1;
                float coordFloatSec = coordSec * 60;
                String sec = String.format("%2.2f", coordFloatSec);
                sec = sec.replace(',', '.');

                coordinates = new String[]{hour, min, sec};
            } else {
                int coordIntHour = (int) coordFloat;
                String hour = String.format("%2d", coordIntHour);

                float coordMin = (coordFloat % 1) * 60;
                int coordIntMin = (int) coordMin;
                String min = String.format("%02d", coordIntMin);

                float coordSec = coordMin % 1;
                float coordFloatSec = coordSec * 60;
                String sec = String.format("%2.2f", coordFloatSec);
                sec = sec.replace(',', '.');

                coordinates = new String[]{hour, min, sec};
            }
        }
        return coordinates;
    }

}
