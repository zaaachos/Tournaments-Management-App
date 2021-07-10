package com.example.managetournamentapp.domain;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class Sport {

    private String name;
    private int minimumPlayers;

    /**
     * the constructor of a sport
     * @param name the name(type) of the sport
     */
    public Sport(String name) {
        boolean flag = checkSportName(name);
        if (flag) {
            this.name = name;
            setMinPlayers(name);
        }
    }

    /**
     * check if the sport name given is included in supporteds sports
     * @param name the name of the sport
     * @return true if the name of the sport is valid
     */
    public boolean checkSportName(String name) {
        for (TournamentType tt : TournamentType.values()) {
            if (tt.name().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * set the minimum number of players that are needed
     * for a game to take place
     * @param name the name of the sport
     */
    public void setMinPlayers(String name) {
        switch (name) {
            case "Volleyball3v3":
            case "Basketball3v3":
                minimumPlayers = 6;
                break;

            case "Football5v5":
            case "Basketball5v5":
                minimumPlayers = 10;
                break;

            case "Football7v7":
                minimumPlayers = 14;
                break;

            case "Volleyball6v6":
                minimumPlayers = 12;
                break;
        }

    }

    /**
     * Change sport selection
     * @param name the name of the new sport
     */
    public void changeSetup(String name) {

        boolean flag = checkSportName(name);
        if (flag) {
            this.name = name;
            setMinPlayers(name);
        } else {
            System.out.println("Wrong settings for the specific sport type selection.");
        }
    }

    /**
     * find the name(type) of the sport
     * @return the name of the sport
     */
    public String getName() {
        return name;
    }

    /**
     * the minimum number of players for a game of this sport
     * @return the minimum number of players
     */
    public int getMinimumPlayers() {
        return minimumPlayers;
    }


    /**
     * the string representation of the sport
     * @return the basic info of the sport to string
     */
    @Override
    public String toString() {
        return "Sport{" +
                "name='" + name + '\'' +
                ", minimumPlayers=" + minimumPlayers +
                '}';
    }

    /**
     *check if two sports are equal
     * @param other is the other sport
     * @return if this sport is equal to the other sport
     */
    public boolean equals(Object other) {

        if (other == null) {
            return false;
        }
        if (!(other instanceof Sport)) {
            return false;
        }
        if (this == other) {
            return true;
        }
        Sport check = (Sport) other;
        if (!(name == null ? check.getName() == null : name.equals(check.getName()))) {
            return false;
        }
        return true;

    }

}
