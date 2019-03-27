package com.example.teodora.mdsapplication.newgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.teodora.mdsapplication.R;

import java.util.ArrayList;


public class NewGameActivity extends Activity {
    private Button add_button;
    private EditText text_field;
    private ListView list_to_show;
    ArrayList<String> members_names;
    ArrayAdapter<String> members_adapater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_game);
        
        list_to_show = (ListView) findViewById(R.id.list_view_new_game);
        add_button = (Button) findViewById(R.id.add_button);
        text_field = (EditText) findViewById(R.id.text_field);

        members_names = new ArrayList<String>();
        members_adapater = new ArrayAdapter<String>(NewGameActivity.this,
                android.R.layout.simple_list_item_1, members_names);

        list_to_show.setAdapter(members_adapater);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addElemInList();
            }
        });
    }

    private void addElemInList () {
        String member_name = text_field.getText().toString();
        if(member_name.length() > 0) {
            members_names.add(member_name);
            members_adapater.notifyDataSetChanged();
            text_field.setText("");
        }
    }
}
