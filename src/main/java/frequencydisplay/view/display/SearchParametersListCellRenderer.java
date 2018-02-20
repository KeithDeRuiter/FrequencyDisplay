/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencydisplay.view.display;

import frequencydisplay.data.SearchParameters;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Keith
 */
public class SearchParametersListCellRenderer implements ListCellRenderer<SearchParameters> {

    private static final Border BORDER = new EmptyBorder(1, 1, 1, 1);
    
    @Override
    public Component getListCellRendererComponent(JList<? extends SearchParameters> list, SearchParameters value, int index, boolean isSelected, boolean cellHasFocus) {
        SearchParameters params = list.getModel().getElementAt(index);
        JLabel comp = new JLabel(params.getName());
        
        comp.setEnabled(list.isEnabled());
        comp.setComponentOrientation(list.getComponentOrientation());
        comp.setBorder(BORDER);
        comp.setOpaque(true);
        comp.setFont(list.getFont());
        
        Color bg = null;
        Color fg = null;

        if (isSelected) {
            bg = list.getSelectionBackground();
            fg = list.getSelectionForeground();
        } else {
            bg = list.getBackground();
            fg = list.getForeground();
        }
        comp.setBackground(bg);
        comp.setForeground(fg);
        
        return comp;
    }
}
