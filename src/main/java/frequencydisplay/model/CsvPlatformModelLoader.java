/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencydisplay.model;

import frequencydisplay.data.Platform;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Keith
 */
public class CsvPlatformModelLoader {
    private static final int CLASS_INDEX = 0;
    private static final int TYPE_INDEX = 1;
    private static final int COUNTRY_INDEX = 2;
    private static final int FREQ1_INDEX = 3;
    private static final int FREQ2_INDEX = 4;
    private static final int FREQ3_INDEX = 5;
    private static final int FREQ4_INDEX = 6;
    private static final int FREQ5_INDEX = 7;
    private static final int BLADES_INDEX = 8;
    private static final int TPK_INDEX = 9;
    private static final int ALT_NAME_INDEX = 10;
    
    
    public static List<Platform> loadPlatformsFromFile(File file) {
        try {
            return CsvPlatformModelLoader.loadPlatformsFromFile(file.getCanonicalPath());
        } catch (IOException ex) {
            System.out.println("Could not resolve canonical path for file: " + file.getPath());
            return new ArrayList<>();
        }
    }
    
    public static List<Platform> loadPlatformsFromFile(String filename) {
        List<Platform> data = new ArrayList<>();
        
        File file = new File(filename);

        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            String line = null;
            System.out.println("Loading platforms from file: " + filename);
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#")) {
                    continue;
                }
                
                String[] csv = line.split(",");
                try {
                    String platformClass = csv[CLASS_INDEX];
                    String platformType = csv[TYPE_INDEX];
                    List<String> countries = Arrays.asList(csv[COUNTRY_INDEX].split("/"));
                    int f1 = Integer.valueOf(csv[FREQ1_INDEX]);
                    int f2 = Integer.valueOf(csv[FREQ2_INDEX]);
                    int f3 = Integer.valueOf(csv[FREQ3_INDEX]);
                    int f4 = Integer.valueOf(csv[FREQ4_INDEX]);
                    int f5 = Integer.valueOf(csv[FREQ5_INDEX]);
                    List<Integer> frequencies = Arrays.asList(f1, f2, f3, f4 ,f5);
                    int blades = Integer.valueOf(csv[BLADES_INDEX]);
                    int tpk = Integer.valueOf(csv[TPK_INDEX]);
                    
                    Platform p = new Platform(platformClass, platformType, countries, frequencies, blades, tpk);
                    data.add(p);
                } catch (NumberFormatException ex) {
                    //Catch errors and continue parsing
                    System.err.println("Error parsing platform line from CSV file: " + line);
                }
            }
        } catch (IOException x) {
            System.err.format("Error loading file, IOException: %s%n", x);
            File fileTouch = new File(filename);
            System.out.println(fileTouch.getAbsolutePath());
        }

        return data;
    }
}
