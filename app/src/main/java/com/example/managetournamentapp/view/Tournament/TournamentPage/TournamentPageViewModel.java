package com.example.managetournamentapp.view.Tournament.TournamentPage;

import androidx.lifecycle.ViewModel;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class TournamentPageViewModel extends ViewModel {
    TournamentPagePresenter presenter;

    /**
     * the constructor
     */
    public TournamentPageViewModel(){
        super();
        presenter = new TournamentPagePresenter();
    }

    /**
     * @return the presenter instance
     */
    public TournamentPagePresenter getPresenter(){
        return presenter;
    }

    /**
     * clear the view of the presenter
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        // avoid leaking activity for any reasons
        presenter.clearView();
    }
}
