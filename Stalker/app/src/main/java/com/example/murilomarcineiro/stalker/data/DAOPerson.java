package com.example.murilomarcineiro.stalker.data;

import com.example.murilomarcineiro.stalker.model.Person;

import java.util.ArrayList;

public class DAOPerson {
    private static DAOPerson INSTANCE;
    private ArrayList<Person> people;

    private DAOPerson(){
        this.people = new ArrayList<>();
        ArrayList<Object> list =  Empacotamento.pull("stalked.dat");
        for (Object item : list) {
            this.people.add((Person)item);
        }
    }

    public static DAOPerson getINSTANCE(){
        if(INSTANCE==null)
            INSTANCE = new DAOPerson();
        return INSTANCE;
    }

    public void addPerson(Person p){
        people.add(p);
    }

    public ArrayList<Person> getPeople(){
        return people;
    }
}
