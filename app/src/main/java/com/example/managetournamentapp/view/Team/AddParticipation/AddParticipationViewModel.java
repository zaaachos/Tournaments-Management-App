package com.example.managetournamentapp.view.Team.AddParticipation;
import androidx.lifecycle.ViewModel;

import com.example.managetournamentapp.memoryDao.TeamDAOMemory;
import com.example.managetournamentapp.memoryDao.TournamentDAOMemory;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class AddParticipationViewModel extends ViewModel {
    AddParticipationPresenter presenter;

    /**
     * the constructor
     */
    public AddParticipationViewModel(){
        presenter = new AddParticipationPresenter();
        presenter.setTournamentDAO( new TournamentDAOMemory() );
        presenter.setTeamDAO( new TeamDAOMemory() );
    }

    /** get the presenter
     * @return the AddParticipationPresenter instance
     */
    public AddParticipationPresenter getPresenter() {
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
