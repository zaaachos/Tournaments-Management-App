package com.example.managetournamentapp.view.Organizer.OrganizerPage;

import androidx.lifecycle.ViewModel;


import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;
import com.example.managetournamentapp.memoryDao.OrganizerDAOMemory;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class OrganizerPageViewModel extends ViewModel {
    OrganizerPagePresenter presenter;

    /**
     * Default Constructor
     */
    public OrganizerPageViewModel() {
        presenter = new OrganizerPagePresenter();
        presenter.setLoggedInUser(new MemoryLoggedInUser());
        presenter.setOrganizerDAO(new OrganizerDAOMemory());
    }

    /** get the presenter
     * @return the OrganizerPagePresenter instance
     */
    public OrganizerPagePresenter getPresenter(){
        return presenter;
    }

    /**
     * clear the view of the presenter
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        // avoid leaking activity for any reason
        presenter.clearView();
    }
}
