/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencydisplay.model;

import frequencydisplay.data.Platform;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 *
 * @author Keith
 */
public class AppModel implements Model {

    private final List<Platform> m_platformList;
    private final List<ModelListener> m_modelListeners;

    private final ScheduledExecutorService m_notifier;
    
    public AppModel() {
        m_platformList = new ArrayList<>();
        m_modelListeners = new ArrayList<>();
        m_notifier = Executors.newSingleThreadScheduledExecutor();
    }
    
    
    
    @Override
    public void addModelListener(ModelListener listener) {
        m_modelListeners.add(listener);
    }
    
    @Override
    public void removeModelListener(ModelListener listener) {
        m_modelListeners.remove(listener);
    }

    @Override
    public void addPlatform(Platform p) {
        m_platformList.add(p);
        notifyPlatformAdd(p);
    }

    @Override
    public void removePlatform(Platform p) {
        m_platformList.remove(p);
        notifyPlatformRemove(p);
    }

    @Override
    public void addSearchParameters() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeSearchParameters() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void notifyPlatformAdd(Platform p) {
        Runnable task = () -> {
            for (ModelListener l : m_modelListeners) {
                l.platformAdded(p);
            }
        };
        m_notifier.execute(task);
    }
    
    private void notifyPlatformRemove(Platform p) {
        Runnable task = () -> {
            for (ModelListener l : m_modelListeners) {
                l.platformRemoved(p);
            }
        };
        m_notifier.execute(task);
    }
}
