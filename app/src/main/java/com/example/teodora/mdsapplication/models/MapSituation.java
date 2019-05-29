package com.example.teodora.mdsapplication.models;

import java.util.ArrayList;

public class MapSituation {
    String currentTeam;
    String curremtMember;
    int pawnMascotRez;

    ArrayList<Integer> pawnModels;
    ArrayList<Integer> positions;

    int indToChange;
    int pozToChange;

    int nrOfTeams;

    public MapSituation() {
        this.curremtMember = this.currentTeam = "";
        this.pawnModels = new ArrayList<>();
        this.positions = new ArrayList<>();
        this.pawnMascotRez = this.indToChange = this.pozToChange = this.nrOfTeams = 0;
    }


    public void setTeamsModels(ArrayList<Integer> pawnModels) {
        this.pawnModels = pawnModels;
        this.positions = new ArrayList<>();
        for (int ind = 0; ind < pawnModels.size(); ind++) {
            this.positions.add(0);
        }
        nrOfTeams = pawnModels.size();
    }

    public void rewardTeam(int team, int points) {
        indToChange = team;
        pozToChange = points;
    }

    public void setTeamInfos(Team theTeam) {
        currentTeam = theTeam.getTeamName();
        curremtMember = theTeam.getTeamMembers()[theTeam.getCurrentMember()];
        pawnMascotRez = Pawns.values()[theTeam.getPawnColor()].getDefaultDrawableID();
    }

    public String getMember() {
        return this.curremtMember;
    }

    public String getTeam() {
        return this.currentTeam;
    }

    public int getPawnMascotRez() {
        return pawnMascotRez;
    }

    public int getIndToChange() {
        return indToChange;
    }

    public int getPozToChange() {
        return pozToChange;
    }

    public ArrayList<Integer> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<Integer> positions) {
        this.positions = positions;
    }

    public ArrayList<Integer> getPawnModel() {
        return pawnModels;
    }
}
