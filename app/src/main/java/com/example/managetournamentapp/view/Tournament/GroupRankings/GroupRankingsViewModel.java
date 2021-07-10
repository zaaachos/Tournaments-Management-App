package com.example.managetournamentapp.view.Tournament.GroupRankings;

import androidx.lifecycle.ViewModel;
import com.example.managetournamentapp.memoryDao.TournamentDAOMemory;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class GroupRankingsViewModel extends ViewModel {
    GroupRankingsPresenter presenter;

    /**
     * the constructor
     */
    public GroupRankingsViewModel() {
        presenter = new GroupRankingsPresenter();
        presenter.setTournamentDAO(new TournamentDAOMemory());
    }

    /** get the presenter
     * @return the GroupRankingsPresenter instance
     */
    public GroupRankingsPresenter getPresenter() {
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
