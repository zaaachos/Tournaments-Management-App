package com.example.managetournamentapp.view.User.Browsing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;

import com.example.managetournamentapp.R;
import com.example.managetournamentapp.domain.Tournament;
import com.example.managetournamentapp.view.HomePage.HomePageActivity;
import com.example.managetournamentapp.view.Organizer.OrganizerTournaments.fragment.TournamentListFragment;
import com.example.managetournamentapp.view.Tournament.TournamentPage.TournamentPageActivity;

import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class BrowsingActivity extends AppCompatActivity implements BrowsingView, TournamentListFragment.OnListFragmentInteractionListener {

    public static final String TOURNAMENT_TITLE_EXTRA = "tournament_title_extra";
    private String tournamentSelected;
    BrowsingViewModel viewModel;

    /**
     * Creates the layout and initializes the activity
     * @param savedInstanceState the Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browsing);
        viewModel = new ViewModelProvider(this).get(BrowsingViewModel.class);
        viewModel.getPresenter().setView(this);


        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            viewModel.getPresenter().findAllTournaments();

            TournamentListFragment tournamentListFragment = TournamentListFragment.newInstance(1);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, tournamentListFragment)
                    .commit();
        }
    }

    /**
     * What happens when an item is selected
     * @param item the tournament that is selected
     */
    @Override
    public void onListFragmentInteraction(Tournament item) {
        tournamentSelected = item.getTitle();
        viewModel.getPresenter().startTournamentPage(item);

    }

    /**
     * get the list of all the tournaments
     * @return the ArrayList of tournaments
     */
    @Override
    public ArrayList<Tournament> getTournamentList() {
        return viewModel.getPresenter().getResults();
    }

    /**
     * start the tournament page activity
     * @param title the title of the tournament
     */
    @Override
    public void startTournamentPage(String title) {
        Intent intent = new Intent(this, TournamentPageActivity.class);
        intent.putExtra(TOURNAMENT_TITLE_EXTRA, title);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }
}