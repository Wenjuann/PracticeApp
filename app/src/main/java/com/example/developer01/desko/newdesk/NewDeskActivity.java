package com.example.developer01.desko.newdesk;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.example.developer01.desko.R;
import com.example.developer01.desko.register.RegisterFragment;
import com.example.developer01.desko.displaydesk.DisplayDeskFragment;

import java.util.Date;

/**
 * Created by developer01 on 22/12/2017.
 */

public class NewDeskActivity extends Activity implements DatePickerFragment.OnDatePickedListener {

    public String datePicked;

    //public Date datePicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.baseContent, new NewDeskFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        Fragment previous = getFragmentManager().findFragmentById(R.id.baseContent);

        if (previous instanceof NewDeskFragment) {
            NewDeskFragment previousFragment = (NewDeskFragment) previous;
            previousFragment.handleDeskBackButtonEvent();
        }
        else {
            // This is a quick get away solution
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.baseContent, new RegisterFragment())
                    .commit();
        }
        /*
        final NewDeskFragment currentFragment = (NewDeskFragment) getFragmentManager().findFragmentById(R.id.baseContent);
        currentFragment.handleDeskBackButtonEvent();
        */
    }

    public void showAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle(" Warning")
                .setMessage("We noticed that you have entered some information already. Are you sure you want to leave this screen?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // go back to the display desk fragment
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.baseContent, new DisplayDeskFragment())
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
                .setMessage("The free desk has been added to database!")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // got back to the register fragment
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.baseContent, new DisplayDeskFragment())
                                .commit();
                    }
                })
                .show();
    }


    public String onDatePicked(String date) {
        datePicked = date;
        return datePicked;
    }

    public String getDatePicked() {
        return datePicked;
    }

    /*
    public Date onDatePicked(String date) {
        datePicked = date;
        return datePicked;
    }

    public String getDatePicked() {
        return datePicked;
    }
    */
}
