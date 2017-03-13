/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencydisplay.model;

import frequencydisplay.data.Platform;
import frequencydisplay.data.SearchParameters;

/**
 *
 * @author Keith
 */
public interface ModelListener {
    public void platformAdded(final Platform p);
    public void platformRemoved(final Platform p);
    public void searchParametersAdded(final SearchParameters searchParameters);
    public void searchParametersRemoved(final SearchParameters searchParameters);
}
