package com.example.managetournamentapp.view.Tournament.TournamentInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.managetournamentapp.R;
import com.example.managetournamentapp.view.Organizer.CreateTournament.CreateTournamentActivity;
import com.example.managetournamentapp.view.Organizer.OrganizerPage.OrganizerPageActivity;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class TournamentInfoActivity extends AppCompatActivity implements TournamentInfoView {
    private TournamentInfoViewModel viewModel;
    public static final String TOURNAMENT_TITLE_EXTRA = "tournament_title_extra";
    private static final String ORGANIZER_TITLE_EXTRA = "organizer_title_extra";
    String tournamentName;
    Button btnEditTournament;
    Button btnDeleteTournament;
    private AlertDialog POPUP_ACTION;

    /**
     * Creates the layout and initializes the activity
     * @param savedInstanceState the Instance state
     */
    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_info);
        tournamentName = this.getIntent().getStringExtra(TOURNAMENT_TITLE_EXTRA);
        viewModel = new ViewModelProvider(this).get(TournamentInfoViewModel.class);
        viewModel.getPresenter().setView(this);
        viewModel.getPresenter().findTournamentInfo(tournamentName);

        btnEditTournament = findViewById(R.id.edit_button);
        btnDeleteTournament = findViewById(R.id.delete_button);

        btnEditTournament.setOnClickListener(v -> viewModel.getPresenter().onEditTournament());
        btnDeleteTournament.setOnClickListener(v -> viewModel.getPresenter().onDeleteTournament());

       viewModel.getPresenter().findAccess();
    }

    /**
     * set the contents in the teams number edit text
     * @param teamsNumber the number of teams
     */
    @Override
    public void setTeamsNumber(String teamsNumber) {
        ((TextView)findViewById(R.id.text_teams_number)).setText(teamsNumber);
    }

    /**
     * set the contents in the location edit text
     * @param location the new location
     */
    @Override
    public void setLocation(String location) {
        ((TextView)findViewById(R.id.text_location)).setText(location);
    }

    /**
     * get the contents of the location edit text
     * @return the given finish date
     */
    @Override
    public void setStartDate(String startDate) {
        ((TextView)findViewById(R.id.text_start_date)).setText(startDate);
    }

    /**
     * get the contents of the finish date edit text
     * @return the given finish date
     */
    @Override
    public void setFinishDate(String finishDate) {
        ((TextView)findViewById(R.id.text_finish_date)).setText(finishDate);
    }

    /**
     * set the contents in the sport edit text
     * @param sportType the new sport
     */
    @Override
    public void setsportType(String sportType) {
        ((TextView)findViewById(R.id.text_sport_type)).setText(sportType);
    }

    /**
     * set the contents in the title edit text
     * @param title the new title
     */
    @Override
    public void setTitle(String title) {
        ((TextView)findViewById(R.id.text_title)).setText(title);
    }

    /**
     * set the contents in the age division edit text
     * @param ageDivision the new age division
     */
    @Override
    public void setAgeDivision(String ageDivision) {
        ((TextView)findViewById(R.id.text_age_division)).setText(ageDivision);
    }

    /**
     * set the contents in the description edit text
     * @param description the new description
     */
    @Override
    public void setDescription(String description) {
        ((TextView)findViewById(R.id.text_description)).setText(description);
    }

    /**
     * when the organizer chooses to edit this tournament
     * the create tournament activity is started
     */
    @Override
    public void startEditTournament() {
        Intent intent = new Intent(TournamentInfoActivity.this, CreateTournamentActivity.class);
        intent.putExtra(TOURNAMENT_TITLE_EXTRA,tournamentName);
        startActivity(intent);
    }

    /**
     * when the organizer presses
     * "yes" on the confirmation
     */
    @Override
    public void yesDeleteTournament(String title) {
        Intent intent = new Intent(TournamentInfoActivity.this, OrganizerPageActivity.class);
        showToast("SUCCESSFULLY DELETED TOURNAMENT.");
        intent.putExtra(ORGANIZER_TITLE_EXTRA,title);
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
     * when the organizer presses
     * "no" on the confirmation
     */
    @Override
    public void noDeleteTournament() {
        POPUP_ACTION.dismiss();
    }

    /**
     * hide the edit and delete buttons
     * if the user viewing the page is not
     * the organizer of this tournament
     */
    @Override
    public void changesOfAccess() {
        btnEditTournament.setVisibility(View.GONE);
        btnDeleteTournament.setVisibility(View.GONE);
    }

    /**
     * ask for a confirmation before
     * deleting a tournament
     */
    @Override
    public void deleteConfirmation() {
        POPUP_ACTION = showPopUp(R.layout.tournament_delete_popup, "Do you really want to delete this Tournament?", R.id.no_delete, R.id.yes_delete);

        POPUP_ACTION.show();
    }

    /**
     * show a popup on the screen
     * @param layoutId the layout of the popup
     * @param msg the message of the popup
     * @param btn1 the first button
     * @param btn2 the second button
     * @return an AlertDialog that will be shown on the screen
     */
    public AlertDialog showPopUp(int layoutId, String msg, int btn1, int btn2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View customLayout = getLayoutInflater().inflate(layoutId, null);

        builder.setView(customLayout);
        AlertDialog dialog = builder.create();

        TextView textMsg = (TextView) customLayout.findViewById(R.id.action_message);

        //no delete
        Button firstButton = (Button) customLayout.findViewById(btn1);
        //yes delete
        Button secondButton = (Button) customLayout.findViewById(btn2);
        textMsg.setText(msg);
        firstButton.setOnClickListener(v->viewModel.getPresenter().onNoDeleteTournament());
        secondButton.setOnClickListener(v->viewModel.getPresenter().onYesDeleteTournament());
        return dialog;
    }

}
