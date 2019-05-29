package com.example.teodora.mdsapplication.models;

import java.util.ArrayList;

public class TeamsManager {
    private int totalTeams;
    private int currentTeam = 0;

    private Team[] teams;

    public int getTotalTeams() { return totalTeams; }
    public void setTotalTeams(int totalTeams) {
        
            this.totalTeams = totalTeams;
            teams = new Team[totalTeams];
            for (int index = 0; index < teams.length; index++) {
                teams[index] = new Team();
            }
    }

    public void setCurrentTeam(int currentTeam) {

        this.currentTeam = currentTeam;
    }

    public Team[] getTeams() {
        return teams;
    }

    public boolean setTeam(int ordinal, Team team) {
        if (ordinal - 1 > totalTeams) return false;
        teams[ordinal - 1] = team;
        return true;
    }

    ArrayList<Integer> getTeamModels() {
        ArrayList<Integer> models = new ArrayList<>();
        for (Team team : teams) {
            models.add(Pawns.values()[team.getPawnColor()].
                    getBoardPawnDrawableID());
        }
        return models;
    }

    Team getTeam(int index) {
        if(index >= totalTeams || index < 0) return null;
        return teams[index];
    }

    public int getCurrentTeam() {
        return currentTeam;
    }

    public Team getTeamOrdinal(int ordinal) {
        return getTeam(ordinal - 1);
    }

        //  When I switch to the next team I firstly switch to the next
        // member of the current team and then increment current team
    public int nextTeam() {
        teams[currentTeam].nextMember();
        currentTeam = (currentTeam + 1) % teams.length;
        return currentTeam;
    }

    public void resetCurrentMembers() {
        for(Team team : teams)
            team.setCurrentMember(0);
    }

}
