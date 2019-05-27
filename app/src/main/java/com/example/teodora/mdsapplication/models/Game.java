package com.example.teodora.mdsapplication.models;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.example.teodora.mdsapplication.R;
import com.example.teodora.mdsapplication.leaderboard.LeaderboardActivity;
import com.example.teodora.mdsapplication.newgame.Congrats;
import com.example.teodora.mdsapplication.newgame.MapActivity;

public class Game extends MapActivity{

    private TeamsManager teamsManager;
    private Team[] allTeams;
    private TextView textViewCurrentTeam;
    private Team currentTeam;
    private CardView[] cards;
    private Context appContext;

    public Game(TeamsManager teamsManager, TextView textViewCurrentTeam, CardView[] cards, Context context) throws InterruptedException {
        this.textViewCurrentTeam = textViewCurrentTeam;
        this.teamsManager = teamsManager;
        this.cards = cards;
        allTeams = teamsManager.getTeams();
        appContext=context;
        startPlaying();
    }

    private void setPawnVisibility(CardView card, int position, String color, Boolean visibility) {

        String imageResource = "element" + position + "pawn";

        if (color.equals("red")) {
            imageResource += 1;
        } else if (color.equals("blue")) {
            imageResource += 2;
        } else if (color.equals("green")) {
            imageResource += 3;
        } else
            imageResource += 1;

        int resID = card.getResources().getIdentifier(imageResource, "id", "com.example.teodora.mdsapplication");

        if (visibility == true)
            card.findViewById(resID).setVisibility(View.VISIBLE);
        else
            card.findViewById(resID).setVisibility(View.INVISIBLE);
    }


    public void startPlaying() throws InterruptedException {
        for (int i = 0; i < 22; i++) {
            setPawnVisibility(cards[i], i, "red", false);
            setPawnVisibility(cards[i], i, "green", false);
            setPawnVisibility(cards[i], i, "blue", false);
        }

        System.out.println("Nr echipe: " + teamsManager.getTotalTeams());

        int i=0;
        while(finished()==false){
            if(i>=0 && i<teamsManager.getTotalTeams()) {
                currentTeam = allTeams[i];
                textViewCurrentTeam.setText("Turn:" + currentTeam.getTeamName());

                String pawnColor = allTeams[i].pawnColorString();
                Integer currentPosition = allTeams[i].getBoardPosition();
                setPawnVisibility(cards[currentPosition], currentPosition, pawnColor, true);
                return;
            }else
                return;

        }
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
