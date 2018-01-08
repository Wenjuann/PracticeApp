package com.example.developer01.desko;

/**
 * Created by developer01 on 28/11/2017.
 */

public class Person {
    int id;
    public String name;
    public String teamName;

    public Person(){

    }

    public Person(String name, String teamName){
        this.id = id;
        this.name = name;
        this.teamName = teamName;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}


