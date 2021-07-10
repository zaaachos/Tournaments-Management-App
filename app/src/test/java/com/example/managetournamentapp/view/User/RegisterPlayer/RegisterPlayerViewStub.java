package com.example.managetournamentapp.view.User.RegisterPlayer;

import com.example.managetournamentapp.domain.AgeDivision;
import com.example.managetournamentapp.domain.Sport;

import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class RegisterPlayerViewStub implements RegisterPlayerView {
    String username, password, name, surname, location, phoneNumber, email, birthdate;
    AgeDivision ageDivision;
    ArrayList<Sport> sportsInterest;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getBirthDate() {
        return birthdate;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public ArrayList<Sport> getSportsInterest() {
        return sportsInterest;
    }

    @Override
    public void setSportsInterest(ArrayList<Sport> sports) {
        this.sportsInterest = sports;
    }

    @Override
    public void showPopUp(RegisterPlayerView view, String msg) {

    }

    @Override
    public void startPlayerPage(String username) {

    }

    @Override
    public void lockFields() {

    }

}
