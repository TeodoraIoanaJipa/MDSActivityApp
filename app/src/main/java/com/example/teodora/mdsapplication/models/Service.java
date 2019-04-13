package com.example.teodora.mdsapplication.models;

import java.util.ArrayList;

public class Service {
    private Service instance = null;

    public ArrayList<Team> teams;

    private Service() {
        teams = new ArrayList<>();
    }

    public Service getInstance() {
        if(null == instance)
            instance = new Service();
        return instance;
    }
}
