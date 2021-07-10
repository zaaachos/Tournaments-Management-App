package com.example.managetournamentapp.memoryDao;

import com.example.managetournamentapp.dao.GroupDAO;
import com.example.managetournamentapp.domain.Group;
import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class GroupDAOMemory implements GroupDAO {
    protected static ArrayList<Group> entities = new ArrayList<>();

    /**
     * Saves a specific group.
     * @param entity
     */
    @Override
    public void save(Group entity) {
        entities.add(entity);
    }


    /**
     * Deletes a specific group.
     * @param entity Group to be deleted.
     */
    @Override
    public void delete(Group entity) {
        entities.remove(entity);
    }

    /**
     * Deletes all groups.
     */
    @Override
    public void deleteAll() {
        entities = new ArrayList<>();
    }

    /**
     * Finds a group
     * @return Returns the specified group.
     */
    @Override
    public Group find() {
        return null;
    }

    /**
     * Finds all groups.
     * @return All groups.
     */
    @Override
    public ArrayList<Group> findAll() {
        ArrayList<Group> result = new ArrayList<>();
        result.addAll(entities);
        return result;
    }
}
