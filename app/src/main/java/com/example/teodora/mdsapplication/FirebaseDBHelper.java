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

public class FirebaseDBHelper {

    private DatabaseReference RootReference;
    private DatabaseReference ChildReference;
    private List<String> drawChallenges = new ArrayList<>();
    private List<String> speakChallenges = new ArrayList<>();
    private List<String> mimeChallenges = new ArrayList<>();

    public interface DataStatus{
        void dataIsLoaded(List<String>drawChallenge, List<String> keys);
    }

    public void getChalleges(final DataStatus dataStatus){
        RootReference = FirebaseDatabase.getInstance().getReference();
        ChildReference = RootReference.child("Challenges");
        DatabaseReference DrawReference = ChildReference.child("Draw");
        DatabaseReference SpeakReference = ChildReference.child("Speak");
        DatabaseReference MimeReference = ChildReference.child("Mima");

        DrawReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //drawChallenges.clear();
                List<String> keys =new ArrayList<>();
                for (DataSnapshot keyNode: dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    drawChallenges.add(keyNode.getValue(String.class));
                }
                dataStatus.dataIsLoaded(drawChallenges,keys);
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
                dataStatus.dataIsLoaded(speakChallenges,keys);
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
                dataStatus.dataIsLoaded(mimeChallenges,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
