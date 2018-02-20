/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencydisplay.view.display;

import frequencydisplay.data.Platform;

/**
 *
 * @author Keith
 */
public class PlatformSelectionEvent {
    private final Platform m_newlySelectedPlatform;
    private final Platform m_previouslySelectedPlatform;

    public PlatformSelectionEvent(Platform newlySelectedPlatform, Platform previouslySelectedPlatform) {
        m_newlySelectedPlatform = newlySelectedPlatform;
        m_previouslySelectedPlatform = previouslySelectedPlatform;
    }

    public Platform getNewlySelectedPlatform() {
        return m_newlySelectedPlatform;
    }

    public Platform getPreviouslySelectedPlatform() {
        return m_previouslySelectedPlatform;
    }
}
