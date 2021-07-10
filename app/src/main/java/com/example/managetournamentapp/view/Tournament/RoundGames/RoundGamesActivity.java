package com.example.managetournamentapp.view.Tournament.RoundGames;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.managetournamentapp.R;
import com.example.managetournamentapp.domain.Game;
import com.example.managetournamentapp.view.Organizer.OrganizerPage.OrganizerPageActivity;
import com.example.managetournamentapp.view.Player.PlayerPage.PlayerPageActivity;
import com.example.managetournamentapp.view.Tournament.RoundGames.fragment.GamesListFragment;
import com.example.managetournamentapp.view.User.Browsing.BrowsingActivity;

import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class RoundGamesActivity extends AppCompatActivity implements GamesListFragment.OnListFragmentInteractionListener, RoundGamesView {

    public static final String TOURNAMENT_TITLE_EXTRA = "tournament_title_extra";
    private static final String ROUND_TEAMS_EXTRA = "round_teams_extra";
    private static final String SPECIFIC_GROUP_EXTRA = "specific_group_extra";
    private static final String PLAYER_USERNAME_EXTRA = "player_username_extra";
    private static final String ORGANIZER_TITLE_EXTRA = "organizer_title_extra";
    RoundGamesViewModel viewModel;
    private static AlertDialog POPUP_ACTION;
    private String tournamentTitle;
    private int roundTeamsNumber;
    private int specificGroup;
    ImageButton btnHome;

    /**
     * Creates the layout and initializes the activity
     * @param savedInstanceState the Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_games);
        viewModel = new ViewModelProvider(this).get(RoundGamesViewModel.class);
        viewModel.getPresenter().setView(this);
        btnHome = findViewById(R.id.imageButton);
        btnHome.setOnClickListener(v -> viewModel.getPresenter().onHomePage());

        tournamentTitle = this.getIntent().getStringExtra(TOURNAMENT_TITLE_EXTRA);
        roundTeamsNumber = Integer.parseInt(this.getIntent().getStringExtra(ROUND_TEAMS_EXTRA));
        specificGroup = Integer.parseInt(this.getIntent().getStringExtra(SPECIFIC_GROUP_EXTRA));

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }
            viewModel.getPresenter().findGames(tournamentTitle, roundTeamsNumber, specificGroup);
            GamesListFragment gamesListFragment = GamesListFragment.newInstance(1);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, gamesListFragment)
                    .commit();
        }
    }

    /**
     * when the organizer presses on a game
     * they can set the score of the game
     * @param game the game that was pressed on
     */
    @Override
    public void showPopup(Game game) {
        POPUP_ACTION = showPopUp(R.layout.game_score_popup, R.id.save_button, R.id.txt_scoreA, R.id.txt_scoreB, game);
        POPUP_ACTION.show();
    }

    /**
     * create the popup
     * @param layoutId the layout of the popup
     * @param btn1 the button of the popup
     * @param txt1 the name of the first team
     * @param txt2 the name of the second team
     * @param game the game that was pressed on
     * @return the AlertDialog that will be shown
     */
    public AlertDialog showPopUp(int layoutId, int btn1, int txt1, int txt2, Game game) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View customLayout = getLayoutInflater().inflate(layoutId, null);
        builder.setView(customLayout);
        AlertDialog dialog = builder.create();

        Button saveButton = (Button) customLayout.findViewById(btn1);
        EditText edit1 = customLayout.findViewById(txt1);
        EditText edit2 = customLayout.findViewById(txt2);
        saveButton.setOnClickListener(v -> viewModel.getPresenter().onSave(game, edit1.getText().toString(), edit2.getText().toString()));
        return dialog;
    }

    /**
     * what happens when the user presses on a game
     * @param item the game that was pressed on
     */
    @Override
    public void onListFragmentInteraction(Game item) {
        viewModel.getPresenter().onPressed(item);
    }

    /**
     * get the games that will take place in this round
     * @return the ArrayList of games in this round
     */
    @Override
    public ArrayList<Game> getGamesList() {
        return viewModel.getPresenter().getResults();
    }

    /**
     * show a toast on the screen
     * @param text the message of the toast
     */
    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * refresh the current activity
     */
    @Override
    public void recreateView() {
        POPUP_ACTION.dismiss();
        POPUP_ACTION = null;
        recreate();
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



