package com.example.developer01.desko.displaydesk;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.developer01.desko.newdesk.NewDeskActivity;
import com.example.developer01.desko.R;
import com.example.developer01.desko.register.RegisterFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by developer01 on 21/12/2017.
 */

public class DisplayDeskFragment extends Fragment implements DisplayDeskView {

    private DisplayDeskPresenter mPresenter;
    public ArrayList<String> availDates;
    public ArrayList<String> avDates;
    public ArrayList<String> availDeskTeam;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new DisplayDeskPresenter(this, getActivity().getBaseContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_display_desk, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.loadAvailDesks();
        setAdderListener();
        setListClickListener();
    }

    public void setAdderListener() {
        FloatingActionButton newDesk = (FloatingActionButton) getActivity().findViewById(R.id.plus_fab);
        newDesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NewDeskActivity.class));
            }
        });
    }

    public void setListClickListener() {
        ListView lv = getActivity().findViewById(R.id.avail_list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                String dateSelected = adapterView.getItemAtPosition(pos).toString();
                String dSe = convertBackFormat(dateSelected);
                mPresenter.loadAvailDesksTeam(dSe);

                new AlertDialog.Builder(getActivity())
                        .setTitle("Date selected: " + dateSelected)
                        .setMessage("Teams that have available desks include: " + availDeskTeam)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();
            }
        });
    }

    @Override
    public void setAvailDeskTeam(DisplayDeskTeamModel deskTeamModel) {
        availDeskTeam = deskTeamModel.getAvailDeskTeamList();
    }

    public void handleLogOffEvent() {
        ((DisplayDeskActivity) getActivity()).showLogOffDialog();
    }

    @Override
    public void populateDeskDateList(DisplayDeskModel deskModel) {

        final ListView datesView = (ListView) getActivity().findViewById(R.id.avail_list);
        avDates = deskModel.getAvailDateList();
        // TODO the collection sort does not work yet. should try to implement on Date object
        Collections.sort(avDates);
        availDates = convertFormat(avDates);
        ArrayAdapter<String> deskArrayAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, availDates);
        datesView.setAdapter(deskArrayAdapter);

    }

    public ArrayList<String> convertFormat(ArrayList<String> dateList) {

        for (int i = 0; i < dateList.size(); i++) {
            String date = dateList.get(i);
            String year = String.valueOf(date.charAt(0)) + String.valueOf(date.charAt(1))
                        + String.valueOf(date.charAt(2)) + String.valueOf(date.charAt(3));
            String month = "";
            String day = "";
            if (String.valueOf(date.charAt(6)).equals(" ")) {
                month = String.valueOf(date.charAt(5));
                for (int j = 7; j < date.length(); j++) {
                    day = day + String.valueOf(date.charAt(j));
                }
            } else {
                month = String.valueOf(date.charAt(5)) + String.valueOf(date.charAt(6));
                for (int j = 8; j < date.length(); j++) {
                    day = day + String.valueOf(date.charAt(j));
                }
            }
            date = day + " " + MONTHS[Integer.valueOf(month)-1] + " " + year;
            dateList.set(i, date);
        }

        return dateList;
    }

    public String convertBackFormat(String date) {

            String day = "";
            String month_name = "";
            Integer month;
            String year = "";
            if (String.valueOf(date.charAt(1)).equals(" ")) {
                day = String.valueOf(date.charAt(0));
                month_name = String.valueOf(date.charAt(2)) + String.valueOf(date.charAt(3))
                            + String.valueOf(date.charAt(4));
                month = indexOfArray(month_name) + 1;
                year = String.valueOf(date.charAt(6)) + String.valueOf(date.charAt(7))
                        + String.valueOf(date.charAt(8)) + String.valueOf(date.charAt(9));
            } else {
                day = String.valueOf(date.charAt(0)) + String.valueOf(date.charAt(1));
                month_name = String.valueOf(date.charAt(3)) + String.valueOf(date.charAt(4))
                        + String.valueOf(date.charAt(5));
                month = indexOfArray(month_name) + 1;
                year = String.valueOf(date.charAt(7)) + String.valueOf(date.charAt(8))
                        + String.valueOf(date.charAt(9)) + String.valueOf(date.charAt(10));
            }

        date = year + " " + month + " " + day;

        return date;
    }

    public int indexOfArray(String input) {
        int i;
        for (i=0; i< MONTHS.length; i++) {
            if (MONTHS[i].equals(input)) break;
        }
        return i;
    }

    public static final String[] MONTHS = {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };

}
