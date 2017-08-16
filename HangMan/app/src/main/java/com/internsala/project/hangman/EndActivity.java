package com.internsala.project.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.internsala.project.hangman.MainActivity;

public class EndActivity extends AppCompatActivity {

    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        message = (TextView)findViewById(R.id.message);
        Bundle gameData = getIntent().getExtras();
        message.setText(gameData.getString("game_report"));
    }

    public void goto_main_activity(View view){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
