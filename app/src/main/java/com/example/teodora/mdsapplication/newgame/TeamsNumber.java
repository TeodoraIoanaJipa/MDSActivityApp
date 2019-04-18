package com.example.teodora.mdsapplication.newgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.teodora.mdsapplication.R;
import com.example.teodora.mdsapplication.models.AppService;

public class TeamsNumber extends Activity {

    CardView twoTeams;
    CardView threeTeams;
    CardView fourTeams;
    AppService appService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_teams_number);

        twoTeams = findViewById(R.id.twoTeamsCardView);
        threeTeams = findViewById(R.id.threeTeamsCardView);
        fourTeams = findViewById(R.id.fourTeamsCardView);

        appService = AppService.getInstance();
        setOnClicks();
    }

    void passTeamsNumber(int howManyTeams) {
        appService.teamsManager.setTotalTeams(howManyTeams);
    }

    void setOnClicks() {
        twoTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passTeamsNumber(2);
                startNewGame();
            }
        });

        threeTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passTeamsNumber(3);
                startNewGame();

            }
        });

        fourTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passTeamsNumber(4);
                startNewGame();
            }
        });
    }

    void startNewGame() {
        Intent intent = new Intent(getApplicationContext(), NewGameActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("CURRENT_TEAM", String.valueOf(1));
        startActivity(intent);
        finish();
    }
}
