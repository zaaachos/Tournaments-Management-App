package com.example.managetournamentapp.view.HomePage;

import androidx.lifecycle.ViewModel;


/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class HomePageViewModel extends ViewModel {

    HomePagePresenter presenter;

    /**
     * Default constructor
     */
    public HomePageViewModel() {
        presenter = new HomePagePresenter();
    }

    /**
     * @return the presenter instance
     */
    public HomePagePresenter getPresenter() {
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