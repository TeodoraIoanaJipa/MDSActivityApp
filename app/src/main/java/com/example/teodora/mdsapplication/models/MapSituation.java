package com.example.teodora.mdsapplication.models;

import javax.xml.transform.stream.StreamSource;

public class MapSituation {
    String currentTeam;
    String curremtMember;
    int pawnMascotRez;

    int[] pawnModels;
    int[] positions;

    int indToChange;
    int pozToChange;

    int nrOfTeams;

    public MapSituation() {
        this.curremtMember = this.currentTeam = "";
        this.pawnModels = this.positions = new int[0];
        this.pawnMascotRez = this.indToChange = this.pozToChange = this.nrOfTeams = 0;
    }


    public void setTeamsModels(int[] pawnModels) {
        this.pawnModels = pawnModels.clone();
        this.positions = new int[pawnModels.length];
        for (int ind = 0; ind < pawnModels.length; ind++) {
            this.positions[ind] = 0;
        }
        nrOfTeams = pawnModels.length;
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

    @Override
    protected MapSituation clone() throws CloneNotSupportedException {
        MapSituation toReturn = new MapSituation();

        toReturn.currentTeam = currentTeam;
        toReturn.curremtMember = curremtMember;
        toReturn.pawnModels = pawnModels.clone();
        toReturn.positions = positions.clone();
        toReturn.indToChange = indToChange;
        toReturn.pozToChange = pozToChange;
        toReturn.nrOfTeams = nrOfTeams;

        return toReturn;
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
}
