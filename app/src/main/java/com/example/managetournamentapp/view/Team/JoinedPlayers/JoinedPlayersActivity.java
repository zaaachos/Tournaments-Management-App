package com.example.managetournamentapp.view.Team.JoinedPlayers;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.managetournamentapp.R;
import com.example.managetournamentapp.domain.Player;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;
import com.example.managetournamentapp.view.Organizer.OrganizerPage.OrganizerPageActivity;
import com.example.managetournamentapp.view.Player.PlayerInfo.PlayerInfoActivity;
import com.example.managetournamentapp.view.Player.PlayerPage.PlayerPageActivity;
import com.example.managetournamentapp.view.Team.InvitePlayers.InvitePlayersActivity;
import com.example.managetournamentapp.view.Team.InvitePlayers.fragment.PlayersListFragment;
import com.example.managetournamentapp.view.User.Browsing.BrowsingActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class JoinedPlayersActivity extends AppCompatActivity implements PlayersListFragment.OnListFragmentInteractionListener, View.OnClickListener, JoinedPlayersView {

    JoinedPlayersViewModel viewModel;
    private static final String PLAYER_USERNAME_EXTRA = "player_username_extra";
    private static final String ORGANIZER_TITLE_EXTRA = "organizer_title_extra";
    public static final String TEAM_NAME_EXTRA = "team_name_extra";
    private static boolean removeActionPopup = false;
    private static AlertDialog POPUP_ACTION;
    private static AlertDialog POPUP_DELETION;
    private static Player playerSelected;
    private boolean captain;
    private boolean player;
    private String teamName;
    private FloatingActionButton inviteNewBtn;
    ImageButton btnHome;


    /**
     * Creates the layout and initializes the activity
     * @param savedInstanceState the Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        teamName = this.getIntent().getStringExtra(TEAM_NAME_EXTRA);
        setContentView(R.layout.activity_joined_players);
        viewModel = new ViewModelProvider(this).get(JoinedPlayersViewModel.class);
        viewModel.getPresenter().setView(this);

        viewModel.getPresenter().findPlayers(teamName);
        inviteNewBtn = (FloatingActionButton) findViewById(R.id.invite_new_players_button);
        btnHome = findViewById(R.id.imageButton);
        inviteNewBtn.setOnClickListener(this);
        btnHome.setOnClickListener(v -> viewModel.getPresenter().onHomePage());

        viewModel.getPresenter().findAccess();

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            PlayersListFragment playersListFragment = PlayersListFragment.newInstance(1);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, playersListFragment)
                    .commit();
        }
    }


    /**
     * what happens when the user presses on a player
     * @param item the player
     */
    @Override
    public void onListFragmentInteraction(Player item) {
        playerSelected = item;

        if (!player || item.equals((new MemoryLoggedInUser()).getUser())) {
            startPlayerInfo();
        } else {
            if (captain) {
                displayPopUpAction(R.layout.player_action_popup, "Name: " + item.getName() + "\nSurname: " + item.getSurname(), R.id.remove_player_popup, R.id.account_player_popup);
            } else {
                startPlayerInfo();
            }
        }
    }

    /**
     * get the players that have joined the team
     * @return the ArrayList of players
     */
    @Override
    public ArrayList<Player> getPlayerList() {
        return viewModel.getPresenter().getResults();
    }

    /**
     * what happens when a button is pressed
     * @param v the current view
     */
    @Override
    public void onClick(View v) {
        if (removeActionPopup) {
            if (v.getId() == R.id.no_delete) {
                removeActionPopup = false;
                dismissPopUpDeletion();
                dismissPopUpAction();
            } else if (v.getId() == R.id.yes_delete) {
                viewModel.getPresenter().removePlayer(teamName, playerSelected);
                dismissPopUpDeletion();
                dismissPopUpAction();
                resetPopUps();
                restartActivity();
            }
        }
        if (v.getId() == R.id.remove_player_popup) {
            removeActionPopup = true;
            displayPopUpDeletion(R.layout.player_delete_popup, "Do you really want to delete this player?", R.id.no_delete, R.id.yes_delete);
        }
        if (v.getId() == R.id.account_player_popup) {
            startPlayerInfo();
        }
        if (v.getId() == R.id.invite_new_players_button) {
            startInvitePlayerPage();
        }
    }

    /**
     * show the invite and delete buttons only to the captain
     * @param captain true if the logged in user is the captain of the team
     * @param player true if the player has joined the team
     */
    @Override
    public void changesOfAccess(boolean captain, boolean player) {
        this.captain = captain;
        this.player = player;
        if (!captain) {
            inviteNewBtn.setVisibility(View.GONE);
        }
    }

    /**
     * @param layoutId the layout of the popup
     * @param msg the message of the popup
     * @param btn1 the first button
     * @param btn2 the second button
     * @return the AlertDialog that will be shown
     */
    @Override
    public AlertDialog showPopUp(int layoutId, String msg, int btn1, int btn2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View customLayout = getLayoutInflater().inflate(layoutId, null);
        builder.setView(customLayout);
        AlertDialog dialog = builder.create();

        TextView textMsg = (TextView) customLayout.findViewById(R.id.action_message);
        Button firstButton = (Button) customLayout.findViewById(btn1);
        Button secondButton = (Button) customLayout.findViewById(btn2);
        textMsg.setText(msg);
        firstButton.setOnClickListener(this);
        secondButton.setOnClickListener(this);
        return dialog;
    }

    /**
     * show the info page of the player
     */
    @Override
    public void startPlayerInfo() {
        Intent intent = new Intent(this, PlayerInfoActivity.class);
        intent.putExtra(PLAYER_USERNAME_EXTRA, playerSelected.getCredentials().getUsername());
        startActivity(intent);

    }

    /**
     * show the page where invites are sent
     */
    @Override
    public void startInvitePlayerPage() {
        Intent intent = new Intent(this, InvitePlayersActivity.class);
        intent.putExtra(TEAM_NAME_EXTRA, teamName);
        startActivity(intent);
    }

    /**
     * show the possible actions popup
     * @param layout the layout of the popup
     * @param msg the message of the popup
     * @param btn1 the first button
     * @param btn2 the second button
     */
    @Override
    public void displayPopUpAction(int layout, String msg, int btn1, int btn2) {
        POPUP_ACTION = showPopUp(layout, msg, btn1, btn2);
        POPUP_ACTION.show();
    }

    /**
     * show the deletion popup
     * @param layout the layout of the popup
     * @param msg the message of the popup
     * @param btn1 the first button
     * @param btn2 the second button
     */
    @Override
    public void displayPopUpDeletion(int layout, String msg, int btn1, int btn2) {
        POPUP_DELETION = showPopUp(layout, msg, btn1, btn2);
        POPUP_DELETION.show();
    }

    /**
     * close the possible actions popup
     */
    @Override
    public void dismissPopUpAction() {
        POPUP_ACTION.dismiss();
    }

    /**
     * close the deletion popup
     */
    @Override
    public void dismissPopUpDeletion() {
        POPUP_DELETION.dismiss();
    }

    /**
     * reset the popups of this activity
     */
    @Override
    public void resetPopUps() {
        POPUP_DELETION = null;
        POPUP_ACTION = null;
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

    /**
     * refresh the current activity
     */
    @Override
    public void restartActivity() {
        this.recreate();
    }

    /**
     * show a toast on the screen
     * @param txt the message of the toast
     */
    @Override
    public void showToast(String txt) {
        Toast.makeText(this,txt, Toast.LENGTH_SHORT).show();
    }
}