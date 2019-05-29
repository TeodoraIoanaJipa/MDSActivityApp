package com.example.teodora.mdsapplication.models;

import com.example.teodora.mdsapplication.newgame.MapActivity;

public class AppService {
    private static AppService instance = null;
    public Integer iterations=0;
    public TeamsManager teamsManager;
    private MapSituation mapSituation;

    private AppService() {
        teamsManager = new TeamsManager();
        mapSituation = null;
    }

    static public AppService getInstance() {
        if(null == instance)
            instance = new AppService();
        return instance;
    }

    public MapSituation getMapSituation() {
        if(mapSituation == null) {
            mapSituation = new MapSituation();
            mapSituation.setTeamInfos(teamsManager.getTeam(teamsManager.getCurrentTeam()));
            mapSituation.setTeamsModels(teamsManager.getTeamModels());
        }
        return mapSituation;
    }

    public void resetGame() {
        mapSituation = null;
        teamsManager.setCurrentTeam(0);
        teamsManager.resetCurrentMembers();
    }

    public void nextTeam() {
        int currTeam =  teamsManager.nextTeam();
        mapSituation.setTeamInfos(teamsManager.getTeam(currTeam));
    }


}

