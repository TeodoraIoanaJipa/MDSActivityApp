package com.example.teodora.mdsapplication.newgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.example.teodora.mdsapplication.R;

import com.example.teodora.mdsapplication.models.AppService;
import com.example.teodora.mdsapplication.models.Pawns;
import com.example.teodora.mdsapplication.models.Team;
import com.example.teodora.mdsapplication.leaderboard.LeaderboardActivity;


import java.util.ArrayList;
import java.util.Arrays;

/**
        The difference between index and ordinal refering to a
    list or array is that index starts with 0 and
    ordinal starts with 1.
        Both terms as used because they make more sense in
    certain contexts
 */

public class NewGameActivity extends Activity {

    private static final String TAG = "NewGameActivity";
    TopView topView;
    EditText teamName;
    PawnButtons pawnButtons;
    TeamMembersInput teamMembersInput;
    AppService appService;
    int currentTeamOrdinal;
    Team currentTeam;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_game);

        currentTeamOrdinal = 1;
        variableInitialisation();
        PawnButtons.resetPawns();
        importTeamInfos();
        setBackAndNext();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveTeamInfos();
        PawnButtons.resetPawns();
    }

    void variableInitialisation() {

        appService = AppService.getInstance();

        currentTeam = appService.teamsManager.getTeamOrdinal(currentTeamOrdinal);
        // auxiliar classes and teamName String
        topView = new TopView(this, currentTeamOrdinal);
        teamName = findViewById(R.id.teamNameInp);
        teamName.setText("");
        pawnButtons = new PawnButtons(this);
        teamMembersInput = new TeamMembersInput(this);

    }

    void setBackAndNext() {
        topView.setOnClicks(
                    // backBtm
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        saveTeamInfos();
                        if(currentTeamOrdinal == 1) {
                            finish();
                            onDestroy();
                        }
                        else {
                            currentTeamOrdinal -= 1;
                            resetFields();
                        }

                    }
                },


                    // nextBtn
                //TODO: add warnings when not all the fileds are completed
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        saveTeamInfos();
                        if(currentTeamOrdinal == appService.teamsManager.getTotalTeams()){
                            Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            onDestroy();
                            finish();
                        } else {
                            currentTeamOrdinal += 1;
                            resetFields();
                        }

                    }
                }
        );
    }



    void saveTeamInfos() {
        currentTeam.setTeamName(String.valueOf(teamName.getText()));
        System.out.println("----------------Selected pawn: "+pawnButtons.getSelectedPawn());
        currentTeam.setPawnColor(pawnButtons.getSelectedPawn());
        currentTeam.setTeamMembers(teamMembersInput.getMembersList());
    }

    void importTeamInfos() {
        topView.setWhichTeam(currentTeamOrdinal);
        teamName.setText(currentTeam.getTeamName());
        pawnButtons.setImportedPawn(currentTeam.getPawnColor());
        teamMembersInput.importMemberList(currentTeam.getTeamMembers());
    }

    void resetFields() {
        variableInitialisation();
        importTeamInfos();

    }
}

/**
 *  TopView class - responsable for the top buttons (back and next)
 * and the text that displays the current team to fill its data.
 *  Back and Next buttons have the switch to the proper NewGameActivity Activity,
 * this meaning that as you press Next you will get a new instance of it
 * for the proper team and after all the teams have been completed you go
 * to the next state of the application.
 *  If you press Back you will be able to see the data that previous teams
 *  have completed for themselves giving them the possibility to modify.
 */
class TopView {
    Activity context;
    Button backBtn;
    Button nextBtn;
    TextView whichTeam;
    int teamOrdinal;

    TopView(Activity context, final int teamOrdinal) {
        this.context = context;
        this.teamOrdinal = teamOrdinal;
        backBtn = context.findViewById(R.id.goBackBtn);
        nextBtn = context.findViewById(R.id.goFurtherBtn);
        whichTeam = context.findViewById(R.id.currentTeamTxt);
    }

    public void setWhichTeam(int teamNumber) {
        String teamToPrint = "Echipa " + teamNumber + ":";
        whichTeam.setText(teamToPrint);
    }

    public void setOnClicks (View.OnClickListener backBtn, View.OnClickListener nextBtn) {
        this.backBtn.setOnClickListener(backBtn);
        this.nextBtn.setOnClickListener(nextBtn);
    }



}


// <<<<<<< master
// /*
//  Enum that stores the order number of each pawn color,
//     the drawable model ID and the view model layout ID
//     set in the xml file
//  */
// enum Pawns {
//     Gray(0, R.drawable.grey_pawn, R.color.black, 0),
//     Red(1, R.drawable.red_pawn, R.drawable.clicked_red_pawn, R.id.pawnSelectBtn1),
//     Blue(2, R.drawable.blue_pawn, R.drawable.clicked_blue_pawn, R.id.pawnSelectBtn4),
//     Green(3, R.drawable.green_pawn, R.drawable.clicked_green_pawn, R.id.pawnSelectBtn3),
//     Yellow(4, R.drawable.yellow_pawn, R.drawable.clicked_yellow_pawn, R.id.pawnSelectBtn2);

//     int identifier;
//     int defaultDrawableID;
//     int clickedDrawableID;
//     int layoutResourceID;

//     Pawns(int identifier, int defaultDrawableID, int clickedDrawableID, int layoutResourceID) {
//         this.identifier = identifier;
//         this.defaultDrawableID = defaultDrawableID;
//         this.clickedDrawableID = clickedDrawableID;
//         this.layoutResourceID = layoutResourceID;

//     }

//     public int getLayoutResourceID() {
//         return layoutResourceID;
//     }

//     public int getDefaultDrawableID(){
//         return defaultDrawableID;
//     }

//     public int getClickedDrawableID() {
//         return clickedDrawableID;
//     }

//     public int getID() {
//         return identifier;
//     }

//     static public int size() {
//         return  4;
//     }
// }
// =======
// >>>>>>> MapTesting

/**
 *
 */
class PawnButtons {
    Activity context;
    private static final String TAG = "PawnButtons";
    static private int nrOfPawns;
        // keeps track of the available pawns for the player to choose from
    static private Boolean available[];
    static void resetPawns(){
        // made all the pawns available. Will probably make a method out of this later
        nrOfPawns = 4;
        available = new Boolean[nrOfPawns];
        for (int index = 0; index < available.length; index++) {
            available[index] = Boolean.TRUE;
        }
    }
    static {
        resetPawns();
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
        available[index] = Boolean.FALSE;

    }

    void deactivateBtn(int index) {
        if(index >= 0) {
            pawnBtn[index].setBackgroundResource(Pawns.values()[index + 1].getDefaultDrawableID());
            available[index] = Boolean.TRUE;
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

    for(int index = 0; index < nrOfPawns; index++ )
        if(available[index] == Boolean.TRUE) {
            final int finalIndex = index;
            pawnBtn[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleBtn(finalIndex);
                }
            });
        } else {
            pawnBtn[index].setClickable(false);
        }
    }

    public int getSelectedPawn() {
        return active + 1;
    }

    public void setImportedPawn(int pawnOrdinal) {
        if(pawnOrdinal < 1) return;
        final int pawn = pawnOrdinal - 1;

        available[pawn] = Boolean.TRUE;
        pawnBtn[pawn].setClickable(true);
        pawnBtn[pawn].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleBtn(pawn);
            }
        });
        activateBtn(pawn);
    }
}

/**
 *  Class responsible for the adding members functionality and area.
 */
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
    textField.setText("");
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

    void importMemberList(String[] memberList) {
        if(memberList.length == 0) membersNames.clear();
        else membersNames.addAll(Arrays.asList(memberList));
        membersAdapater.notifyDataSetChanged();
    }

    String[] getMembersList() {
        String elem[] = new String[membersNames.size()];
        membersNames.toArray(elem);
        return elem;
    }


}