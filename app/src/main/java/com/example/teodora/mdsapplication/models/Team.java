package com.example.teodora.mdsapplication.models;

import java.util.ArrayList;
import java.util.Arrays;

public class Team {
    private String teamName;
    private int pawnColor;
    private ArrayList<String> teamMembers;

    private int totalPoints;
    private int boardPosition;

    Team() {
        teamName = "";
        pawnColor = 0;
        teamMembers = new ArrayList<>();
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public void setPawnColor(int pawnColor) {
        this.pawnColor = pawnColor;
    }
    public void setTeamMembers(String[] teamMembers) {
        this.teamMembers.clear();
        this.teamMembers.addAll(Arrays.asList(teamMembers));
    }

    public String getTeamName() {
        return teamName;
    }
    public int getPawnColor() {
        return pawnColor;
    }
    public String[] getTeamMembers() {
        String[] toReturn = new String[teamMembers.size()];
        toReturn = teamMembers.toArray(toReturn);
        return toReturn;
    }



    public String pawnColorString() {
        switch (pawnColor) {
            case 1:
                return  "rosu";
            case 2:
                return "albastru";
            case 3:
                return "verde";
            case 4:
                return "yellow";
            default:
                return "grey";
        }
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" Team name: ");
        stringBuffer.append(teamName);
        stringBuffer.append(" | Pawn color: ");
        stringBuffer.append(pawnColorString());
        stringBuffer.append(" | Number of members: ");
        stringBuffer.append(teamMembers.size());
        return String.valueOf(stringBuffer);
    }
}