package com.example.teodora.mdsapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import android.widget.Button;
import com.example.teodora.mdsapplication.leaderboard.LeaderboardActivity;
import com.example.teodora.mdsapplication.newgame.TeamsNumber;

public class MenuActivity extends AppCompatActivity {

    private CardView teamNumbersCardView;
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

        leaderboardCardView = (CardView) findViewById(R.id.leaderBoardCardView);
        leaderboardCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LeaderboardActivity.class);
                startActivity(intent);

            }
        });

    }
}
