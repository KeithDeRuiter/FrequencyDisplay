/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencydisplay.view.display;

import frequencydisplay.data.Platform;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Keith
 */
public class PlatformTableDisplay {
    
    private JScrollPane m_pane;
    private JTable m_table;
    private PlatformTableModel m_model;

    private final List<PlatformSelectionListener> m_listeners;
    
    public PlatformTableDisplay() {
        m_listeners = new ArrayList<>();
    }
    
    
    public void initialize() {
        m_model = new PlatformTableModel();
        
        m_table = new JTable(m_model);
        m_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        m_table.setRowSelectionAllowed(true);
        m_table.setColumnSelectionAllowed(false);
        m_table.getTableHeader().setReorderingAllowed(false);
        m_table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            
            private Platform previousSelection;
            
            @Override
            public void valueChanged(ListSelectionEvent e) {
                ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                
                int firstIndex = e.getFirstIndex();
                int lastIndex = e.getLastIndex();
                boolean isAdjusting = e.getValueIsAdjusting();
                /*System.out.println("Event for indexes "
                              + firstIndex + " - " + lastIndex
                              + "; isAdjusting is " + isAdjusting
                              + "; selected indexes:");*/

                Platform newSelection = null;
                
                if (lsm.isSelectionEmpty()) {
                    /*System.out.println(" <none>");*/
                } else {
                    // Find out which indexes are selected.
                    int minIndex = lsm.getMinSelectionIndex();
                    int maxIndex = lsm.getMaxSelectionIndex();
                    for (int i = minIndex; i <= maxIndex; i++) {
                        if (lsm.isSelectedIndex(i)) {
                            newSelection = m_model.getPlatformAtIndex(i);
                        }
                    }
                }
                
                PlatformSelectionEvent pse = new PlatformSelectionEvent(newSelection, previousSelection);
                previousSelection = newSelection;
                
                notifyOfSelectionChange(pse);
            }
        });
        
        m_pane = new JScrollPane(m_table);
    }
    
    public JComponent getComponent() {
        return m_pane;
    }
    
    public void addPlatformSelectionListener(PlatformSelectionListener listener) {
        m_listeners.add(listener);
    }
    
    public void removePlatformSelectionListener(PlatformSelectionListener listener) {
        m_listeners.remove(listener);
    }
    
    private void notifyOfSelectionChange(PlatformSelectionEvent event) {
        for (PlatformSelectionListener l : m_listeners) {
            l.selectionChanged(event);
        }
    }
    
    public void addPlatform(Platform p) {
        m_model.addPlatformRow(p);
    }
}
