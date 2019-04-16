package com.example.teodora.mdsapplication.models;

public class AppService {
    private static AppService instance = null;

    public TeamsManager teamsManager;
    private AppService() {
        teamsManager = new TeamsManager();
    }

    static public AppService getInstance() {
        if(null == instance)
            instance = new AppService();
        return instance;
    }
}

