package com.example.teodora.mdsapplication.models;

import com.example.teodora.mdsapplication.R;

/*
 Enum that stores the order number of each pawn color,
    the drawable model ID and the view model layout ID
    set in the xml file
 */
public enum Pawns {
    Gray(0, R.drawable.grey_pawn, R.color.black, 0,0),
    Red(1, R.drawable.red_pawn, R.drawable.clicked_red_pawn, R.id.pawnSelectBtn1, R.drawable.pionas_rosu),
    Green(2, R.drawable.green_pawn, R.drawable.clicked_green_pawn, R.id.pawnSelectBtn3, R.drawable.pionas_verde),
    Blue(3, R.drawable.blue_pawn, R.drawable.clicked_blue_pawn, R.id.pawnSelectBtn4, R.drawable.pionas_albastru),
    Yellow(4, R.drawable.yellow_pawn, R.drawable.clicked_yellow_pawn, R.id.pawnSelectBtn2, R.drawable.pionas_galben);

    int identifier;
    int defaultDrawableID;
    int clickedDrawableID;
    int layoutResourceID;

    int boardPawnDrawableID;

    Pawns(int identifier, int defaultDrawableID, int clickedDrawableID, int layoutResourceID, int boardPawnDrawableID) {
        this.identifier = identifier;
        this.defaultDrawableID = defaultDrawableID;
        this.clickedDrawableID = clickedDrawableID;
        this.layoutResourceID = layoutResourceID;
        this.boardPawnDrawableID = boardPawnDrawableID;

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

    public int getBoardPawnDrawableID() {
        return boardPawnDrawableID;
    }

    public int getID() {
        return identifier;
    }

    static public int size() {
        return  4;
    }
}
