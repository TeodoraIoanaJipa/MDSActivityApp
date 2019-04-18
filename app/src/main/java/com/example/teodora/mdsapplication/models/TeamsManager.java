package com.example.teodora.mdsapplication.models;

public class TeamsManager {
    private int totalTeams;
    private int existingTeams;
    private Team[] teams;


    public int getTotalTeams() { return totalTeams; }
    public void setTotalTeams(int totalTeams) {
        
            this.totalTeams = totalTeams;
            teams = new Team[totalTeams];
            for (int index = 0; index < teams.length; index++) {
                teams[index] = new Team();
            }
    }
    
    public boolean setTeam(int ordinal, Team team) {
        if (ordinal - 1 > totalTeams) return false;
        teams[ordinal - 1] = team;
        return true;
    }

    public Team getTeam(int index) {
        if(index >= totalTeams || index < 0) return null;
        return teams[index];
    }

    public Team getTeamOrdinal(int ordinal) {
        return getTeam(ordinal - 1);
    }


}
