package com.example.managetournamentapp.view.Team.TeamPage;

import androidx.lifecycle.ViewModel;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;
import com.example.managetournamentapp.memoryDao.TeamDAOMemory;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class TeamPageViewModel extends ViewModel {
    TeamPagePresenter presenter;

    /**
     * the constructor
     */
    public TeamPageViewModel() {
        presenter = new TeamPagePresenter();
        presenter.setLoggedInUser( new MemoryLoggedInUser() );
        presenter.setTeamDAO(new TeamDAOMemory());
    }

    /** get the presenter
     * @return the TeamPagePresenter instance
     */
    public TeamPagePresenter getPresenter() {
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
