package com.example.managetournamentapp.view.Player.JoinedTeams;

import androidx.lifecycle.ViewModel;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;
import com.example.managetournamentapp.memoryDao.PlayerDAOMemory;
import com.example.managetournamentapp.memoryDao.TeamDAOMemory;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class JoinedTeamsViewModel extends ViewModel{
    JoinedTeamsPresenter presenter;

    /**
     * the constructor
     */
    public JoinedTeamsViewModel() {
        super();
        presenter = new JoinedTeamsPresenter();
        presenter.setTeamDAO( new TeamDAOMemory());
        presenter.setPlayerDAO( new PlayerDAOMemory());
        presenter.setLoggedInUser( new MemoryLoggedInUser() );
    }

    /** get the presenter
     * @return the JoinedTeamsPresenter instance
     */
    public JoinedTeamsPresenter getPresenter() {
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

