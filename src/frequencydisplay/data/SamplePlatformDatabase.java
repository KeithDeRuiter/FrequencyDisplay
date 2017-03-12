/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencydisplay.data;

import java.util.Arrays;

/**
 *
 * @author Keith
 */
public class SamplePlatformDatabase {
    public static final Platform A_CLASS = new Platform("A", "DDG", Arrays.asList("US", "GB"), Arrays.asList(60, 120, 300, 900, 1200), 8, 12);
    public static final Platform B_CLASS = new Platform("B", "FFG", Arrays.asList("RU"), Arrays.asList(50, 100, 320, 700, 1300), 6, 7);
}
