package com.example.managetournamentapp.view.Team.TeamPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.managetournamentapp.R;

import com.example.managetournamentapp.view.Organizer.OrganizerPage.OrganizerPageActivity;
import com.example.managetournamentapp.view.Player.PlayerPage.PlayerPageActivity;
import com.example.managetournamentapp.view.Team.JoinedPlayers.JoinedPlayersActivity;
import com.example.managetournamentapp.view.Team.ParticipatingTournaments.ParticipatingTournamentsActivity;
import com.example.managetournamentapp.view.Team.TeamInfo.TeamInfoActivity;
import com.example.managetournamentapp.view.User.Browsing.BrowsingActivity;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class TeamPageActivity extends AppCompatActivity implements TeamPageView {
    private TeamPageViewModel viewModel;
    public static final String TEAM_NAME_EXTRA = "team_name_extra";
    private static final String PLAYER_USERNAME_EXTRA = "player_username_extra";
    private static final String ORGANIZER_TITLE_EXTRA = "organizer_title_extra";
    private boolean changeOfAccess = false;
    String teamName;
    TextView txtTeamName;
    Button btnTeamInfo;
    Button btnTeamPlayers;
    Button btnTeamParticipations;
    ImageButton btnHome;

    /**
     * Creates the layout and initializes the activity
     * @param savedInstanceState the Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        teamName =  this.getIntent().getStringExtra(TEAM_NAME_EXTRA);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_page);

        viewModel = new ViewModelProvider(this).get(TeamPageViewModel.class);
        viewModel.getPresenter().setView(this);
        viewModel.getPresenter().findTeamInfo(teamName);

        txtTeamName = findViewById(R.id.text_team_name);
        btnTeamInfo = findViewById(R.id.team_info_button);
        btnTeamPlayers = findViewById(R.id.team_players_button);
        btnTeamParticipations = findViewById(R.id.team_participations_button);
        btnHome = findViewById(R.id.imageButton);
        btnHome.setOnClickListener(v -> viewModel.getPresenter().onHomePage());


        txtTeamName.setText( teamName);
        btnTeamInfo.setOnClickListener(v ->  startTeamInfo());
        btnTeamPlayers.setOnClickListener(v -> startTeamPlayers());
        btnTeamParticipations.setOnClickListener(v ->  startTeamParticipations());

       viewModel.getPresenter().findAccess();
    }

    /**
     * when the "info" button is pressed
     * the team info activity starts
     */
    @Override
    public void startTeamInfo() {
        Intent intent = new Intent(TeamPageActivity.this, TeamInfoActivity.class);
        intent.putExtra(TEAM_NAME_EXTRA, teamName);
        startActivity(intent);
    }

    /**
     * when the "players" button is pressed
     * the participating players activity starts
     */
    @Override
    public void startTeamPlayers() {
        Intent intent = new Intent(TeamPageActivity.this, JoinedPlayersActivity.class);
        intent.putExtra(TEAM_NAME_EXTRA, teamName);
        startActivity(intent);
    }

    /**
     * when the "participations" button is pressed
     * the participations activity starts
     */
    @Override
    public void startTeamParticipations() {
        Intent intent = new Intent(TeamPageActivity.this, ParticipatingTournamentsActivity.class);
        intent.putExtra(TEAM_NAME_EXTRA, teamName);
        startActivity(intent);
    }

    /**
     *only the players of this team can see
     * the participations of this team
     */
    @Override
    public void changesOfAccess() {
        btnTeamParticipations.setVisibility(View.GONE);
        changeOfAccess = true;
    }

    /**
     * what happens when the homepage button is pressed
     * @param noLogin boolean parameter, if true the user has not logged in
     * @param isPlayer boolean parameter,if true the user is a player
     * @param name name of the player or title of the organizer
     */
    @Override
    public void backToHomePage(boolean noLogin, boolean isPlayer, String name) {
        if (noLogin){
            Intent intent = new Intent(this, BrowsingActivity.class);
            startActivity(intent);
        }
        else if (isPlayer){
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
