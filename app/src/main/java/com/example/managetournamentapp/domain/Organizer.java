package com.example.managetournamentapp.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class Organizer extends User {

    private String title;
    private ArrayList<Tournament> tournaments = new ArrayList<>();

    /**
     * the default constructor of the organizer
     */
    public Organizer() {
        super();
        this.title = "";
    }

    /**
     * the full constructor of an organizer
     * @param name is the manager's name
     * @param surname is the manager's surname
     * @param phoneNumber is the contact phone number
     * @param email  is the contact email
     * @param birthDate is the date of birth of the manager
     * @param credentials are the login credentials of the organizer's account
     * @param title get the title of the organizer (for example UEFA)
     */
    public Organizer(String name, String surname, String phoneNumber, String email, LocalDate birthDate, Credentials credentials, String title) {
        super(name, surname, phoneNumber, email, birthDate, credentials);
        this.title = title;

    }

    /**
     * get the title that the organizer uses
     * @return the title of the organizer
     */
    public String getTitle() {
        return title;
    }

    /**
     * set the title of the organizer
     * @param title is the new title of the organizer
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * get all the tournaments that the organizer has ever created
     * @return the arraylist of the organizer's tournaments
     */
    public ArrayList<Tournament> getTournaments() {
        return new ArrayList<>(tournaments);
    }

    /**
     * add a new tournament in the organizer's created tournaments
     * @param tour is the new tournament
     */
    public void addTournament(Tournament tour) {
        if (tour == null) {
            return;
        }
        if (!tournaments.contains(tour)) {
            tournaments.add(tour);
        }
    }

    /**
     * delete a tournament from the organizer's created tournaments
     * @param tour is the tournament that will be deleted
     */
    public void deleteTournament(Tournament tour) {
        if (tour == null) {
            return;
        }
        if (!tournaments.contains(tour)) {
            return;
        }

        if (tour.isRunning()) {
            return;
        }

        Iterator<Participation> iter = tour.friendGetParticipations().iterator();
        while (iter.hasNext()) {
            Participation participation = iter.next();
            Team team = participation.getTeam();
            team.removeParticipation(participation);
        }
        tournaments.remove(tour);
    }

    /**
     * the string representation of the organizer
     * @return the basic info of the organizer to string
     */
    @Override
    public String toString() {
        return "Organizer{" +
                "title='" + title + '\'' +
                ", sportType='" + '\'' +
                '}';
    }

    /**
     *check if two organizers are equal
     * @param other the other organizer
     * @return if this organizer is equal to the other organizer
     */
    public boolean equals(Object other) {
        if (other == this)
            return true;
        if (other == null)
            return false;
        if (other instanceof Organizer) {
            Organizer otherOrganizer = (Organizer) other;
            if (title.equals(otherOrganizer.title) && otherOrganizer.title != null)         // each Organizer has a unique Title. EK15!
                return true;
        }
        return false;
    }
}
