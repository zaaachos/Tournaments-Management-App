package com.example.managetournamentapp.view.Tournament.TournamentInfo;


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
public class TournamentInfoPresenterTest {

    private TournamentInfoPresenter presenter;
    private TournamentInfoView view;

    /**
     * setUp the view and presenter for testing Presenter Methods
     * @throws Exception if setup fail
     */
    @Before
    public void setUp() throws Exception {

        new MemoryInitializer().prepareData();
        view = new TournamentInfoViewStub();
        presenter = new TournamentInfoPresenter();
        presenter.setLoggedInUser(new MemoryLoggedInUser());
        presenter.setTournamentDAO(new TournamentDAOMemory());

        presenter.setView(view);
    }

    /**
     * Test if presenter gives the access properly
     */
    @Test
    public void findAccess() {

        // SET ORGANIZER AS LOGGED IN USER
        new MemoryLoggedInUser().setUser(new OrganizerDAOMemory().findByTitle("ESKA"));
        presenter.findAccess();
        Assert.assertTrue(((TournamentInfoViewStub) view).onChange);
    }

    /**
     * Test the deletion of a tournament
     */
    @Test
    public void testDeleteTournament() {
        presenter.findTournamentInfo("TOURNOUA1");
        presenter.onDeleteTournament();
        Assert.assertFalse(((TournamentInfoViewStub) view).onConfirm);

        // SET ORGANIZER AS LOGGED IN USER
        new MemoryLoggedInUser().setUser(new OrganizerDAOMemory().findByTitle("ESKA"));
        presenter.onDeleteTournament();
        Assert.assertFalse(((TournamentInfoViewStub) view).onConfirm);


        // DELETE A TOURNAMENT WITH NO PARTICIPATION
        presenter.findTournamentInfo("NBA");
        presenter.onDeleteTournament();
        Assert.assertTrue(((TournamentInfoViewStub) view).onConfirm);

    }

    /**
     * Test the edit of a tournament
     */
    @Test
    public void testEditTournament() {
        presenter.findTournamentInfo("TOURNOUA1");
        presenter.onEditTournament();
        Assert.assertFalse(((TournamentInfoViewStub) view).onEdit);

        // SET ORGANIZER AS LOGGED IN USER
        new MemoryLoggedInUser().setUser(new OrganizerDAOMemory().findByTitle("ESKA"));
        presenter.onEditTournament();
        Assert.assertFalse(((TournamentInfoViewStub) view).onEdit);

        // EDIT A TOURNAMENT WITH NO PARTICIPATION
        presenter.findTournamentInfo("NBA");
        presenter.onEditTournament();
        Assert.assertTrue(((TournamentInfoViewStub) view).onEdit);

    }

    /**
     * Test different buttons
     */
    @Test
    public void testButtons() {
        new MemoryLoggedInUser().setUser(new OrganizerDAOMemory().findByTitle("ESKA"));
        presenter.onYesDeleteTournament();
        Assert.assertTrue(((TournamentInfoViewStub) view).onYesDelete);
        presenter.onNoDeleteTournament();
        Assert.assertTrue(((TournamentInfoViewStub) view).onNoDelete);
    }

}