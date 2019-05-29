package com.example.teodora.mdsapplication;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FirebaseDBHelper {

    private DatabaseReference RootReference;
    private DatabaseReference ChildReference;
    private List<String> drawChallenges = new ArrayList<>();
    private List<String> speakChallenges = new ArrayList<>();
    private List<String> mimeChallenges = new ArrayList<>();

    private List<String> echipaLeader = new ArrayList<>();
    private List<String> punctajLeader = new ArrayList<>();

    public interface DataStatus{
        void dataIsLoaded(List<String>drawChallenge,List<String> speakChallenge,
                          List<String> mimeChallenge, List<String> echipaLeade, List<String> punctajLeade, List<String> keys);
    }


    public void getChalleges(final DataStatus dataStatus, final String name){
        RootReference = FirebaseDatabase.getInstance().getReference();
        ChildReference = RootReference.child("Challenges");
        DatabaseReference DrawReference = ChildReference.child("Desene");
        DatabaseReference SpeakReference = ChildReference.child("Speak");
        DatabaseReference MimeReference = ChildReference.child("Mima");

        DatabaseReference EchipaReference = ChildReference.child("Echipa");
        DatabaseReference PunctajReference = ChildReference.child("PunctajEchipa");

        ChildReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Random rand = new Random();
                int n = rand.nextInt(50);
                ChildReference.child("Echipa").child("key"+n).setValue(name);
                ChildReference.child("PunctajEchipa").child("key"+n);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        EchipaReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //drawChallenges.clear();
                List<String> keys =new ArrayList<>();
                for (DataSnapshot keyNode: dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    echipaLeader.add(keyNode.getValue(String.class));
                    System.out.println(echipaLeader.get(0));
                }
                dataStatus.dataIsLoaded(drawChallenges,speakChallenges,mimeChallenges, echipaLeader, punctajLeader,keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        PunctajReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //drawChallenges.clear();
                List<String> keys =new ArrayList<>();
                for (DataSnapshot keyNode: dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    punctajLeader.add(keyNode.getValue(String.class));
                }
                dataStatus.dataIsLoaded(drawChallenges,speakChallenges,mimeChallenges, echipaLeader, punctajLeader,keys);
                System.out.println("--------------------");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DrawReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //drawChallenges.clear();
                List<String> keys =new ArrayList<>();
                for (DataSnapshot keyNode: dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    drawChallenges.add(keyNode.getValue(String.class));
                }
                dataStatus.dataIsLoaded(drawChallenges,speakChallenges,mimeChallenges, echipaLeader, punctajLeader,keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        SpeakReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //drawChallenges.clear();
                List<String> keys =new ArrayList<>();
                for (DataSnapshot keyNode: dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    speakChallenges.add(keyNode.getValue(String.class));
                }
                dataStatus.dataIsLoaded(drawChallenges,speakChallenges,mimeChallenges, echipaLeader, punctajLeader,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        MimeReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                List<String> keys =new ArrayList<>();
                for (DataSnapshot keyNode: dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    mimeChallenges.add(keyNode.getValue(String.class));
                }
                dataStatus.dataIsLoaded(drawChallenges,speakChallenges,mimeChallenges, echipaLeader, punctajLeader,keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
