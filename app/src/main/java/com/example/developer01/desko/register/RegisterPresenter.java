package com.example.developer01.desko.register;

import android.content.Context;

/**
 * Created by developer01 on 18/12/2017.
 */

public class RegisterPresenter {

    private final RegisterUseCase mRegisterUseCase;
    private final RegisterView mView;

    public RegisterPresenter(RegisterView view, Context context){
        mView = view;
        mRegisterUseCase = new RegisterUseCase(context);
    }

    public void loadDetails() {
        RegisterTeamModel teamModel = mRegisterUseCase.getTeamDetails();
        mView.populateTeamList(teamModel);
    }

    public void loadTeamMembers(String teamName){
        RegisterMemberModel memberModel = mRegisterUseCase.getMemberDetails(teamName);
        mView.populateMemberList(memberModel);
    }
}
