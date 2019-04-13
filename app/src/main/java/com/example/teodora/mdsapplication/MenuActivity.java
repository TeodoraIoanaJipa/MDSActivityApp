package com.example.teodora.mdsapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.example.teodora.mdsapplication.leaderboard.LeaderboardActivity;
import com.example.teodora.mdsapplication.newgame.NewGameActivity;

public class MenuActivity extends AppCompatActivity {

    private CardView new_game_btn;
    private CardView leaderboardCardView;
    private Button testTimerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        new_game_btn = (CardView) findViewById(R.id.new_game_card_view);
        new_game_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewGameActivity.class);
                startActivity(intent);
            }
        });

        leaderboardCardView = (CardView) findViewById(R.id.leaderboardcardView);
        leaderboardCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LeaderboardActivity.class);
                startActivity(intent);
            }
        });

        testTimerBtn = (Button) findViewById(R.id.testTimerBtn);

        testTimerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CardAndTimeActivity.class);
                startActivity(intent);
            }
        });
    }
}
