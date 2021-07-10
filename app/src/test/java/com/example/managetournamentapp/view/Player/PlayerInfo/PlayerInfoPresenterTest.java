package com.example.managetournamentapp.view.Player.PlayerInfo;

import com.example.managetournamentapp.domain.Player;
import com.example.managetournamentapp.domain.User;
import com.example.managetournamentapp.memoryDao.MemoryInitializer;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;
import com.example.managetournamentapp.memoryDao.PlayerDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class PlayerInfoPresenterTest {

    private PlayerInfoPresenter presenter;
    private PlayerInfoView view;

    /**
     * setUp the view and presenter for testing Presenter Methods
     * @throws Exception if setup fail
     */
    @Before
    public void setUp() throws Exception {
        new MemoryInitializer().prepareData();
        view = new PlayerInfoViewStub();
        presenter = new PlayerInfoPresenter();
        presenter.setPlayerDAO(new PlayerDAOMemory());
        presenter.setLoggedInUser(new MemoryLoggedInUser());

        presenter.setView(view);
    }

    /**
     * Test if presenter gives the access correctly
     */
    @Test
    public void findAccess() {

        presenter.findAccess();
        presenter.findPlayerInfo("tommy0");
        Player p = new PlayerDAOMemory().find("tommy0");
        User u = p;
        Assert.assertTrue(p.equals(u));

        // if tommy0 selects to see his info page!
        new MemoryLoggedInUser().setUser(new PlayerDAOMemory().find("tommy0"));
        presenter.findPlayerInfo("tommy0");
        presenter.findAccess();
        Assert.assertTrue(((PlayerInfoViewStub) view).onChange);

    }

    /**
     * Test the deletion of a player on different cases
     */
    @Test
    public void deletePlayerInfo() {

        // CAN'T DELETE
        new MemoryLoggedInUser().setUser(new PlayerDAOMemory().find("tommy0"));
        presenter.findPlayerInfo("tommy0");
        presenter.onDeletePlayer();
        Assert.assertTrue(new MemoryLoggedInUser().getUser() != null);

        // CAN DELETE
        new MemoryLoggedInUser().setUser(new PlayerDAOMemory().find("tommy28"));
        presenter.findPlayerInfo("tommy28");
        presenter.onDeletePlayer();


        presenter.onNoDeletePlayer();
        Assert.assertTrue(new MemoryLoggedInUser().getUser() != null);

        presenter.onYesDeletePlayer();
        Assert.assertTrue(new MemoryLoggedInUser().getUser() == null);
        Assert.assertTrue(((PlayerInfoViewStub) view).onDelete);
    }

    /**
     * Test user's action
     */
    @Test
    public void editPlayer() {
        presenter.onEditPlayer();
        Assert.assertTrue(((PlayerInfoViewStub) view).onEdit);
    }

}