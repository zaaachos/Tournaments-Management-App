package com.example.managetournamentapp.view.Tournament.GroupRankings;

import com.example.managetournamentapp.domain.Team;
import com.example.managetournamentapp.memoryDao.TournamentDAOMemory;

import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class GroupRankingsViewStub implements GroupRankingsView {
    ArrayList<Team> teams = new TournamentDAOMemory().find("TOURNOUA1").findTeams();
    boolean onHome = false;

    @Override
    public ArrayList<Team> getTeamsList() {
        return teams;
    }

    @Override
    public void backToHomePage(boolean noLogin, boolean isPlayer, String name) {
        onHome = true;
    }
}
