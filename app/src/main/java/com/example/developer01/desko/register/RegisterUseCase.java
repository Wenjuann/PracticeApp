package com.example.developer01.desko.register;

import android.content.Context;

import com.example.developer01.desko.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by developer01 on 18/12/2017.
 */

public class RegisterUseCase {

    DatabaseHelper db;

    public RegisterUseCase(Context context){
        db = new DatabaseHelper(context);
    }

    public RegisterTeamModel getTeamDetails() {
        ArrayList<String> teamNameList = db.getAllTeamName();
        RegisterTeamModel model = new RegisterTeamModel(teamNameList);
        return model;
    }

    public RegisterMemberModel getMemberDetails(String teamName) {
        ArrayList<String> teamMemberList = db.getAllMemberName(teamName);
        RegisterMemberModel model = new RegisterMemberModel(teamMemberList);
        return model;
    }

}
