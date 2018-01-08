package com.example.developer01.desko.newuser;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.developer01.desko.R;
import com.example.developer01.desko.register.RegisterFragment;

/**
 * Created by developer01 on 19/12/2017.
 */

public class NewUserActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.baseContent, new NewUserFragment())
                .commit();
    }

    @Override
    public void onBackPressed(){
        final NewUserFragment currentFragment = (NewUserFragment) getFragmentManager().findFragmentById(R.id.baseContent);
        currentFragment.handleBackButtonEvent();
    }

    public void showAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle(" Warning")
                .setMessage("Are you sure you want to leave this screen?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // go back to the register fragment
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.baseContent, new RegisterFragment())
                                .commit();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void showConfirmDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Success")
                .setMessage("You have now been added to the list. Please go back to log in.")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // got back to the register fragment
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.baseContent, new RegisterFragment())
                                .commit();
                    }
                })
                .show();
    }
}
