/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencydisplay.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Keith
 */
public class SearchParameters {
    private final String name;
    private final List<FrequencyBand> searchBands;

    private SearchParameters(String name, List<FrequencyBand> searchBands) {
        this.name = name;
        this.searchBands = searchBands;
    }

    public String getName() {
        return name;
    }

    public List<FrequencyBand> getSearchBands() {
        return Collections.unmodifiableList(searchBands);
    }
    
    
    public static class Builder {
        String builderName;
        private List<FrequencyBand> builderSearchBands;

        public Builder(String name) {
            builderName = name;
            builderSearchBands = new ArrayList<>();
        }

        public Builder() {
            this("");
        }
        
        public Builder name(String name) {
            builderName = name;
            return this;
        }
        
        public Builder band(int center, int width) {
            FrequencyBand band = new FrequencyBand(center, width, 100);
            builderSearchBands.add(band);
            return this;
        }
        
        public SearchParameters build() {
            SearchParameters params = new SearchParameters(builderName, builderSearchBands);
            return params;
        }
    }
}
