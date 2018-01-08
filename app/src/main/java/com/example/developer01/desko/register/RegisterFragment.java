package com.example.developer01.desko.register;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.developer01.desko.newuser.NewUserActivity;
import com.example.developer01.desko.R;
import com.example.developer01.desko.displaydesk.DisplayDeskActivity;

import java.util.ArrayList;

/**
 * Created by developer01 on 18/12/2017.
 */

public class RegisterFragment extends Fragment implements RegisterView {

    private RegisterPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new RegisterPresenter(this, getActivity().getBaseContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_register, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.loadDetails();
        setUserListener();
        setLoginListener();
    }

    @Override
    public void populateTeamList(RegisterTeamModel teamModel) {
        // Find the team Spinner
        Spinner teamSpinner = (Spinner) getActivity().findViewById(R.id.team_spinner);
        final ArrayList<String> teamNames = teamModel.getTeamNameList();
        ArrayAdapter<String> teamArrayAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(),
                android.R.layout.simple_spinner_item, teamNames);
        // drop down layout style - list view with radio button
        teamArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        teamSpinner.setAdapter(teamArrayAdapter);

        teamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                String teamSelected = adapterView.getItemAtPosition(pos).toString();
                Toast.makeText(adapterView.getContext(),
                        teamSelected + " " + "Team Selected!",
                        Toast.LENGTH_SHORT).show();
                mPresenter.loadTeamMembers(teamSelected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void populateMemberList(RegisterMemberModel memberModel) {
        // Find the name Spinner
        Spinner nameSpinner = (Spinner) getActivity().findViewById(R.id.name_spinner);
        final ArrayList<String> names = memberModel.getTeamMemberList();
        ArrayAdapter<String> nameArrayAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(),
                android.R.layout.simple_spinner_item, names);
        // drop down layout style - list view with radio button
        nameArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        nameSpinner.setAdapter(nameArrayAdapter);
        nameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Spinner teamSpinner = (Spinner) getActivity().findViewById(R.id.team_spinner);
                Toast.makeText(parent.getContext(),
                        parent.getItemAtPosition(pos).toString() + " " + "Selected!",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void setUserListener() {
        Button newUser = (Button) getActivity().findViewById(R.id.new_user_button);
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NewUserActivity.class));
            }
        });
    }

    public void setLoginListener() {
        Button login = (Button) getActivity().findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DisplayDeskActivity.class));
            }
        });
    }

}
