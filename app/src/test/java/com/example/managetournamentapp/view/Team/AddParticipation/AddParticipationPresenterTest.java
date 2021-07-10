package com.example.managetournamentapp.view.Team.AddParticipation;


import com.example.managetournamentapp.domain.Team;
import com.example.managetournamentapp.domain.Tournament;
import com.example.managetournamentapp.memoryDao.MemoryInitializer;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;
import com.example.managetournamentapp.memoryDao.PlayerDAOMemory;
import com.example.managetournamentapp.memoryDao.TeamDAOMemory;
import com.example.managetournamentapp.memoryDao.TournamentDAOMemory;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class AddParticipationPresenterTest {
    private AddParticipationPresenter presenter;
    private AddParticipationView view;
    private Team team;

    /**
     * setUp the view and presenter for testing Presenter Methods
     * @throws Exception if setup fail
     */
    @Before
    public void setUp() {
        new MemoryInitializer().prepareData();
        view = new AddParticipationViewStub();
        presenter = new AddParticipationPresenter();
        presenter.setTournamentDAO(new TournamentDAOMemory());
        presenter.setTeamDAO(new TeamDAOMemory());
        presenter.setView(view);

        team = new TeamDAOMemory().find("Celtic8");
        presenter.findTournaments(team.getName());
    }

    /**
     * Test the addition of a new participation in a tournament
     */
    @Test
    public void addParticipation() {
        Tournament tournament = presenter.getResults().get(0);
        int number1 = team.getParticipations().size();

        presenter.onTournamentPage(tournament.getTitle());
        Assert.assertEquals(number1, team.getParticipations().size());

        presenter.onAddParticipation(tournament.getTitle());
        Assert.assertEquals(number1 + 1, team.getParticipations().size());

        presenter.onStartPartTournaments();
        Assert.assertEquals(number1 + 1, team.getParticipations().size());

    }

    /**
     * Test user's action
     */
    @Test
    public void testAction() {
        new MemoryLoggedInUser().setUser(new PlayerDAOMemory().find("tommy0"));
        presenter.onHomePage();
        Assert.assertTrue(((AddParticipationViewStub) view).onHome);

    }


}