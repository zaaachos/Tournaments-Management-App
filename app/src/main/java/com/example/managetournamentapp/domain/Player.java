package com.example.managetournamentapp.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class Player extends User {

    private String location;
    private AgeDivision ageDivision;
    private ArrayList<Sport> sportsInterested = new ArrayList<>();
    //teams that the specific player has joined
    private ArrayList<Team> teamsJoined = new ArrayList<>();
    //teams that the player is a captain of
    private ArrayList<Team> captainInTeams = new ArrayList<>();
    private ArrayList<Invitation> invitesReceived = new ArrayList<>();


    /**
     * the default constructor
     */
    public Player() {
        location = "";
        ageDivision = null;
    }

    /**
     *the full constructor of a new player
     * @param name of the player
     * @param surname of the player
     * @param location of the player
     * @param phoneNumber of the player
     * @param email of the player
     * @param birthDate of the player
     * @param credentials of the player
     */
    public Player(String name, String surname, String location, String phoneNumber, String email, LocalDate birthDate, Credentials credentials) {
        super(name, surname, phoneNumber, email, birthDate, credentials);
        this.location = location;
        LocalDate now = LocalDate.now();
        int diff = (int) this.getBirthDate().until(now, ChronoUnit.YEARS);
        initAgeDivision(diff);
    }

    /**
     * set the age division according to the player's age
     * @param age is the age of the player
     */
    public void initAgeDivision(int age) {
        if (age <= 12) {
            ageDivision = AgeDivision.valueOf("K12");
        } else if (age <= 15) {
            ageDivision = AgeDivision.valueOf("K15");
        } else if (age <= 18) {
            ageDivision = AgeDivision.valueOf("K18");
        } else {
            ageDivision = AgeDivision.valueOf("K100");
        }
    }


    /**
     * add a new team in the player's joined teams
     * @param team is the new team
     */
    public void addJoinedTeam(Team team) {
        if (team == null) {
            return;
        }
        if (team.hasAnyActivePart()) {
            return;
        }
        if (!canJoin(team)) {
            return;
        }
        team.friendGetPlayers().add(this);
        teamsJoined.add(team);
        if (team.getCaptain().equals(this)) {
            captainInTeams.add(team);
        }
    }

    /**
     * remove a team from the player's joined teams
     * @param team the team that will be removed
     */
    public void removeJoinedTeam(Team team) {
        if (team == null) {
            return;
        }
        if (team.hasAnyActivePart())
            return;
        if (!teamsJoined.contains(team)) {
            return;
        }
        team.friendGetPlayers().remove(this);
        teamsJoined.remove(team);
        if (team.getCaptain().equals(this)) {
            deleteTeam(team);
        }
    }

    /**
     * get the teams that the player is currently joined to
     * @return the ArrayList of the player's joined teams
     */
    public ArrayList<Team> getTeamsJoined() {
        return new ArrayList<>(teamsJoined);
    }

    /**
     *delete a team that the player has created (works only if the player is the captain of this team)
     * @param team is the team that will be deleted
     */
    public void deleteTeam(Team team) {
        if (team == null) {
            return;
        }
        if (!team.getCaptain().equals(this)) {
            return;
        }
        //tsekarei sthn ousia ola
        //ta running participations
        if (!team.hasAnyActivePart()) {
            this.teamsJoined.remove(team);
            this.captainInTeams.remove(team);

            //removing from each player the specific team
            //since it was just deleted by the captain
            for (Player player : team.friendGetPlayers()) {
                player.getTeamsJoined().remove(team);
                player.teamsJoined.remove(team);
            }

            ArrayList<Participation> participations = team.getUndoneParticipations();
            for (Participation part : participations) {

                Tournament tournament = part.getTournament();
                tournament.removeParticipation(part);
            }
        }
    }

    /**
     *add a team to the list of teams whose captain is this player
     * @param team the team that will be added
     */
    public void addCaptainInTeams(Team team) {
        if (team == null) {
            return;
        }
        if (team.hasAnyActivePart())
            return;
        if (!team.getPlayers().contains(this)) {
            return;
        }
        team.friendGetPlayers().add(this);
        ;
        captainInTeams.add(team);
    }

    /**
     * remove a team to the list of teams whose captain is this player
     * @param team the team that will be remover
     */
    public void removeCaptainInTeams(Team team) {
        if (team == null) {
            return;
        }
        if (team.hasAnyActivePart())
            return;
        if (!team.getPlayers().contains(this)) {
            return;
        }
        captainInTeams.remove(team);
    }

    /**
     * get the teams that this player is managing
     * @return the array list of teams whose captain is this player
     */
    public ArrayList<Team> getCaptainInTeams() {
        return new ArrayList<>(captainInTeams);
    }

    /**
     * find if this player can join a particular team
     * @param team the team that is examined
     * @return true if the player is able to join this particular team
     */
    public boolean canJoin(Team team) {
        // check if player belongs in the same
        // age group
        if (!getAgeDivision().equals(team.getAgeDivision())) {
            return false;
        }
        //if the player is already in the team, no need to join again
        if (team.getPlayers().contains(this)) {
            return false;
        }
        //check if this player is available for the specific sport
        if (!getSportsInterested().contains(team.getSportType())) {
            return false;
        }

        return true;
    }


    /**
     * reply to an invitation (accept or reject)
     * @param invite the invitation that the player will reply to
     * @param accept is true if the player accepts the invitation
     */
    public void replyToInvitation(Invitation invite, boolean accept) {
        if (invite == null)
            return;
        if (!invitesReceived.contains(invite))
            return;
        if (!invite.getPending())
            return;
        if (accept) {
            invite.setAccepted(true);
            invite.getTeam().addPlayer(this);
        } else {
            invite.setAccepted(false);
        }
    }

    /**
     * get the invites that have been sent to the player
     * @return all the invites that the player has received
     */
    public ArrayList<Invitation> getInvitesReceived() {
        return new ArrayList<>(invitesReceived);
    }


    /**
     * add a new sport that the player is interested in
     * teams of this sport will be able to sent invitations to the player
     * @param sport is the new sport
     */
    public void addSportInterested(Sport sport) {
        if (sport == null) {
            return;
        }
        if (sportsInterested.contains(sport)) {
            return;
        }
        sportsInterested.add(sport);
    }

    /**
     * empty list of sports that the player is interested in
     * , the player will not receive any invites after this
     */
    public void clearSportsInterest() {
        sportsInterested.clear();
    }

    /**
     * remove a sport that the player is interested in
     * teams of this sport will not be able to sent invitations to the player
     * @param sport is the sport
     */
    public void removeSportInterested(Sport sport) {
        if (sport == null) {
            return;
        }
        if (sportsInterested.contains(sport)) {
            sportsInterested.remove(sport);
        }
    }

    /**
     * find the sports whose teams can sent invitations to the player
     * @return the arraylist of the sports that the user is interested in
     */
    public ArrayList<Sport> getSportsInterested() {
        return new ArrayList<>(sportsInterested);
    }

    /**
     * add a new invitation to the invitations arraylist
     * @param invite is the invitation that will be added
     */
    public void addInvite(Invitation invite) {
        if (invite == null) {
            return;
        }
        invitesReceived.add(invite);
    }

    /**
     * remove an invitation to the invitations arraylist
     * @param invite is the invitation that will be removed
     */
    public void removeInvite(Invitation invite) {
        if (invite == null) {
            return;
        }
        if (!invitesReceived.contains(invite)) {
            return;
        }
        invitesReceived.remove(invite);
    }

    /**
     * get the player's location
     * @return the location of the player
     */
    public String getLocation() {
        return location;
    }

    /**
     * set the player;s location
     * @param location the new location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * get the age division that the player belongs to
     * @return the player's age division
     */
    public AgeDivision getAgeDivision() {
        return ageDivision;
    }


    /**
     * get the player's participations that are either running or scheduled for the future
     * @return the player's participations that have not been finished
     */
    public ArrayList<Participation> getUndoneParticipations() {
        ArrayList<Participation> runningParticipations = new ArrayList<>();
        for (Team team : teamsJoined) {
            runningParticipations.addAll(team.getUndoneParticipations());
        }
        return runningParticipations;
    }

    /**
     * the string representation of the player
     * @return the basic info of the player to string
     */
    @Override
    public String toString() {
        return super.toString() + "Location: " + getLocation() + " Age group: " + getAgeDivision();
    }

    /**
     *check if two players are equal
     * @param other the other players
     * @return if this player is equal to the other players
     */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!super.equals(other)) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (other instanceof Player) {
            Player otherPlayer = (Player) other;
            if (location.equals(otherPlayer.getLocation())
                    && ageDivision.equals(otherPlayer.getAgeDivision())
                    && invitesReceived.equals(otherPlayer.getInvitesReceived())
                    && sportsInterested.equals(otherPlayer.getSportsInterested()))
                return true;
        }
        return false;
    }


}
