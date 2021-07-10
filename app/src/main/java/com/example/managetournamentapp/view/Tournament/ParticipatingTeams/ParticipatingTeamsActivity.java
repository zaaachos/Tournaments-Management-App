package com.example.managetournamentapp.view.Tournament.ParticipatingTeams;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.managetournamentapp.R;
import com.example.managetournamentapp.domain.Team;
import com.example.managetournamentapp.view.Organizer.OrganizerPage.OrganizerPageActivity;
import com.example.managetournamentapp.view.Player.PlayerPage.PlayerPageActivity;
import com.example.managetournamentapp.view.Team.TeamPage.TeamPageActivity;
import com.example.managetournamentapp.view.Tournament.ParticipatingTeams.fragment.TeamsListFragment;
import com.example.managetournamentapp.view.User.Browsing.BrowsingActivity;

import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class ParticipatingTeamsActivity extends AppCompatActivity implements ParticipatingTeamsView, TeamsListFragment.OnListFragmentInteractionListener {

    public static final String TOURNAMENT_TITLE_EXTRA = "tournament_title_extra";
    public static final String TEAM_NAME_EXTRA = "team_name_extra";
    private static final String PLAYER_USERNAME_EXTRA = "player_username_extra";
    private static final String ORGANIZER_TITLE_EXTRA = "organizer_title_extra";
    ParticipatingTeamsViewModel viewModel;
    String tournamentTitle;
    ImageButton btnHome;

    /**
     * Creates the layout and initializes the activity
     * @param savedInstanceState the Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participating_teams);
        viewModel = new ViewModelProvider(this).get(ParticipatingTeamsViewModel.class);
        viewModel.getPresenter().setView(this);
        btnHome = findViewById(R.id.imageButton);
        btnHome.setOnClickListener(v -> viewModel.getPresenter().onHomePage());
        tournamentTitle = this.getIntent().getStringExtra(TOURNAMENT_TITLE_EXTRA);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            viewModel.getPresenter().findParticipatingTeams(tournamentTitle);
            TeamsListFragment teamsListFragment = TeamsListFragment.newInstance(1);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, teamsListFragment)
                    .commit();
        }
    }

    /**
     * what happens when the user presses on a item
     * @param item the item that was pressed on
     */
    @Override
    public void onListFragmentInteraction(Team item) {
        viewModel.getPresenter().onTeamSelected(item);
    }

    /**
     * get the teams that participate in this tournament
     * @return the ArrayList of teams
     */
    @Override
    public ArrayList<Team> getTeamsList() {
        return viewModel.getPresenter().getResults();
    }

    /**
     * show the page of the chosen team
     * @param teamname the name of the team
     */
    @Override
    public void startTeamPage(String teamname) {
        Intent intent = new Intent(ParticipatingTeamsActivity.this, TeamPageActivity.class);
        intent.putExtra(TEAM_NAME_EXTRA, teamname);
        startActivity(intent);
    }

    /**
     * what happens when the homepage button is pressed
     * @param isPlayer is true if the logged in user is a player
     * @param name is the name of a player. or the title of an organizer
     * @param noLogin boolean parameter, if true the user has not logged in
     */
    @Override
    public void backToHomePage(boolean noLogin, boolean isPlayer, String name) {
        if (noLogin){
            Intent intent = new Intent(this,BrowsingActivity.class);
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
