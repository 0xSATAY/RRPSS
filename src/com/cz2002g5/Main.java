package com.cz2002g5;

import com.cz2002g5.Controller.RRPSS;
import java.io.IOException;

/**
 * The type Main.
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException Thrown if file is not found when calling CSVFileUtil class methods io exception
     */
public static void main(String[] args) throws IOException {
        RRPSS pos = new RRPSS();
        pos.run();
    }
}
