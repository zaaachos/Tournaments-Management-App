package com.example.managetournamentapp.dao;

import com.example.managetournamentapp.domain.Invitation;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public interface InvitationDAO {

    /**
     * Saves a specific invitation.
     * @param entity Invitation to be saved.
     */
    void save(Invitation entity);

    /**
     * Finds an invitations specified by
     * @param teamName the team Name
     * @param dateSent and the date it was sent
     */
    void find(String teamName, LocalDate dateSent);

    /**
     * Finds all invitations.
     * @return All invitations.
     */
    ArrayList<Invitation> findAll();

    /**
     * Deletes a specific invitation.
     * @param entity Invitation to be deleted
     */
    void delete(Invitation entity);

    /**
     * Deletes all invitations.
     */
    void deleteAll();


}
