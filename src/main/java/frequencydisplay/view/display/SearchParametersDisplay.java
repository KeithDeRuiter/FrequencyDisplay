/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencydisplay.view.display;

import frequencydisplay.data.Platform;
import frequencydisplay.data.SearchParameters;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Keith
 */
public class SearchParametersDisplay {
    
    private final List<SearchParametersSelectionListener> m_listeners;
    
    private final SearchParametersListModel parameterListModel;
    
    private JComponent m_listComponent;
    private JComponent m_editorComponent;
    
    private JFormattedTextField m_freqField;
    private JFormattedTextField m_bwField;

    public SearchParametersDisplay() {
        m_listeners = new ArrayList<>();
        parameterListModel = new SearchParametersListModel();
    }
    
    public void initialize() {
        parameterListModel.addSearchParameters(new SearchParameters.Builder("Steam Turbine").band(100, 20).build());
        parameterListModel.addSearchParameters(new SearchParameters.Builder("Nuclear").band(125, 20).build());
        parameterListModel.addSearchParameters(new SearchParameters.Builder("Gas Turbine").band(140, 20).build());
        parameterListModel.addSearchParameters(new SearchParameters.Builder("Diesel-Electric").band(160, 20).build());
        parameterListModel.addSearchParameters(new SearchParameters.Builder("Diesel").band(175, 20).build());
        parameterListModel.addSearchParameters(new SearchParameters.Builder("Gasoline").band(190, 20).build());
        parameterListModel.addSearchParameters(new SearchParameters.Builder("Supercarrier (100000 tons)").band(300, 50).build());
        parameterListModel.addSearchParameters(new SearchParameters.Builder("LPH, Merchant (50000 tons)").band(350, 50).build());
        parameterListModel.addSearchParameters(new SearchParameters.Builder("AOR, SSBN (25000) tons").band(350, 50).build());
        parameterListModel.addSearchParameters(new SearchParameters.Builder("SSBN (15000) tons").band(375, 50).build());
        parameterListModel.addSearchParameters(new SearchParameters.Builder("Cruiser (10000) tons").band(400, 50).build());
        parameterListModel.addSearchParameters(new SearchParameters.Builder("SSN, DDG (5000) tons").band(425, 50).build());
        parameterListModel.addSearchParameters(new SearchParameters.Builder("SSK, FFG (2500) tons").band(450, 50).build());
        parameterListModel.addSearchParameters(new SearchParameters.Builder("SSK, Corvette (1000) tons").band(500, 75).build());
        parameterListModel.addSearchParameters(new SearchParameters.Builder("FSG, Minesweeper (500) tons").band(525, 50).build());
        parameterListModel.addSearchParameters(new SearchParameters.Builder("SSM, Missile Boat (100) tons").band(550, 50).build());
        parameterListModel.addSearchParameters(new SearchParameters.Builder("Patrol Craft (50) tons").band(575, 50).build());
        parameterListModel.addSearchParameters(new SearchParameters.Builder("Speedboat (<25 tons)").band(600, 50).build());
        
        
        JList list = new JList(parameterListModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        list.setCellRenderer(new SearchParametersListCellRenderer());
        
        list.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            private SearchParameters previousSelection;
            
            @Override
            public void valueChanged(ListSelectionEvent e) {
                ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                
                //Get the selection
                SearchParameters newSelection = null;
                if (!lsm.isSelectionEmpty()) {
                    // Single selection, so only one index will be selected
                    int minIndex = lsm.getMinSelectionIndex();
                    newSelection = parameterListModel.getElementAt(minIndex);
                } else {
                    return;
                }
                
                //Update labels
                m_freqField.setText(String.valueOf(newSelection.getSearchBands().get(0).getCenterFreq()));
                m_bwField.setText(String.valueOf(newSelection.getSearchBands().get(0).getBandwidth()));
                
                //Construct event to notify listeners
                SearchParametersSelectionEvent pse = new SearchParametersSelectionEvent(newSelection, previousSelection);
                notifyOfSelectionChange(pse);
                
                previousSelection = newSelection;
            }
        });
        
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));
        
        m_listComponent = listScroller;
        
        JPanel editorPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        editorPanel.add(new JLabel("Center Frequency (Hz):"), gbc);
        
        gbc.gridx++;
        m_freqField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        m_freqField.setColumns(7);
        m_freqField.setEditable(false);
        editorPanel.add(m_freqField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        editorPanel.add(new JLabel("Bandwidth (Hz):"), gbc);
        
        gbc.gridx++;
        m_bwField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        m_bwField.setColumns(7);
        m_bwField.setEditable(false);
        editorPanel.add(m_bwField, gbc);
        
        m_editorComponent = editorPanel;
    }

    public JComponent getListComponent() {
        return m_listComponent;
    }

    public JComponent getEditorComponent() {
        return m_editorComponent;
    }
        
    public void addSearchParametersSelectionListener(SearchParametersSelectionListener listener) {
        m_listeners.add(listener);
    }
    
    public void removeSearchParametersSelectionListener(SearchParametersSelectionListener listener) {
        m_listeners.remove(listener);
    }
    
    private void notifyOfSelectionChange(SearchParametersSelectionEvent event) {
        for (SearchParametersSelectionListener l : m_listeners) {
            l.selectionChanged(event);
        }
    }
    
}
