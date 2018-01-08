package com.example.developer01.desko.displaydesk;

import android.content.Context;

import com.example.developer01.desko.DatabaseHelper;
import com.example.developer01.desko.displaydesk.DisplayDeskModel;

import java.util.ArrayList;

/**
 * Created by developer01 on 22/12/2017.
 */

public class DisplayDeskUseCase {

    DatabaseHelper db;

    public DisplayDeskUseCase(Context context){
        db = new DatabaseHelper(context);
    }

    public DisplayDeskModel getAvailDesks() {
        ArrayList<String> availDesks = db.getAllAvailDesks();
        DisplayDeskModel model = new DisplayDeskModel(availDesks);
        return model;
    }

    public DisplayDeskTeamModel getAvailDesksTeam(String date) {
        ArrayList<String> availDesksTeam = db.getAllAvailDesksTeam(date);
        DisplayDeskTeamModel model = new DisplayDeskTeamModel(availDesksTeam);
        return model;
    }
}
