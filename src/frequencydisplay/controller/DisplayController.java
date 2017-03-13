/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencydisplay.controller;

import frequencydisplay.data.Platform;
import frequencydisplay.data.SearchParameters;
import frequencydisplay.model.Model;
import frequencydisplay.model.ModelListener;
import frequencydisplay.view.View;

/**
 *
 * @author Keith
 */
public class DisplayController implements ModelListener {
    
    private final Model m_model;
    private final View m_view;

    public DisplayController(Model model, View view) {
        m_model = model;
        m_view = view;
        
    }
    
    public void launch() {
        m_model.addModelListener(this);
        
        m_view.showView();
    }

    @Override
    public void platformAdded(Platform p) {
        m_view.addPlatformToList(p);
    }

    @Override
    public void platformRemoved(Platform p) {
        m_view.removePlatformFromList(p);
    }

    @Override
    public void searchParametersAdded(final SearchParameters searchParameters) {
        
    }

    @Override
    public void searchParametersRemoved(final SearchParameters searchParameters) {
        
    }
    
}
