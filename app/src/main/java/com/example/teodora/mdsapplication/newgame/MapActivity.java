package com.example.teodora.mdsapplication.newgame;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.example.teodora.mdsapplication.CardAndTimeActivity;
import com.example.teodora.mdsapplication.Pop;
import com.example.teodora.mdsapplication.R;
import com.example.teodora.mdsapplication.leaderboard.LeaderboardActivity;
import com.example.teodora.mdsapplication.models.AppService;
import com.example.teodora.mdsapplication.models.Team;
import com.example.teodora.mdsapplication.models.TeamsManager;

public class MapActivity extends AppCompatActivity {
    private AppService appService = AppService.getInstance();
    private TeamsManager teamsManager;
    private Team[] allTeams;
    private TextView textViewCurrentTeam;
    private Team currentTeam;
    private CardView[] cards;
    private Context appContext;

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("------------Am intrat in onResume");
        continueGame();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        System.out.println("Am intrat in onCreate");
        appContext = getApplicationContext();


            //iar cu Extra punctele pe care le-a acumulat echipa curenta ca sa pot sa o deplasez la dreapta

            initializeCards();

            textViewCurrentTeam = findViewById(R.id.textViewCurrentTeam);

            try {
                startPlaying();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        
    }

    private void initializeCards(){
        System.out.println("----------Am initializat cardurile");
        CardView card5 = findViewById(R.id.Card5);
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CardAndTimeActivity.class);
                intent.putExtra("NumarPuncte", 5);
                startActivity(intent);
            }
        });

        CardView card4 = (CardView) findViewById(R.id.Card4);
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = CardAndTimeActivity.makeIntent(getApplicationContext());
                intent.putExtra("NumarPuncte", 4);
                startActivity(intent);

            }
        });

        CardView card3 = (CardView) findViewById(R.id.Card3);
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = CardAndTimeActivity.makeIntent(getApplicationContext());
                intent.putExtra("NumarPuncte", 3);
                startActivity(intent);
            }
        });

        //I got the cardViews from the Map so I can use them in the game
        cards = new CardView[48];
        //element47 is the finish box you have to be on to win
        for (int i = 0; i < 47; i++) {
            String cardID = "element" + i;
            int resID = getResources().getIdentifier(cardID, "id", getPackageName());
            cards[i] = ((CardView) findViewById(resID));
        }
        teamsManager = appService.teamsManager;
        allTeams = teamsManager.getTeams();
    }

    private void setPawnVisibility(CardView card, int position, String color, Boolean visibility) {
        System.out.println("------------------Am setat vizibilitatea cardului "+position);
        String imageResource = "element" + position + "pawn";

        switch (color) {
            case "red":
                imageResource += 1;
                break;
            case "blue":
                imageResource += 2;
                break;
            case "green":
                imageResource += 3;
                break;
            default:
                imageResource += 1;
                break;
        }

        int resID = card.getResources().getIdentifier(imageResource, "id", getPackageName());

        if (visibility == true)
            card.findViewById(resID).setVisibility(View.VISIBLE);
        else
            card.findViewById(resID).setVisibility(View.INVISIBLE);
    }



    private void initializeGame(){
        for (int i = 0; i < 26; i++) {
            setPawnVisibility(cards[i], i, "red", false);
            setPawnVisibility(cards[i], i, "green", false);
            setPawnVisibility(cards[i], i, "blue", false);
        }
    }

    public void continueGame(){
        int i;
        System.out.println("Sunt in Continuegame");

        while(finished()==false){
            i=teamsManager.getCurrentTeam();
           currentTeam = allTeams[i];
           textViewCurrentTeam.setText("Turn:" + currentTeam.getTeamName());

           String pawnColor = allTeams[i].pawnColorString();
           Integer currentPosition = allTeams[i].getBoardPosition();
           Integer lastBoardPosition = allTeams[i].getLastPosition();
           setPawnVisibility(cards[lastBoardPosition],lastBoardPosition,pawnColor, false);
           setPawnVisibility(cards[currentPosition], currentPosition, pawnColor, true);


           if(i+1<teamsManager.getTotalTeams())
               teamsManager.setCurrentTeam(teamsManager.getCurrentTeam()+1);
           else
               teamsManager.setCurrentTeam(0);
           return;
        }
    }

    public void startPlaying() throws InterruptedException {
        System.out.println("Sunt in startPlaying");

        initializeGame();

        int i=0;

        continueGame();
    }

    private boolean finished(){
        for(Team oneOfTheTeams : allTeams){
            if(oneOfTheTeams.getBoardPosition()==47) {
                Intent intent = new Intent(appContext, Congrats.class);
                startActivity(intent);
                Intent intent2 = new Intent(appContext, LeaderboardActivity.class);
                //cod leaderboard
                return true;
            }
        }
        return false;
    }

}
