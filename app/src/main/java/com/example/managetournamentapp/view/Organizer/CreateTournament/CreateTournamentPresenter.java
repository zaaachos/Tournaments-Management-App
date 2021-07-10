package com.example.managetournamentapp.view.Organizer.CreateTournament;


import com.example.managetournamentapp.dao.TournamentDAO;
import com.example.managetournamentapp.domain.AgeDivision;
import com.example.managetournamentapp.domain.Organizer;
import com.example.managetournamentapp.domain.Sport;
import com.example.managetournamentapp.domain.Tournament;
import com.example.managetournamentapp.domain.TournamentType;
import com.example.managetournamentapp.domain.User;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class CreateTournamentPresenter {
    private CreateTournamentView view;
    private ArrayList<String> sportTypes;
    private ArrayList<String> ageDivisions;
    private ArrayList<String> teamNumbers;
    private Tournament connectedTournament;
    private TournamentDAO tournamentDAO;
    private Organizer organizer;

    /**
     * Default Constructor
     */
    public CreateTournamentPresenter() {
        sportTypes = findSportTypes();
        ageDivisions = findAgeDivisions();
        teamNumbers = new ArrayList<>(Arrays.asList(new String[]{"8", "16", "32"}));
    }

    /**
     * Shows previous info of the tournament
     * @param tournamentName name of tournament that we want to show the info
     */
    public void showPreviousInfo(String tournamentName) {
        if (tournamentName == null)
            return;
        connectedTournament = tournamentDAO.find(tournamentName);
        if (connectedTournament == null)
            return;

        view.setTournamentTitle(connectedTournament.getTitle());
        view.setDescription(connectedTournament.getDescription());
        view.setLocation(connectedTournament.getLocation());
        view.setStartDate(connectedTournament.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).replace("-", "/"));
        view.setFinishDate(connectedTournament.getFinishDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).replace("-", "/"));
        view.setAgeDivision(getAgeDivisionIndex(connectedTournament.getAgeDivision().toString()));
        view.setTeamsNumber(getTeamsNumberIndex(String.valueOf(connectedTournament.getMAX_TEAMS_NUMBER())));
        view.setSportType(getSportTypeIndex(connectedTournament.getSportType().getName()));

        view.lockPrevious();
    }

    /**
     * When the user presses the save button when being on the create tournament page
     * In this method appropriate checks are done
     */
    public void onSaveTournament() {
        String title = view.getTournamentTitle();
        String location = view.getLocation();
        String startDate = view.getStartDate();
        String finishDate = view.getFinishDate();
        String description = view.getDescription();
        String ageDivision = ageDivisions.get(view.getAgeDivision());
        String sportType = sportTypes.get(view.getSportType());
        String teamsNumber = teamNumbers.get(view.getTeamsNumber());


        if (title.length() < 2 || title.length() > 20 || !validateTitle(title))
            view.showPopUp(view, "Title must contain at least 2 alphanumerical chars and be 20 chars long!");
        else if (location.length() < 2 || location.length() > 20 || !validateName(location))
            view.showPopUp(view, "Location must be at least 2 chars and only alphabetical chars!");
        else if (!validateDate(startDate) || !validateDate(finishDate))
            view.showPopUp(view, "Not valid date!");
        else {

            if (connectedTournament == null) {
                if (checkTitle(title, connectedTournament)) {
                    view.showPopUp(view, "Title already in use! Try a new one.");
                    return;
                }
                LocalDate startLocalDate = reformatDate(startDate);
                if (!checkDateIsNotPassed(startLocalDate)) {
                    view.showPopUp(view, "Start Date has already passed! Try a new one.");
                    return;
                }

                LocalDate finishLocalDate = reformatDate(finishDate);
                if (!checkDateIsNotPassed(finishLocalDate)) {
                    view.showPopUp(view, "Finish Date has already passed! Try a new one.");
                    return;
                }

                ArrayList<String> basicInfo = new ArrayList<>(Arrays.asList(title, startLocalDate.toString(), finishLocalDate.toString(), location, sportType, teamsNumber, ageDivision, description));
                view.startSetDates(basicInfo);
            } else {
                if (checkTitle(title, connectedTournament)) {
                    view.showPopUp(view, "Title already in use! Try a new one.");
                    return;
                }

                LocalDate startLocalDate = reformatDate(startDate);
                if (!checkDateIsNotPassed(startLocalDate)) {
                    view.showPopUp(view, "Start Date has already passed! Try a new one.");
                    return;
                }

                LocalDate finishLocalDate = reformatDate(finishDate);
                if (!checkDateIsNotPassed(finishLocalDate)) {
                    view.showPopUp(view, "Finish Date has already passed! Try a new one.");
                    return;
                }

                connectedTournament.setTitle(title);
                connectedTournament.setDescription(description);
                connectedTournament.setLocation(location);
                connectedTournament.setStartDate(startLocalDate);
                connectedTournament.setFinishDate(finishLocalDate);
                connectedTournament.setAgeDivision(AgeDivision.values()[getAgeDivisionIndex(ageDivision)]);
                connectedTournament.setSportType(new Sport(sportType));
                view.startSaveTournament(organizer.getTitle());
            }
        }
    }

    /**
     * Sets the organizer to the current user that is logged in as an organizer
     * @param user the user to be set as an organizer
     */
    public void setOrganizer(User user) {
        if (user == null)
            return;
        if (!(user instanceof Organizer))
            return;
        organizer = (Organizer) user;

    }

    /**
     * Returns the tournament sport type
     * @return the tournament sport type found
     */
    public ArrayList<String> getSportTypes() {
        return sportTypes;
    }

    /**
     * Returns the tournament age division
     * @return the tournament age division found
     */
    public ArrayList<String> getAgeDivisions() {
        return ageDivisions;
    }

    /**
     * Returns the tournament team numbers
     * @return the tournament team numbers found
     */
    public ArrayList<String> getTeamNumbers() {
        return teamNumbers;
    }

    /**
     * Returns the sport type index taken from the arraylist
     * @param sportType sportType to be found in the arraylist
     * @return the index that the sportype is in. 0 in case it doesn't exist
     */
    private int getSportTypeIndex(String sportType) {
        for (int i = 0; i < sportTypes.size(); i++) {
            if (sportTypes.get(i).equals(sportType))
                return i;
        }
        return 0;
    }

    /**
     * Returns the age division index taken from the arraylist
     * @param ageDivision age division to be found in the arraylist
     * @return the index that the specific age division is in. 0 in case it doesn't exist
     */
    private int getAgeDivisionIndex(String ageDivision) {
        for (int i = 0; i < ageDivisions.size(); i++) {
            if (ageDivisions.get(i).equals(ageDivision))
                return i;
        }
        return 0;
    }

    /**
     * Returns the teams number idex taken from the arraylist
     * @param teamsNumber teams number to be found in the arraylist
     * @return the index that the specific teams number is in. 0 in case it doesn't exist
     */
    private int getTeamsNumberIndex(String teamsNumber) {
        for (int i = 0; i < teamNumbers.size(); i++) {
            if (teamNumbers.get(i).equals(teamsNumber))
                return i;
        }
        return 0;
    }

    /**
     * Returns the arraylist containing sport types
     * @return the arraylist of sport types to be returned
     */
    private ArrayList<String> findSportTypes() {
        ArrayList<String> sportTypes = new ArrayList<>();
        for (int i = 0; i < TournamentType.values().length; i++) {
            sportTypes.add(TournamentType.values()[i].toString());
        }
        return sportTypes;
    }

    /**
     * Returns the arraylist containing sport types
     * @return the arraylist of sport types to be returned
     */
    private ArrayList<String> findAgeDivisions() {
        ArrayList<String> ageDivisions = new ArrayList<>();
        for (int i = 0; i < AgeDivision.values().length; i++) {
            ageDivisions.add(AgeDivision.values()[i].toString());
        }
        return ageDivisions;
    }


    /**
     * Sets the tournamentDAO
     * @param tournamentDAO tournamentDAO to be set
     */
    public void setTournamentDAO(TournamentDAO tournamentDAO) {
        this.tournamentDAO = tournamentDAO;
    }

    /**
     * Checks if the tournament title is being used
     * @param title title to be checked
     * @param tournament tournament with the specific title
     * @return true if it exists, false otherwise
     */
    public boolean checkTitle(String title, Tournament tournament) {
        return tournamentDAO.TitleIsUsed(title, tournament);
    }

    /**
     * Sets the view
     * @param view view to be set
     */
    public void setView(CreateTournamentView view) {
        this.view = view;
    }

    /**
     * Sets view to null
     */
    public void clearView() {
        this.view = null;
    }

    /**
     * Validates title
     * @param name the name we want to check if it's valid.
     * @return true if the name is valid
     */
    public boolean validateTitle(String name) {
        String valid = "^[a-zA-Z0-9]+$";
        Pattern pattern = Pattern.compile(valid);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    /**
     * Validates name
     * @param name the name we want to check if it's valid.
     * @return true if the name is valid
     */
    public boolean validateName(String name) {
        String valid = "^[a-zA-Z]*$";
        Pattern pattern = Pattern.compile(valid);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    /**
     * Reformats date in the desired format
     * @param date to reformat
     * @return the new date
     */
    public LocalDate reformatDate(String date) {
        date = date.replace("/", "-");
        LocalDate Localdate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-uuuu"));
        return Localdate;
    }

    /**
     * Validates date
     * @param date the date we want to check if it's valid
     * @return true if the date is valid
     */
    public boolean validateDate(String date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Check if the date has passed
     * @param date Date that is checked
     * @return
     */
    public boolean checkDateIsNotPassed(LocalDate date) {
        return date.isAfter(LocalDate.now());
    }

    /**
     * Returns to organizer profile
     */
    public void onHomePage() {

        view.backToHomePage(organizer.getTitle());

    }
}
