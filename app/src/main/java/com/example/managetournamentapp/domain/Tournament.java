package com.example.managetournamentapp.domain;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class Tournament {

    private final int MAX_TEAMS_NUMBER;
    private LocalDate startDate, finishDate;
    private String title, location, description;
    private Sport sportType;
    private AgeDivision ageDivision;
    private ArrayList<Round> rounds = new ArrayList<>();
    private ArrayList<LocalDate> dates = new ArrayList<>();
    private ArrayList<Participation> participations = new ArrayList<>();

    /**
     * the default constructor of this tournament
     */
    public Tournament() {
        title = "";
        startDate = null;
        finishDate = null;
        location = "";
        description = "";
        sportType = null;
        dates = null;
        ageDivision = null;
        MAX_TEAMS_NUMBER = 32;
    }

    /**
     * the full constructor of this tournamet
     * @param title of the tournament
     * @param startDate of the tournament
     * @param finishDate of the tournament
     * @param location of the tournament
     * @param sportType of the tournament
     * @param maxTeamsNumber of the tournament
     * @param ageDivision of the tournament
     * @param dates of the tournament
     */
    public Tournament(String title, LocalDate startDate, LocalDate finishDate, String location, Sport sportType, int maxTeamsNumber, AgeDivision ageDivision, ArrayList<LocalDate> dates) {
        this.title = title;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.location = location;
        this.sportType = sportType;
        this.MAX_TEAMS_NUMBER = maxTeamsNumber;
        this.ageDivision = ageDivision;
        this.dates = dates;
        initRounds(dates);
    }

    /**
     * initialize the game dates for each round
     * @param dates the arraylist of dates
     */
    public void initRounds(ArrayList<LocalDate> dates) {
        int teamsNumber = MAX_TEAMS_NUMBER;
        int firstIndex = 0;
        int lastIndex = MAX_TEAMS_NUMBER / 4 * 6;
        rounds.add(new Round(MAX_TEAMS_NUMBER, false,  new ArrayList<>(dates.subList(firstIndex, lastIndex)) ));
        for (int i = 0; i < log2(MAX_TEAMS_NUMBER) - 1; i++) {
            teamsNumber = teamsNumber / 2;
            firstIndex = lastIndex;
            lastIndex = firstIndex + teamsNumber / 2;
            rounds.add(new Round(teamsNumber, true, new ArrayList<>(dates.subList(firstIndex, lastIndex)) ));
        }
    }

    /**
     * add a new participation in this tournament
     * @param participation the new participation
     */
    public void addParticipation(Participation participation) {
        if (participation == null) {
            return;
        }
        participation.getTeam().addParticipation(participation);
    }

    /**
     * if the last team just entered the tournament
     * the first round is arranged
     */
    public void checkIfStarts(){
        if ( isFull()) {
            rounds.get(0).setup(findTeams());
        }
    }

    /**
     * find the teams of this tournament
     * @return the teams that participate in this tournament
     */
    public ArrayList<Team> findTeams(){
        ArrayList<Team> teams = new ArrayList<>();
        for (Participation p : participations)
            teams.add(p.getTeam());
        return teams;
    }

    /**
     *remove a participation from the tournament
     * @param participation that will be removed
     */
    public void removeParticipation(Participation participation) {
        if (participation == null) {
            return;
        }
        participation.getTeam().removeParticipation(participation);
    }

    /**
     * get the current participations in this tournament
     * @return a copy of the arraylist with the participations
     */
    public ArrayList<Participation> getParticipations() {
        return new ArrayList<>(participations);
    }

    /**
     * get the current participations in this tournament
     * @return the arraylist with the participations
     */
    public ArrayList<Participation> friendGetParticipations() {
        return participations;
    }

    /**
     * find if the number of teams currently participating
     * is equal to the number of teams needed to start the tournament
     * @return true if the tournament is full
     */
    public boolean isFull() {
        if (participations == null)
            return false;

        return participations.size() == MAX_TEAMS_NUMBER;
    }

    /**
     * find if the tournament is taking place at the current moment
     * @return true if the tournament is currently happening
     */
    public boolean isRunning() {
        LocalDate now = LocalDate.now();
        return !(now.isBefore(startDate)) && !(now.isAfter(finishDate));
    }

    /**
     * find the rounds of this tournament
     * there can be 3 or 4 or 5 rounds
     * @return a copy of arraylist of the rounds
     */
    public ArrayList<Round> getRounds() {
        return new ArrayList<>(rounds);
    }

    /**
     * get the title of the tournament
     * @return the title of the tournament
     */
    public String getTitle() {
        return title;
    }

    /**
     * set a new title for the tournament
     * @param title is the new title
     */
    public void setTitle(String title) {
        if (title == null || !getParticipations().isEmpty()) {
            return;
        }
        this.title = title;
    }

    /**
     * get the location that the tournament will take place in
     * @return the location of the tournament
     */
    public String getLocation() {
        return location;
    }

    /**
     * set a new location for the tournament
     * @param location is the new location
     */
    public void setLocation(String location) {
        if (location == null || !getParticipations().isEmpty()) {
            return;
        }
        this.location = location;
    }

    /**
     * get the description
     * @return the description of the tournament
     */
    public String getDescription() {
        return description;
    }

    /**
     * set a new desctiption for the tournament
     * @param description the new descreption
     */
    public void setDescription(String description) {
        if (description == null || !getParticipations().isEmpty()) {
            return;
        }
        this.description = description;
    }

    /**
     * get the sport type of the tournament
     * @return the sport of the tournament
     */
    public Sport getSportType() {
        return sportType;
    }

    /**
     * set a new sport type for the tournament
     * @param sportType the new sport
     */
    public void setSportType(Sport sportType) {
        if (sportType == null || !getParticipations().isEmpty()) {
            return;
        }
        this.sportType = sportType;
    }

    /**
     * get the date when the tournament begns
     * @return the LocalDate representation of the start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * set a new start date for the tournament
     * @param startDate the new start date
     */
    public void setStartDate(LocalDate startDate) {
        if (startDate == null || !getParticipations().isEmpty()) {
            return;
        }
        this.startDate = startDate;
    }

    /**
     * get the date when the tournament end
     * @return the LocalDate representation of the finish date
     */
    public LocalDate getFinishDate() {
        return finishDate;
    }

    /**
     * set a new finish date for the tournament
     * @param finishDate the new finish date
     */
    public void setFinishDate(LocalDate finishDate) {
        if (finishDate == null || !getParticipations().isEmpty()) {
            return;
        }
        this.finishDate = finishDate;
    }


    /**
     * get the age division of the teams in this tournament
     * @return the age division requirement of this tournament
     */
    public AgeDivision getAgeDivision() {
        return ageDivision;
    }

    /**
     * set a new age division for this tournament
     * @param ageDivision the new age division
     */
    public void setAgeDivision(AgeDivision ageDivision) {
        if (ageDivision == null || !getParticipations().isEmpty()) {
            return;
        }
        this.ageDivision = ageDivision;
    }

    /**
     * get the maximum number of teams that can be added
     * until this tournament is considered full
     * @return the max number of teams
     */
    public int getMAX_TEAMS_NUMBER() {
        return MAX_TEAMS_NUMBER;
    }

    /**
     * finds the logarithm of a number, using 2 as a base
     * @param x an integer
     * @return the binary logarithm of 2
     */
    public int log2(int x) {
        return (int) (Math.log(x) / Math.log(2));
    }

    /**
     * the string representation of the tournament
     * @return the basic info of the tournament to string
     */
    @Override
    public String toString() {
        return "Tournament{" +
                "title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", sportType=" + sportType +
                ", maxTeamsNumber=" + MAX_TEAMS_NUMBER +
                ", roundsNumber=" + rounds.size() +
                ", teams=" + MAX_TEAMS_NUMBER +
                ", ageDivision=" + ageDivision +
                '}';
    }



    /**
     *check if two tournaments are equal
     * @param other is the other tournament
     * @return if this tournament is equal to the other tournament
     */
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        if (other instanceof Tournament) {
            Tournament otherTour = (Tournament) other;
            if ((title.equals(otherTour.title) && otherTour.title != null) &&
                    (startDate.equals(otherTour.startDate) && otherTour.startDate != null) &&
                    (finishDate.equals(otherTour.finishDate) && otherTour.finishDate != null) &&
                    (location.equals(otherTour.location) && otherTour.location != null) &&
                    (sportType.equals(otherTour.sportType) && otherTour.sportType != null) &&
                    (ageDivision.equals(otherTour.ageDivision) && otherTour.ageDivision != null) &&
                    (otherTour.dates != null && dates.equals(otherTour.dates)) &&
                    (rounds.equals(otherTour.rounds) && otherTour.rounds != null) &&
                    getMAX_TEAMS_NUMBER() == otherTour.getMAX_TEAMS_NUMBER())
                return true;
        }
        return false;
    }

}
