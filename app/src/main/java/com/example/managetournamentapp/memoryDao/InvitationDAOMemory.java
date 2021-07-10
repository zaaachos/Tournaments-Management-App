package com.example.managetournamentapp.memoryDao;

import com.example.managetournamentapp.dao.InvitationDAO;
import com.example.managetournamentapp.domain.Invitation;

import java.time.LocalDate;
import java.util.ArrayList;


/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class InvitationDAOMemory implements InvitationDAO {
    protected static ArrayList<Invitation> entities = new ArrayList<>();

    /**
     * Invitation to be saved.
     * @param entity
     */
    @Override
    public void save(Invitation entity) {
        entities.add(entity);
    }

    /**
     * Deletes a specific invitation.
     * @param entity Invitation to be deleted
     */
    @Override
    public void delete(Invitation entity) {
        entities.remove(entity);
    }

    /**
     * Deletes all invitations.
     */
    @Override
    public void deleteAll() {
        entities = new ArrayList<>();
    }


    /**
     * Finds an invitations specified by
     * @param teamName the team Name
     * @param dateSent and the date it was sent
     */
    @Override
    public void find(String teamName, LocalDate dateSent) {

    }

    /**
     * Finds all invitations.
     * @return All invitations.
     */
    @Override
    public ArrayList<Invitation> findAll() {
        ArrayList<Invitation> result = new ArrayList<>();
        result.addAll(entities);
        return result;
    }
}
