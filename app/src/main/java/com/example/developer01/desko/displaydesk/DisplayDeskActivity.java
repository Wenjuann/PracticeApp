package com.example.developer01.desko.displaydesk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.developer01.desko.R;
import com.example.developer01.desko.register.RegisterFragment;

public class DisplayDeskActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.baseContent, new DisplayDeskFragment())
                .commit();

    }

    @Override
    public void onBackPressed() {
        final DisplayDeskFragment currentFragment = (DisplayDeskFragment) getFragmentManager().findFragmentById(R.id.baseContent);
        currentFragment.handleLogOffEvent();
    }

    public void showLogOffDialog() {
        new AlertDialog.Builder(this)
                .setTitle(" Warning")
                .setMessage("Are you sure you want to log off?")
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

}
