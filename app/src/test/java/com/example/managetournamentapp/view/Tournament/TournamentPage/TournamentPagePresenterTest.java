package com.example.managetournamentapp.view.Tournament.TournamentPage;

import com.example.managetournamentapp.memoryDao.MemoryInitializer;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;
import com.example.managetournamentapp.memoryDao.OrganizerDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class TournamentPagePresenterTest {

    private TournamentPagePresenter presenter;
    private TournamentPageView view;

    /**
     * setUp the view and presenter for testing Presenter Methods
     * @throws Exception if setup fail
     */
    @Before
    public void setUp() throws Exception {

        new MemoryInitializer().prepareData();
        view = new TournamentPageViewStub();
        presenter = new TournamentPagePresenter();

        presenter.setView(view);
    }

    /**
     * Test user's actions
     */
    @Test
    public void testActions() {
        new MemoryLoggedInUser().setUser(new OrganizerDAOMemory().findByTitle("ESKA"));
        presenter.onHomePage();
        Assert.assertTrue(((TournamentPageViewStub) view).onHome);

        presenter.onTournamentGames();
        Assert.assertTrue(((TournamentPageViewStub) view).onGames);

        presenter.onTournamentInfo();
        Assert.assertTrue(((TournamentPageViewStub) view).onInfo);

        presenter.onTournamentTeams();
        Assert.assertTrue(((TournamentPageViewStub) view).onTeams);

    }
}