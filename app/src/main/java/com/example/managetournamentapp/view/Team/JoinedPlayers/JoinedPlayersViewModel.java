package com.example.managetournamentapp.view.Team.JoinedPlayers;

import androidx.lifecycle.ViewModel;

import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;
import com.example.managetournamentapp.memoryDao.PlayerDAOMemory;
import com.example.managetournamentapp.memoryDao.TeamDAOMemory;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class JoinedPlayersViewModel extends ViewModel {
    JoinedPlayersPresenter presenter;

    /**
     * the constructor
     */
    public JoinedPlayersViewModel() {
        presenter = new JoinedPlayersPresenter();
        presenter.setPlayerDAO(new PlayerDAOMemory());
        presenter.setTeamDAO(new TeamDAOMemory());
        presenter.setLoggedInUser(new MemoryLoggedInUser());
    }

    /** get the presenter
     * @return the JoinedPlayersPresenter instance
     */
    public JoinedPlayersPresenter getPresenter() {
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
