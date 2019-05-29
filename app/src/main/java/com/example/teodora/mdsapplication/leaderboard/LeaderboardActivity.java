package com.example.teodora.mdsapplication.leaderboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;
import com.example.teodora.mdsapplication.FirebaseDBHelper;
import com.example.teodora.mdsapplication.R;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        final List<String> Challenges = new ArrayList<>();
        final List<TextView> challenge = new ArrayList<>(12);
        for(int i=1;i<6;i++){
            String challengeStr="myc"+i;
            int resID = getResources().getIdentifier(challengeStr, "id", getPackageName());
            challenge.add((TextView)findViewById(resID));
        }

        new FirebaseDBHelper().getChalleges(new FirebaseDBHelper.DataStatus() {
            @Override
            public void dataIsLoaded(List<String> drawChallenge,List<String> speakChallenge,
                                     List<String> mimeChallenge, List<String> echipeLeade,
                                     List<String> punctajLeade, List<String> keys) {
                int i=0;
                for(String echipa : echipeLeade){
                    if(i<challenge.size()){
                        challenge.get(i).setText("Locul "+ (i+1) + ": " + echipa);
                        i++;
                    }
                }
                i=0;
                for(String punctaj : punctajLeade){
                    if(i<challenge.size()){
                        challenge.get(i).setText(challenge.get(i).getText()+punctaj+" ");
                        i++;
                    }
                }

            }
        });
    }



}