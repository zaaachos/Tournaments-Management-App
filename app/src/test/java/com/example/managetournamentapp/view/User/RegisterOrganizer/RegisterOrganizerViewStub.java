package com.example.managetournamentapp.view.User.RegisterOrganizer;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class RegisterOrganizerViewStub implements RegisterOrganizerView{
    String username, password, name , surname , phoneNumber, email, birthdate, organizerTitle;

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
    public String getOrganizerTitle() {
        return organizerTitle;
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
    public void setTitle(String title) {
        this.organizerTitle = title;
    }

    @Override
    public void showPopUp(RegisterOrganizerView view, String msg) {

    }

    @Override
    public void startOrganizerPage(String title) {

    }

    @Override
    public void lockFields() {

    }

}
