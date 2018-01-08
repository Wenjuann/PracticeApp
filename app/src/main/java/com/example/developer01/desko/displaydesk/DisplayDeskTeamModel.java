package com.example.developer01.desko.displaydesk;

import java.util.ArrayList;

/**
 * Created by developer01 on 04/01/2018.
 */

public class DisplayDeskTeamModel {
    private final ArrayList<String> availDeskTeamList;

    public DisplayDeskTeamModel(ArrayList<String> list) {
        availDeskTeamList = list;
    }

    public ArrayList<String> getAvailDeskTeamList() {
        return availDeskTeamList;
    }
}
