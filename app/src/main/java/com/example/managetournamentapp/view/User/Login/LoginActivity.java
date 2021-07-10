package com.example.managetournamentapp.view.User.Login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.managetournamentapp.R;
import com.example.managetournamentapp.view.HomePage.HomePageActivity;
import com.example.managetournamentapp.view.Organizer.OrganizerPage.OrganizerPageActivity;
import com.example.managetournamentapp.view.Player.PlayerPage.PlayerPageActivity;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    private static final String PLAYER_USERNAME_EXTRA = "player_username_extra";
    private static final String ORGANIZER_TITLE_EXTRA = "organizer_title_extra";
    private Button loginBtn;
    LoginViewModel viewModel;

    /**
     * Creates the layout and initializes the activity
     * @param savedInstanceState the Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        viewModel.getPresenter().setView(this);
        loginBtn = (Button) findViewById(R.id.complete_login);
        loginBtn.setOnClickListener(this);
    }

    /**
     * get the contents of the edit text
     * @return the username given
     */
    @Override
    public String getUsername() {
        EditText USERNAME = (EditText) findViewById(R.id.login_input_username);
        return USERNAME.getText().toString();
    }

    /**
     * get the contents of the edit text
     * @return the password given
     */
    @Override
    public String getPassword() {
        EditText PASSWORD = (EditText) findViewById(R.id.login_input_password);
        return PASSWORD.getText().toString();
    }

    /**
     *show a popup on the screen
     * @param view the view of the popup
     * @param msg the message that will be shown
     */
    @Override
    public void showPopUp(LoginView view, String msg) {
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
        if (v.getId() == R.id.complete_login) {
            viewModel.getPresenter().validateCredentials();
        }
    }

    /**
     * start the player page activity
     * @param username the username of the player
     */
    public void startPlayerPage(String username) {
        Toast.makeText(this,
                "LOGGED IN",
                Toast.LENGTH_SHORT)
                .show();
        Intent intent = new Intent(this, PlayerPageActivity.class);
        intent.putExtra(PLAYER_USERNAME_EXTRA, username);
        startActivity(intent);
    }

    /**
     * start the organizer page activity
     * @param title the title of the player
     */
    public void startOrganizerPage(String title) {
        Toast.makeText(this,
                "LOGGED IN",
                Toast.LENGTH_SHORT)
                .show();
        Intent intent = new Intent(this, OrganizerPageActivity.class);
        intent.putExtra(ORGANIZER_TITLE_EXTRA, title );
        startActivity(intent);
    }

    /**
     * what happens when the back button is pressed
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }

}