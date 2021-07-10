package com.example.managetournamentapp.view.Player.CreateTeam;

import com.example.managetournamentapp.dao.PlayerDAO;
import com.example.managetournamentapp.dao.TeamDAO;
import com.example.managetournamentapp.domain.Player;
import com.example.managetournamentapp.domain.Sport;
import com.example.managetournamentapp.domain.Team;
import com.example.managetournamentapp.domain.TournamentType;
import com.example.managetournamentapp.domain.User;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class CreateTeamPresenter {
    private CreateTeamView view;
    private ArrayList<String> sportTypes;
    private Team connectedTeam;
    private Player player = null;
    private PlayerDAO playerDAO;
    private TeamDAO teamDAO;

    /**
     * default constructor
     */
    public CreateTeamPresenter() {
        sportTypes = findSportTypes();
    }

    /**
     * show the previous info of the team, if we are on edit mode
     *
     * @param teamName the name of the team
     */
    public void showPreviousInfo(String teamName) {
        if (teamName == null)
            return;
        connectedTeam = teamDAO.find(teamName);
        if (connectedTeam == null)
            return;

        view.setTeamName(connectedTeam.getName());
        view.setTeamColors(connectedTeam.getColors());
        view.setSportType(getTypeIndex(connectedTeam.getSportType().getName()));
        view.lockSportType();
    }

    /**
     * either a new team is saved, or an existing team is updated
     */
    public void onSaveTeam() {
        String name = view.getTeamName();
        String colors = view.getTeamColors();
        String sportType = sportTypes.get(view.getSportType());

        if (name.length() < 5 || name.length() > 20 || !validateName(name)) {
            view.showPopUp(view, "Name must be at least 5 chars and no longer than 20 chars!");
        } else if (colors.length() < 3 || colors.length() > 20 || !validateName(colors)) {
            view.showPopUp(view, "Color must be at least 3 chars and no longer than 20 chars!");
        } else {
            // IF TEAM IS NEW!
            if (connectedTeam == null) {
                if (checkTeamName(name, connectedTeam)) {
                    view.showPopUp(view, "Team name is already in use! Try a new one.");
                    return;
                }
                Player player = ((Player) (new MemoryLoggedInUser()).getUser());
                Team team = new Team(name, new Sport(sportType), player.getAgeDivision(), player, colors);
                teamDAO.save(team);
            } else {
                if (checkTeamName(name, connectedTeam)) {
                    view.showPopUp(view, "Team name is already in use! Try a new one.");
                    return;
                }
                connectedTeam.setName(name);
                connectedTeam.setColors(colors);
            }
            view.startSaveTeam(player.getCredentials().getUsername());
        }
    }

    /**
     * get the sport types
     *
     * @return the ArrayList of sport types
     */
    public ArrayList<String> getSportTypes() {
        return sportTypes;
    }

    /**
     * finds the index of a sport type in the sportType ArrayList
     *
     * @param sportType the sport type
     * @return the index of the sport type
     */
    private int getTypeIndex(String sportType) {
        for (int i = 0; i < sportTypes.size(); i++) {
            if (sportTypes.get(i).equals(sportType))
                return i;
        }
        return 0;
    }

    /**
     * convert the tournament type enum to an arraylist
     *
     * @return the ArrayList of the sport types
     */
    private ArrayList<String> findSportTypes() {
        ArrayList<String> sportTypes = new ArrayList<>();
        for (int i = 0; i < TournamentType.values().length; i++) {
            sportTypes.add(TournamentType.values()[i].toString());
        }
        return sportTypes;
    }

    /**
     * set the teamDAO
     *
     * @param teamDAO the new TeamDAO
     */
    public void setTeamDAO(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    /**
     * set the player
     *
     * @param player the player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * set the playerDAO
     *
     * @param playerDAO the new PlayerDAO
     */
    public void setPlayerDAO(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    /**
     * set a new view
     *
     * @param view the new view
     */
    public void setView(CreateTeamView view) {
        this.view = view;
    }

    /**
     * clear the view
     */
    public void clearView() {
        this.view = null;
    }

    /**
     * @param name the name we want to check if it's valid.
     * @return true if the name is valid
     */
    public boolean validateName(String name) {
        String valid = "^[a-zA-Z0-9]+$";
        Pattern pattern = Pattern.compile(valid);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    /**
     * Checks if title is used by another team
     *
     * @param title title input
     * @param team  current team
     * @return true if title used by another team
     */
    public boolean checkTeamName(String title, Team team) {
        return teamDAO.TitleIsUsed(title, team);
    }

    /**
     * what happens when the homepage button is pressed
     */
    public void onHomePage() {
        User user = new MemoryLoggedInUser().getUser();
        view.backToHomePage(user.getCredentials().getUsername());

    }


}
