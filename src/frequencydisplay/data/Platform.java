/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencydisplay.data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Keith
 */
public class Platform {
    private final String m_platformClass;
    private final String m_type;
    private final List<String> m_countries;
    private final List<Integer> m_frequencies;
    private final int m_numBlades;
    private final int m_turnsPerKnot;

    public Platform(String platformClass, String type, List<String> countries, List<Integer> frequencies, int numBlades, int turnsPerKnot) {
         m_platformClass = platformClass;
         m_type = type;
         m_countries = countries;
         m_frequencies = frequencies;
         m_numBlades = numBlades;
         m_turnsPerKnot = turnsPerKnot;
    }

    public String getPlatformClass() {
        return m_platformClass;
    }

    public String getType() {
        return m_type;
    }

    public List<String> getCountries() {
        return m_countries;
    }

    public List<Integer> getFrequencies() {
        return m_frequencies;
    }

    public int getNumBlades() {
        return m_numBlades;
    }

    public int getTurnsPerKnot() {
        return m_turnsPerKnot;
    }
    
}
