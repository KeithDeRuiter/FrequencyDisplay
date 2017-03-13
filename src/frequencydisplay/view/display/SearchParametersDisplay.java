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

    public SearchParametersDisplay() {
        m_listeners = new ArrayList<>();
        parameterListModel = new SearchParametersListModel();
    }
    
    public void initialize() {
        parameterListModel.addSearchParameters(new SearchParameters.Builder("Sneaky").band(50, 15).build());
        
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
                
                SearchParameters newSelection = null;
                
                if (!lsm.isSelectionEmpty()) {
                    // Single selection, so only one index will be selected
                    int minIndex = lsm.getMinSelectionIndex();
                    newSelection = parameterListModel.getElementAt(minIndex);
                }
                
                SearchParametersSelectionEvent pse = new SearchParametersSelectionEvent(newSelection, previousSelection);
                previousSelection = newSelection;
                
                notifyOfSelectionChange(pse);
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
        JFormattedTextField freqField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        freqField.setColumns(7);
        editorPanel.add(freqField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        editorPanel.add(new JLabel("Bandwidth (Hz):"), gbc);
        
        gbc.gridx++;
        JFormattedTextField bwField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        bwField.setColumns(7);
        editorPanel.add(bwField, gbc);
        
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
