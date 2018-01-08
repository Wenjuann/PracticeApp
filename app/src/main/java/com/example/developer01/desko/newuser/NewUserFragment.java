package com.example.developer01.desko.newuser;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.developer01.desko.R;
import com.example.developer01.desko.register.RegisterFragment;

/**
 * Created by developer01 on 19/12/2017.
 */

public class NewUserFragment extends Fragment implements NewUserView {

    NewUserPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialisePresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_new_user, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // check for button click and action accordingly
        addPersonToDatabase();
    }

    @Override
    public String getInputName() {
        EditText mName = (EditText) getActivity().findViewById(R.id.name_prompt);
        return mName.getText().toString();
    }

    @Override
    public String getInputTeam() {
        EditText mTeam = (EditText) getActivity().findViewById(R.id.team_prompt);
        return mTeam.getText().toString();
    }

    public void addPersonToDatabase() {
        Button register = (Button) getActivity().findViewById(R.id.register_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.addMember();
                ((NewUserActivity) getActivity()).showConfirmDialog();
            }
        });
    }

    public void handleBackButtonEvent() {
        initialisePresenter();
        if (mPresenter.shouldShowAlertDialog()) {
            ((NewUserActivity) getActivity()).showAlertDialog();
        } else {
            navigateBack();
        }
    }

    private void initialisePresenter() {
        mPresenter = new NewUserPresenter(this, getActivity().getBaseContext());
    }

    public void navigateBack() {
        /* probably nothing to go back to, as the current fragment is NewUser, Previous in RegisterActivity
        getFragmentManager().popBackStackImmediate();
        */
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.baseContent, new RegisterFragment())
                .commit();
    }
}
