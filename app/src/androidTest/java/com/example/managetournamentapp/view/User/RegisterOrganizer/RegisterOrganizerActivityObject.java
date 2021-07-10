package com.example.managetournamentapp.view.User.RegisterOrganizer;

import android.content.Context;

import androidx.test.espresso.action.ViewActions;

import com.example.managetournamentapp.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class RegisterOrganizerActivityObject {

    Context context;

    public RegisterOrganizerActivityObject fillField(String hint, String value){
        onView(withHint(hint)).perform(clearText());
        onView(withHint(hint)).perform(typeText(value));
        return this;
    }

    public RegisterOrganizerActivityObject pressSave(){
        onView(withId(R.id.saveOrganizerBtn)).perform(click());
        return this;
    }

    public RegisterOrganizerActivityObject pressBack(){
        onView(withId(R.id.saveOrganizerBtn)).perform( ViewActions.pressBack());
        return this;
    }

}
