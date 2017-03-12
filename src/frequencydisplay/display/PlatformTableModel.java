/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencydisplay.display;

import frequencydisplay.data.Platform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Keith
 */
public class PlatformTableModel extends DefaultTableModel {
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
    
    private final Map<String, Platform> m_platformClassMap;

    public PlatformTableModel() {
        m_platformClassMap = new HashMap<>();
    }
    
    

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int col) {
        return COLUMN_NAMES[col];
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
        m_platformClassMap.put(platform.getPlatformClass(), platform);
        addRow(getTableRowForPlatform(platform));
    }
    
    public Platform getPlatformAtIndex(int i) {
        String c = (String)getValueAt(i, 0);  //Use the class as the key to lookup the platform in the map
        return m_platformClassMap.get(c);
    }
    
    public static Object[] getTableRowForPlatform(Platform p) {
        List<Object> rowList = new ArrayList<>();
        
        //Class
        rowList.add(p.getPlatformClass());
        
        //5 Frequencies
        List<Integer> freqs = p.getFrequencies();
        for (int i = 0; i < 5 ; i++) {
            if (i < freqs.size()) {
                rowList.add(freqs.get(i));
            } else {
                //Default value if there are not enough frequencies
                rowList.add(null);
            }
        }
        
        //Number of Blades
        rowList.add(p.getNumBlades());
        
        //Turns Per Knot
        rowList.add(p.getTurnsPerKnot());
        
        Object[] rowArray = new Object[rowList.size()];
        rowArray = rowList.toArray(rowArray);
        
        return rowArray;
    }
    
    
}
