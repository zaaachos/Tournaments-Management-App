package com.example.managetournamentapp.view.Player.PlayerPage;

import androidx.lifecycle.ViewModel;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;
import com.example.managetournamentapp.memoryDao.PlayerDAOMemory;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class PlayerPageViewModel extends ViewModel {
    PlayerPagePresenter presenter;

    /**
     * the constructor
     */
    public PlayerPageViewModel() {
        presenter = new PlayerPagePresenter();
        presenter.setLoggedInUser( new MemoryLoggedInUser() );
        presenter.setPlayerDAO( new PlayerDAOMemory());
    }

    /** get the presenter
     * @return the PlayerPagePresenter instance
     */
    public PlayerPagePresenter getPresenter() {
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
