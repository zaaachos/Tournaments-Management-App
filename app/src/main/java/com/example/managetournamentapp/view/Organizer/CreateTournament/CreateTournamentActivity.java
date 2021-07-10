package com.example.managetournamentapp.view.Organizer.CreateTournament;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.managetournamentapp.R;
import com.example.managetournamentapp.view.Organizer.OrganizerPage.OrganizerPageActivity;
import com.example.managetournamentapp.view.Organizer.SetDates.SetDatesActivity;

import java.util.ArrayList;


/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class CreateTournamentActivity extends AppCompatActivity implements CreateTournamentView {
    private static final String BASIC_INFO_EXTRA = "basic_info_extra";
    public static final String TOURNAMENT_TITLE_EXTRA = "tournament_title_extra";
    public static final String ORGANIZER_TITLE_EXTRA = "organizer_title_extra";
    private CreateTournamentViewModel viewModel;
    private Button saveBtn;
    String tournamentTitle;
    private Spinner sportTypeSpinner;
    private Spinner ageDivisionSpinner;
    private Spinner teamsNumberSpinner;
    ImageButton btnHome;

    /**
     * Creates the layout and initializes the activity
     * @param savedInstanceState the Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tournament);

        tournamentTitle = this.getIntent().getStringExtra(TOURNAMENT_TITLE_EXTRA);

        if(tournamentTitle != null){
            ActionBar actionBar = getSupportActionBar();
            if(actionBar != null)
            {
                actionBar.setTitle("Tournament Edit");
            }
        }

        viewModel = new ViewModelProvider(this).get(CreateTournamentViewModel.class);
        viewModel.getPresenter().setView(this);

        sportTypeSpinner = findViewById(R.id.sport_spinner);
        setSportTypeSpinner(viewModel.getPresenter().getSportTypes());

       teamsNumberSpinner =  findViewById(R.id.teams_number_spinner);
       setTeamsNumberSpinner( viewModel.getPresenter().getTeamNumbers() );

       ageDivisionSpinner =  findViewById(R.id.age_spinner);
       setAgeDivisionSpinner( viewModel.getPresenter().getAgeDivisions() );

        viewModel.getPresenter().showPreviousInfo(tournamentTitle);

        saveBtn = findViewById(R.id.saveTeambtn);
        btnHome = findViewById(R.id.imageButton);
        saveBtn.setOnClickListener(v -> viewModel.getPresenter().onSaveTournament());
        btnHome.setOnClickListener(v -> viewModel.getPresenter().onHomePage());
    }

    /**
     * Goes back to organizer profile
     * if the user edits account and
     * presses the save button
     * @param organizerTitle organizer title that is passed as an extra to the next activity
     */
    @Override
    public void startSaveTournament(String organizerTitle) {
        Intent intent = new Intent(CreateTournamentActivity.this, OrganizerPageActivity.class);
        intent.putExtra(ORGANIZER_TITLE_EXTRA, organizerTitle);
        startActivity(intent);
    }

    /**
     * If user saves the initial info of the new tournament
     * @param basicInfo arraylist containing the basic info of the newly created tournament, passed as an extra
     */
    @Override
    public void startSetDates(ArrayList<String> basicInfo) {
        Intent intent = new Intent(CreateTournamentActivity.this, SetDatesActivity.class);
        intent.putExtra(BASIC_INFO_EXTRA, basicInfo);
        startActivity(intent);
    }

    /**
     * Returns the organizer title of the related field in the current layout
     * @return the organizer title found in the related field
     */
    @Override
    public String getTournamentTitle() {
        return ((EditText) findViewById(R.id.tournament_title)).getText().toString();

    }

    /**
     * Returns the tournament description of the related field in the current layout
     * @return the tournament description found in the related field
     */
    @Override
    public String getDescription() {
        return ((EditText) findViewById(R.id.tournament_description)).getText().toString();

    }

    /**
     * Returns the tournament location of the related field in the current layout
     * @return the organizer title found in the related field
     */
    @Override
    public String getLocation() {
        return ((EditText) findViewById(R.id.tournament_location)).getText().toString();
    }

    /**
     * Returns the tournament start date of the related field in the current layout
     * @return the tournament start date found in the related field
     */
    @Override
    public String getStartDate() {
        return ((EditText) findViewById(R.id.tournament_start_date)).getText().toString();

    }

    /**
     * Returns the tournament finish date of the related field in the current layout
     * @return the tournament finish date found in the related field
     */
    @Override
    public String getFinishDate() {
        return ((EditText) findViewById(R.id.tournament_finish_Date)).getText().toString();

    }

    /**
     * Returns the tournament age division of the related field in the current layout
     * @return the tournament age division found in the related field
     */
    @Override
    public int getAgeDivision() {
        return ageDivisionSpinner.getSelectedItemPosition();
    }

    /**
     * Returns the tournament teams number of the related field in the current layout
     * @return the tournament teams number found in the related field
     */
    @Override
    public int getTeamsNumber() {
        return teamsNumberSpinner.getSelectedItemPosition();

    }

    /**
     * Returns the tournament sport type of the related field in the current layout
     * @return the tournament sport type found in the related field
     */
    @Override
    public int getSportType() {
        return sportTypeSpinner.getSelectedItemPosition();
    }

    /**
     * Method used
     * to set the tournament title field of activity's layout
     * screen to the tournament title that user has typed in
     * @param title title of tournament to be set
     */
    @Override
    public void setTournamentTitle(String title) {
        ((EditText) findViewById(R.id.tournament_title)).setText(title);

    }

    /**
     * Method used
     * to set the tournament description field of activity's layout
     * screen to the tournament description that user has typed in
     * @param description description of tournament to be set
     */
    @Override
    public void setDescription(String description) {
        ((EditText) findViewById(R.id.tournament_description)).setText(description);

    }

    /**
     * Method used
     * to set the tournament location field of activity's layout
     * screen to the tournament location that user has typed in
     * @param location location of tournament to be set
     */
    @Override
    public void setLocation(String location) {
        ((EditText) findViewById(R.id.tournament_location)).setText(location);
    }

    /**
     * Method used
     * to set the tournament start date field of activity's layout
     * screen to the tournament start date that user has typed in
     * @param date start date of tournament to be set
     */
    @Override
    public void setStartDate(String date) {
        ((EditText) findViewById(R.id.tournament_start_date)).setText(date);
    }

    /**
     * Method used
     * to set the tournament finish date of activity's layout
     * screen to the tournament finish date that user has typed in
     * @param date  finish date of tournament to be set
     */
    @Override
    public void setFinishDate(String date) {
        ((EditText) findViewById(R.id.tournament_finish_Date)).setText(date);
    }

    /**
     * Method used
     * to set the tournament age division of activity's layout
     * screen to the tournament age division that user has typed in
     * @param position position of spinner that must be set
     */
    @Override
    public void setAgeDivision(int position) {
        ageDivisionSpinner.setSelection(position);
    }

    /**
     * Method used
     * to set the tournament teams number of activity's layout
     * screen to the tournament teams number that user has typed in
     * @param position position of spinner that must be set
     */
    @Override
    public void setTeamsNumber(int position) {
        teamsNumberSpinner.setSelection(position);
    }

    /**
     * Method used
     * to set the tournament sport type of activity's layout
     * screen to the tournament sport type that user has typed in
     * @param position position of spinner that must be set
     */
    @Override
    public void setSportType(int position) {
        sportTypeSpinner.setSelection(position);

    }

    /**
     * Locks the fields so they can't be changed
     */
    @Override
    public void lockPrevious() {
        findViewById(R.id.tournament_start_date).setEnabled(false);
        findViewById(R.id.tournament_finish_Date).setEnabled(false);
        teamsNumberSpinner.setEnabled(false);
    }

    /**
     * Shows a pop up message
     * @param view of the current layout
     * @param msg message that the pop up shows
     */
    @Override
    public void showPopUp(CreateTournamentView view, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View customLayout = getLayoutInflater().inflate(R.layout.wrong_input_popup, null);
        builder.setView(customLayout);
        AlertDialog dialog = builder.create();
        Button OKbtn = (Button) customLayout.findViewById(R.id.OK_popup);
        TextView errorMsg = (TextView) customLayout.findViewById(R.id.error_messsage);      // display message we want.
        errorMsg.setText(msg);
        OKbtn.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    /**
     * Method used
     * to set the tournament sport type of activity's layout
     * screen to the current tournament sport type
     * @param list the content of the spinner to be set
     */
    public void setSportTypeSpinner(ArrayList<String> list){
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sportTypeSpinner.setAdapter(adapter);
    }

    /**
     * Method used
     * to set the tournament age division of activity's layout
     * screen to the current tournament age division
     * @param list the content of the spinner to be set
     */
    public void setAgeDivisionSpinner(ArrayList<String> list){
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ageDivisionSpinner.setAdapter(adapter);
    }

    /**
     * Method used
     * to set the tournament teams number of activity's layout
     * screen to the current tournament teams number
     * @param list the content of the spinner to be set
     */
    public void setTeamsNumberSpinner(ArrayList<String> list){
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamsNumberSpinner.setAdapter(adapter);
    }

    /**
     * Goes back to organizer profile
     * @param name organizer name that is passed as an extra in the organizer page activity
     */
    @Override
    public void backToHomePage(String name) {

        Intent intent = new Intent (this, OrganizerPageActivity.class);
        intent.putExtra(ORGANIZER_TITLE_EXTRA, name);
        startActivity(intent);

    }
}
