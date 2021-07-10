package com.example.managetournamentapp.view.Team.InvitePlayers;

import androidx.lifecycle.ViewModel;

import com.example.managetournamentapp.memoryDao.PlayerDAOMemory;
import com.example.managetournamentapp.memoryDao.TeamDAOMemory;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class InvitePlayersViewModel extends ViewModel {
    InvitePlayersPresenter presenter;

    /**
     * the constructor
     */
    public InvitePlayersViewModel() {
        presenter = new InvitePlayersPresenter();
        presenter.setPlayerDAO(new PlayerDAOMemory());
        presenter.setTeamDAO(new TeamDAOMemory());
    }

    /** get the presenter
     * @return the InvitePlayersPresenter instance
     */
    public InvitePlayersPresenter getPresenter() {
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
