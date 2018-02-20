/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencydisplay.view.display;

import frequencydisplay.data.SearchParameters;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author Keith
 */
public class SearchParametersListModel extends AbstractListModel<SearchParameters>{
    private final List<SearchParameters> m_data;

    public SearchParametersListModel() {
        m_data = new ArrayList<>();
    }
    
    @Override
    public int getSize() {
        return m_data.size();
    }

    @Override
    public SearchParameters getElementAt(int index) {
        SearchParameters params = null;
        if (index < m_data.size() && index >= 0) {
            params = m_data.get(index);
        }
        return params;
    }
    
    public void addSearchParameters(SearchParameters parameters) {
        int index = m_data.size();
        m_data.add(parameters);
        fireIntervalAdded(this, index, index);
    }
    
    public void removeSearchParameters(SearchParameters parameters) {
        int index = m_data.indexOf(parameters);
        if (index != -1) {
            m_data.remove(parameters);
            fireIntervalRemoved(this, index, index);
        }
    }
}
