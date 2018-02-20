/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencydisplay.view;

import frequencydisplay.data.Platform;

/**
 *
 * @author Keith
 */
public interface View {
    public void addViewListener(ViewListener listener);
    public void removeViewListener(ViewListener listener);
    public void initialize();
    public void showView();
    public void hideView();
    public void addPlatformToList(Platform platform);
    public void removePlatformFromList(Platform platform);
    public void addSearchParametersToList();
    public void removeSearchParametersFromList();
}
