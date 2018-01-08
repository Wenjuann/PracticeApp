package com.example.developer01.desko.register;

import java.util.ArrayList;

/**
 * Created by developer01 on 18/12/2017.
 */

public class RegisterMemberModel {
    private final ArrayList<String> teamMemberList;

    public RegisterMemberModel(ArrayList<String> list){
        teamMemberList = list;
    }

    public ArrayList<String> getTeamMemberList() {
        return teamMemberList;
    }
}
