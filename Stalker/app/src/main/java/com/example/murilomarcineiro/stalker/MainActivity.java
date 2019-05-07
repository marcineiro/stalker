package com.example.murilomarcineiro.stalker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.murilomarcineiro.stalker.data.DAOPerson;
import com.example.murilomarcineiro.stalker.model.Person;


public class MainActivity extends AppCompatActivity {

    public static final String PERSON_KEY = "com.example.murilomarcineiro.stalker";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClickList(View view){
        Intent intent = new Intent(getBaseContext(), ListActivity.class);
        intent.putExtra(PERSON_KEY, DAOPerson.getINSTANCE().getPeople());
        startActivity(intent);
    }

    public void onClickNew(View view){
        Intent intent = new Intent(this, NewActivity.class);
        startActivity(intent);
    }
}
