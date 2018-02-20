/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencydisplay.view.display;

import frequencydisplay.data.SearchParameters;

/**
 *
 * @author Keith
 */
public class SearchParametersSelectionEvent {
    private final SearchParameters m_newlySelectedSearchParameters;
    private final SearchParameters m_previouslySelectedSearchParameters;

    public SearchParametersSelectionEvent(SearchParameters newlySelectedSearchParameters, SearchParameters previouslySelectedSearchParameters) {
        m_newlySelectedSearchParameters = newlySelectedSearchParameters;
        m_previouslySelectedSearchParameters = previouslySelectedSearchParameters;
    }

    public SearchParameters getNewlySelectedSearchParameters() {
        return m_newlySelectedSearchParameters;
    }

    public SearchParameters getPreviouslySelectedSearchParameters() {
        return m_previouslySelectedSearchParameters;
    }
}
