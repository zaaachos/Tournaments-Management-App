package com.example.managetournamentapp.view.Tournament.TournamentRounds;

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

public class TournamentRoundsPresenterTest {

    private TournamentRoundsPresenter presenter;
    private TournamentRoundsView view;

    /**
     * setUp the view and presenter for testing Presenter Methods
     * @throws Exception if setup fail
     */
    @Before
    public void setUp() throws Exception {

        new MemoryInitializer().prepareData();
        view = new TournamentRoundsViewStub();
        presenter = new TournamentRoundsPresenter();
        presenter.setTournamentDAO(new TournamentDAOMemory());

        presenter.setView(view);
    }

    /**
     * Test presenter onGroup
     */
    @Test
    public void testGroup() {
        presenter.findTournamentInfo("TOURNOUA1");
        presenter.onGroups();
        Assert.assertTrue(((TournamentRoundsViewStub) view).onGroups);

    }

    /**
     * Test presenter on8
     */
    @Test
    public void test8() {
        presenter.findTournamentInfo("TOURNOUA1");
        presenter.on8();
        Assert.assertTrue(((TournamentRoundsViewStub) view).onRound);

    }
    /**
     * Test presenter on16
     */
    @Test
    public void test16() {
        presenter.findTournamentInfo("TOURNOUA1");
        presenter.on16();
        Assert.assertTrue(((TournamentRoundsViewStub) view).onRound);

    }
    /**
     * Test presenter onSemiFinals
     */
    @Test
    public void testSemiFinals() {
        presenter.findTournamentInfo("TOURNOUA1");
        presenter.onSemifinals();
        Assert.assertTrue(((TournamentRoundsViewStub) view).onRound);
    }
    /**
     * Test presenter onFinal
     */
    @Test
    public void testFinal() {
        presenter.findTournamentInfo("TOURNOUA1");
        presenter.onFinal();
        Assert.assertTrue(((TournamentRoundsViewStub) view).onRound);
    }

    /**
     * Test if presenter gives the access properly
     */
    @Test
    public void findAccess() {
        presenter.findTournamentInfo("TOURNOUA1");
        presenter.findAccess();
        Assert.assertTrue(((TournamentRoundsViewStub) view).onChange);

    }

    /**
     * Test user's actions.
     */
    @Test
    public void testAction(){
        new MemoryLoggedInUser().setUser(new OrganizerDAOMemory().findByTitle("ESKA"));
        presenter.onHomePage();
        Assert.assertTrue(((TournamentRoundsViewStub) view).onHome);
    }


}