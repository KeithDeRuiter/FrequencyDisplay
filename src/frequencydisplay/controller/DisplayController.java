/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencydisplay.controller;

import frequencydisplay.model.Model;
import frequencydisplay.view.View;

/**
 *
 * @author Keith
 */
public class DisplayController {
    
    private Model m_model;
    private View m_view;

    public DisplayController(Model model, View view) {
        m_model = model;
        m_view = view;
    }
    
    public void launch() {
        m_view.showView();
    }
    
}
