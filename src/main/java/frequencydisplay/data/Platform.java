/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencydisplay.data;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Keith
 */
public class Platform {
    private final String platformClass;
    private final String type;
    private final List<String> countries;
    private final List<Integer> frequencies;
    private final int numBlades;
    private final int turnsPerKnot;

    public Platform(String platformClass, String type, List<String> countries, List<Integer> frequencies, int numBlades, int turnsPerKnot) {
         this.platformClass = platformClass;
         this.type = type;
         this.countries = countries;
         this.frequencies = frequencies;
         this.numBlades = numBlades;
         this.turnsPerKnot = turnsPerKnot;
    }

    public String getPlatformClass() {
        return platformClass;
    }

    public String getType() {
        return type;
    }

    public List<String> getCountries() {
        return Collections.unmodifiableList(countries);
    }

    public List<Integer> getFrequencies() {
        return Collections.unmodifiableList(frequencies);
    }

    public int getNumBlades() {
        return numBlades;
    }

    public int getTurnsPerKnot() {
        return turnsPerKnot;
    }
}
