package com.example.managetournamentapp.view.Tournament.RoundGames;

import com.example.managetournamentapp.memoryDao.MemoryInitializer;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;
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

public class RoundGamesPresenterTest {

    private RoundGamesPresenter presenter;
    private RoundGamesView view;

    /**
     * setUp the view and presenter for testing Presenter Methods
     * @throws Exception if setup fail
     */
    @Before
    public void setUp() throws Exception {
        new MemoryInitializer().prepareData();
        view = new RoundGamesViewStub();
        presenter = new RoundGamesPresenter();
        presenter.setTournamentDAO(new TournamentDAOMemory());
        presenter.setLoggedInUser(new MemoryLoggedInUser());
        presenter.setView(view);

    }

    /**
     * Test if presenter finds games properly from DAO ( for a Tournament )
     */
    @Test
    public void findGames() {
        presenter.findGames("TOURNOUA1", 8, -1);
        Assert.assertEquals(12, presenter.getResults().size());

    }

    /**
     * Test user actions on different cases
     */
    @Test
    public void onPressedTest() {
        presenter.findGames("TOURNOUA1", 4, 0);
        presenter.onPressed(presenter.getResults().get(0));
        Assert.assertFalse(((RoundGamesViewStub) view).onPop);

        new MemoryLoggedInUser().setUser(new OrganizerDAOMemory().findByTitle("ESKA"));
        presenter.findAccess();
        presenter.findGames("TOURNOUA1", 8, 0);
        presenter.onPressed(presenter.getResults().get(0));
        Assert.assertTrue(((RoundGamesViewStub) view).onPop);


    }

    /**
     * Test saving of games in different cases
     */
    @Test
    public void onSaveTest() {
        presenter.findGames("TOURNOUA1", 8, 0);
        presenter.onSave(presenter.getResults().get(0), "2", "0");
        Assert.assertTrue(presenter.getResults().get(0).isFinished());
        Assert.assertEquals(2, presenter.getResults().get(0).getScoreA());
        Assert.assertEquals(0, presenter.getResults().get(0).getScoreB());

        //GIVE ACCESS TO ORGANIZER
        new MemoryLoggedInUser().setUser(new OrganizerDAOMemory().findByTitle("ESKA"));
        presenter.findAccess();
        presenter.onSave(presenter.getResults().get(0), "2", "0");
        Assert.assertTrue(presenter.getResults().get(0).isFinished());
    }

    /**
     * Test user's actions
     */
    @Test
    public void changePage() {
        new MemoryLoggedInUser().setUser(new OrganizerDAOMemory().findByTitle("ESKA"));
        presenter.onHomePage();
        Assert.assertTrue(((RoundGamesViewStub) view).onHome);

    }
}