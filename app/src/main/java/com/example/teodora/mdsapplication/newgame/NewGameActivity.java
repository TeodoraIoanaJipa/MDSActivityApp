package com.example.teodora.mdsapplication.newgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.teodora.mdsapplication.R;

import java.util.ArrayList;


public class NewGameActivity extends Activity {
    private Button addButton;
    private EditText textField;
    private ListView listToShow;
    ArrayList<String> membersNames;
    ArrayAdapter<String> membersAdapater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_game);

        listToShow = (ListView) findViewById(R.id.membersListView);
        addButton = (Button) findViewById(R.id.memberAddBtn);
        textField = (EditText) findViewById(R.id.memberNameInp);

            //declaring an adaptor for the members list view
        membersNames = new ArrayList<String>();
        membersAdapater = new ArrayAdapter<String>(NewGameActivity.this,
                android.R.layout.simple_list_item_1, membersNames);
        listToShow.setAdapter(membersAdapater);

            //set on click to add member into list view
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMemberInList();
            }
        });
    }

    private void addMemberInList() {
        String memberName = textField.getText().toString();
        if(memberName.length() > 0) {
            membersNames.add(0,memberName);
            membersAdapater.notifyDataSetChanged();
            textField.setText("");
        }
    }
}
