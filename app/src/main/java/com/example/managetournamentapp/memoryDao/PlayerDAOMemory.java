package com.example.managetournamentapp.memoryDao;

import com.example.managetournamentapp.dao.PlayerDAO;
import com.example.managetournamentapp.domain.Credentials;
import com.example.managetournamentapp.domain.Player;
import com.example.managetournamentapp.domain.User;

import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class PlayerDAOMemory implements PlayerDAO {
    protected static ArrayList<Player> entities = new ArrayList<>();

    /**
     * Verifies user, checks if credentials are ok.
     *
     * @param credentials Credentials of User.
     * @return true if credentials are verified.
     */
    @Override
    public boolean verify(Credentials credentials) {
        boolean valid = false;
        if (exist(credentials))
            valid = true;
        return valid;
    }

    /**
     * Saves a specific player.
     *
     * @param entity Player to be saved.
     */
    @Override
    public void save(Player entity) {
        entities.add(entity);
    }

    /**
     * Deletes a specific player.
     *
     * @param entity Player to be deleted.
     */
    @Override
    public void delete(Player entity) {
        entities.remove(entity);
    }

    /**
     * Deletes all players.
     */
    @Override
    public void deleteAll() {
        entities = new ArrayList<>();
    }


    /**
     * Finds all players.
     *
     * @return All players.
     */
    @Override
    public ArrayList<Player> findAll() {
        ArrayList<Player> result = new ArrayList<>();
        result.addAll(entities);
        return result;

    }

    /**
     * Finds a specific player.
     *
     * @param userName Player username.
     * @return Player with the specific username.
     */
    @Override
    public Player find(String userName) {
        for (Player p : entities) {
            if (p.getCredentials().getUsername().equals(userName))
                return p;
        }
        return null;
    }

    /**
     * Check if the user who tries to login exists in DAO.
     *
     * @param credentials User input credentials
     * @return true if User exists in DAO
     */
    @Override
    public boolean exist(Credentials credentials) {
        for (Player o : entities) {
            if (o.getCredentials().equals(credentials))
                return true;
        }
        return false;
    }

    /**
     * Checks if credentials is used by another user in registration
     * @param credentials User input credentials
     * @param user if user is current
     * @return true if credentials used by another user
     */
    @Override
    public boolean isUsedByAnother(Credentials credentials, User user) {
        User existingPlayer = find(credentials.getUsername());
        return !(existingPlayer == null || existingPlayer.equals(user));
    }


}
