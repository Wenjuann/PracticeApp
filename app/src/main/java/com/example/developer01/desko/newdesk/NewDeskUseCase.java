package com.example.developer01.desko.newdesk;

import android.content.Context;

import com.example.developer01.desko.DatabaseHelper;

/**
 * Created by developer01 on 22/12/2017.
 */

public class NewDeskUseCase {
    DatabaseHelper db;

    public NewDeskUseCase(Context context){
        db = new DatabaseHelper(context);
    }

    public boolean checkInputInfo() {
        return true;
    }

    public void setDeskDetails(String name, String team, String date) {
        db.addDesk(name, team, date);
    }
}
