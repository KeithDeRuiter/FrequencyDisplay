/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencydisplay.display;

import frequencydisplay.data.Platform;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Keith
 */
public class PlatformTableModel extends AbstractTableModel {
    private final String[] COLUMN_NAMES = {
        "Class",
        "Freq 1",
        "Freq 2",
        "Freq 3",
        "Freq 4",
        "Freq 5",
        "Blades",
        "TPK"
    };
    
    private final List<Platform> m_data;
    
    
    public PlatformTableModel() {
        m_data = new ArrayList<>();
    }
    
    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }
    
    @Override
    public int getRowCount() {
        return m_data.size();
    }

    @Override
    public String getColumnName(int col) {
        return COLUMN_NAMES[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Platform p = m_data.get(row);
        
        Object val = null;
        switch (col) {
            case 0:
                val = p.getPlatformClass();
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                if (p.getFrequencies().size() >= col) {
                    val = p.getFrequencies().get(col - 1);
                }
                break;
            case 6:
                val = p.getNumBlades();
                break;
            case 7:
                val = p.getTurnsPerKnot();
                break;
        }
        
        return val;
    }
    
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
    
    public void addPlatformRow(Platform platform) {
        m_data.add(platform);    
        
        fireTableRowsInserted(getRowCount(), getRowCount());
    }
    
    public Platform getPlatformAtIndex(int i) {
        return m_data.get(i);
    }
}
