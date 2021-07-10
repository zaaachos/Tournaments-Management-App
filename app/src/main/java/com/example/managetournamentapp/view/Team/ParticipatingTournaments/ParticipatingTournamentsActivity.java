package com.example.managetournamentapp.view.Team.ParticipatingTournaments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.managetournamentapp.R;
import com.example.managetournamentapp.domain.Tournament;
import com.example.managetournamentapp.view.Organizer.OrganizerTournaments.fragment.TournamentListFragment;
import com.example.managetournamentapp.view.Player.PlayerPage.PlayerPageActivity;
import com.example.managetournamentapp.view.Team.AddParticipation.AddParticipationActivity;
import com.example.managetournamentapp.view.Tournament.TournamentPage.TournamentPageActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class ParticipatingTournamentsActivity extends AppCompatActivity implements ParticipatingTournamentsView, TournamentListFragment.OnListFragmentInteractionListener {
    public static final String TEAM_NAME_EXTRA = "team_name_extra";
    public static final String TOURNAMENT_TITLE_EXTRA = "tournament_title_extra";
    private static final String PLAYER_USERNAME_EXTRA = "player_username_extra";
    String teamName;
    ParticipatingTournamentsViewModel viewModel;
    private FloatingActionButton addBtn;
    ImageButton btnHome;

    /**
     * Creates the layout and initializes the activity
     * @param savedInstanceState the Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participating_tournaments);
        teamName =  this.getIntent().getStringExtra(TEAM_NAME_EXTRA);
        viewModel = new ViewModelProvider(this).get(ParticipatingTournamentsViewModel.class);
        viewModel.getPresenter().setView(this);

        addBtn = findViewById(R.id.add_participation_button);
        btnHome = findViewById(R.id.imageButton);
        addBtn.setOnClickListener(v -> viewModel.getPresenter().onAddParticipation());
        btnHome.setOnClickListener(v -> viewModel.getPresenter().onHomePage());

        if (findViewById(R.id.fragment_container) != null){
            if (savedInstanceState != null){
                return;
            }
            viewModel.getPresenter().findParticipatingTournaments(teamName);
            viewModel.getPresenter().findAccess();

            TournamentListFragment tournamentListFragment = TournamentListFragment.newInstance(1);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, tournamentListFragment)
                    .commit();
        }
    }

    /**
     * get the tournaments that the team participates in
     * @return the ArrayList of tournaments
     */
    @Override
    public ArrayList<Tournament> getTournamentList() {
        return viewModel.getPresenter().getResults();
    }

    /**
     * what happens when a tournament is selected
     * @param item the tournament
     */
    @Override
    public void onListFragmentInteraction(Tournament item) {
        Intent intent = new Intent(ParticipatingTournamentsActivity.this, TournamentPageActivity.class);
        intent.putExtra(TOURNAMENT_TITLE_EXTRA, item.getTitle());
        startActivity(intent);
    }

    /**
     * show the page where participations are created
     */
    @Override
    public void startAddParticipation(){
        Intent intent = new Intent(ParticipatingTournamentsActivity.this, AddParticipationActivity.class);
        intent.putExtra(TEAM_NAME_EXTRA, teamName);
        startActivity(intent);
    }

    /**
     * only the captain can create a new participation
     */
    public void changesOfAccess(){
        addBtn.setVisibility(View.GONE);
    }

    /**
     * what happens when the homepage button is pressed
     * @param name is the name of a player. or the title of an organizer
     */
    @Override
    public void backToHomePage(String name) {
            Intent intent = new Intent(this, PlayerPageActivity.class);
            intent.putExtra(PLAYER_USERNAME_EXTRA,name);
            startActivity(intent);


    }
}