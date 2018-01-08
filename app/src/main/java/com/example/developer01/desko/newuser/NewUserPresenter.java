package com.example.developer01.desko.newuser;

import android.content.Context;

/**
 * Created by developer01 on 19/12/2017.
 */

public class NewUserPresenter {
    protected final NewUserUseCase mNewUserUseCase;
    private final NewUserView mNewUserView;
    private static final String empty = "";


    public NewUserPresenter(NewUserView view, Context context){
        mNewUserView = view;
        mNewUserUseCase = new NewUserUseCase(context);
    }

    public void addMember() {
        String name = mNewUserView.getInputName();
        String team = mNewUserView.getInputTeam();
        mNewUserUseCase.setUserDetails(name, team);
    }

    private boolean fieldWasEdited(){
        return isNameEdited() || isTeamEdited();
    }

    private boolean isNameEdited(){
        return !mNewUserView.getInputName().equals(empty);
    }

    private boolean isTeamEdited(){
        return !mNewUserView.getInputTeam().equals(empty);
    }

    public boolean shouldShowAlertDialog(){
        return fieldWasEdited();
    }
}
