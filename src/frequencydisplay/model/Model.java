/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencydisplay.model;

/**
 *
 * @author Keith
 */
public interface Model {
    public void addModelListener();
    public void addPlatform();
    public void removePlatform();
    public void addSearchParameters();
    public void removeSearchParameters();
    
}
