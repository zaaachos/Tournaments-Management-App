package com.example.managetournamentapp.view.Tournament.TournamentRounds;

import androidx.lifecycle.ViewModel;

import com.example.managetournamentapp.memoryDao.TournamentDAOMemory;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class TournamentRoundsViewModel extends ViewModel {
    TournamentRoundsPresenter presenter;

    /**
     * the constructor
     */
    public TournamentRoundsViewModel(){
        presenter = new TournamentRoundsPresenter();
        presenter.setTournamentDAO(new TournamentDAOMemory());
    }

    /**
     * @return the presenter instance
     */
    public TournamentRoundsPresenter getPresenter(){
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
