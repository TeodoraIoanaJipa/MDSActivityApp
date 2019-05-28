package com.example.teodora.mdsapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import android.widget.ImageView;
import com.example.teodora.mdsapplication.Rules.Rules1;
import com.example.teodora.mdsapplication.newgame.TeamsNumber;

public class MenuActivity extends AppCompatActivity {

    private CardView teamNumbersCardView;
    private CardView rulesCardView;
    private CardView leaderboardCardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        teamNumbersCardView = (CardView) findViewById(R.id.newGameCardView);
        teamNumbersCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TeamsNumber.class);
                startActivity(intent);
            }
        });

        rulesCardView = (CardView) findViewById(R.id.settingsCardView) ;
        rulesCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Rules1.class);
                startActivity(intent);
            }
        });


        leaderboardCardView = (CardView) findViewById(R.id.leaderBoardCardView);
        leaderboardCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Congrats.class);
//                startActivity(intent);

            }
        });

    }
}
