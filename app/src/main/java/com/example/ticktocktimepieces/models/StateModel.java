package com.example.ticktocktimepieces.models;

public class StateModel {

    String name;
    public StateModel(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return name;
    }
}

