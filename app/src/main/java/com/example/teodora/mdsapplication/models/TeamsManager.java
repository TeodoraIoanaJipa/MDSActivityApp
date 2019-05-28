package com.example.teodora.mdsapplication.models;

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

    int[] getTeamModels() {
        int[] models = new int[teams.length];
        for (int ind = 0; ind < teams.length; ind++) {
            models[ind] = Pawns.values()[teams[ind].getPawnColor()].getBoardPawnDrawableID();
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

}
