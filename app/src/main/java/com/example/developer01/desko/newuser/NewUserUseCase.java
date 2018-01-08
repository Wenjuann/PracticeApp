package com.example.developer01.desko.newuser;

import android.content.Context;

import com.example.developer01.desko.DatabaseHelper;
import com.example.developer01.desko.Person;

/**
 * Created by developer01 on 19/12/2017.
 */

public class NewUserUseCase {

    DatabaseHelper db;

    public NewUserUseCase(Context context){
        db = new DatabaseHelper(context);
    }

    public void setUserDetails(String name, String teamName){
        db.addPerson(new Person(name, teamName));
    }
}
