package com.example.teodora.mdsapplication.newgame;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.teodora.mdsapplication.R;

import java.io.BufferedOutputStream;
import java.util.ArrayList;


public class NewGameActivity extends Activity {
    private static final String TAG = "NewGameActivity";
    EditText teamName;
    PawnButtons pawnButtons;
    TeamMembersInput teamMembersInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_game);

        teamName = findViewById(R.id.teamNameInp);
        pawnButtons = new PawnButtons(this);
        teamMembersInput = new TeamMembersInput(this);

        teamName.clearFocus();
        Log.d(TAG, "onCreate: Finished Creations");
    }
}

/*
 Enum that stores the order number of each pawn color,
    the drawable model ID and the view model layout ID
    set in the xml file
 */
enum Pawns {
    Gray(0, R.drawable.grey_pawn, R.color.black, 0),
    Red(1, R.drawable.red_pawn, R.drawable.clicked_red_pawn, R.id.pawnSelectBtn1),
    Yellow(2, R.drawable.yellow_pawn, R.drawable.clicked_yellow_pawn, R.id.pawnSelectBtn2),
    Green(3, R.drawable.green_pawn, R.drawable.clicked_green_pawn, R.id.pawnSelectBtn3),
    Blue(4, R.drawable.blue_pawn, R.drawable.clicked_blue_pawn, R.id.pawnSelectBtn4);

    int identifier;
    int defaultDrawableID;
    int clickedDrawableID;
    int layoutResourceID;

    Pawns(int identifier, int defaultDrawableID, int clickedDrawableID, int layoutResourceID) {
        this.identifier = identifier;
        this.defaultDrawableID = defaultDrawableID;
        this.clickedDrawableID = clickedDrawableID;
        this.layoutResourceID = layoutResourceID;

    }

    public int getLayoutResourceID() {
        return layoutResourceID;
    }

    public int getDefaultDrawableID(){
        return defaultDrawableID;
    }

    public int getClickedDrawableID() {
        return clickedDrawableID;
    }

    public int getID() {
        return identifier;
    }

    static public int size() {
        return  4;
    }
}

/**
 *
 */
class PawnButtons {
    Activity context;
    private static final String TAG = "PawnButtons";
    static private int nrOfPawns;
        // keeps track of the available pawns for the player to choose from
    static private Boolean available[];
    static {
        // made all the pawns available. Will probably make a method out of this later
        nrOfPawns = 4;
        available = new Boolean[nrOfPawns];
        for (int index = 0; index < available.length; index++) {
            available[index] = Boolean.TRUE;
        }
    }
        // references to the view models
    private Button pawnBtn[];
        // active and atLeastOneActive keep track of the clicked pawns
    private int active;

    PawnButtons(Activity context) {
        this.context = context;

        pawnBtn = new Button[nrOfPawns];
        active = -1;

        for (int index = 0; index < nrOfPawns; index++) {
            pawnBtn[index] = this.context.findViewById (Pawns.values()[index + 1].getLayoutResourceID());

            if(available[index] == Boolean.TRUE) {
                pawnBtn[index].setBackgroundResource(Pawns.values()[index + 1].getDefaultDrawableID());

            } else {
                pawnBtn[index].setBackgroundResource(Pawns.Gray.getDefaultDrawableID());
                pawnBtn[index].setClickable(Boolean.FALSE);
            }
            
        }
        setOnClicks();
        
    }

    void activateBtn(int index) {
        pawnBtn[index].setBackgroundResource(Pawns.values()[index + 1].getClickedDrawableID());
        active = index;

    }

    void deactivateBtn(int index) {
        if(index >= 0) {
            pawnBtn[index].setBackgroundResource(Pawns.values()[index + 1].getDefaultDrawableID());
            if(active == index) active = -1;
        }
    }

    void toggleBtn(int index) {
        if(active == index) deactivateBtn(active);
        else {
            deactivateBtn(active);
            activateBtn(index);
        }

    }

    void setOnClicks() {
        pawnBtn[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleBtn(0);
            }
        });

        pawnBtn[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleBtn(1);
            }
        });

        pawnBtn[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleBtn(2);
            }
        });

        pawnBtn[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleBtn(3);
            }
        });


    }

}

class TeamMembersInput {


    private Button addButton;
    private EditText textField;
    private ListView listToShow;
    private ArrayList<String> membersNames;
    private ArrayAdapter<String> membersAdapater;
    private Activity context;

    TeamMembersInput(Activity context) {
    this.context = context;
        //import models by ID
    listToShow = this.context.findViewById(R.id.membersListView);
    addButton = this.context.findViewById(R.id.memberAddBtn);
    textField = this.context.findViewById(R.id.memberNameInp);

        //declaring an adaptor for the members list view
    membersNames = new ArrayList<>();
    membersAdapater = new ArrayAdapter<>(context,
        android.R.layout.simple_list_item_1, membersNames);
    listToShow.setAdapter(membersAdapater);

        //set on click to add member into list view
    addButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addMemberInList();
        }
    });

    textField.clearFocus();
    }

    /** Method that takes a string from the input member text field
     * and adds it in the membersNames ArrayList while also notifying
     * the adaptor of the change
     */
    private void addMemberInList() {
        String memberName = textField.getText().toString();
        if(memberName.length() > 0) {
            membersNames.add(0,memberName);
            membersAdapater.notifyDataSetChanged();
            textField.setText("");
        }
    }

}
