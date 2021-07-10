package com.example.managetournamentapp.view.Team.ParticipatingTournaments;
import androidx.lifecycle.ViewModel;

import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;
import com.example.managetournamentapp.memoryDao.TeamDAOMemory;
import com.example.managetournamentapp.memoryDao.TournamentDAOMemory;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class ParticipatingTournamentsViewModel extends ViewModel {
    ParticipatingTournamentsPresenter presenter;

    /**
     * the constructor
     */
    public ParticipatingTournamentsViewModel() {
        presenter = new ParticipatingTournamentsPresenter();
        presenter.setTournamentDAO( new TournamentDAOMemory());
        presenter.setLoggedInUser(new MemoryLoggedInUser());
        presenter.setTeamDAO(new TeamDAOMemory());

    }

    /** get the presenter
     * @return the ParticipatingTournamentsPresenter instance
     */
    public ParticipatingTournamentsPresenter getPresenter() {
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