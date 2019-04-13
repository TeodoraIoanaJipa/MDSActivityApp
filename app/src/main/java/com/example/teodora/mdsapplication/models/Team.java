package com.example.teodora.mdsapplication.models;

import java.util.ArrayList;
import java.util.Arrays;

public class Team {
    private String teamName;
    private int pawnColor;
    private ArrayList<String>  teamMembers;

    private int totalPoints;
    private int boardPosition;

    Team() {
        teamMembers = new ArrayList<>();
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getPawnColor() {
        return pawnColor;
    }

    public void setPawnColor(int pawnColor) {
        this.pawnColor = pawnColor;
    }

    public ArrayList<String> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(String[] teamMembers) {
        this.teamMembers.addAll(Arrays.asList(teamMembers));
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
}
