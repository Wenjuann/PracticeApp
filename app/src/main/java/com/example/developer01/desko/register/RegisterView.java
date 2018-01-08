package com.example.developer01.desko.register;

import com.example.developer01.desko.BasePresenter;

/**
 * Created by developer01 on 18/12/2017.
 */

public interface RegisterView extends BasePresenter.ViewInterface {
    void populateTeamList(RegisterTeamModel teamModel);
    void populateMemberList(RegisterMemberModel memberModel);
}
