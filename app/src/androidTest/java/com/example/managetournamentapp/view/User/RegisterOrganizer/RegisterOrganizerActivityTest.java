package com.example.managetournamentapp.view.User.RegisterOrganizer;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;

import com.example.managetournamentapp.R;
import com.example.managetournamentapp.view.Organizer.OrganizerPage.OrganizerPageActivity;
import com.example.managetournamentapp.view.User.RegisterOrganizer.RegisterOrganizerActivity;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class RegisterOrganizerActivityTest {

    @Rule
    public IntentsTestRule<RegisterOrganizerActivity> rule = new IntentsTestRule<>(RegisterOrganizerActivity.class);

    private RegisterOrganizerActivityObject activityObject;

    @Before
    public void setup(){

        activityObject = new RegisterOrganizerActivityObject();
    }


    @Test
    public void basicFill(){
//        onView(withId(R.id.saveOrganizerBtn)).check(matches( withText("SAVE")));

        activityObject
                .fillField("username","user123")
                .fillField("password","12345")
                .fillField("name","nick")
                .fillField("surname","quick")
                .fillField("phone","0000000000")
                .pressBack()
                .fillField("email","aaaa@aaaa")
                .pressBack()
                .fillField("birthdate","11/11/2000")
                .pressBack()
                .fillField("title","quick")
                .pressBack()
                .pressSave();

        intended(hasComponent(OrganizerPageActivity.class.getName()));

    }
}
