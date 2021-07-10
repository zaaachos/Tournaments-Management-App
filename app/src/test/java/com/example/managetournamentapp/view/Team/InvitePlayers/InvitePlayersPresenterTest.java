package com.example.managetournamentapp.view.Team.InvitePlayers;

import com.example.managetournamentapp.domain.Player;
import com.example.managetournamentapp.domain.Team;
import com.example.managetournamentapp.memoryDao.MemoryInitializer;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;
import com.example.managetournamentapp.memoryDao.PlayerDAOMemory;
import com.example.managetournamentapp.memoryDao.TeamDAOMemory;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class InvitePlayersPresenterTest {
    InvitePlayersPresenter presenter;
    InvitePlayersView view;
    private Team team;

    /**
     * setUp the view and presenter for testing Presenter Methods
     * @throws Exception if setup fail
     */
    @Before
    public void setUp() {
        new MemoryInitializer().prepareData();
        view = new InvitePlayersViewStub();
        presenter = new InvitePlayersPresenter();
        presenter.setPlayerDAO(new PlayerDAOMemory());
        presenter.setTeamDAO(new TeamDAOMemory());
        presenter.setView(view);

        team = new TeamDAOMemory().find("Celtic0");
        presenter.findPlayers(team.getName());
    }

    /**
     * Test if captain can invite new players from Players List
     */
    @Test
    public void invitePlayers(){
        Player player = presenter.getResults().get(0);

        int number1 = player.getInvitesReceived().size();
        int number2 = team.getPlayers().size();

        presenter.inviteNewPlayer(team.getName(), player);
        Assert.assertEquals(number1+1, player.getInvitesReceived().size());

        presenter.closePopAction();
        presenter.displayPopAction(0,"",0,0, true);
        Assert.assertEquals(number1+1, player.getInvitesReceived().size());

        presenter.onPlayerAccountSelected(player);
        presenter.restartActivity();
        Assert.assertEquals(number1+1, player.getInvitesReceived().size());
        Assert.assertEquals(number2, team.getPlayers().size());
    }

    /**
     * Test user's actions
     */
    @Test
    public void testAction(){
        new MemoryLoggedInUser().setUser(new PlayerDAOMemory().find("tommy0"));
        presenter.onHomePage();
        Assert.assertTrue(((InvitePlayersViewStub) view).onHome);
    }



}