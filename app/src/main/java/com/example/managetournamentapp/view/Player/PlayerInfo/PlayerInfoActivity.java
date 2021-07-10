package com.example.managetournamentapp.view.Player.PlayerInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.managetournamentapp.R;
import com.example.managetournamentapp.view.HomePage.HomePageActivity;
import com.example.managetournamentapp.view.User.RegisterPlayer.RegisterPlayerActivity;


/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class PlayerInfoActivity extends AppCompatActivity implements PlayerInfoView {
    private PlayerInfoViewModel viewModel;
    private static final String PLAYER_USERNAME_EXTRA = "player_username_extra";

    Button btnEditPlayer;
    Button btnDeletePlayer;
    String playerUsername;
    AlertDialog POPUP_DELETION;

    /**
     * Creates the layout and initializes the activity
     * @param savedInstanceState the Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_info);
        playerUsername = this.getIntent().getStringExtra(PLAYER_USERNAME_EXTRA);

        setContentView(R.layout.activity_player_info);

        viewModel = new ViewModelProvider(this).get(PlayerInfoViewModel.class);
        viewModel.getPresenter().setView(this);
        viewModel.getPresenter().findPlayerInfo(playerUsername);

        btnEditPlayer = findViewById(R.id.edit_player_button);
        btnDeletePlayer = findViewById(R.id.delete_player_button);
        btnEditPlayer.setOnClickListener(v -> viewModel.getPresenter().onEditPlayer());
        btnDeletePlayer.setOnClickListener(v -> viewModel.getPresenter().onDeletePlayer());

        viewModel.getPresenter().findAccess();
    }

    /**
     * show the possible actions popup
     * @param layoutId the layout of the popup
     * @param msg the message of the popup
     * @param btn1 the first button
     * @param btn2 the second button
     */
    @Override
    public AlertDialog showPopUp(int layoutId, String msg, int btn1, int btn2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View customLayout = getLayoutInflater().inflate(layoutId, null);

        builder.setView(customLayout);
        AlertDialog dialog = builder.create();

        TextView textMsg = (TextView) customLayout.findViewById(R.id.action_message);

        //no delete
        Button firstButton = (Button) customLayout.findViewById(btn1);
        //yes delete
        Button secondButton = (Button) customLayout.findViewById(btn2);

        textMsg.setText(msg);

        firstButton.setOnClickListener(v -> viewModel.getPresenter().onNoDeletePlayer());
        secondButton.setOnClickListener(v -> viewModel.getPresenter().onYesDeletePlayer());
        return dialog;
    }

    /**
     * set the contents in the username edit text
     * @param username the new username
     */
    @Override
    public void setUsername(String username) {
        ((TextView) findViewById(R.id.text_username)).setText(username);
    }

    /**
     * set the contents in the password edit text
     * @param password the new password
     */
    @Override
    public void setPassword(String password) {
        ((TextView) findViewById(R.id.text_password)).setText(password);
    }

    /**
     * set the contents in the name edit text
     * @param name the new name
     */
    @Override
    public void setName(String name) {
        ((TextView) findViewById(R.id.text_name)).setText(name);
    }

    /**
     * set the contents in the surname edit text
     * @param surname the new surname
     */
    @Override
    public void setSurname(String surname) {
        ((TextView) findViewById(R.id.text_surname)).setText(surname);
    }

    /**
     * set the contents in the phone number edit text
     * @param phone the new phone number
     */
    @Override
    public void setPhone(String phone) {
        ((TextView) findViewById(R.id.text_phone)).setText(phone);
    }

    /**
     * set the contents in the email edit text
     * @param email the new email
     */
    @Override
    public void setEmail(String email) {
        ((TextView) findViewById(R.id.text_email)).setText(email);
    }

    /**
     * set the contents in the location edit text
     * @param location the new location
     */
    @Override
    public void setLocation(String location) {
        ((TextView) findViewById(R.id.text_location)).setText(location);
    }

    /**
     * set the contents in the birth date edit text
     * @param date the new birth date
     */
    @Override
    public void setBirthDate(String date) {
        ((TextView) findViewById(R.id.text_birth_date)).setText(date);
    }

    /**
     * when the player decides to edit his account
     * the register player activity is started
     */
    @Override
    public void startEditPlayer() {
        Intent intent = new Intent(PlayerInfoActivity.this, RegisterPlayerActivity.class);
        intent.putExtra(PLAYER_USERNAME_EXTRA, playerUsername);
        startActivity(intent);
    }

    /**
     * when the player decides to delete his account
     */
    @Override
    public void startDeletePlayer() {
        Toast.makeText(this, "USER HAS BEEN DELETED. BACK TO HOME PAGE.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(PlayerInfoActivity.this, HomePageActivity.class);
        startActivity(intent);
    }

    /**
     * show a toast when the player can't delete the account
     */
    @Override
    public void showCantDelete() {
        Toast.makeText(this, "YOU CAN'T DELETE YOUR ACCOUNT. REASON: PARTICIPATING IN A TEAM", Toast.LENGTH_SHORT).show();
    }

    /**
     * the account can be edited or deleted only by the player
     * who owns it
     */
    @Override
    public void changesOfAccess() {
        btnEditPlayer.setVisibility(View.GONE);
        btnDeletePlayer.setVisibility(View.GONE);
        (findViewById(R.id.password_row)).setVisibility(View.GONE);
    }

    /**
     * show the deletion popup
     * @param layout the layout of the popup
     * @param msg the message of the popup
     * @param btn1 the first button
     * @param btn2 the second button
     */
    @Override
    public void displayPopUp(int layout, String msg, int btn1, int btn2) {
        POPUP_DELETION = showPopUp(layout, msg, btn1, btn2);
        POPUP_DELETION.show();
    }

    /**
     * close the deletion popup
     */
    @Override
    public void dismissPopUp() {
        POPUP_DELETION.dismiss();
        POPUP_DELETION = null;
    }
}
