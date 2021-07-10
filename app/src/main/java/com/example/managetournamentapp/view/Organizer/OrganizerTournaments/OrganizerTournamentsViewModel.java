package com.example.managetournamentapp.view.Organizer.OrganizerTournaments;
import androidx.lifecycle.ViewModel;

import com.example.managetournamentapp.memoryDao.OrganizerDAOMemory;
import com.example.managetournamentapp.memoryDao.TournamentDAOMemory;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class OrganizerTournamentsViewModel extends ViewModel {
    OrganizerTournamentsPresenter presenter;

    /**
     * Default constructor
     */
    public OrganizerTournamentsViewModel() {
        presenter = new OrganizerTournamentsPresenter();
        presenter.setTournamentDAO( new TournamentDAOMemory() );
        presenter.setOrganizerDAO( new OrganizerDAOMemory() );
    }

    /** get the presenter
     * @return the PlayerPagePresenter instance
     */
    public OrganizerTournamentsPresenter getPresenter() {
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
