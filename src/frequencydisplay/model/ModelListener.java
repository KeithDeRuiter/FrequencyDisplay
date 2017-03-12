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
public interface ModelListener {
    public void platformAdded();
    public void platformRemoved();
    public void searchParametersAdded();
    public void searchParametersRemoved();
}
