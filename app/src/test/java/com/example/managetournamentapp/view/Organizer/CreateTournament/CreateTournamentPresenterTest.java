package com.example.managetournamentapp.view.Organizer.CreateTournament;

import com.example.managetournamentapp.memoryDao.MemoryInitializer;
import com.example.managetournamentapp.memoryDao.OrganizerDAOMemory;
import com.example.managetournamentapp.memoryDao.TournamentDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class CreateTournamentPresenterTest {

    private CreateTournamentPresenter presenter;
    private CreateTournamentView view;

    /**
     * setUp the view and presenter for testing Presenter Methods
     * @throws Exception if setup fail
     */
    @Before
    public void setUp() throws Exception {

        new MemoryInitializer().prepareData();
        view = new CreateTournamentViewStub();
        presenter = new CreateTournamentPresenter();
        presenter.setTournamentDAO(new TournamentDAOMemory());
        presenter.setOrganizer(new OrganizerDAOMemory().findByTitle("ESKA"));

        presenter.setView(view);
    }

    /**
     * Test the creation of a new Tournament
     */
    @Test
    public void createNew() {

        view.setTournamentTitle("ESKANA2");
        view.setAgeDivision(0);
        view.setSportType(0);
        view.setDescription("New tournament!");
        view.setLocation("Athens");
        view.setTeamsNumber(0);
        view.setStartDate("20/08/2021");
        view.setFinishDate("20/08/2021");

        presenter.onSaveTournament();

        Assert.assertEquals(presenter.getTeamNumbers().get(0), "8");
        Assert.assertEquals(presenter.getAgeDivisions().get(1), "K15");
        Assert.assertEquals(presenter.getSportTypes().get(0), "Basketball3v3");

    }

    /**
     * Test the edit of a an existing Tournament
     */
    @Test
    public void changeExisting() {
        String title = "TOURNOUA1";
        presenter.showPreviousInfo(title);
        view.setTournamentTitle("TOURNOUA2");
        presenter.onSaveTournament();
        Assert.assertTrue(((CreateTournamentViewStub) view).onSave);

        //title does not change because there are active participations.

        String newName = new TournamentDAOMemory().find("TOURNOUA1").getTitle();
        Assert.assertEquals("TOURNOUA1", newName);
    }

    /**
     * Test user's actions ond different clicks
     */
    @Test
    public void testActions() {
        presenter.onHomePage();
        Assert.assertTrue(((CreateTournamentViewStub) view).onHome);

        presenter.showPreviousInfo("TOURNOUA1");
        Assert.assertTrue(((CreateTournamentViewStub) view).onLock);
    }


}