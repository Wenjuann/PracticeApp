package com.example.developer01.desko.newdesk;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.developer01.desko.R;

import java.util.Date;

/**
 * Created by developer01 on 02/01/2018.
 */

public class DatePickerFragment extends Fragment {

    private OnDatePickedListener mListener;

    public interface OnDatePickedListener {
        String onDatePicked(String date);
        //Date onDatePicked(Date date);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_pick_date, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setSubmitListener();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mListener =
                    (OnDatePickedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnDatePickedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setSubmitListener() {
        final DatePicker simpleDatePicker;
        // initiate the date picker and a button
        simpleDatePicker = (DatePicker) getActivity().findViewById(R.id.simpleDatePicker);
        Button submit = (Button) getActivity().findViewById(R.id.submit_button);
        // perform click event on submit button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the values for day of month , month and year from a date picker
                String day = "Day = " + simpleDatePicker.getDayOfMonth();
                String month = "Month = " + MONTHS[simpleDatePicker.getMonth()];
                String year = "Year = " + simpleDatePicker.getYear();
                // display the values by using a toast
                Toast.makeText(getActivity().getApplicationContext(), "Your have chosen:\n"
                        + day + "\n" + month + "\n" + year, Toast.LENGTH_LONG).show();
                String date = simpleDatePicker.getYear() + " " + (simpleDatePicker.getMonth()+1)
                                + " " + simpleDatePicker.getDayOfMonth();
                mListener.onDatePicked(date);

                /*Date dSelected = new Date(simpleDatePicker.getYear(), simpleDatePicker.getMonth(), simpleDatePicker.getDayOfMonth());
                mListener.onDatePicked(dSelected);*/

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.baseContent, new NewDeskFragment())
                        .commit();
            }
        });
    }

    public static final String[] MONTHS = {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };

}
