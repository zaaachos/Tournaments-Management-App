package com.example.managetournamentapp.view.Organizer.OrganizerInfo;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class OrganizerInfoViewStub implements OrganizerInfoView {
    String username, password, name, surname, phone, email, birthdate, title;
    boolean onEdit = false, onDelete = false, onShow = false;

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
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setBirthDate(String date) {
        this.birthdate = date;
    }

    @Override
    public void startEditOrganizer(String organizerUsername) {
        onEdit = true;
    }

    @Override
    public void startDeleteOrganizer() {
        onDelete = true;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void showCantDelete() {
        onShow = true;
    }
}
