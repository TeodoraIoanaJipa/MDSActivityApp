package com.example.teodora.mdsapplication;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.teodora.mdsapplication.models.AppService;
import com.example.teodora.mdsapplication.models.Team;

import java.util.ArrayList;
import java.util.List;

import java.util.Locale;

public class CardAndTimeActivity extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 120000;

    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private Button doneButton;
    private ImageButton popupBtn;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private AppService appService = AppService.getInstance();


    public static Intent makeIntent(Context applicationContext) {
        Intent intent = new Intent(applicationContext, CardAndTimeActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_and_time);

        mTextViewCountDown = findViewById(R.id.timerTw);

        mButtonReset = findViewById(R.id.resetBtn);
        mButtonStartPause = findViewById(R.id.startBtn);

        doneButton = findViewById(R.id.doneBtn);

        popupBtn = findViewById(R.id.popUpBtn);


        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateCountDownText();
        popupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Pop.makeIntent(getApplicationContext());
                startActivity(intent);
            }
        });



        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer points = getIntent().getIntExtra("NumarPuncte",0);

                AppService appService=AppService.getInstance();
                Team[] echipe = appService.teamsManager.getTeams();
                Integer currentTeam = appService.teamsManager.getCurrentTeam();
                echipe[currentTeam].setLastPosition(echipe[currentTeam].getBoardPosition());
                //avem nevoie si de ultima casuta pe care s-a aflat ca sa putem sa i setam vizibilitatea la false
                echipe[currentTeam].setBoardPosition(echipe[currentTeam].getBoardPosition()+points);
                if(echipe[currentTeam].getBoardPosition()>=47){
//                    Intent intent = new Intent(getApplicationContext(), Congrats.class);
//                    startActivity(intent);
                    finish();
                }
                //adun punctele pe care le-a acumulat echipa curenta ca sa pot sa o deplasez la dreapta
                System.out.println("--------------------DONE");
                finish();
            }
        });

        final List<String> Challenges = new ArrayList<>();
        final List<TextView> challenge = new ArrayList<>(12);
        for(int i=1;i<=6;i++){
            String challengeStr="challenge"+i;
            int resID = getResources().getIdentifier(challengeStr, "id", getPackageName());
            challenge.add((TextView)findViewById(resID));
        }

        new FirebaseDBHelper().getChalleges(new FirebaseDBHelper.DataStatus() {
            @Override
            public void dataIsLoaded(List<String> drawChallenge,List<String> speakChallenge,
                                     List<String> mimeChallenge, List<String> keys) {

                int i=0;
                for(int j=0;j<mimeChallenge.size()-appService.iterations;j++){
                    if(i<challenge.size()){
                        challenge.get(i).setText(mimeChallenge.get(j+appService.iterations));
                        i+=3;
                    }
                }

                i=1;
                for(int j=0;j<speakChallenge.size()-appService.iterations;j++){
                    if(i<challenge.size()){
                        challenge.get(i).setText(speakChallenge.get(j+appService.iterations));
                        i+=3;
                    }
                }

                i=2;
                for(int j=0;j<drawChallenge.size()-appService.iterations;j++){
                    if(i<challenge.size()){
                        challenge.get(i).setText(drawChallenge.get(j+appService.iterations));
                        i+=3;

                    }
                }
                if(appService.iterations<=15)
                    appService.iterations+=1;
                else
                    appService.iterations=0;

            }
        });


    }
    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Start");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();

        mTimerRunning = true;
        mButtonStartPause.setText("pause");
        mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText(){
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormatted);
        if (minutes == 0 && seconds == 0) {
            Intent intent = new Intent(getApplicationContext(), FailedMessage.class);
            startActivity(intent);
            finish();
        }
    }


}
