package com.example.managetournamentapp.view.User.RegisterPlayer;

import com.example.managetournamentapp.domain.Sport;
import com.example.managetournamentapp.memoryDao.MemoryInitializer;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;
import com.example.managetournamentapp.memoryDao.OrganizerDAOMemory;
import com.example.managetournamentapp.memoryDao.PlayerDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class RegisterPlayerPresenterTest {

    private RegisterPlayerPresenter presenter;
    private RegisterPlayerView view;

    /**
     * setUp the view and presenter for testing Presenter Methods
     * @throws Exception if setup fail
     */
    @Before
    public void setup(){
        new MemoryInitializer().prepareData();
        view = new RegisterPlayerViewStub();
        presenter = new RegisterPlayerPresenter();
        presenter.setLoggedInUser( new MemoryLoggedInUser());
        presenter.setPlayerDAO( new PlayerDAOMemory());
        presenter.setOrganizerDAO(new OrganizerDAOMemory());
        presenter.setView(view);

    }


    /**
     * Test the creation a new player
     */
    @Test
    public void createNew() {

        view.setUsername("nick12");
        view.setPassword("12345");
        view.setName("nickthequick");
        view.setSurname("kalathis");
        view.setPhoneNumber("0000000000");
        view.setEmail("aaaa@aaaa");
        view.setBirthdate("11/11/2000");
        view.setLocation("patisia");
        view.setSportsInterest( new ArrayList<>(Arrays.asList(new Sport("Basketball3v3"))) );

        int players1 = new PlayerDAOMemory().findAll().size();
        presenter.handlePlayerData();
        int players2 = new PlayerDAOMemory().findAll().size();
        Assert.assertEquals(players1+1,players2);

        Assert.assertEquals(presenter.getLoggedInUser().getUser(), new PlayerDAOMemory().find("nick12"));
    }

    /**
     * Test the edit of an existing player
     */
    @Test
    public void changeExisting(){

        String userName = "tommy0";
        presenter.showPreviousInfo(userName);
        view.setName("newNameTom");
        presenter.handlePlayerData();

        String newName = new PlayerDAOMemory().find("tommy0").getName();
        Assert.assertEquals(newName,"newNameTom");
    }

}