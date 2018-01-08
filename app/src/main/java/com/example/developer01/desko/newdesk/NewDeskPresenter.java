package com.example.developer01.desko.newdesk;

import android.content.Context;

/**
 * Created by developer01 on 22/12/2017.
 */

public class NewDeskPresenter {
    private final NewDeskUseCase mNewDeskUseCase;
    private final NewDeskView mNewDeskView;
    private static final String empty = "";

    NewDeskPresenter(NewDeskView view, Context context) {
        mNewDeskView = view;
        mNewDeskUseCase = new NewDeskUseCase(context);
    }

    void addDesk() {
        String name = mNewDeskView.getDeskName();
        String team = mNewDeskView.getDeskTeam();
        String date = mNewDeskView.getAvailDate();
        mNewDeskUseCase.setDeskDetails(name, team, date);
    }

    private boolean fieldDeskWasEdited() {
        return isDeskNameEdited()
                || isDeskTeamEdited()
                || isDeskDateEdited();
    }

    private boolean isDeskNameEdited(){
        return !mNewDeskView.getDeskName().equals(empty);
    }

    private boolean isDeskTeamEdited(){
        return !mNewDeskView.getDeskTeam().equals(empty);
    }

    private boolean isDeskDateEdited() { return !mNewDeskView.getAvailDate().equals(empty); }

    boolean shouldShowAlertDialog() {
        return fieldDeskWasEdited();
    }
}
