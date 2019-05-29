package com.example.teodora.mdsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.teodora.mdsapplication.newgame.MapActivity;

public class FinishOrReplay extends MenuActivity {
    private Button replayBtn;
    private TextView winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish_or_replay);
        String team = getIntent().getStringExtra("Winner Name");
        winner = findViewById(R.id.winnerTW);
        winner.setText(team);
        replayBtn = findViewById(R.id.playAgainBtn);


        replayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
                finish();
                onDestroy();
            }
        });


    }
}

