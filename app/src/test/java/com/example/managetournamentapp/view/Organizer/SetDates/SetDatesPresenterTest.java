package com.example.managetournamentapp.view.Organizer.SetDates;

import com.example.managetournamentapp.domain.Tournament;
import com.example.managetournamentapp.memoryDao.MemoryInitializer;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;
import com.example.managetournamentapp.memoryDao.OrganizerDAOMemory;
import com.example.managetournamentapp.memoryDao.TournamentDAOMemory;

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

public class SetDatesPresenterTest {

    private SetDatesPresenter presenter;
    private SetDatesView view;
    private Tournament tournament;

    /**
     * setUp the view and presenter for testing Presenter Methods
     * @throws Exception if setup fail
     */
    @Before
    public void setUp() throws Exception {

        new MemoryInitializer().prepareData();
        view = new SetDatesViewStub();
        presenter = new SetDatesPresenter();
        presenter.setTournamentDAO(new TournamentDAOMemory());
        presenter.setOrganizer(new OrganizerDAOMemory().findByTitle("ESKA"));

        presenter.setView(view);
        tournament = new TournamentDAOMemory().find("TOURNOUA1");
    }


    /**
     * Test saving of a Tournament
     */
    @Test
    public void testOnSaveTournament() {
        ArrayList<String> basicInfo = new ArrayList<>(Arrays.asList(tournament.getTitle(), tournament.getStartDate().toString(), tournament.getFinishDate().toString(), tournament.getLocation(), tournament.getSportType().getName(), String.valueOf(tournament.getMAX_TEAMS_NUMBER()), tournament.getAgeDivision().toString(), tournament.getDescription()));
        presenter.findBasicInfo(basicInfo);
        presenter.onSaveTournament();
        Assert.assertTrue(((SetDatesViewStub) view).onSave);
    }

    /**
     * Test user's actions on different clicks
     */
    @Test
    public void testChangePage() {
        new MemoryLoggedInUser().setUser(new OrganizerDAOMemory().findByTitle("ESKA"));
        presenter.onHomePage();
        Assert.assertTrue(((SetDatesViewStub) view).onHome);
    }

}