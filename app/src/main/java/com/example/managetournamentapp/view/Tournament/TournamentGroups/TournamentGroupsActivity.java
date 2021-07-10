package com.example.managetournamentapp.view.Tournament.TournamentGroups;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.managetournamentapp.R;
import com.example.managetournamentapp.view.Organizer.OrganizerPage.OrganizerPageActivity;
import com.example.managetournamentapp.view.Player.PlayerPage.PlayerPageActivity;
import com.example.managetournamentapp.view.Tournament.GroupRankings.GroupRankingsActivity;
import com.example.managetournamentapp.view.Tournament.RoundGames.RoundGamesActivity;
import com.example.managetournamentapp.view.User.Browsing.BrowsingActivity;

import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class TournamentGroupsActivity extends AppCompatActivity implements TournamentGroupsView {
    public static final String TOURNAMENT_TITLE_EXTRA = "tournament_title_extra";
    private static final String ROUND_TEAMS_EXTRA = "round_teams_extra" ;
    private static final String SPECIFIC_GROUP_EXTRA = "specific_group_extra" ;
    private static final String PLAYER_USERNAME_EXTRA = "player_username_extra";
    private static final String ORGANIZER_TITLE_EXTRA = "organizer_title_extra";
    private static AlertDialog POPUP_ACTION;
    private TournamentGroupsViewModel viewModel;
    String tournamentTitle;
    ArrayList<Button> groupButtons = new ArrayList<>();
    ImageButton btnHome;

    /**
     * Creates the layout and initializes the activity
     * @param savedInstanceState the Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tournamentTitle = this.getIntent().getStringExtra(TOURNAMENT_TITLE_EXTRA);
        setContentView(R.layout.activity_tournament_groups);

        viewModel = new ViewModelProvider(this).get(TournamentGroupsViewModel.class);
        viewModel.getPresenter().setView(this);
        viewModel.getPresenter().findTournamentInfo(tournamentTitle);

        btnHome = findViewById(R.id.imageButton);
        btnHome.setOnClickListener(v -> viewModel.getPresenter().onHomePage());

        groupButtons.add( findViewById(R.id.group_A_button) );
        groupButtons.add( findViewById(R.id.group_B_button) );
        groupButtons.add( findViewById(R.id.group_C_button) );
        groupButtons.add( findViewById(R.id.group_D_button) );
        groupButtons.add( findViewById(R.id.group_E_button) );
        groupButtons.add( findViewById(R.id.group_F_button) );
        groupButtons.add( findViewById(R.id.group_G_button) );
        groupButtons.add( findViewById(R.id.group_H_button) );

        for (int i=0;i<groupButtons.size();i++) {
            int finalI = i;
            groupButtons.get(i).setOnClickListener(v -> viewModel.getPresenter().onGroup(finalI) );
        }
        viewModel.getPresenter().findAccess();
    }

    /**
     * when the user presses on a group
     * a popup is shown, where he can choose to view the
     * games or the rankings of this group
     * @param index the index of the group that was chosen
     */
    @Override
    public void showPopup(int index){
        POPUP_ACTION = showPopUp(R.layout.group_action_popup, R.id.games_button, R.id.rankings_button, index);
        POPUP_ACTION.show();
    }

    /**
     * create the popup
     * @param layoutId the layout of the popup
     * @param btn1 the first button
     * @param btn2 the second button
     * @param index the index of the group that was chosen
     * @return the AlertDialog that will be shown
     */
    public AlertDialog showPopUp(int layoutId, int btn1, int btn2, int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View customLayout = getLayoutInflater().inflate(layoutId, null);
        builder.setView(customLayout);
        AlertDialog dialog = builder.create();

        Button firstButton = (Button) customLayout.findViewById(btn1);
        Button secondButton = (Button) customLayout.findViewById(btn2);
        firstButton.setOnClickListener(v -> viewModel.getPresenter().onGames(index) );
        secondButton.setOnClickListener(v -> viewModel.getPresenter().onRankings(index) );
        return dialog;
    }

    /**
     * hide the button of a group
     * if this group doesn't exist in the tournament
     * @param groupsNumber the number of groups in the first round
     */
    @Override
    public void changesOfAccess(int groupsNumber) {
        for (int i=groupsNumber;i<groupButtons.size();i++)
            groupButtons.get(i).setVisibility(View.GONE);
    }

    /**
     * show the games of a group
     * @param tournamentTitle the title of the tournament
     * @param roundTeamsNumber the number of the teams in this round
     * @param index the index of the particular group
     */
    @Override
    public void showGroupGames(String tournamentTitle, int roundTeamsNumber, int index) {
        Intent intent = new Intent(TournamentGroupsActivity.this, RoundGamesActivity.class);
        intent.putExtra(TOURNAMENT_TITLE_EXTRA, tournamentTitle);
        intent.putExtra(ROUND_TEAMS_EXTRA, String.valueOf(roundTeamsNumber));
        intent.putExtra(SPECIFIC_GROUP_EXTRA, String.valueOf(index));
        startActivity(intent);
    }

    /**
     * show the rankings of the teams in a group
     * @param tournamentTitle the title of the tournament
     * @param roundTeamsNumber the number of the teams in this round
     * @param index the index of the particular group
     */
    @Override
    public void showGroupRankings(String tournamentTitle, int roundTeamsNumber, int index) {
        Intent intent = new Intent(TournamentGroupsActivity.this, GroupRankingsActivity.class);
        intent.putExtra(TOURNAMENT_TITLE_EXTRA, tournamentTitle);
        intent.putExtra(SPECIFIC_GROUP_EXTRA, String.valueOf(index));
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
