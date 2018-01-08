package com.example.developer01.desko.register;

import android.os.Bundle;
import android.app.Activity;

import com.example.developer01.desko.DatabaseHelper;
import com.example.developer01.desko.Person;
import com.example.developer01.desko.R;


public class RegisterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // database helper
        //DatabaseHelper db = new DatabaseHelper(this);
        //db.deletePerson(new Person("Simon","Tablets"));

        setContentView(R.layout.base_activity);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.baseContent, new RegisterFragment())
                .commit();
    }

}













        /*
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.AdapterView.OnItemSelectedListener;
        import android.widget.Spinner;
        import android.widget.Button;
        import android.content.Intent;
        import android.widget.ArrayAdapter;
        import java.util.ArrayList;
        import android.widget.Toast;
        */
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Find the team Spinner
        Spinner teamSpinner = (Spinner) findViewById(R.id.team_spinner);

        // database helper
        DatabaseHelper db = new DatabaseHelper(this);
        // db.addPerson(new Person("Ankit","Passport"));

        // loading spinner data from database
        loadSpinnerData(teamSpinner, db);

        teamSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
                final DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                Spinner nameSpinner = (Spinner) findViewById(R.id.name_spinner);
                Toast.makeText(parent.getContext(),
                        parent.getItemAtPosition(pos).toString() + " " + "Team Selected!",
                        Toast.LENGTH_SHORT).show();
                //update the list of names!
                String teamName = parent.getItemAtPosition(pos).toString();
                loadMemberSpinnerData(nameSpinner, db, teamName);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        Spinner nameSpinner = (Spinner) findViewById(R.id.name_spinner);
        nameSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
                final DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                Spinner teamSpinner = (Spinner) findViewById(R.id.team_spinner);
                Toast.makeText(parent.getContext(),
                        parent.getItemAtPosition(pos).toString() + " " + "Selected!",
                        Toast.LENGTH_SHORT).show();
                //update the list of team!
                String memberName = parent.getItemAtPosition(pos).toString();
                //teamName = db.getTeamName(memberName);
                //loadSpinnerData(, db);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        // For the Register Button
        Button button = (Button) findViewById(R.id.register_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, DisplayDeskActivity.class));
            }
        });

        // TODO nameSpinner!!!
    }


    private void loadSpinnerData(Spinner teamSpinner, DatabaseHelper db){
        // spinner drop down elements
        ArrayList teamList = db.getAllTeamName();
        // creating adapter for spinner
        ArrayAdapter<String> teamArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, teamList);
        // drop down layout style - list view with radio button
        teamArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        teamSpinner.setAdapter(teamArrayAdapter);
    }


    private void loadMemberSpinnerData(Spinner nameSpinner, DatabaseHelper db, String teamName){
        // spinner drop down elements
        ArrayList teamMemberList = db.getAllMemberName(teamName);
        // creating adapter for spinner
        ArrayAdapter<String> teamArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, teamMemberList);
        // drop down layout style - list view with radio button
        teamArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        nameSpinner.setAdapter(teamArrayAdapter);
    }
    */