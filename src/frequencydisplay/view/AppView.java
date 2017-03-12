/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencydisplay.view;

import frequencydisplay.data.FrequencyBand;
import frequencydisplay.data.Platform;
import frequencydisplay.data.SamplePlatformDatabase;
import frequencydisplay.display.FrequencyDisplayComponent;
import frequencydisplay.display.PlatformSelectionEvent;
import frequencydisplay.display.PlatformSelectionListener;
import frequencydisplay.display.PlatformTable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Keith
 */
public class AppView implements View {
    
    private JFrame m_frame;
    private final String WINDOW_TITLE = "Frequency Display";

    private FrequencyDisplayComponent freqComponent;
    private PlatformTable table;
    
    public AppView() {
    }
    
    @Override
    public void initialize() {
        m_frame = new JFrame(WINDOW_TITLE);
        m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m_frame.setPreferredSize(new Dimension(1200, 500));
        m_frame.setLayout(new BorderLayout());
        
        //Frequency display
        freqComponent = new FrequencyDisplayComponent();
        freqComponent.initialize();
        JComponent fd = freqComponent.getComponent();
        fd.setPreferredSize(new Dimension(500, 100));
        m_frame.add(fd, BorderLayout.SOUTH);
        
        //Table
        table = new PlatformTable();
        table.initialize();
        m_frame.add(table.getComponent(), BorderLayout.CENTER);

        //Add listener so bands wil be displayed when a platform is selected
        table.addPlatformSelectionListener(new PlatformSelectionListener() {
            List<FrequencyBand> currentlySelectedBands = new ArrayList<>();
            
            @Override
            public void platformSelectionChanged(PlatformSelectionEvent event) {
                //Clear old bands
                for (FrequencyBand b : currentlySelectedBands) {
                    freqComponent.removeBand(b);
                }
                currentlySelectedBands.clear();
                
                //Add new bands
                for (Integer freq : event.getNewlySelectedPlatform().getFrequencies()) {
                    FrequencyBand band = new FrequencyBand(freq, 1);
                    currentlySelectedBands.add(band);
                    freqComponent.addBand(band);
                }
            }
        });
        
        //Control panel
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
                    FrequencyBand band = new FrequencyBand(freq, bw);
                    freqComponent.addBand(band);
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
                freqComponent.clearAllBands();
            }
        });
        controlPanel.add(clearButton);
        
        m_frame.add(controlPanel, BorderLayout.NORTH);
    }
    
    @Override
    public void showView() {
        m_frame.pack();
        m_frame.setVisible(true);
    }
    
    @Override
    public void hideView() {
        m_frame.setVisible(false);
    }

    @Override
    public void addPlatformToList(Platform platform) {
        table.addPlatform(platform);
    }

    @Override
    public void removePlatformFromList(Platform platform) {
        
    }

    @Override
    public void addSearchParametersToList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeSearchParametersFromList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
