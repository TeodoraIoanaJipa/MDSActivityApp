package com.example.teodora.mdsapplication.models;

import java.util.ArrayList;
import java.util.Arrays;

public class Team {
    private String teamName;
    private int pawnColor;
    private ArrayList<String> teamMembers;
    private int currentMember = 0;
    private int totalPoints;
    private int lastPosition;
    private int boardPosition;

    public Team() {
        teamName = "";
        pawnColor = 0;
        boardPosition = 0;
        lastPosition = 0;
        totalPoints = 0;
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

    public int getBoardPosition() {
        return boardPosition;
    }

    public void setBoardPosition(int boardPosition) {
        this.boardPosition = boardPosition;
    }

    public int getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(int lastPosition) {
        this.lastPosition = lastPosition;
    }

    public String pawnColorString() {
        switch (pawnColor) {
            case 1:
                return "red";
            case 2:
                return "albastru";
            case 3:
                return "green";
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

    public int nextMember() {
        currentMember = (currentMember + 1) % teamMembers.size();
        return currentMember;
    }

    public int getCurrentMember() {
        return currentMember;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
   }
}
