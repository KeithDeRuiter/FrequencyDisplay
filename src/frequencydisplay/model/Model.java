/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencydisplay.model;

import frequencydisplay.data.Platform;
import frequencydisplay.data.SearchParameters;
import java.util.List;

/**
 *
 * @author Keith
 */
public interface Model {
    public void addModelListener(ModelListener listener);
    public void removeModelListener(ModelListener listener);
    public void addPlatform(Platform p);
    public void removePlatform(Platform p);
    public void addSearchParameters(SearchParameters params);
    public void removeSearchParameters(SearchParameters params);
    public List<Platform> getAllPlatforms();
    public List<SearchParameters> getAllSearchParameters();
}
