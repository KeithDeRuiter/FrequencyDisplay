/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencydisplay;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Keith
 */
public class FrequencyDisplay {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Frequency Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1200, 200));
        frame.setLayout(new BorderLayout());
        
        final FrequencyDisplayComponent comp = new FrequencyDisplayComponent();
        comp.initialize();
        
        frame.add(comp.getComponent(), BorderLayout.CENTER);
        
        JPanel controlPanel = new JPanel();
        
        controlPanel.add(new JLabel("Freq (Hz): "));
        
        final JFormattedTextField freqField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        freqField.setColumns(7);
        controlPanel.add(freqField);
        
        controlPanel.add(new JLabel("Width (Hz): "));
        
        final JFormattedTextField bwField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        bwField.setColumns(7);
        controlPanel.add(bwField);
        
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int freq = Integer.valueOf(freqField.getText());
                    int bw = Integer.valueOf(bwField.getText());
                    comp.addBand(freq, bw);
                } catch (NumberFormatException ex) {
                    // ignore
                }
            }
        });
        controlPanel.add(addButton);
        
        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int freq = Integer.valueOf(freqField.getText());
                    comp.removeBand(freq);
                } catch (NumberFormatException ex) {
                    // ignore
                }
            }
        });
        controlPanel.add(removeButton);
        
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comp.clearAllBands();
            }
        });
        controlPanel.add(clearButton);
        
        frame.add(controlPanel, BorderLayout.NORTH);
        
        frame.pack();
        frame.setVisible(true);
    }
    
}
