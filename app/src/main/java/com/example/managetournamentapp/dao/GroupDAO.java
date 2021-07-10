package com.example.managetournamentapp.dao;

import com.example.managetournamentapp.domain.Group;

import java.util.ArrayList;


/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public interface GroupDAO {

    /**
     * Saves a specific group.
     * @param entity
     */
    void save(Group entity);

    /**
     * Finds a game.
     * @return Returns the specified game.
     */
    Group find();

    /**
     * Finds all groups.
     * @return All groups.
     */
    ArrayList<Group> findAll();

    /**
     * Deletes a specific group.
     * @param entity Group to be deleted.
     */
    void delete(Group entity);

    /**
     * Deletes all groups.
     */
    void deleteAll();

}
