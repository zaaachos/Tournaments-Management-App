package com.example.managetournamentapp.view.User.RegisterPlayer;

import androidx.lifecycle.ViewModel;

import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;
import com.example.managetournamentapp.memoryDao.OrganizerDAOMemory;
import com.example.managetournamentapp.memoryDao.PlayerDAOMemory;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class RegisterPlayerViewModel extends ViewModel {
    RegisterPlayerPresenter presenter;

    /**
     * the default constructor
     */
    public RegisterPlayerViewModel() {
        presenter = new RegisterPlayerPresenter();
        presenter.setLoggedInUser( new MemoryLoggedInUser());
        presenter.setPlayerDAO(new PlayerDAOMemory());
        presenter.setOrganizerDAO(new OrganizerDAOMemory());
    }

    /** get the presenter
     * @return the RegisterPlayerPresenter instance
     */
    public RegisterPlayerPresenter getPresenter() {
        return presenter;
    }

    /**
     * clear the view of the presenter
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        presenter.clearView();
    }

}