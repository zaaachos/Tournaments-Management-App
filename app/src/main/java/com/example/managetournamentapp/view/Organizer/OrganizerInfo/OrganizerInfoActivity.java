package com.example.managetournamentapp.view.Organizer.OrganizerInfo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.managetournamentapp.R;
import com.example.managetournamentapp.view.HomePage.HomePageActivity;
import com.example.managetournamentapp.view.User.RegisterOrganizer.RegisterOrganizerActivity;


/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class OrganizerInfoActivity extends AppCompatActivity implements OrganizerInfoView {
    private OrganizerInfoViewModel viewModel;
    public static final String ORGANIZER_TITLE = "organizer_title";
    Button btnEditOrganizer;
    Button btnDeleteOrganizer;

    /**
     * Creates the layout and initializes the activity
     * @param savedInstanceState the Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer_info);


        viewModel = new ViewModelProvider(this).get(OrganizerInfoViewModel.class);
        viewModel.getPresenter().setView(this);
        viewModel.getPresenter().findOrganizerInfo();

        btnEditOrganizer = findViewById(R.id.edit_organizer_button);
        btnDeleteOrganizer = findViewById(R.id.delete_organizer_button);

        btnEditOrganizer.setOnClickListener(v -> viewModel.getPresenter().onEditOrganizer());
        btnDeleteOrganizer.setOnClickListener(v -> viewModel.getPresenter().onDeleteOrganizer());

    }

    /**
     * Method used
     * to set the username field of activity's layout
     * screen to the username of the organizer that is logged in
     * @param username username of organizer to be set
     */
    @Override
    public void setUsername(String username) {
        ((TextView) findViewById(R.id.text_username)).setText(username);
    }

    /**
     * Method used
     * to set the password field of activity's layout
     * to the password of the organizer that is logged in
     * @param password password of organizer to be set
     */
    @Override
    public void setPassword(String password) {
        ((TextView) findViewById(R.id.text_password)).setText(password);
    }
    /**
     * Method used
     * to set the name field of activity's layout
     * to the name of the organizer that is logged in
     * @param name name of organizer to be set
     */
    @Override
    public void setName(String name) {
        ((TextView) findViewById(R.id.text_name)).setText(name);
    }

    /**
     * Method used
     * to set the surname field of activity's layout
     * to the surname of the organizer that is logged in
     * @param surname surname of organizer to be set
     */
    @Override
    public void setSurname(String surname) {
        ((TextView) findViewById(R.id.text_surname)).setText(surname);
    }

    /**
     * Method used
     * to set the phone field of activity's layout
     * to the phone number of the organizer that is logged in
     * @param phone phone of organizer to be set
     */
    @Override
    public void setPhone(String phone) {
        ((TextView) findViewById(R.id.text_phone)).setText(phone);
    }

    /**
     * Method used
     * to set the email field of activity's layout
     * to the email of the organizer that is logged in
     * @param email email of organizer to be set
     */
    @Override
    public void setEmail(String email) {
        ((TextView) findViewById(R.id.text_email)).setText(email);
    }

    /**
     * Method used
     * to set the birthdate field of activity's layout
     * to the birthdate of the organizer that is logged in
     * @param date birthdate of organizer to be set
     */
    @Override
    public void setBirthDate(String date) {
        ((TextView) findViewById(R.id.text_birth_date)).setText(date);
    }

    /**
     * Method used
     * to set the title field of activity's layout
     * to the title of the organizer that is logged in
     * @param title title of organizer to be set
     */
    @Override
    public void setTitle(String title) {
        ((TextView) findViewById(R.id.text_organizer_title)).setText(title);
    }

    /**
     * Starts the register organizer activity so that the user can modify account
     * @param organizerTitle title of organizer to be passed as an extra
     */
    @Override
    public void startEditOrganizer(String organizerTitle) {
        Intent intent = new Intent(OrganizerInfoActivity.this, RegisterOrganizerActivity.class);
        intent.putExtra( ORGANIZER_TITLE , organizerTitle);
        startActivity(intent);
    }

    /**
     * Deletes the organizer's account
     * and goes back to the home page screen
     */
    @Override
    public void startDeleteOrganizer() {
        Intent intent = new Intent(OrganizerInfoActivity.this, HomePageActivity.class);
        startActivity(intent);
    }

    /**
     * Toast message shown in case user can't delete account
     */
    @Override
    public void showCantDelete() {
        Toast.makeText(this,"YOU CAN'T DELETE YOUR ACCOUNT", Toast.LENGTH_SHORT).show();
    }


}
