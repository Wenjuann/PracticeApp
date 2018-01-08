package com.example.developer01.desko.displaydesk;

import com.example.developer01.desko.BasePresenter;
import com.example.developer01.desko.displaydesk.DisplayDeskModel;

/**
 * Created by developer01 on 22/12/2017.
 */

public interface DisplayDeskView extends BasePresenter.ViewInterface {
    void populateDeskDateList(DisplayDeskModel deskModel);
    void setAvailDeskTeam(DisplayDeskTeamModel deskTeamModel);
}
