package com.example.teodora.mdsapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        CardView myCardView = (CardView)findViewById(R.id.CardViewStarts);
        myCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startMenuIntent = new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(startMenuIntent);
            }
        });
    }
}
