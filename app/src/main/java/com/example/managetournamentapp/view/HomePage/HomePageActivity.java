package com.example.managetournamentapp.view.HomePage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.managetournamentapp.R;
import com.example.managetournamentapp.memoryDao.MemoryInitializer;
import com.example.managetournamentapp.view.User.Browsing.BrowsingActivity;
import com.example.managetournamentapp.view.User.Login.LoginActivity;
import com.example.managetournamentapp.view.User.RegisterOrganizer.RegisterOrganizerActivity;
import com.example.managetournamentapp.view.User.RegisterPlayer.RegisterPlayerActivity;


/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class HomePageActivity extends AppCompatActivity implements HomePageView {

    private Button connectBtn;
    private Button browseBtn;
    HomePageViewModel viewModel;
    private static boolean first = true;

    /**
     * Creates the layout and initializes the activity
     * @param savedInstanceState the Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Memory Initialization
        if (first) {
            new MemoryInitializer().prepareData();
            first = false;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        viewModel = new ViewModelProvider(this).get(HomePageViewModel.class);
        viewModel.getPresenter().setView(this);

        connectBtn = (Button) findViewById(R.id.connect_button);
        browseBtn = (Button) findViewById(R.id.browse_button);
        connectBtn.setOnClickListener(v -> viewModel.getPresenter().onConnectAction());
        browseBtn.setOnClickListener(v -> viewModel.getPresenter().onBrowseAction());
    }


    /**
     * Shows pop up message with two buttons
     * @param layoutId layouf of the popup message
     * @param btn1 id of the first button
     * @param btn2 if of the second button
     * @param firstPopup boolean variable to inform us if this is the second popup we want to show
     */
    public void showPopUp(int layoutId, int btn1, int btn2, boolean firstPopup) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View customLayout = getLayoutInflater().inflate(layoutId, null);
        builder.setView(customLayout);
        AlertDialog dialog = builder.create();

        //log in button or org register button
        Button firstButton = (Button) customLayout.findViewById(btn1);
        //register button or player register button
        Button secondButton = (Button) customLayout.findViewById(btn2);

        if (firstPopup) {
            firstButton.setOnClickListener(v -> viewModel.getPresenter().onLogInAction());
            secondButton.setOnClickListener(v -> viewModel.getPresenter().onRegisterAction());
        } else {
            firstButton.setOnClickListener(v -> viewModel.getPresenter().onOrganizerRegisterAction());
            secondButton.setOnClickListener(v -> viewModel.getPresenter().onPlayerRegisterAction());
        }
        dialog.show();
    }

    /**
     * Shows a pop up that prompts the user
     * to press appropriate buttons (register, login, etc)
     */
    @Override
    public void connectAction() {
        showPopUp(R.layout.register_login, R.id.login_button, R.id.register_button, true);
    }

    /**
     * Starts the login activity.
     * This method is called when the user
     * clicks on the login button
     */
    @Override
    public void loginAction() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

    /**
     This method is called when the user presses
     the register button. It is used to show a pop up
     from which the user chooses to register either as
     a player or an organizer
     */
    @Override
    public void registerAction() {
        Toast.makeText(this,
                "REGISTER",
                Toast.LENGTH_SHORT)
                .show();
        showPopUp(R.layout.register_selection, R.id.organizer_register, R.id.player_register, false);
    }

    /**
     * Starts the organizer registration activity.
     * This method is called when the user
     * clicks on the register (organizer) button
     */
    @Override
    public void organizerRegisterAction() {
        Toast.makeText(this,
                "ORGANIZER",
                Toast.LENGTH_SHORT)
                .show();
        Intent intent = new Intent(this, RegisterOrganizerActivity.class);
        startActivity(intent);
    }

    /**
     * Starts the player registration activity.
     * This method is called when the user
     * clicks on the register (player) button
     */
    @Override
    public void playerRegisterAction() {
        Toast.makeText(this,
                "PLAYER",
                Toast.LENGTH_SHORT)
                .show();
        Intent intent = new Intent(this, RegisterPlayerActivity.class);
        startActivity(intent);
    }

    /**
     * Starts the Browse Activity
     * This method is called when the user
     * clicks on the browse button
     */
    @Override
    public void browseAction() {
        Intent intent = new Intent(this, BrowsingActivity.class);
        startActivity(intent);
    }

    /**
     * Overrides the back button and throws a pop up message
     * asking for confirmation of wanted action
     */
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomePageActivity.this);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}