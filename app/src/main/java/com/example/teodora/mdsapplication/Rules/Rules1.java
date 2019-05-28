package com.example.teodora.mdsapplication.Rules;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.example.teodora.mdsapplication.R;

public class Rules1 extends Activity {
    private ImageButton closeBtn;
    private ImageButton rightArrowBtn;
    private ImageButton leftArrowBtn;
    private int pageNumber;
    private int maxPageNumber;
    private ImageView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rules);
        closeBtn = findViewById(R.id.rulesCloseBtn);
        text = findViewById(R.id.textImage);
        pageNumber = 1;
        maxPageNumber = 6;
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String firstPage = "page1";

        int imgSrc = getResources().getIdentifier(
                firstPage, "drawable", getPackageName()
        );
        text.setImageResource(imgSrc);

        text.setImageResource(imgSrc);
        rightArrowBtn = findViewById(R.id.rightArrowBtn);
        rightArrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pageNumber < maxPageNumber)
                {
                    String page = "page";
                     page = page + String.valueOf(pageNumber+1);
                     int imgSrc = getResources().getIdentifier(
                             page, "drawable", getPackageName()
                     );
                     text.setImageResource(imgSrc);
                     pageNumber++;
                }
            }
        });


        leftArrowBtn = findViewById(R.id.leftArrowBtn);
        leftArrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pageNumber > 1)
                {
                    String page = "page";
                    page = page + String.valueOf(pageNumber-1);
                    int imgSrc = getResources().getIdentifier(
                            page, "drawable", getPackageName()
                    );
                    text.setImageResource(imgSrc);
                    pageNumber--;
                }
            }
        });
    }
}
