/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencydisplay.model;

import frequencydisplay.data.Platform;

/**
 *
 * @author Keith
 */
public interface Model {
    public void addModelListener(ModelListener listener);
    public void removeModelListener(ModelListener listener);
    public void addPlatform(Platform p);
    public void removePlatform(Platform p);
    public void addSearchParameters();
    public void removeSearchParameters();
    
}
