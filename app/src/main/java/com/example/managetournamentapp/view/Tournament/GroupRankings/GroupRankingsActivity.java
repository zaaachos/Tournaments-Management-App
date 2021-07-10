package com.example.managetournamentapp.view.Tournament.GroupRankings;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

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

public class GroupRankingsActivity extends AppCompatActivity implements GroupRankingsView,TeamsListFragment.OnListFragmentInteractionListener {

    public static final String  TOURNAMENT_TITLE_EXTRA= "tournament_title_extra";
    public static final String TEAM_NAME_EXTRA = "team_name_extra";
    private static final String SPECIFIC_GROUP_EXTRA = "specific_group_extra" ;
    private static final String PLAYER_USERNAME_EXTRA = "player_username_extra";
    private static final String ORGANIZER_TITLE_EXTRA = "organizer_title_extra";
    GroupRankingsViewModel viewModel;
    private String tournamentTitle;
    private int specificGroup;
    ImageButton btnHome;

    /**
     * Creates the layout and initializes the activity
     * @param savedInstanceState the Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_rankings);
        viewModel = new ViewModelProvider(this).get(GroupRankingsViewModel.class);
        viewModel.getPresenter().setView(this);

        btnHome = findViewById(R.id.imageButton);
        btnHome.setOnClickListener(v -> viewModel.getPresenter().onHomePage());

        tournamentTitle = this.getIntent().getStringExtra(TOURNAMENT_TITLE_EXTRA);
        specificGroup = Integer.parseInt( this.getIntent().getStringExtra(SPECIFIC_GROUP_EXTRA) );

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }
            viewModel.getPresenter().findTeams(tournamentTitle, specificGroup);
            TeamsListFragment teamsListFragment = TeamsListFragment.newInstance(1);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, teamsListFragment)
                    .commit();
        }
    }

    /**
     * What happens when an item is selected
     * @param item the team that is selected
     */
    @Override
    public void onListFragmentInteraction(Team item) {
        if (item.getName()==null){
            Toast.makeText(this,"THE TEAM IS NOT SET", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(GroupRankingsActivity.this, TeamPageActivity.class);
        intent.putExtra(TEAM_NAME_EXTRA, item.getName());
        startActivity(intent);
    }


    /**
     * get the list of the teams in the group
     * @return the ArrayList of teams
     */
    @Override
    public ArrayList<Team> getTeamsList() {
        return viewModel.getPresenter().getResults();
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
