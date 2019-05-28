package com.example.teodora.mdsapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

public class FailedMessage  extends CardAndTimeActivity {


    public static Intent makeIntent(Context applicationContext) {
        Intent intent = new Intent(applicationContext, CardAndTimeActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_over);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                finish();
            }
        }, 1500);
    }


}
