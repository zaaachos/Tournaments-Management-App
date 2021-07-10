package com.example.managetournamentapp.view.User.RegisterOrganizer;

import com.example.managetournamentapp.dao.LoggedInUser;
import com.example.managetournamentapp.dao.OrganizerDAO;
import com.example.managetournamentapp.dao.PlayerDAO;
import com.example.managetournamentapp.domain.Credentials;
import com.example.managetournamentapp.domain.Organizer;
import com.example.managetournamentapp.domain.User;
import com.example.managetournamentapp.memoryDao.OrganizerDAOMemory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class RegisterOrganizerPresenter {

    private RegisterOrganizerView view;
    private OrganizerDAO organizerDAO;
    private PlayerDAO playerDAO;
    private Organizer connectedOrganizer;
    private LoggedInUser loggedInUser;

    /**
     * default constructor
     */
    public RegisterOrganizerPresenter() {
    }

    /**
     * show the organizer's previous info, if we are on edit mode
     *
     * @param organizerTitle the organizer's title
     */
    public void showPreviousInfo(String organizerTitle) {
        if (organizerTitle == null)
            return;
        connectedOrganizer = organizerDAO.findByTitle(organizerTitle);
        if (connectedOrganizer == null)
            return;
        if (connectedOrganizer != null)//edit mode
        {
            view.setName(connectedOrganizer.getName());
            view.setSurname(connectedOrganizer.getSurname());
            view.setUsername(connectedOrganizer.getCredentials().getUsername());
            view.setPassword(connectedOrganizer.getCredentials().getPassword());
            view.setPhoneNumber(connectedOrganizer.getPhoneNumber());
            view.setEmail(connectedOrganizer.getEmail());
            view.setBirthdate(connectedOrganizer.getBirthDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).replace("-", "/"));
            view.setTitle(connectedOrganizer.getTitle());
            view.lockFields();
        }
    }

    /**
     * handle the input fields
     * create or edit an Organizer object
     */
    public void handleOrganizerData() {
        String usename = view.getUsername();
        String password = view.getPassword();
        String name = view.getName();
        String surname = view.getSurname();
        String phoneNumber = view.getPhoneNumber();
        String email = view.getEmail();
        String birthDate = view.getBirthDate();
        String title = view.getOrganizerTitle();

        // validate user data
        if (usename.length() < 5 || usename.length() > 20)
            view.showPopUp(view, "Username must be at least 5 chars and no longer than 20 chars!");
        else if (password.length() < 5)
            view.showPopUp(view, "Password must be at least 5 chars!");
        else if (name.length() < 2 || !validateName(name))
            view.showPopUp(view, "Name must be at least 2 chars and only alphabetical chars!");
        else if (surname.length() < 2 || !validateName(surname))
            view.showPopUp(view, "Surname must be at least 2 chars and only alphabetical chars!");
        else if (phoneNumber.length() != 10 || !validatePhone(phoneNumber))
            view.showPopUp(view, "Phone number must contain 10 numbers!");
        else if (email.length() < 2 || !checkEmail(email))
            view.showPopUp(view, "Not valid email!");
        else if (!validateBirthdate(birthDate))
            view.showPopUp(view, "Not valid date!");
        else if (!isAdult(birthDate))
            view.showPopUp(view, "You have to be over 18 years old!");
        else {
            // IF USER IS NEW!
            if (connectedOrganizer == null) {
                if (alreadyInUse(new Credentials(usename, password), connectedOrganizer)) {
                    view.showPopUp(view, "Username already in use! Try a new one.");
                    return;
                }
                if (TitleAlreadyInUse(title, connectedOrganizer)) {
                    view.showPopUp(view, "Title already in use! Try a new one.");
                    return;
                }
                Organizer organizer = new Organizer(name, surname, phoneNumber, email, LocalDate.parse(reformatBirthdate(birthDate)), new Credentials(usename, password), title);
                organizerDAO = new OrganizerDAOMemory();
                organizerDAO.save(organizer);
                loggedInUser.setUser(organizer);
                view.startOrganizerPage(title);
            } else {
                if (alreadyInUse(new Credentials(usename, password), connectedOrganizer)) {
                    view.showPopUp(view, "Username already in use! Try a new one.");
                    return;
                }

                if (TitleAlreadyInUse(title, connectedOrganizer)) {
                    view.showPopUp(view, "Title already in use! Try a new one.");
                    return;
                }
                connectedOrganizer.setName(name);
                connectedOrganizer.setSurname(surname);
                connectedOrganizer.setCredentials(new Credentials(usename, password));
                connectedOrganizer.setBirthDate(LocalDate.parse(reformatBirthdate(birthDate)));
                connectedOrganizer.setTitle(title);
                connectedOrganizer.setPhoneNumber(phoneNumber);
                connectedOrganizer.setEmail(email);
                view.startOrganizerPage(title);
            }
        }
    }

    /**
     * @param emailToCheck the email we want to check if it's valid.
     * @return true if the email is valid, else false.
     */
    public boolean checkEmail(String emailToCheck) {
        String valid = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(valid);
        Matcher matcher = pattern.matcher(emailToCheck);
        return matcher.matches();
    }

    /**
     * @param name the email we want to check if it's valid.
     * @return true if the name is valid, else false.
     */
    public boolean validateName(String name) {
        String valid = "^[a-zA-Z]*$";
        Pattern pattern = Pattern.compile(valid);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    /**
     * @param phone the email we want to check if it's valid.
     * @return true if the phone is valid, else false.
     */
    public boolean validatePhone(String phone) {
        String valid = "[0-9]+";
        Pattern pattern = Pattern.compile(valid);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    /**
     * reformatting the given string
     *
     * @param birthdate the birth date in the initial format
     * @return the reformatted birth date
     */
    public String reformatBirthdate(String birthdate) {
        birthdate = birthdate.replace("/", "-");
        String dateFormat = LocalDate.parse(birthdate, DateTimeFormatter.ofPattern("dd-MM-uuuu")).format(DateTimeFormatter.ofPattern("uuuu-MM-dd"));
        return dateFormat;
    }

    /**
     * @param birthdate the email we want to check if it's valid.
     * @return true if the birthd ate is valid, else false.
     */
    public boolean validateBirthdate(String birthdate) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(birthdate);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * @param birthdate the email we want to check if it's valid.
     * @return true if the user is adult
     */
    public boolean isAdult(String birthdate) {
        LocalDate now = LocalDate.now();
        String format = reformatBirthdate(birthdate);
        int diff = (int) LocalDate.parse(format).until(now, ChronoUnit.YEARS);
        return diff >= 18;
    }

    /**
     * Check if there is another user who has these credentials
     *
     * @param credentials the user credentials to check in registration
     * @return true if credentials are existed in DAO
     */
    public boolean alreadyInUse(Credentials credentials, User user) {
        boolean already = false;
        if (playerDAO.isUsedByAnother(credentials, user))
            already = true;
        if (organizerDAO.isUsedByAnother(credentials, user))
            already = true;
        return already;

    }

    /**
     * Check if there is another user who has this title
     *
     * @param title the user title to check in registration
     * @return true if credentials are existed in DAO
     */
    public boolean TitleAlreadyInUse(String title, User user) {
        boolean already = false;
        if (organizerDAO.TitleUsedByAnother(title, user))
            already = true;
        return already;

    }

    /**
     * get the loggedInUser
     *
     * @return the LoggedInUser object
     */
    public LoggedInUser getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * set the loggedInUser
     *
     * @param loggedInUser the new LoggedInUser
     */
    public void setLoggedInUser(LoggedInUser loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    /**
     * set a new view
     *
     * @param view the new view
     */
    public void setView(RegisterOrganizerView view) {
        this.view = view;
    }

    /**
     * clear the view
     */
    public void clearView() {
        this.view = null;
    }

    /**
     * set the organizerDAO
     *
     * @param organizerDAO the new OrganizerDAO
     */
    public void setOrganizerDAO(OrganizerDAO organizerDAO) {
        this.organizerDAO = organizerDAO;
    }

    /**
     * set the playerDAO
     *
     * @param playerDAO the new PlayerDAO
     */
    public void setPlayerDAO(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }


}
