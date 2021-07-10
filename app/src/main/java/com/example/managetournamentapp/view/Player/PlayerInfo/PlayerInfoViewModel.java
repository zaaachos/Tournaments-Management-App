package com.example.managetournamentapp.view.Player.PlayerInfo;

import androidx.lifecycle.ViewModel;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;
import com.example.managetournamentapp.memoryDao.PlayerDAOMemory;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class PlayerInfoViewModel extends ViewModel {
    PlayerInfoPresenter presenter;

    /**
     * the constructor
     */
    public PlayerInfoViewModel() {
        super();
        presenter = new PlayerInfoPresenter();
        presenter.setPlayerDAO(new PlayerDAOMemory());
        presenter.setLoggedInUser(new MemoryLoggedInUser());
    }

    /** get the presenter
     * @return the PlayerInfoPresenter instance
     */
    public PlayerInfoPresenter getPresenter() {
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
