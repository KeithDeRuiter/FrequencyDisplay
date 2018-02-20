/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencydisplay.model;

import frequencydisplay.data.Platform;
import frequencydisplay.data.SearchParameters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 *
 * @author Keith
 */
public class AppModel implements Model {

    private final List<Platform> platformList;
    private final List<SearchParameters> searchParametersList;
    private final List<ModelListener> modelListeners;

    private final ScheduledExecutorService notifier;
    
    public AppModel() {
        platformList = Collections.synchronizedList(new ArrayList<>());
        searchParametersList = Collections.synchronizedList(new ArrayList<>());
        modelListeners = new ArrayList<>();
        notifier = Executors.newSingleThreadScheduledExecutor();
    }
    
    
    @Override
    public void addModelListener(ModelListener listener) {
        modelListeners.add(listener);
    }
    
    @Override
    public void removeModelListener(ModelListener listener) {
        modelListeners.remove(listener);
    }

    @Override
    public void addPlatform(Platform p) {
        platformList.add(p);
        notifyPlatformAdd(p);
    }

    @Override
    public void removePlatform(Platform p) {
        platformList.remove(p);
        notifyPlatformRemove(p);
    }

    @Override
    public void addSearchParameters(SearchParameters params) {
        searchParametersList.add(params);
        notifySearchParametersAdd(params);
    }

    @Override
    public void removeSearchParameters(SearchParameters params) {
        searchParametersList.remove(params);
        notifySearchParametersRemove(params);
    }

    @Override
    public List<Platform> getAllPlatforms() {
        return Collections.unmodifiableList(platformList);
    }

    @Override
    public List<SearchParameters> getAllSearchParameters() {
        return Collections.unmodifiableList(searchParametersList);
    }
    
    private void notifyPlatformAdd(Platform p) {
        Runnable task = () -> {
            for (ModelListener l : modelListeners) {
                l.platformAdded(p);
            }
        };
        notifier.execute(task);
    }
    
    private void notifyPlatformRemove(Platform p) {
        Runnable task = () -> {
            for (ModelListener l : modelListeners) {
                l.platformRemoved(p);
            }
        };
        notifier.execute(task);
    }
    
    private void notifySearchParametersAdd(SearchParameters params) {
        Runnable task = () -> {
            for (ModelListener l : modelListeners) {
                l.searchParametersAdded(params);
            }
        };
        notifier.execute(task);
    }
    
    private void notifySearchParametersRemove(SearchParameters params) {
        Runnable task = () -> {
            for (ModelListener l : modelListeners) {
                l.searchParametersRemoved(params);
            }
        };
        notifier.execute(task);
    }

    @Override
    public Platform getPlatformByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Cannot get platform by name, name was null!");
        }
        for (Platform p : getAllPlatforms()) {
            if (name.equals(p.getPlatformClass())) {
                return p;
            }
        }
        return null;
    }
}
