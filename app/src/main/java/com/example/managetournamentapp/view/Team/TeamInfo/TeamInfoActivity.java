package com.example.managetournamentapp.view.Team.TeamInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.managetournamentapp.R;
import com.example.managetournamentapp.view.Organizer.OrganizerPage.OrganizerPageActivity;
import com.example.managetournamentapp.view.Player.CreateTeam.CreateTeamActivity;
import com.example.managetournamentapp.view.Player.PlayerPage.PlayerPageActivity;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class TeamInfoActivity  extends AppCompatActivity implements TeamInfoView {
    private TeamInfoViewModel viewModel;
    public static final String TEAM_NAME_EXTRA = "team_name_extra";
    private static final String PLAYER_USERNAME_EXTRA = "player_username_extra";
    private static final String ORGANIZER_TITLE_EXTRA = "organizer_title_extra";
    String teamName;
    Button btnEditTeam;
    Button btnDeleteTeam;
    ImageButton btnHome;

    /**
     * Creates the layout and initializes the activity
     * @param savedInstanceState the Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_info);

        teamName =  this.getIntent().getStringExtra(TEAM_NAME_EXTRA);
        viewModel = new ViewModelProvider(this).get(TeamInfoViewModel.class);
        viewModel.getPresenter().setView(this);
        viewModel.getPresenter().findTeamInfo(teamName);


        btnEditTeam = findViewById(R.id.edit_team_button);
        btnDeleteTeam = findViewById(R.id.delete_team_button);
        btnHome = findViewById(R.id.imageButton);

        btnEditTeam.setOnClickListener(v -> viewModel.getPresenter().onEditTeam());
        btnDeleteTeam.setOnClickListener(v -> viewModel.getPresenter().onDeleteTeam());
//        btnHome.setOnClickListener(v -> viewModel.getPresenter().onHomePage());
        viewModel.getPresenter().findAccess();
    }

    /**
     * set the contents in the name edit text
     * @param name name of the team
     */
    @Override
    public void setTeamName(String name) {
        ((TextView)findViewById(R.id.team_name)).setText(name);
    }

    /**
     * set the contents in the colors edit text
     * @param colors colors of the team
     */
    @Override
    public void setColors(String colors) {
        ((TextView)findViewById(R.id.team_colors)).setText(colors);
    }

    /**
     * set the contents in the division edit text
     * @param division division of the team
     */
    @Override
    public void setAgeDivision(String division) {
        ((TextView)findViewById(R.id.age_division)).setText(division);
    }

    /**
     * set the contents in the sport edit text
     * @param sport sport of the team
     */
    @Override
    public void setSport(String sport) {
        ((TextView)findViewById(R.id.sport_type)).setText(sport);
    }

    /**
     * when the captain decides to edit this team
     * the create team activity is started
     */
    @Override
    public void startEditTeam() {
        Intent intent = new Intent(TeamInfoActivity.this, CreateTeamActivity.class);
        intent.putExtra(TEAM_NAME_EXTRA, teamName);
        startActivity(intent);
    }

    /**
     * when the captain decides to delete this team
     * @param playerUsername the player's username
     */
    @Override
    public void startDeleteTeam(String playerUsername) {
        Intent intent = new Intent(TeamInfoActivity.this, PlayerPageActivity.class);
        intent.putExtra(PLAYER_USERNAME_EXTRA, playerUsername);
        startActivity(intent);
    }

    /**
     * show a toast on the screen
     * @param txt the message of the toast
     */
    @Override
    public void showToast(String txt) {

        Toast.makeText(this,txt, Toast.LENGTH_SHORT).show();
    }

    /**
     * only the captain can edit and delete the team
     */
    @Override
    public void changesOfAccess() {
        btnEditTeam.setVisibility(View.GONE);
        btnDeleteTeam.setVisibility(View.GONE);
    }

    /**
     * what happens when the homepage button is pressed
     * @param isPlayer true if the logged in user is a player
     * @param name is the name of a player. or the title of an organizer
     */
    @Override
    public void backToHomePage(boolean isPlayer, String name) {
        if (isPlayer){
            Intent intent = new Intent(this, PlayerPageActivity.class);
            intent.putExtra(PLAYER_USERNAME_EXTRA,name);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent (this, OrganizerPageActivity.class);
            intent.putExtra(ORGANIZER_TITLE_EXTRA, name);
            startActivity(intent);
        }
    }
}
