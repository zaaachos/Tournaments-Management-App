package com.example.managetournamentapp.domain;

import java.time.LocalDate;


/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class Game {

    private String arena;
    private int scoreA, scoreB;
    private Team teamA, teamB;
    private LocalDate date;
    private boolean isFinished;

    public Game(){
    }

    /**
     * Basic constructor
     * @param arena where the game will take place
     * @param teamA the first team
     * @param teamB the second team
     * @param date  the day of the game
     */
    public Game(String arena, Team teamA, Team teamB, LocalDate date) {
        this.arena = arena;
        this.teamA = teamA;
        this.teamB = teamB;
        this.date = date;
        isFinished = false;
    }

    /**
     * Initialization constructor
     * @param date
     */
    public Game(LocalDate date) {
        this.date = date;
        isFinished = false;
    }

    /**
     *
     * @return the index
     * -1 if teamA wins
     * 0 if game is tied
     * 1 if teamB wins
     * 2 if the game is not finished
     */
    public int findWinner() {
        if(!isFinished())
            return 2;
        return Integer.compare(scoreA, scoreB);
    }

    /**
     *  get the arena of the game
     * @return the arena of the game
     */
    public String getArena() {
        return arena;
    }

    /**
     *  set the arena of the game
     * @param arena
     */
    public void setArena(String arena) {
        this.arena = arena;
    }

    /**
     *returns true only if the game has finished and the score has been set
     * @return true if the game has finished
     */
    public boolean isFinished() {
        return isFinished;
    }

    /**
     * used to declare the game as finished
     * @param finished is set true if the game has finished
     */
    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    /**
     * get the score of the first team
     * @return the score/points in favor of the first team
     */
    public int getScoreA() {
        return scoreA;
    }

    /**
     * set the score of the first team
     * @param scoreA the score of the first team
     */
    public void setScoreA(int scoreA) {
        this.scoreA = scoreA;
    }

    /**
     * get the score of the second team
     * @return the score/points in favor of the second team
     */
    public int getScoreB() {
        return scoreB;
    }

    /**
     * set the score of the second team
     * @param scoreB the score of the second team
     */
    public void setScoreB(int scoreB) {
        this.scoreB = scoreB;
    }

    /**
     *get the instance of the first team
     * @return the first team
     */
    public Team getTeamA() {
        return teamA;
    }

    /**
     *set the first team
     * @param teamA is the first team
     */
    public void setTeamA(Team teamA) {
        this.teamA = teamA;
    }

    /**
     *get the instance of the second team
     * @return the second team
     */
    public Team getTeamB() {
        return teamB;
    }

    /**
     *set the first team
     * @param teamB is the first team
     */
    public void setTeamB(Team teamB) {
        this.teamB = teamB;
    }

    /**
     * get the day that the game takes place
     * @return the LocalDate of the game
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * set the day that the game takes place
     * @param date is he LocalDate of the game
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * check if two games are equal
     * @param other is the other game
     * @return true if this equals the other according to the basic fields
     */
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other instanceof Game) {
            Game otherGame = (Game) other;
            if (teamA.equals(otherGame.teamA) && teamB.equals(otherGame.teamB) && scoreA==otherGame.scoreA
                    && scoreB==otherGame.scoreB && arena.equals(otherGame.arena)
                        && isFinished==otherGame.isFinished && date.equals(otherGame.getDate()) )
                return true;
        }
        return false;
    }

    /**
     * the string representation of the game
     * @return the basic fields to string
     */
    @Override
    public String toString() {
        return "Game{" +
                "arena='" + arena +
                ", scoreA=" + scoreA +
                ", scoreB=" + scoreB +
                ", date=" + date +
                ", isFinished=" + isFinished +
                '}';
    }
}
