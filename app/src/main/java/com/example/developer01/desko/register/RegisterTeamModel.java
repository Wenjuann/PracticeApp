package com.example.developer01.desko.register;

import java.util.ArrayList;

/**
 * Created by developer01 on 18/12/2017.
 */

public class RegisterTeamModel {

    private final ArrayList<String> teamNameList;

    public RegisterTeamModel(ArrayList<String> list) {
        teamNameList = list;
    }

    public ArrayList<String> getTeamNameList() {
        return teamNameList;
    }
}
