/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencydisplay.data;

/**
 *
 * @author Keith
 */
public class FrequencyBand {
    private final int m_centerFreq;
    private final int m_bandwidth;
    private final int m_intensity;

    public FrequencyBand(int centerFreq, int bandwidth, int intensity) {
        m_centerFreq = centerFreq;
        m_bandwidth = bandwidth;
        m_intensity = intensity;
    }
    
    public int getCenterFreq() {
        return m_centerFreq;
    }
    
    public int getBandwidth() {
        return m_bandwidth;
    }
    
    public int getStartFreq() {
        return m_centerFreq - m_bandwidth / 2;
    }
    
    public int getEndFreq() {
        return m_centerFreq + m_bandwidth / 2;
    }

    public int getIntensity() {
        return m_intensity;
    }

    @Override
    public String toString() {
        return "FrequencyBand{" + "m_centerFreq=" + m_centerFreq + ", m_bandwidth=" + m_bandwidth + ", m_intensity=" + m_intensity + '}';
    }
    
    
}
