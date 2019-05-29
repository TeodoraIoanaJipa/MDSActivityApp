package com.example.teodora.mdsapplication.newgame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import android.widget.ThemedSpinnerAdapter;
import com.example.teodora.mdsapplication.CardAndTimeActivity;
import com.example.teodora.mdsapplication.Pop;
import com.example.teodora.mdsapplication.R;
import com.example.teodora.mdsapplication.leaderboard.LeaderboardActivity;
import com.example.teodora.mdsapplication.models.*;

import javax.xml.transform.stream.StreamSource;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

import static android.content.ContentValues.TAG;

public class MapActivity extends AppCompatActivity {
    static final int NR_OF_SQUARES = 47;
    static final int MILLIS_FOR_STEOP_DELAY = 500;
    private CardView cardView3, cardView4, cardView5;
    private AppService appService = AppService.getInstance();
    private TextView currTeam;
    private TextView currMember;
    private ImageView pawnMascot;
    private BoardManager rectangManager;
    static boolean gameOver = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        importElements();
        rectangManager = new BoardManager(this);
        rectangManager.initialDraw(appService.getMapSituation().getPawnModel());
        loadMap();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateBoard();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appService.resetGame();
    }

    private void loadMap() {
        MapSituation situation = appService.getMapSituation();

        currTeam.setText(situation.getTeam());
        currMember.setText(situation.getMember());
        pawnMascot.setImageResource(situation.getPawnMascotRez());

    }

    void updateBoard() {
        rectangManager.setModifiers(appService.getMapSituation());
        rectangManager.updateBoard();
        AppService.getInstance().nextTeam();
        loadMap();

        if(gameOver) {
            pawnMascot.setImageResource(Pawns.Gray.getDefaultDrawableID());
            View.OnClickListener empty =  (v) -> {};
            cardView3.setOnClickListener(empty);
            cardView4.setOnClickListener(empty);
            cardView5.setOnClickListener(empty);

        }
    }

    private void importElements() {
        currTeam = findViewById(R.id.textViewCurrentTeam);
        currMember = findViewById(R.id.textViewCurrentMember);
        pawnMascot = findViewById(R.id.teamMascotImg);
        cardView3 = findViewById(R.id.Card3);
        cardView4 = findViewById(R.id.Card4);
        cardView5 = findViewById(R.id.Card5);


        cardView3.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CardAndTimeActivity.class);
            intent.putExtra("NumarPuncte", 3);
            startActivity(intent);
        });
        cardView4.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CardAndTimeActivity.class);
            intent.putExtra("NumarPuncte", 4);
            startActivity(intent);
        });
        cardView5.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CardAndTimeActivity.class);
            intent.putExtra("NumarPuncte", 5);
            startActivity(intent);
        });
//
//        cardView3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AppService.getInstance().nextTeam();
//                updateBoard();
//            }
//        });
//
//        cardView4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AppService.getInstance().getMapSituation().rewardTeam(
//                        AppService.getInstance().teamsManager.getCurrentTeam(),
//                        3
//                );
//                updateBoard();
//            }
//        });
//
//        cardView5.setOnClickListener((v) -> rectangManager.clearOld());

    }
}


class BoardManager{
    private ArrayList<BoardRectangle> rectangles;

    private ArrayList<Integer> positions;
    private ArrayList<Integer> models;
    private int indToChange;
    private int pozToChange;

    BoardManager(Activity context) {
        positions = new ArrayList<>();
        models = new ArrayList<>();

        indToChange = pozToChange = 0;

        rectangles = new ArrayList<>();

        for (int ind = 0; ind < MapActivity.NR_OF_SQUARES; ind++) {
            BoardRectangle newReact = new BoardRectangle(context, ind);
            rectangles.add(newReact);
        }
    }

    void initialDraw(ArrayList<Integer> models) {
        this.models = models;
        BoardRectangle first = rectangles.get(0);
        for (int ind = 0; ind < models.size(); ind++) {
            first.pushPawn(models.get(ind));
        }
    }

    void setModifiers(MapSituation situation) {
        positions = situation.getPositions();
        indToChange = situation.getIndToChange();
        pozToChange = situation.getPozToChange();
    }

    void updateBoard() {
        if(pozToChange == 0) return;
        clearOld();
        int newPoz = positions.get(indToChange) + pozToChange;
        if(newPoz >= MapActivity.NR_OF_SQUARES) {
            MapActivity.gameOver = true;
            newPoz = MapActivity.NR_OF_SQUARES - 1;
        }
        for (int ind = 0; ind < positions.size(); ind++) {
            if(positions.get(ind) == newPoz)
                positions.set(ind, positions.get(ind) - 1);
        }
        positions.set(indToChange, newPoz);
        drawNew();
        export();
    }

    void export() {
        MapSituation situation = AppService.getInstance().getMapSituation();
        situation.setPositions(positions);
        situation.rewardTeam(AppService.getInstance().teamsManager.getCurrentTeam(), 0);
    }

    void clearOld() {
        for (BoardRectangle rec:rectangles) {
            rec.clear();
        }
    }

    void drawNew() {
        for (int ind = 0; ind < positions.size(); ind++) {
            rectangles.get(positions.get(ind)).pushPawn(models.get(ind));
        }
    }




}

class BoardRectangle {
    ImageView[] images = new ImageView[3];
    Integer[] imageRez = new Integer[3];


    BoardRectangle(Activity context, int elementOrdinal) {
        String sayElem = "element";
        String sayPawn = "pawn";

        String rootName = sayElem + elementOrdinal;
        for (int ind = 0; ind < 3; ind++) {
            int nr = ind + 1;
            String theName = rootName + sayPawn + nr;
            images[ind] = context.findViewById(
                    context.getResources().getIdentifier(theName, "id",
                            context.getPackageName())
            );

            imageRez[ind] = 0;
        }
        drawImages();
    }

    void clear() {
        Arrays.fill(imageRez, 0);
        drawImages();
    }

    private void drawImages() {
        for (int ind = 0; ind < images.length; ind++) {
            images[ind].setImageResource(
                    imageRez[ind]
            );
        }
    }

    void pushPawn(int pawnRez) {
        imageRez[2] = imageRez[1];
        imageRez[1] = imageRez[0];
        imageRez[0] = pawnRez;
        drawImages();
    }

//    int popPawn() {
//        for (int ind = 2; ind >= 0; ind--)
//            if(imageRez[ind] != 0) {
//                int toReturn = imageRez[ind];
//                imageRez[ind] = 0;
//                drawImages();
//                return toReturn;
//            }
//        return 0;
//    }
//
//    int popPawn(int rezID) {
//        if(imageRez[0] == rezID) {
//            imageRez[0] = imageRez[1];
//            imageRez[1] = imageRez[2];
//            imageRez[2] = 0;
//        } else if(imageRez[1] == rezID) {
//            imageRez[1] = imageRez[2];
//            imageRez[2] = 0;
//        } else if(imageRez[2] == rezID) {
//            imageRez[2] = 0;
//        } else return 0;
//
//        drawImages();
//        return rezID;
//    }



}

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_map);
//
//        System.out.println("----------------Am intrat in onCreate");
//        appContext = getApplicationContext();
//
//        initializeCards();
//        textViewCurrentTeam = findViewById(R.id.textViewCurrentTeam);
//        try {
//            startPlaying();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        System.out.println("---------------Am intrat in onResume");
//        continueGame();
//    }
//
//
//    private void initializeCards(){
//        System.out.println("----------Am initializat cardurile");
//        CardView card5 = findViewById(R.id.Card5);
//        card5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), CardAndTimeActivity.class);
//                intent.putExtra("NumarPuncte", 5);
//                startActivity(intent);
//            }
//        });
//
//        CardView card4 = (CardView) findViewById(R.id.Card4);
//        card4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = CardAndTimeActivity.makeIntent(getApplicationContext());
//                intent.putExtra("NumarPuncte", 4);
//                startActivity(intent);
//
//            }
//        });
//
//        CardView card3 = (CardView) findViewById(R.id.Card3);
//        card3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = CardAndTimeActivity.makeIntent(getApplicationContext());
//                intent.putExtra("NumarPuncte", 3);
//                startActivity(intent);
//            }
//        });
//
//        //I got the cardViews from the Map so I can use them in the game
//        cards = new CardView[48];
//        //element47 is the finish box you have to be on to win
//        for (int i = 0; i < 47; i++) {
//            String cardID = "element" + i;
//            int resID = getResources().getIdentifier(cardID, "id", getPackageName());
//            cards[i] = ((CardView) findViewById(resID));
//        }
//        teamsManager = appService.teamsManager;
//        allTeams = teamsManager.getTeams();
//    }
//    private boolean  colorAtPositionIsTaken(Integer position, Integer color){
//        for(Team team:allTeams){
//            if(team.getBoardPosition()==position && team.getPawnColor()==color)
//                return true;
//        }
//        return false;
//    }
//
//    private void setPawnVisibility(CardView card, int position, Integer color, Boolean visibility) {
//        System.out.println("------------------Am setat vizibilitatea cardului "+position);
//        String imageResource = "element" + position + "pawn";
//
//        if(color!=4){
//            imageResource+=color;
//        }else{
//            if(!colorAtPositionIsTaken(position,1))
//                imageResource+=1;
//            else
//            if(!colorAtPositionIsTaken(position,2))
//                imageResource+=2;
//            else
//                imageResource+=3;
//        }
//
//        int resID = card.getResources().getIdentifier(imageResource, "id", getPackageName());
//
//        if(color==4)
//            card.findViewById(resID).setBackgroundResource(R.drawable.pionas_galben);
//
//        if (visibility == true)
//            card.findViewById(resID).setVisibility(View.VISIBLE);
//        else
//            card.findViewById(resID).setVisibility(View.INVISIBLE);
//    }
//
//
//
//    private void initializeGame(){
//        for (int i = 0; i < 47; i++) {
//            setPawnVisibility(cards[i], i, 1, false);
//            setPawnVisibility(cards[i], i, 2, false);
//            setPawnVisibility(cards[i], i, 3, false);
//        }
//    }
//
//    private void continueGame() {
//        int i;
//        System.out.println("------------------Sunt in Continuegame");
//
//        while (finished() == false) {
//            i = teamsManager.getCurrentTeam();
//            System.out.println("Echipa curenta : "+ i);
//            currentTeam = allTeams[i];
//            textViewCurrentTeam.setText("Turn:" + currentTeam.getTeamName());
//
//            Integer pawnColor = allTeams[i].getPawnColor();
//            Integer currentPosition = allTeams[i].getBoardPosition();
//            Integer lastBoardPosition = allTeams[i].getLastPosition();
//            if(currentPosition <=47) {
//                if (currentPosition != lastBoardPosition)
//                    setPawnVisibility(cards[lastBoardPosition], lastBoardPosition, pawnColor, false);
//                setPawnVisibility(cards[currentPosition], currentPosition, pawnColor, true);
//
//            if (i + 1 < teamsManager.getTotalTeams())
//                teamsManager.setCurrentTeam(teamsManager.getCurrentTeam() + 1);
//            else
//                teamsManager.setCurrentTeam(0);
//
//            }
//            return;
//        }
//        Intent intent = new Intent(getApplicationContext(), Congrats.class);
//        startActivity(intent);
//
//    }
//
//    public void startPlaying() throws InterruptedException {
//
//        initializeGame();
//    }
//
//    private boolean finished(){
//        for(Team oneOfTheTeams : allTeams){
//            if(oneOfTheTeams.getBoardPosition()>=47) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//}
