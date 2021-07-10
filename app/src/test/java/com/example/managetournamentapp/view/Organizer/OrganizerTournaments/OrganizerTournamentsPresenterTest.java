package com.example.managetournamentapp.view.Organizer.OrganizerTournaments;

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

public class OrganizerTournamentsPresenterTest {
    private OrganizerTournamentsPresenter presenter;
    private OrganizerTournamentsView view;

    /**
     * setUp the view and presenter for testing Presenter Methods
     * @throws Exception if setup fail
     */
    @Before
    public void setUp() throws Exception {
        new MemoryInitializer().prepareData();
        view = new OrganizerTournamentsViewStub();
        presenter = new OrganizerTournamentsPresenter();
        presenter.setTournamentDAO(new TournamentDAOMemory());
        presenter.setOrganizerDAO(new OrganizerDAOMemory());

        presenter.setView(view);
    }

    /**
     * Test if presenter find's organizer's tournaments from DAO
     */
    @Test
    public void findOrganizerTournaments() {
        // NULL
        presenter.findCreatedTournaments("EPSA");

        // NOT NULL
        presenter.findCreatedTournaments("ESKA");
        Assert.assertEquals(presenter.getResults().get(0), new TournamentDAOMemory().find("TOURNOUA1"));
        Assert.assertEquals("TOURNOUA1", presenter.getResults().get(0).getTitle());
    }

    /**
     * Test user's action
     */
    @Test
    public void changePage() {
        presenter.onAddTournament();
        Assert.assertTrue(((OrganizerTournamentsViewStub) view).onCreate);

        presenter.onHomePage();
        Assert.assertTrue(((OrganizerTournamentsViewStub) view).onHome);

    }


}