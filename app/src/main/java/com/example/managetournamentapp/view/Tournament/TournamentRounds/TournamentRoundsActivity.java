package com.example.managetournamentapp.view.Tournament.TournamentRounds;

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
import com.example.managetournamentapp.view.Tournament.RoundGames.RoundGamesActivity;
import com.example.managetournamentapp.view.Tournament.TournamentGroups.TournamentGroupsActivity;
import com.example.managetournamentapp.view.User.Browsing.BrowsingActivity;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class TournamentRoundsActivity extends AppCompatActivity implements TournamentRoundsView {
    public static final String TOURNAMENT_TITLE_EXTRA = "tournament_title_extra";
    private static final String ROUND_TEAMS_EXTRA = "round_teams_extra" ;
    private static final String SPECIFIC_GROUP_EXTRA = "specific_group_extra" ;
    private static final String PLAYER_USERNAME_EXTRA = "player_username_extra";
    private static final String ORGANIZER_TITLE_EXTRA = "organizer_title_extra";
    private TournamentRoundsViewModel viewModel;
    String tournamentTitle;
    Button btnGroups;
    Button btn16;
    Button btn8;
    Button btnSemifinals;
    Button btnFinal;
    ImageButton btnHome;

    /**
     * Creates the layout and initializes the activity
     * @param savedInstanceState the Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tournamentTitle = this.getIntent().getStringExtra(TOURNAMENT_TITLE_EXTRA);
        setContentView(R.layout.activity_tournament_rounds);
        TextView textView = (TextView) findViewById(R.id.text_tournament_name);
        textView.setText(tournamentTitle);

        viewModel = new ViewModelProvider(this).get(TournamentRoundsViewModel.class);
        viewModel.getPresenter().setView(this);
        viewModel.getPresenter().findTournamentInfo(tournamentTitle);

        btnGroups = findViewById(R.id.groups_button);
        btn16 = findViewById(R.id.round16_button);
        btn8 = findViewById(R.id.round8_button);
        btnSemifinals = findViewById(R.id.semifinals_button);
        btnFinal = findViewById(R.id.final_button);
        btnHome = findViewById(R.id.imageButton);

        viewModel.getPresenter().findAccess();

        btnGroups.setOnClickListener(v -> viewModel.getPresenter().onGroups());
        btn16.setOnClickListener(v -> viewModel.getPresenter().on16());
        btn8.setOnClickListener(v -> viewModel.getPresenter().on8());
        btnSemifinals.setOnClickListener(v -> viewModel.getPresenter().onSemifinals());
        btnFinal.setOnClickListener(v -> viewModel.getPresenter().onFinal());
        btnHome.setOnClickListener(v -> viewModel.getPresenter().onHomePage());

    }

    /**
     * hide the button of a round
     * if this round doesn't exist in the tournament
     * @param teamsNumber the number of teams in the tournament
     */
    @Override
    public void changesOfAccess(int teamsNumber) {
        if (teamsNumber == 8){
            btn8.setVisibility(View.GONE);
            btn16.setVisibility(View.GONE);
        }else if (teamsNumber==16){
            btn16.setVisibility(View.GONE);
        }
    }

    /**
     * when a player presses on the button of a round
     * apart from the group stage round
     * @param tournamentTitle the title of the tournament
     * @param roundTeamsNumber the number of teams in the round
     */
    @Override
    public void showRoundGames(String tournamentTitle, int roundTeamsNumber){
        Intent intent = new Intent(TournamentRoundsActivity.this, RoundGamesActivity.class);
        intent.putExtra(TOURNAMENT_TITLE_EXTRA, tournamentTitle);
        intent.putExtra(ROUND_TEAMS_EXTRA, String.valueOf(roundTeamsNumber));
        intent.putExtra(SPECIFIC_GROUP_EXTRA, String.valueOf(-1));
        startActivity(intent);
    }

    /**
     * when the "groups" button is pressed,
     * the user wants to explore the "group" stage (1st round)
     * the tournament groups activity starts
     */
    @Override
    public void showGroups(String tournamentTitle, int roundTeamsNumber){
        Intent intent = new Intent(TournamentRoundsActivity.this, TournamentGroupsActivity.class);
        intent.putExtra(TOURNAMENT_TITLE_EXTRA, tournamentTitle);
        startActivity(intent);
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
