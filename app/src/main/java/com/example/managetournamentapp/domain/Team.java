package com.example.managetournamentapp.domain;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class Team {

    private String name, colors;
    private Sport sportType;
    private AgeDivision ageDivision;
    private Player captain;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Participation> participations = new ArrayList<>();

    /**
     * default contructor of a team
     */
    public Team() {
    }

    /**
     *the full constructor of the team
     * @param name of the team
     * @param sportType of the team
     * @param ageDivision of the team
     * @param captain of the team
     * @param colors of the team
     */
    public Team(String name, Sport sportType, AgeDivision ageDivision, Player captain, String colors) {
        this.name = name;
        this.sportType = sportType;
        this.ageDivision = ageDivision;
        this.colors = colors;
        this.captain = captain;
        addPlayer(captain);
    }

    /**
     * get the players that have joined this team
     * @return a copy of arraylist of players in this team
     */
    public ArrayList<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    /**
     * get the players that have joined this team
     * @return the arraylist of players in this team
     */
    protected ArrayList<Player> friendGetPlayers(){
        return players;
    }

    /**
     * add a new player in the team
     * @param player the player that will be added
     */
    public void addPlayer(Player player) {
        if (player == null) {
            return;
        }
        player.addJoinedTeam(this);
    }

    /**
     * remove a player from the team
     * @param player that will be removed
     */
    public void removePlayer(Player player) {
        if (player == null) {
            return;
        }
        player.removeJoinedTeam(this);
    }


    /**
     * find if the team has participations that are happening right now
     * @return true if the team is currently participating in a tournament
     */
    public boolean hasAnyActivePart() {
        //check if there is any running participation
        for (Participation participation : participations) {
            if (participation.getStartDate().isBefore(LocalDate.now()) && participation.getFinishDate().isAfter(LocalDate.now())) {
                return true;
            }
        }
        return false;
    }

    /**
     * set a member of the team as the new captain
     * @param player the new captain
     */
    public void setCaptain(Player player) {
        if (player == null) {
            return;
        }
        captain.removeCaptainInTeams(this);
        player.addCaptainInTeams(this);
        captain = player;
    }

    /**
     * get the current captain of the team
     * @return the captain
     */
    public Player getCaptain() {
        return captain;
    }

    /**
     * send an invitation to a player
     * @param player the player who is invited
     */
    public void invitePlayer(Player player) {
        if (player == null || players.contains(player)) {
            return;
        }
        if (!player.canJoin(this)) {
            return;
        }
        player.addInvite(new Invitation(this));
    }


    /**
     *  add a new participation - the team
     *  participates in a new tournament
     * @param participation the new participation
     */
    public void addParticipation(Participation participation) {
        if (participation == null) {
            return;
        }
        if (participations.contains(participation)) {
            return;
        }
        if (canParticipate(participation)) {
            participations.add(participation);
            participation.getTournament().friendGetParticipations().add(participation);
            participation.getTournament().checkIfStarts();
        }
    }

    /**
     *  remove a participation - the team
     *  exits a tournament
     * @param participation the participation
     */
    public void removeParticipation(Participation participation) {
        if (participation == null) {
            return;
        }
        if (!participations.contains(participation)) {
            return;
        }
        participations.remove(participation);
        participation.getTournament().friendGetParticipations().remove(participation);
    }


    /**
     * find if this participation is valid
     * @param participation the participation which is checked
     * @return true if this participation can actually happen
     */
    public boolean canParticipate(Participation participation) {
        Tournament tournToJoin = participation.getTournament();

        if (!getAgeDivision().equals(tournToJoin.getAgeDivision())) {
            return false;
        }
        if (hasAnyActivePart()) {
            return false;
        }

        if (!tournToJoin.getSportType().equals(sportType)) {
            return false;
        }
        if (tournToJoin.isFull()) {
            return false;
        }
        if (players.size() < (sportType.getMinimumPlayers() / 2)) {
            return false;
        }
        //if there is a player that participates in the specific tournament
        //with another team, then this team can't join the tournament
        for (Player player : players) {
            for (Participation playerPart : player.getUndoneParticipations()) {
                if (playerPart.inSameTournament(participation)) {
                    return false;
                }
            }
        }
        for (Participation part : participations) {
            if (participation.isSimultaneous(part))
                return false;
        }
        return true;
    }

    /**
     * get all the participations of this team
     * @return a copy of the participations arraylist
     */
    public ArrayList<Participation> getParticipations() {
        return new ArrayList<>(participations);
    }

    /**
     * find the participations that are either happening now or are scheduled
     * @return the participations of this team, that are not finished
     */
    public ArrayList<Participation> getUndoneParticipations() {
        ArrayList<Participation> runningParticipations = new ArrayList<>();
        for (Participation p : participations) {
            if (!p.isPast())
                runningParticipations.add(p);
        }
        return runningParticipations;
    }

    /**
     * get the name of this team
     * @return the name of the team
     */
    public String getName() {
        return name;
    }

    /**
     * set a new name for this team
     * @param name is the new name
     */
    public void setName(String name) {
        if (name == null || !getUndoneParticipations().isEmpty()) {
            return;
        }
        this.name = name;
    }

    /**
     * get the colors of this team
     * @return the colors of the team
     */
    public String getColors() {
        return colors;
    }

    /**
     * set new colors for this team
     * @param colors is the new name
     */
    public void setColors(String colors) {
        if (colors == null || !getUndoneParticipations().isEmpty()) {
            return;
        }
        this.colors = colors;
    }

    /**
     * get the sport of this team
     * @return the Sport of the team
     */
    public Sport getSportType() {
        return sportType;
    }

    /**
     * get the age division that this team competes in
     * @return the age division of the team
     */
    public AgeDivision getAgeDivision() {
        return ageDivision;
    }

    /**
     * the string representation of the team
     * @return the basic info of the team in a string
     */
    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", sportType=" + sportType +
                ", ageDivision=" + ageDivision +
                '}';
    }


    /**
     *check if two teams are equal
     * @param other is the other team
     * @return if this team is equal to the other team
     */
    public boolean equals(Object other) {
        if (this == other){
            return true;
        }
        if (other instanceof Team) {
            Team otherTeam = (Team) other;
            if (name.equals(otherTeam.name) &&
                    sportType.equals(otherTeam.sportType)
                    && colors.equals(otherTeam.colors)
                    && players.equals(otherTeam.players)
                    && ageDivision.equals(otherTeam.getAgeDivision())
                    && captain.equals(otherTeam.captain))
//                    && participations.equals(otherTeam.participations))
                return true;
        }
        return false;
    }

}
