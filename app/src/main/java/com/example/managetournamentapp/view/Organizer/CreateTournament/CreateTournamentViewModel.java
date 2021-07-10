package com.example.managetournamentapp.view.Organizer.CreateTournament;


import androidx.lifecycle.ViewModel;

import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;
import com.example.managetournamentapp.memoryDao.TournamentDAOMemory;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class CreateTournamentViewModel extends ViewModel {
    CreateTournamentPresenter presenter;

    /**
     * Default constructor
     */
    public CreateTournamentViewModel() {
        presenter = new CreateTournamentPresenter();
        presenter.setTournamentDAO( new TournamentDAOMemory());
        presenter.setOrganizer(new MemoryLoggedInUser().getUser());

    }

    /**
     * @return the presenter instance
     */
    public CreateTournamentPresenter getPresenter() {

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
