package com.example.managetournamentapp.view.Tournament.RoundGames;

import com.example.managetournamentapp.domain.Game;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class RoundGamesViewStub implements RoundGamesView {
    boolean onHome = false, onPop = false;

    @Override
    public void showToast(String test) {

    }

    @Override
    public void showPopup(Game game) {
        onPop = true;
    }

    @Override
    public void recreateView() {

    }

    @Override
    public void backToHomePage(boolean noLogin, boolean isPlayer, String name) {
        onHome = true;
    }
}
