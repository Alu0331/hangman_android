package com.internsala.project.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onPress(View view)   //when you press the start button
    {
        Intent i = new Intent(this,GameActivity.class); // intent is used when an activity is to be switched
        startActivity(i); //to start an activity
    }


}
