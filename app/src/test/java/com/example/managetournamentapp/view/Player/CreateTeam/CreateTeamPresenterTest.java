package com.example.managetournamentapp.view.Player.CreateTeam;

import com.example.managetournamentapp.domain.Player;
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

public class CreateTeamPresenterTest {


    private CreateTeamPresenter presenter;
    private CreateTeamView view;
    private Player captain;

    /**
     * setUp the view and presenter for testing Presenter Methods
     * @throws Exception if setup fail
     */
    @Before
    public void setUp() throws Exception {

        new MemoryInitializer().prepareData();
        view = new CreateTeamViewStub();
        presenter = new CreateTeamPresenter();
        presenter.setPlayerDAO(new PlayerDAOMemory());
        presenter.setTeamDAO(new TeamDAOMemory());
        captain = new PlayerDAOMemory().find("tommy0");
        new MemoryLoggedInUser().setUser(captain);
        presenter.setPlayer(new PlayerDAOMemory().find("tommy0"));


        presenter.setView(view);
    }

    /**
     * Test the creation of a nea team
     */
    @Test
    public void createNew() {

        view.setTeamName("RealMadrid");
        view.setTeamColors("white");
        view.setSportType(2);
        presenter.onSaveTeam();
        Assert.assertTrue(((CreateTeamViewStub) view).onSave);

        Assert.assertEquals(presenter.getSportTypes().get(2), "Football5v5");

    }

    /**
     * Test the edit of an existing Team
     */
    @Test
    public void changeExisting() {
        String teamName = "Celtic10";
        presenter.showPreviousInfo(teamName);
        Assert.assertTrue(((CreateTeamViewStub) view).onLock);

        view.setTeamName("Bulls");
        presenter.onSaveTeam();

        String newName = new TeamDAOMemory().find("Bulls").getName();
        Assert.assertEquals("Bulls", newName);
    }

    /**
     * Test user's actions on clicks
     */
    @Test
    public void testAction() {
        presenter.onHomePage();
        Assert.assertTrue(((CreateTeamViewStub) view).onHome);

    }


}