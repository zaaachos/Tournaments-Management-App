package com.example.managetournamentapp.view.User.RegisterOrganizer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.managetournamentapp.R;
import com.example.managetournamentapp.view.Organizer.OrganizerPage.OrganizerPageActivity;


/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class RegisterOrganizerActivity extends AppCompatActivity implements RegisterOrganizerView, View.OnClickListener {

    RegisterOrganizerViewModel viewModel;
    public static final String ORGANIZER_TITLE = "organizer_title";
    private static final String ORGANIZER_TITLE_EXTRA = "organizer_title_extra";
    private Button saveBtn;
    String organizerTitle;

    /**
     * Creates the layout and initializes the activity
     * @param savedInstanceState the Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_organizer);

        organizerTitle = this.getIntent().getStringExtra(ORGANIZER_TITLE);
        if(organizerTitle != null){
            ActionBar actionBar = getSupportActionBar();
            if(actionBar != null)
            {
                actionBar.setTitle("Organizer Edit");
            }
        }

        viewModel = new ViewModelProvider(this).get(RegisterOrganizerViewModel.class);
        viewModel.getPresenter().setView(this);
        viewModel.getPresenter().showPreviousInfo(organizerTitle);
        saveBtn = findViewById(R.id.saveOrganizerBtn);
        saveBtn.setOnClickListener(this);

    }

    /**
     *show a popup on the screen
     * @param view the view of the popup
     * @param msg the message that will be shown
     */
    public void showPopUp(RegisterOrganizerView view, String msg) {
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
     * what happens when a button is pressed
     * @param v the current view
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.saveOrganizerBtn) {
            viewModel.getPresenter().handleOrganizerData();
        }
    }

    /**
     * start the organizer page activity
     * @param title the title of the player
     */
    public void startOrganizerPage(String title) {
        Intent intent = new Intent(this, OrganizerPageActivity.class);
        intent.putExtra(ORGANIZER_TITLE_EXTRA, title );
        startActivity(intent);
    }

    /**
     * some fields can't be changed
     */
    @Override
    public void lockFields() {
        findViewById(R.id.birthdate).setEnabled(false);
    }

    /**
     * get the contents of the edit text
     * @return the given username
     */
    @Override
    public String getUsername() {
        EditText USERNAME = (EditText) findViewById(R.id.username);
        return USERNAME.getText().toString();
    }

    /**
     * get the contents of the edit text
     * @return the given password
     */
    @Override
    public String getPassword() {
        EditText PASSWORD = (EditText) findViewById(R.id.password);
        return PASSWORD.getText().toString();
    }

    /**
     * get the contents of the edit text
     * @return the given name
     */
    @Override
    public String getName() {
        EditText NAME = (EditText) findViewById(R.id.name);
        return NAME.getText().toString();
    }

    /**
     * get the contents of the edit text
     * @return the given surname
     */
    @Override
    public String getSurname() {
        EditText SURNAME = (EditText) findViewById(R.id.surname);
        return SURNAME.getText().toString();
    }

    /**
     * get the contents of the edit text
     * @return the name given
     */
    @Override
    public String getPhoneNumber() {
        EditText PHONE = (EditText) findViewById(R.id.phone);
        return PHONE.getText().toString();
    }

    /**
     * get the contents of the edit text
     * @return the given email
     */
    @Override
    public String getEmail() {
        EditText EMAIL = (EditText) findViewById(R.id.email);
        return EMAIL.getText().toString();
    }

    /**
     * get the contents of the edit text
     * @return the given birth date
     */
    @Override
    public String getBirthDate() {
        EditText BIRTHDATE = (EditText) findViewById(R.id.birthdate);
        return BIRTHDATE.getText().toString();
    }

    /**
     * get the contents of the edit text
     * @return the given title
     */
    @Override
    public String getOrganizerTitle() {
        EditText TITLE = (EditText) findViewById(R.id.organizer_title);
        return TITLE.getText().toString();
    }

    /**
     * set the contents in the username edit text
     * @param username the new username
     */
    @Override
    public void setUsername(String username) {
        EditText USERNAME = (EditText) findViewById(R.id.username);
        USERNAME.setText(username);
    }

    /**
     * set the contents in the password edit text
     * @param password the new password
     */
    @Override
    public void setPassword(String password) {
        EditText PASSWORD = (EditText) findViewById(R.id.password);
        PASSWORD.setText(password);
    }

    /**
     * set the contents in the name edit text
     * @param name the new name
     */
    @Override
    public void setName(String name) {
        EditText NAME = (EditText) findViewById(R.id.name);
        NAME.setText(name);
    }

    /**
     * set the contents in the surname edit text
     * @param surname the new surname
     */
    @Override
    public void setSurname(String surname) {
        EditText SURNAME = (EditText) findViewById(R.id.surname);
        SURNAME.setText(surname);
    }

    /**
     * set the contents in the phone number edit text
     * @param phoneNumber the new phone number
     */
    @Override
    public void setPhoneNumber(String phoneNumber) {
        EditText PHONE = (EditText) findViewById(R.id.phone);
        PHONE.setText(phoneNumber);
    }

    /**
     * set the contents in the email edit text
     * @param email the new email
     */
    @Override
    public void setEmail(String email) {
        EditText EMAIL = (EditText) findViewById(R.id.email);
        EMAIL.setText(email);
    }

    /**
     * set the contents in the birth date edit text
     * @param birthdate the new birth date
     */
    @Override
    public void setBirthdate(String birthdate) {
        EditText BIRTHDATE = (EditText) findViewById(R.id.birthdate);
        BIRTHDATE.setText(birthdate);
    }


    /**
     * set the contents in the title edit text
     * @param title the new title
     */
    @Override
    public void setTitle(String title) {
        EditText TITLE = (EditText) findViewById(R.id.organizer_title);
        TITLE.setText(title);
    }


}