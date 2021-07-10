package com.example.managetournamentapp.domain;

import java.time.LocalDate;


/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class Invitation {
    private Team team;
    private boolean pending,accepted=false;
    private LocalDate date;

    /**
     * constructor of an Invitation object
     * @param team the team that sends the invite
     */
    public Invitation(Team team){
        this.team = team;
        this.pending = true;
        date = LocalDate.now();
    }

    /**
     * find out if the Invitation has been answered (accepted or rejected)
     * @return true if the Invitation is pending
     */
    public boolean getPending() {
        return pending;
    }

    /**
     * find out if the player has accepted the Invitation of this team
     * @return true if the Invitation has been accepted by the player
     */
    public Boolean getAccepted() {
        return accepted;
    }

    /**
     * set set the Invitation as accepted if the player agrees to join the team
     * @param accepted is true if the Invitation is accepted
     */
    public void setAccepted(boolean accepted) {
        if (pending){
            this.accepted = accepted;
            pending = false;
        }
    }

    /**
     * get the date that the Invitation was sent
     * @return the LocalDate representation of the date
     */
     public LocalDate getDate() {
        return date;
    }

    /**
     * get the team that sent the Invitation to a player
     * @return the team that sent the Invitation
     */
    public Team getTeam() {
        return team;
    }

    /**
     * set the team that sents the Invitation to a player
     * @param team the team that sends the Invitation
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     *check the equality of two Invitation objects
     * @param other the other invite
     * @return true if this invite is equal to the other invite
     */
    public boolean equals(Object other) {
        if (this == other){
            return true;
        }
        if (other instanceof Invitation) {
            Invitation otherInvite = (Invitation) other;
            if (team.equals(otherInvite.team) && date.equals(otherInvite.date)) {
                return true;
            }
        }
        return false;
    }



}
