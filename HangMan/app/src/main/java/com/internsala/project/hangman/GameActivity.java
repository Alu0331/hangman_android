package com.internsala.project.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    // indices 0 - 20 are animals
    // indices 21-38 are fruits
    String[] strings = {"buffalo","cow","hen","cat","dog","elephant","fox","goose","horse","jaguar","kangaroo","lion","mouse","mongoose","ox","rat","rabbit","rhinoceros","sheep","tiger","yak",
            "apple","banana","cherry","dates","grapes", "gooseberry", "guava","jackfruit","kiwi","lemon","mango","orange","olive","peach","papaya","pomegranate",
            "strawberry","watermelon"};
    //here we declare objects of each class
    Button ok;
    EditText input_val;
    ImageView game_image_view;
    LinearLayout my_output_layout;
    //no of wrong attempts and successful attempts are intialized to 0
    int wrong_attempts = 0, successful_attempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //every component taken before is now initialized
        //(Button) is class
        //findviewbyid - find the relative class wrt ID

        ok = (Button)findViewById(R.id.input_button);
        input_val = (EditText)findViewById(R.id.input_value);
        game_image_view = (ImageView)findViewById(R.id.game_images);
        my_output_layout = (LinearLayout)findViewById(R.id.output_layout);

        //after this, we must generate random indices  from 0 to len of strings array
        // hence we use Math.random() to generate some decimal no between 0 - 1
        // and then it is multiplied with length of strings
        //then this numbers is floored and it is stored in one variable
        //i.e extracting the corresponding string with generated random index

        int index = (int)(Math.random()*strings.length);
        final String selected_string = strings[index];
        final ArrayList<String> used_characters = new ArrayList<>();
        // an array list is created since we do not know the size of the array

        //Now we need to dynamically add the textviews(*) according to the length of the string

        final TextView[] myTextViews = new TextView[selected_string.length()];
        for(int i=0;i< selected_string.length();i++)
        {
            final TextView rowTextView = new TextView(this); //creation of new textview
            rowTextView.setText("*"); //property is set for rowTwxtView
            rowTextView.setTextColor(0xFF303F9F); //black colour is set
            rowTextView.setTextSize(20); //size 20
            //now add this textview to linear layout
            my_output_layout.addView(rowTextView);
            myTextViews[i] = rowTextView; //saving the references of textview for later

        }
        ok.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                try{
                    String entered = input_val.getText().toString(); // input character entered by the user //
                    input_val.setText(""); // removing the character from the display //
                    if(entered.matches("")) // when the user press the OK button without enetering any character //
                    {
                        Toast.makeText(GameActivity.this,"Enter any character and then press OK",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(used_characters.contains(entered))
                    {
                        Toast.makeText(GameActivity.this,"USED",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    used_characters.add(entered);

                    int matched = 0;

                    for(int i=0;i<selected_string.length();i++)
                    {
                        if(String.valueOf(selected_string.charAt(i)).equals(entered))
                        {
                            myTextViews[i].setText(entered);
                            matched = 1;
                            successful_attempts+=1;
                        }
                    }

                    if(matched!=1){
                        wrong_attempts+=1;
                        switch(wrong_attempts){
                            case 1 : game_image_view.setImageResource(R.drawable.first);
                                break;
                            case 2 : game_image_view.setImageResource(R.drawable.second);
                                break;
                            case 3 : game_image_view.setImageResource(R.drawable.third);
                                break;
                            case 4 : game_image_view.setImageResource(R.drawable.fourth);
                                break;
                            case 5 : game_image_view.setImageResource(R.drawable.fifth);
                                break;
                            case 6 : game_image_view.setImageResource(R.drawable.sixth);
                                break;
                        }
                    }

                    if(successful_attempts == selected_string.length()){
                        Intent i = new Intent(GameActivity.this,EndActivity.class);
                        i.putExtra("game_report","Congratulations You have \n\t Won");
                        startActivity(i);
                    }
                    else if(wrong_attempts == 6)
                    {
                        Intent i = new Intent(GameActivity.this,EndActivity.class);
                        i.putExtra("game_report","Oops you have lost\n\t" + selected_string);
                        startActivity(i);
                    }

                }
                catch (Exception e){
                    // do something //
                    Toast.makeText(GameActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}