package com.example.developer01.desko.newdesk;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.developer01.desko.R;
import com.example.developer01.desko.displaydesk.DisplayDeskFragment;

/**
 * Created by developer01 on 22/12/2017.
 */

public class NewDeskFragment extends Fragment implements NewDeskView {

    private NewDeskPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new NewDeskPresenter(this, getActivity().getBaseContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_new_desk, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // check for button click and action accordingly
        addDeskToDatabase();
        pickCalendarDate();
    }

    public String getDeskName() {
        EditText mName = (EditText) getActivity().findViewById(R.id.desk_name_prompt);
        return mName.getText().toString();
    }

    public  String getDeskTeam() {
        EditText mTeam = (EditText) getActivity().findViewById(R.id.desk_team_prompt);
        return mTeam.getText().toString();
    }

    public String getAvailDate() {
        return ((NewDeskActivity) getActivity()).getDatePicked();
    }

    public void handleDeskBackButtonEvent() {
        if (mPresenter.shouldShowAlertDialog()) {
            ((NewDeskActivity) getActivity()).showAlertDialog();
        } else {
            navigateBack();
        }
    }

    public void navigateBack() {
        /* probably nothing to go back to, as the current fragment is NewUser, Previous in RegisterActivity
        getFragmentManager().popBackStackImmediate();
        */
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.baseContent, new DisplayDeskFragment())
                .commit();
    }

    public void addDeskToDatabase() {
        Button add = (Button) getActivity().findViewById(R.id.add_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.addDesk();
                ((NewDeskActivity) getActivity()).showConfirmDialog();
            }
        });
    }

    public void pickCalendarDate(){
        Button pickDate = (Button) getActivity().findViewById(R.id.pic_date_button);
        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.baseContent, new DatePickerFragment())
                        .commit();
            }
        });
    }

}
