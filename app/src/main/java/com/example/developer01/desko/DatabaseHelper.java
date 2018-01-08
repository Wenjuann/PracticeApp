package com.example.developer01.desko;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by developer01 on 28/11/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "personManager";

    private static final String TABLE_PERSON = "person";
    private static final String KEY_NAME = "name";
    private static final String KEY_TEAMNAME = "teamName";
    private static final String KEY_DATE = "availDate";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // updated table with new Key_Date column
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String createTableSql = String.format("CREATE TABLE %s (%s TEXT, %s TEXT, %s TEXT)", TABLE_PERSON, KEY_NAME, KEY_TEAMNAME, KEY_DATE);
//        String CREATE_PERSON_TABLE = "CREATE_TABLE " + TABLE_PERSON + "("
//                + KEY_ID + " INTEGER PRIMARY KEY"
//                + KEY_NAME + " TEXT " + KEY_TEAMNAME + " TEXT" + ")";
        db.execSQL(createTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSON);
        onCreate(db);
    }

    // CRUD Operations (Insert/Update/Delete/Select Records)
    public void addPerson(Person person) {

        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_PERSON;
        Cursor cursor = db.rawQuery(selectQuery, null);

        String personName = person.getName();
        boolean dup = false;
        if(cursor.moveToFirst()){
            do {
                String name = cursor.getString(0);
                if (personName.equals(name)) {
                    dup = true;
                    break;
                }
            } while(cursor.moveToNext());
        }
        cursor.close();

        ContentValues values = new ContentValues();
        if (!dup) {
            values.put(KEY_NAME, person.getName());
            values.put(KEY_TEAMNAME, person.getTeamName());
            values.put(KEY_DATE, "N/A");
            db.insert(TABLE_PERSON, null, values);
        }

        db.close(); // closing database connection
    }

    public void addDesk(String name, String team, String date) {

        SQLiteDatabase db = this.getWritableDatabase();

        /*String selectQuery = "SELECT * FROM " + TABLE_PERSON;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do {
                String personName = cursor.getString(0);
                if (personName.equals(name)) {
                    ContentValues values = new ContentValues();
                    values.put(KEY_NAME, name);
                    values.put(KEY_TEAMNAME, team);
                    values.put(KEY_DATE, date);
                    db.update(TABLE_PERSON, values, KEY_NAME + " = " + name ,null);
                    break;
                }
            } while(cursor.moveToNext());
        }
        cursor.close();*/

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_TEAMNAME, team);
        values.put(KEY_DATE, date);
        db.update(TABLE_PERSON, values, KEY_NAME + "=?",new String[]{name});

        db.close();
    }

    public ArrayList<String> getAllTeamName() {
        ArrayList<String> teamNameList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_PERSON;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                String teamName = cursor.getString(1);

                boolean dup = false;
                for (int i = 0; i < teamNameList.size();i++){
                    if (teamName.equals(teamNameList.get(i))){
                        dup = true;
                    }
                }

                if (!dup) teamNameList.add(teamName);
            } while(cursor.moveToNext());
        }
        cursor.close();

        return teamNameList;
    }

    // fetch all the names for team members
    public ArrayList<String> getAllMemberName(String teamName) {
        ArrayList<String> teamMemberNameList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_PERSON;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                if ((cursor.getString(1)).equals(teamName)) {
                    String memberName = cursor.getString(0);
                    teamMemberNameList.add(memberName);
                }
            } while(cursor.moveToNext());
        }

        return teamMemberNameList;
    }

    public ArrayList<String> getAllAvailDesks() {
        ArrayList<String> deskList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_PERSON;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                String availDate = cursor.getString(2);

                String empty = "N/A";
                if (!availDate.equals(empty) && !deskList.contains(availDate)) {
                    deskList.add(availDate);
                }
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return deskList;
    }

    public ArrayList<String> getAllAvailDesksTeam(String date) {
        ArrayList<String> availDeskTeam = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_PERSON + " WHERE " + KEY_DATE + "='" + date + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            do {
                availDeskTeam.add(cursor.getString(1));
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return availDeskTeam;
    }
















    Person getName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PERSON, new String[]{KEY_NAME, KEY_TEAMNAME}, KEY_NAME + "=?",
                new String[]{String.valueOf(name)}, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Person person = new Person(cursor.getString(0), cursor.getString(1));
        cursor.close();

        return person;
    }

    public List<Person> getAllPerson() {
        List<Person> personList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_PERSON;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do {
                Person person = new Person();
                person.setName(cursor.getString(0));
                person.setTeamName(cursor.getString(1));

                personList.add(person);
            } while(cursor.moveToNext());
        }
        cursor.close();

        return personList;
    }

    // teamname getter
    public String getTeamName(String memberName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_PERSON;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                if ((cursor.getString(0)).equals(memberName)) {
                    return cursor.getString(1);
                }
            } while (cursor.moveToNext());
        }
        return cursor.getString(1);
    }

    public int updatePerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(KEY_NAME, person.getName());
        //values.put(KEY_TEAMNAME, person.getTeamName());
        //values.put(Key_DATE, person.)

        return db.update(TABLE_PERSON, values, KEY_NAME + "=?", new String[]{person.getName()});
    }

    public void deletePerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PERSON, KEY_NAME + "=?", new String[]{person.getName()});
        db.close();
    }

}
