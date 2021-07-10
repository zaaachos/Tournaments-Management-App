package com.example.managetournamentapp.view.Organizer.OrganizerInfo;

import com.example.managetournamentapp.memoryDao.MemoryInitializer;
import com.example.managetournamentapp.memoryDao.OrganizerDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class OrganizerInfoPresenterTest {

    private OrganizerInfoPresenter presenter;
    private OrganizerInfoView view;

    /**
     * setUp the view and presenter for testing Presenter Methods
     * @throws Exception if setup fail
     */
    @Before
    public void setUp() throws Exception {

        new MemoryInitializer().prepareData();
        view = new OrganizerInfoViewStub();
        presenter = new OrganizerInfoPresenter();
        presenter.setOrganizerDAO(new OrganizerDAOMemory());
        presenter.setOrganizer(new OrganizerDAOMemory().findByTitle("ESKA"));


        presenter.setView(view);
    }

    /**
     * Test the deletion of an Organizer on different cases
     */
    @Test
    public void deleteOrganizer() {
        presenter.findOrganizerInfo();
        // Can't delete case
        presenter.onDeleteOrganizer();
        Assert.assertTrue(((OrganizerInfoViewStub) view).onShow);

        // can delete case
        presenter.setOrganizer(new OrganizerDAOMemory().findByTitle("ESKANA"));
        presenter.onDeleteOrganizer();
        Assert.assertTrue(((OrganizerInfoViewStub) view).onDelete);
    }

    /**
     * Test the edit of an Organizer
     */
    @Test
    public void editOrganizer() {
        presenter.findOrganizerInfo();
        presenter.onEditOrganizer();
        Assert.assertTrue(((OrganizerInfoViewStub) view).onEdit);

    }

}