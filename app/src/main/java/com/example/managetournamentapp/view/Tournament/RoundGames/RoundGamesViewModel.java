package com.example.managetournamentapp.view.Tournament.RoundGames;

import androidx.lifecycle.ViewModel;

import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;
import com.example.managetournamentapp.memoryDao.TournamentDAOMemory;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class RoundGamesViewModel extends ViewModel {
    RoundGamesPresenter presenter;

    /**
     * the constructor
     */
    public RoundGamesViewModel(){
        presenter = new RoundGamesPresenter();
        presenter.setTournamentDAO( new TournamentDAOMemory());
        presenter.setLoggedInUser(new MemoryLoggedInUser());
    }


    /** get the presenter
     * @return the RoundGamesPresenter instance
     */
    public RoundGamesPresenter getPresenter(){
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
