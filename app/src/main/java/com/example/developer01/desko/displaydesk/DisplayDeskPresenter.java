package com.example.developer01.desko.displaydesk;

import android.content.Context;

/**
 * Created by developer01 on 22/12/2017.
 */

public class DisplayDeskPresenter {

    private final DisplayDeskUseCase mDisplayDeskUseCase;
    private final DisplayDeskView mView;

    public DisplayDeskPresenter(DisplayDeskView view, Context context) {
        mView = view;
        mDisplayDeskUseCase = new DisplayDeskUseCase(context);
    }

    public void loadAvailDesks() {
        DisplayDeskModel deskModel = mDisplayDeskUseCase.getAvailDesks();
        mView.populateDeskDateList(deskModel);
    }

    public void loadAvailDesksTeam(String date) {
        final DisplayDeskTeamModel deskTeamModel = mDisplayDeskUseCase.getAvailDesksTeam(date);
        mView.setAvailDeskTeam(deskTeamModel);
    }

}
