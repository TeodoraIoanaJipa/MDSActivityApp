package com.example.teodora.mdsapplication;

<<<<<<< HEAD
import android.os.Bundle;

class Pop  extends CardAndTimeActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
=======
import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class Pop  extends CardAndTimeActivity {

    public static Intent makeIntent(Context applicationContext) {
        Intent intent = new Intent(applicationContext, Pop.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popwindoww);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);


        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8), (int)(height*0.8));
>>>>>>> 433233abf713c1b4f7f541b2fe2e55e6bf52de71
    }
}
