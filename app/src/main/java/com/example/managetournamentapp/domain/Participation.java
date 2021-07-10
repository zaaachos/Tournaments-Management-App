package com.example.managetournamentapp.domain;


import java.time.LocalDate;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class Participation {

    private LocalDate startDate, finishDate;
    private Tournament tournament;
    private Team team;

    /**
     * the default constructor of a participation
     */
    public Participation() {}

    /**
     *the full constructor , the start date is the day that the tournament will start and the
     * finish date is the date that the tournament will finish
     * @param tournament the tournament that the team will participate to
     * @param team the team
     */
    public Participation(Tournament tournament, Team team) {
        this.tournament = tournament;
        this.team = team;
        this.startDate = tournament.getStartDate();
        this.finishDate = tournament.getFinishDate();
    }

    /**
     * get the tournament of the participation
     * @return
     */
    public Tournament getTournament() {
        return tournament;
    }

    /**
     * get the team that participates
     * @return the team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * get the date that the participation starts
     * @return the LocalDate representation of the start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * get the date that the participation ends
     * @return the LocalDate representation of the finish date
     */
    public LocalDate getFinishDate() {
        return finishDate;
    }

    /**
     * find if the tournament finish date was before the current date
     * @return true if the tournament has been completed
     */
    public boolean isPast() {
        return finishDate.isBefore(LocalDate.now());
    }

    /**
     * set the date that the participation starts
     * @param startDate the start date
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * set the date that the participation finishes
     * @param finishDate the finish date
     */
    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    /**
     * set a new tournament for the participation
     * @param tournament the new tournament of the participation
     */
    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    /**
     * set a new team for the participation
     * @param team the new team of the participation
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * find if two participations take place in the same tournament
     * @param other the other participation
     * @return true if this Participation is in the same tournament with the other Participation
     */
    public boolean inSameTournament(Participation other) {  // may need a check for dates
        if (other == null)
            return false;
        return tournament.equals(other.tournament);
    }

    /**
     *check if two participations over overlaping dates
     * @param other the other participation
     * @return true if this Participation dates overlap with the other Participation dates
     */
    public boolean isSimultaneous(Participation other) {
        if (finishDate.isBefore(other.getStartDate()) || other.getFinishDate().isBefore(startDate)) {
            return false;
        }
        return true;
    }

    /**
     * find if the participation is running at this moment
     * @return true if the participation is currently taking place
     */
    public boolean isRunning() {
        LocalDate now = LocalDate.now();
        return !(now.isBefore(startDate)) && !(now.isAfter(finishDate));
    }

    /**
     * find the string representation of the participation
     * @return the basic info of the participation to string
     */
    @Override
    public String toString() {
        return "Participation{" +
                "startDate=" + startDate +
                ", finishDate=" + finishDate +
                '}';
    }


    /**
     * check if two participations are equal
     * @param other is the other participation
     * @return true if this participation equals the other participation according to the basic fields
     */
    public boolean equals(Object other){
        if (other == null) {
            return false;
        }
        if (!(other instanceof Participation)) {
            return false;
        }
        if (this == other) {
            return true;
        }
        Participation check = (Participation) other;

        if (!(team == null ? check.getTeam()
                == null : team.equals(check.getTeam()))) {
            return false;
        }
        if (!(tournament == null ? check.getTournament()
                == null : tournament.equals(check.getTournament()))) {
            return false;
        }
        return true;
    }
}
