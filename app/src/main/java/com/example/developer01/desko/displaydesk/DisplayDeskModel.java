package com.example.developer01.desko.displaydesk;

import java.util.ArrayList;

/**
 * Created by developer01 on 27/12/2017.
 */

public class DisplayDeskModel {
    private final ArrayList<String> availDateList;

    public DisplayDeskModel(ArrayList<String> list) {
        availDateList = list;
    }

    public ArrayList<String> getAvailDateList() {
        return availDateList;
    }
}
