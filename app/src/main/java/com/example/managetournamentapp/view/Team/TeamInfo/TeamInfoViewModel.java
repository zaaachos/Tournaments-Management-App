package com.example.managetournamentapp.view.Team.TeamInfo;

import androidx.lifecycle.ViewModel;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;
import com.example.managetournamentapp.memoryDao.TeamDAOMemory;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class TeamInfoViewModel extends ViewModel {
    TeamInfoPresenter presenter;

    /**
     * the constructor
     */
    public TeamInfoViewModel() {
        presenter = new TeamInfoPresenter();
        presenter.setLoggedInUser( new MemoryLoggedInUser() );
        presenter.setTeamDAO(new TeamDAOMemory());
    }

    /** get the presenter
     * @return the JoinedPlayersPresenter instance
     */
    public TeamInfoPresenter getPresenter() {
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
