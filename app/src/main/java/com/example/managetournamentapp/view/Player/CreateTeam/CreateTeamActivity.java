package com.example.managetournamentapp.view.Player.CreateTeam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.managetournamentapp.R;
import com.example.managetournamentapp.view.Player.JoinedTeams.JoinedTeamsActivity;
import com.example.managetournamentapp.view.Player.PlayerPage.PlayerPageActivity;

import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */
public class CreateTeamActivity extends AppCompatActivity implements CreateTeamView {

    CreateTeamViewModel viewModel;
    public static final String TEAM_NAME_EXTRA = "team_name_extra";
    private static final String PLAYER_USERNAME_EXTRA = "player_username_extra";
    private Button saveBtn;
    private Spinner spinner;
    String teamName;
    ImageButton btnHome;

    /**
     * Creates the layout and initializes the activity
     *
     * @param savedInstanceState the Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);

        teamName = this.getIntent().getStringExtra(TEAM_NAME_EXTRA);

        if (teamName != null) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle("Team Edit");
            }
        }

        viewModel = new ViewModelProvider(this).get(CreateTeamViewModel.class);
        viewModel.getPresenter().setView(this);

        spinner = findViewById(R.id.sport_spinner);
        setSpinnerList(viewModel.getPresenter().getSportTypes());

        viewModel.getPresenter().showPreviousInfo(teamName);

        saveBtn = findViewById(R.id.saveTeambtn);
        btnHome = findViewById(R.id.imageButton);
        saveBtn.setOnClickListener(v -> viewModel.getPresenter().onSaveTeam());
        btnHome.setOnClickListener(v -> viewModel.getPresenter().onHomePage());
    }

    /**
     * show the player's page activity
     * after the team has been saved
     * @param userName the username of the captain
     */
    @Override
    public void startSaveTeam(String userName) {
        Intent intent = new Intent(CreateTeamActivity.this, JoinedTeamsActivity.class);
        intent.putExtra(PLAYER_USERNAME_EXTRA, userName);
        startActivity(intent);
    }

    /**
     * get the colors of the team
     * @return the name of the team
     */
    @Override
    public String getTeamName() {
        return ((EditText) findViewById(R.id.team_name)).getText().toString();
    }

    /**
     * get the colors of the team
     * @return the colors
     */
    @Override
    public String getTeamColors() {
        return ((EditText) findViewById(R.id.team_colors)).getText().toString();
    }

    /**
     * set the contents of the spinner
     */
    @Override
    public int getSportType() {
        return spinner.getSelectedItemPosition();
    }

    /**
     * set the contents in the name edit text
     * @param name the new name
     */
    @Override
    public void setTeamName(String name) {
        ((EditText) findViewById(R.id.team_name)).setText(name);
    }

    /**
     * set the contents in the colors edit text
     * @param colors the new colors
     */
    @Override
    public void setTeamColors(String colors) {
        ((EditText) findViewById(R.id.team_colors)).setText(colors);

    }

    /**
     * set the contents of the spinner
     * @param position the index of the sport type
     */
    @Override
    public void setSportType(int position) {
        spinner.setSelection(position);
    }

    /**
     * freeze the sport type spinner
     */
    @Override
    public void lockSportType() {
        spinner.setEnabled(false);
    }

    /**
     * set the choices of the sport type spinner
     * @param list
     */
    public void setSpinnerList(ArrayList<String> list) {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    /**
     *show a popup on the screen
     * @param view the view of the popup
     * @param msg the message that will be shown
     */
    @Override
    public void showPopUp(CreateTeamView view, String msg) {
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
     * what happens when the homepage button is pressed
     * @param name is the name of a player
     */
    @Override
    public void backToHomePage(String name) {
        Intent intent = new Intent(this, PlayerPageActivity.class);
        intent.putExtra(PLAYER_USERNAME_EXTRA, name);
        startActivity(intent);
    }

}
