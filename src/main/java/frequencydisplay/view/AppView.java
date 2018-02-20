/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencydisplay.view;

import frequencydisplay.data.FrequencyBand;
import frequencydisplay.data.Platform;
import frequencydisplay.view.display.FrequencyDisplayComponent;
import frequencydisplay.view.display.PlatformSelectionEvent;
import frequencydisplay.view.display.PlatformSelectionListener;
import frequencydisplay.view.display.PlatformTableDisplay;
import frequencydisplay.view.display.SearchParametersDisplay;
import frequencydisplay.view.display.SearchParametersSelectionEvent;
import frequencydisplay.view.display.SearchParametersSelectionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Keith
 */
public class AppView implements View {
    
    private JFrame frame;
    private final String WINDOW_TITLE = "Frequency Display";

    private FrequencyDisplayComponent freqComponent;
    private PlatformTableDisplay table;
    
    private final List<ViewListener> listeners;
    
    public AppView() {
        listeners = new ArrayList<>();
    }
    
    @Override
    public void initialize() {
        frame = new JFrame(WINDOW_TITLE);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                listeners.forEach((listener) -> {
                    listener.viewClosed();
                });
                frame.dispose();
                System.exit(0);
            }
        });
        frame.setPreferredSize(new Dimension(1200, 500));
        frame.setLayout(new BorderLayout());
        
        //Frequency display
        freqComponent = new FrequencyDisplayComponent();
        freqComponent.initialize();
        JComponent fd = freqComponent.getComponent();
        fd.setPreferredSize(new Dimension(500, 100));
        frame.add(fd, BorderLayout.SOUTH);
        
        //Table
        table = new PlatformTableDisplay();
        table.initialize();
        frame.add(table.getComponent(), BorderLayout.CENTER);

        //Add listener so bands wil be displayed when a platform is selected
        table.addPlatformSelectionListener(new PlatformSelectionListener() {
            List<UUID> currentlySelectedBands = new ArrayList<>();
            
            @Override
            public void selectionChanged(PlatformSelectionEvent event) {
                //Clear old bands
                for (UUID id : currentlySelectedBands) {
                    freqComponent.removeBand(id);
                }
                currentlySelectedBands.clear();
                
                //Add new bands
                for (Integer freq : event.getNewlySelectedPlatform().getFrequencies()) {
                    FrequencyBand band = new FrequencyBand(freq, 3, 50);
                    UUID id = freqComponent.addTargetBand(band);
                    currentlySelectedBands.add(id);
                }
            }
        });
        
        //Search Parameters
        SearchParametersDisplay spDisplay = new SearchParametersDisplay();
        spDisplay.initialize();
        spDisplay.addSearchParametersSelectionListener(new SearchParametersSelectionListener() {
            List<UUID> currentlySearchBands = new ArrayList<>();
            
            @Override
            public void selectionChanged(SearchParametersSelectionEvent event) {
                //Clear old bands
                for (UUID id : currentlySearchBands) {
                    freqComponent.removeBand(id);
                }
                currentlySearchBands.clear();
                
                //Add new bands
                for (FrequencyBand band : event.getNewlySelectedSearchParameters().getSearchBands()) {
                    UUID id = freqComponent.addSearchBand(band);
                    currentlySearchBands.add(id);
                }
            }
        });
        JComponent spList = spDisplay.getListComponent();
        JComponent spEditor = spDisplay.getEditorComponent();
        JPanel searchParamsPanel = new JPanel(new BorderLayout());
        searchParamsPanel.add(spList, BorderLayout.CENTER);
        searchParamsPanel.add(spEditor, BorderLayout.NORTH);
        
        frame.add(searchParamsPanel, BorderLayout.EAST);
        
//        //Control panel
//        JPanel controlPanel = new JPanel();
//        
//        controlPanel.add(new JLabel("Freq (Hz): "));
//        
//        final JFormattedTextField freqField = new JFormattedTextField(NumberFormat.getIntegerInstance());
//        freqField.setColumns(7);
//        controlPanel.add(freqField);
//        
//        controlPanel.add(new JLabel("Width (Hz): "));
//        
//        final JFormattedTextField bwField = new JFormattedTextField(NumberFormat.getIntegerInstance());
//        bwField.setColumns(7);
//        controlPanel.add(bwField);
//        
//        JButton addButton = new JButton("Add");
//        addButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    int freq = Integer.valueOf(freqField.getText());
//                    int bw = Integer.valueOf(bwField.getText());
//                    FrequencyBand band = new FrequencyBand(freq, bw, 40);
//                    freqComponent.addTargetBand(band);
//                } catch (NumberFormatException ex) {
//                    // ignore
//                }
//            }
//        });
//        controlPanel.add(addButton);
//        
//        JButton removeButton = new JButton("Remove");
//        removeButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    int freq = Integer.valueOf(freqField.getText());
//                    
//                } catch (NumberFormatException ex) {
//                    // ignore
//                }
//            }
//        });
//        controlPanel.add(removeButton);
//        
//        JButton clearButton = new JButton("Clear");
//        clearButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                freqComponent.clearAllBands();
//            }
//        });
//        controlPanel.add(clearButton);
//        
//        m_frame.add(controlPanel, BorderLayout.NORTH);
    }
    
    @Override
    public void showView() {
        frame.pack();
        frame.setVisible(true);
    }
    
    @Override
    public void hideView() {
        frame.setVisible(false);
    }

    @Override
    public void addPlatformToList(Platform platform) {
        table.addPlatform(platform);
    }

    @Override
    public void removePlatformFromList(Platform platform) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addSearchParametersToList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeSearchParametersFromList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addViewListener(ViewListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeViewListener(ViewListener listener) {
        listeners.remove(listener);
    }
}
