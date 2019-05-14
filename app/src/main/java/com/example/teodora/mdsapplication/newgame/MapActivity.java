package com.example.teodora.mdsapplication.newgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.example.teodora.mdsapplication.CardAndTimeActivity;
import com.example.teodora.mdsapplication.R;
import com.example.teodora.mdsapplication.models.AppService;
import com.example.teodora.mdsapplication.models.Game;
import com.example.teodora.mdsapplication.models.TeamsManager;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        CardView card5 = (CardView) findViewById(R.id.Card5);
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CardAndTimeActivity.class);
                startActivity(intent);
            }
        });

        CardView card4 = (CardView) findViewById(R.id.Card4);
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = CardAndTimeActivity.makeIntent(getApplicationContext());
                startActivity(intent);

            }
        });

        CardView card3 = (CardView) findViewById(R.id.Card3);
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = CardAndTimeActivity.makeIntent(getApplicationContext());


                startActivity(intent);
            }
        });

        //I got the cardViews from the Map so I can use the in the game
        CardView[] cards= new CardView[48];
        //element47 is the finish box you have to be on to win
        for(int i=0; i<47; i++) {
            String cardID = "element" + i;
            int resID = getResources().getIdentifier(cardID, "id", getPackageName());
            cards[i] = ((CardView) findViewById(resID));
        }

        AppService appService = AppService.getInstance();

        TextView displayCurrentTeam = findViewById(R.id.textViewCurrentTeam);

        Game game = new Game(appService.teamsManager,displayCurrentTeam,cards,getApplicationContext());
    }
}
