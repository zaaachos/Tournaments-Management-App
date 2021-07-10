package com.example.managetournamentapp.view.Organizer.OrganizerInfo;

import androidx.lifecycle.ViewModel;

import com.example.managetournamentapp.domain.Organizer;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;
import com.example.managetournamentapp.memoryDao.OrganizerDAOMemory;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class OrganizerInfoViewModel extends ViewModel {
    OrganizerInfoPresenter presenter;

    /**
     * Default constructor
     */
    public OrganizerInfoViewModel(){
        presenter = new OrganizerInfoPresenter();
        presenter.setOrganizer((Organizer)new MemoryLoggedInUser().getUser());
        presenter.setOrganizerDAO(new OrganizerDAOMemory());
    }

    /**
     * @return the presenter instance
     */
    public OrganizerInfoPresenter getPresenter(){
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
