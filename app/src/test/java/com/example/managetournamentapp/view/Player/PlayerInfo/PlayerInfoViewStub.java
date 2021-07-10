package com.example.managetournamentapp.view.Player.PlayerInfo;

import androidx.appcompat.app.AlertDialog;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class PlayerInfoViewStub implements PlayerInfoView {
    AlertDialog dialog;
    String username, password, name, surname, phone, email, location, birthdate;
    boolean onEdit = false, onDelete = false, onShow = false, onChange = false;

    @Override
    public AlertDialog showPopUp(int layoutId, String msg, int btn1, int btn2) {
        return this.dialog;
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
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void setBirthDate(String date) {
        this.birthdate = date;
    }

    @Override
    public void startEditPlayer() {
        onEdit = true;
    }

    @Override
    public void startDeletePlayer() {
        onDelete = true;
    }

    @Override
    public void showCantDelete() {
        onShow = true;
    }

    @Override
    public void changesOfAccess() {
        onChange = true;
    }

    @Override
    public void displayPopUp(int layout, String msg, int btn1, int btn2) {

    }

    @Override
    public void dismissPopUp() {

    }
}
