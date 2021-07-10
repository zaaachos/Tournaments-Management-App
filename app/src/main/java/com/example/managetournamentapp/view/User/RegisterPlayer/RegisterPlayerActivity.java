package com.example.managetournamentapp.view.User.RegisterPlayer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.managetournamentapp.R;
import com.example.managetournamentapp.domain.Sport;
import com.example.managetournamentapp.view.Player.PlayerPage.PlayerPageActivity;

import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class RegisterPlayerActivity extends AppCompatActivity implements RegisterPlayerView, View.OnClickListener {

    RegisterPlayerViewModel viewModel;
    private static final String PLAYER_USERNAME_EXTRA = "player_username_extra";
    private Button saveBtn;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    private CheckBox checkBox6;
    String playerUsername;
    private ArrayList<Sport> sportsInterest = new ArrayList<>();


    /**
     * Creates the layout and initializes the activity
     *
     * @param savedInstanceState the Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_player);
        System.out.println("PLAYER REGISTRATION CURRENT PAGE");
        playerUsername = this.getIntent().getStringExtra(PLAYER_USERNAME_EXTRA);

        if (playerUsername != null) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle("Player Edit");
            }
        }

        viewModel = new ViewModelProvider(this).get(RegisterPlayerViewModel.class);
        viewModel.getPresenter().setView(this);

        saveBtn = (Button) findViewById(R.id.savePlayerBtn);
        saveBtn.setOnClickListener(this);

        checkBox1 = (CheckBox) findViewById(R.id.radio_button_basketball3v3);
        checkBox2 = (CheckBox) findViewById(R.id.radio_button_basketball5v5);
        checkBox3 = (CheckBox) findViewById(R.id.radio_button_football5v5);
        checkBox4 = (CheckBox) findViewById(R.id.radio_button_football7v7);
        checkBox5 = (CheckBox) findViewById(R.id.radio_button_volleyball3v3);
        checkBox6 = (CheckBox) findViewById(R.id.radio_button_volleyball6v6);

        viewModel.getPresenter().showPreviousInfo(playerUsername);
    }


    /**
     * show a popup on the screen
     *
     * @param view the view of the popup
     * @param msg  the message that will be shown
     */
    @Override
    public void showPopUp(RegisterPlayerView view, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View customLayout = getLayoutInflater().inflate(R.layout.wrong_input_popup, null);
        builder.setView(customLayout);
        AlertDialog dialog = builder.create();
        Button OKbtn = (Button) customLayout.findViewById(R.id.OK_popup);
        TextView errorMsg = (TextView) customLayout.findViewById(R.id.error_messsage);      // display message we want.
        errorMsg.setText(msg);
        OKbtn.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    /**
     * what happens when a button is pressed
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.savePlayerBtn) {
            System.out.println("SAVE BUTTON PRESSED!");
            handleSportsInterest();
            viewModel.getPresenter().handlePlayerData();
        }
    }

    /**
     * start the player page activity
     *
     * @param username the username of the player
     */
    public void startPlayerPage(String username) {
        Intent intent = new Intent(this, PlayerPageActivity.class);
        intent.putExtra(PLAYER_USERNAME_EXTRA, username);
        startActivity(intent);
    }

    @Override
    public void lockFields() {
        findViewById(R.id.birthdate).setEnabled(false);
    }

    /**
     * get the contents of the edit text
     *
     * @return the given username
     */
    @Override
    public String getUsername() {
        EditText USERNAME = (EditText) findViewById(R.id.username);
        return USERNAME.getText().toString();
    }

    /**
     * get the contents of the edit text
     *
     * @return the given password
     */
    @Override
    public String getPassword() {
        EditText PASSWORD = (EditText) findViewById(R.id.password);
        return PASSWORD.getText().toString();
    }

    /**
     * get the contents of the edit text
     *
     * @return the given name
     */
    @Override
    public String getName() {
        EditText NAME = (EditText) findViewById(R.id.name);
        return NAME.getText().toString();
    }

    /**
     * get the contents of the edit text
     *
     * @return the given surname
     */
    @Override
    public String getSurname() {
        EditText SURNAME = (EditText) findViewById(R.id.surname);
        return SURNAME.getText().toString();
    }

    /**
     * get the contents of the edit text
     *
     * @return the name given
     */
    @Override
    public String getPhoneNumber() {
        EditText PHONE = (EditText) findViewById(R.id.phone);
        return PHONE.getText().toString();
    }

    /**
     * get the contents of the edit text
     *
     * @return the given birth date
     */
    @Override
    public String getEmail() {
        EditText EMAIL = (EditText) findViewById(R.id.email);
        return EMAIL.getText().toString();
    }

    /**
     * get the contents of the edit text
     *
     * @return the birth date
     */
    @Override
    public String getBirthDate() {
        EditText BIRTHDATE = (EditText) findViewById(R.id.birthdate);
        return BIRTHDATE.getText().toString();
    }

    /**
     * get the contents of the edit text
     *
     * @return the given location
     */
    @Override
    public String getLocation() {
        EditText LOCATION = (EditText) findViewById(R.id.location);
        return LOCATION.getText().toString();
    }

    /**
     * set the contents in the username edit text
     *
     * @param username the new username
     */
    @Override
    public void setUsername(String username) {
        EditText USERNAME = (EditText) findViewById(R.id.username);
        USERNAME.setText(username);
    }

    /**
     * set the contents in the password edit text
     *
     * @param password the new password
     */
    @Override
    public void setPassword(String password) {
        EditText PASSWORD = (EditText) findViewById(R.id.password);
        PASSWORD.setText(password);
    }

    /**
     * set the contents in the name edit text
     *
     * @param name the new name
     */
    @Override
    public void setName(String name) {
        EditText NAME = (EditText) findViewById(R.id.name);
        NAME.setText(name);
    }

    /**
     * set the contents in the surname edit text
     *
     * @param surname the new surname
     */
    @Override
    public void setSurname(String surname) {
        EditText SURNAME = (EditText) findViewById(R.id.surname);
        SURNAME.setText(surname);
    }

    /**
     * set the contents in the phone number edit text
     *
     * @param phoneNumber the new phone number
     */
    @Override
    public void setPhoneNumber(String phoneNumber) {
        EditText PHONE = (EditText) findViewById(R.id.phone);
        PHONE.setText(phoneNumber);
    }

    /**
     * set the contents in the email edit text
     *
     * @param email the new email
     */
    @Override
    public void setEmail(String email) {
        EditText EMAIL = (EditText) findViewById(R.id.email);
        EMAIL.setText(email);
    }

    /**
     * set the contents in the birth date edit text
     *
     * @param birthdate the new birth date
     */
    @Override
    public void setBirthdate(String birthdate) {
        EditText BIRTHDATE = (EditText) findViewById(R.id.birthdate);
        BIRTHDATE.setText(birthdate);
    }

    /**
     * set the contents in the location edit text
     *
     * @param location the new location
     */
    @Override
    public void setLocation(String location) {
        EditText LOCATION = (EditText) findViewById(R.id.location);
        LOCATION.setText(location);
    }

    /**
     * get the sports that the player has selected
     *
     * @return the arraylist of sports
     */
    @Override
    public ArrayList<Sport> getSportsInterest() {
        return this.sportsInterest;

    }

    /**
     * checks the correct checkboxes
     */
    public void handleSportsInterest() {
        if (checkBox1.isChecked())
            sportsInterest.add(new Sport("Basketball3v3"));
        else {
            sportsInterest.remove(new Sport("Basketball3v3"));
        }
        if (checkBox2.isChecked())
            sportsInterest.add(new Sport("Basketball5v5"));
        else {
            sportsInterest.remove(new Sport("Basketball5v5"));
        }
        if (checkBox3.isChecked())
            sportsInterest.add(new Sport("Football5v5"));
        else {
            sportsInterest.remove(new Sport("Football5v5"));
        }
        if (checkBox4.isChecked())
            sportsInterest.add(new Sport("Football7v7"));
        else {
            sportsInterest.remove(new Sport("Football7v7"));
        }
        if (checkBox5.isChecked())
            sportsInterest.add(new Sport("Volleyball3v3"));
        else {
            sportsInterest.remove(new Sport("Volleyball3v3"));
        }
        if (checkBox6.isChecked())
            sportsInterest.add(new Sport("Volleyball6v6"));
        else {
            sportsInterest.remove(new Sport("Volleyball6v6"));
        }
        System.out.println("Handle: " + sportsInterest);
    }

    /**
     * set the contents in the sport checkboxes
     *
     * @param sports the arraylist of sports that the player is interested in
     */
    @Override
    public void setSportsInterest(ArrayList<Sport> sports) {
        System.out.println("Set sport: " + sports);
        if (sports.contains(new Sport("Basketball3v3")))
            checkBox1.setChecked(true);
        if (sports.contains(new Sport("Basketball5v5")))
            checkBox2.setChecked(true);
        if (sports.contains(new Sport("Football5v5")))
            checkBox3.setChecked(true);
        if (sports.contains(new Sport("Football7v7")))
            checkBox4.setChecked(true);
        if (sports.contains(new Sport("Volleyball3v3")))
            checkBox5.setChecked(true);
        if (sports.contains(new Sport("Volleyball6v6")))
            checkBox6.setChecked(true);


    }

}