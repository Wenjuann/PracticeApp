package com.example.developer01.desko.newdesk;

import com.example.developer01.desko.BasePresenter;

/**
 * Created by developer01 on 22/12/2017.
 */

public interface NewDeskView extends BasePresenter.ViewInterface {
    String getDeskName();
    String getDeskTeam();
    String getAvailDate();
}
