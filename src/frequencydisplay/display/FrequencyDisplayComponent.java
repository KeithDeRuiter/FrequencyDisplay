/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencydisplay.display;

import frequencydisplay.data.FrequencyBand;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;

/**
 *
 * @author Keith
 */
public class FrequencyDisplayComponent {
    JFrequencyDisplay m_component;
    
    public FrequencyDisplayComponent() {
    }
    
    public void initialize() {
        m_component = new JFrequencyDisplay(0, 1000);
    }
    
    public void addBand(FrequencyBand band) {
        System.out.println("Display Adding band: " + band);
        m_component.addBand(band);
    }
    
    public void removeBand(FrequencyBand band) {
        m_component.removeBand(band);
    }
    
    public void clearAllBands() {
        m_component.removeAllBands();
    }
    
    public JComponent getComponent() {
        return m_component;
    }
    
    
    
    private class JFrequencyDisplay extends JComponent {

        private static final String LABEL = "Frequency (Hz)";
        
        private static final int BAND_HEIGHT = 50;
        private static final int TICK_HEIGHT = 4;
        
        private final int m_lowerFreqBound;
        private final int m_upperFreqBound;
        private final int m_lowerIntensityBound;
        private final int m_upperIntensityBound;
        
        private List<FrequencyBand> m_bands;
        
        
        public JFrequencyDisplay(int lowerBound, int upperBound) {
            m_lowerFreqBound = lowerBound;
            m_upperFreqBound = upperBound;
            m_lowerIntensityBound = 0;
            m_upperIntensityBound = 100;
            
            m_bands = new ArrayList<>();
            
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseMoved(e);
                    System.out.println("Mouse at: <" + e.getX() + ", " + e.getY() + ">");
                }
            });
        }
        
        public void addBand(FrequencyBand band) {
            System.out.println("Adding band to display: " + band);
            m_bands.add(band);
            repaint();
        }
        
        public void removeBand(FrequencyBand band) {
            System.out.println("Removing band from display: " + band);
            m_bands.remove(band);
            repaint();
        }
        
        public void removeAllBands() {
            System.out.println("Removing all bands from display");
            m_bands.clear();
            repaint();
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            
            int width = getWidth();
            int height = getHeight();
            
            //Blank screen
            g2d.setColor(getBackground());
            g2d.fillRect(0, 0, width, height);
            
            
            //Get offsets for label size
            FontMetrics fm = g2d.getFontMetrics();
            Rectangle2D rect = fm.getStringBounds(LABEL, g2d);
            int labelHeight = (int)rect.getHeight();
            int labelDescender = fm.getMaxDescent();
            
            int virtualGraphHeight = Math.abs(m_upperIntensityBound - m_lowerIntensityBound);
            int virtualGraphWidth = Math.abs(m_upperFreqBound - m_lowerFreqBound);
            
            int graphBottomEdge = getHeight() -  2 * labelHeight;
            float horizScaleFactor = (float)width / (float)virtualGraphWidth;
            float vertScaleFactor = (float)(graphBottomEdge) / (float)virtualGraphHeight;
            
            System.out.println("hScale: " + horizScaleFactor);
            System.out.println("vScale: " + vertScaleFactor);
            
            //Blank graph area
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, width, graphBottomEdge);
            
            //Draw Text Labels
            g2d.setColor(Color.BLACK);
            g2d.drawString(LABEL, 10, height - labelDescender);
            for (int i = m_lowerFreqBound; i <= m_upperFreqBound; i += 100) {
                //Get unscaled location, then scale it to find out where the text would be placed
                Point2D textLocation = new Point2D.Float(i * horizScaleFactor, height - labelHeight - labelDescender);

                //render the text while still under initial transform so the words are not stretched
                g2d.drawString(String.valueOf(i), (int)textLocation.getX(), (int)textLocation.getY());
                
                System.out.println("Drawing string at: " + textLocation);
            }
            
            //Draw tick marks
            g2d.setColor(Color.WHITE);
            g2d.drawLine(0, graphBottomEdge, virtualGraphWidth, graphBottomEdge);
            for (int i = 0; i <= m_upperFreqBound; i += 100) {
                Point2D tickLocation = new Point2D.Float(i * horizScaleFactor, graphBottomEdge - TICK_HEIGHT);
                g2d.drawLine((int)tickLocation.getX(), (int)tickLocation.getY(), (int)tickLocation.getX(), graphBottomEdge);
            }
            
            //Scale after labels/ticks so text will not be stretched.  accounted for with text positioning
            g2d.scale(horizScaleFactor, vertScaleFactor);
            
            //Draw Bands
            for (FrequencyBand band : m_bands) {
                System.out.println("Drawing band: " + band);
                g2d.setColor(Color.GREEN);
                g2d.fillRect(band.getStartFreq(), virtualGraphHeight - BAND_HEIGHT, band.getBandwidth(), 50);
            }
        }
        
    }
    
}
