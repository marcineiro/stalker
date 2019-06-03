package com.example.murilomarcineiro.stalker;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.murilomarcineiro.stalker.data.DAOPerson;
import com.example.murilomarcineiro.stalker.model.Person;
import com.example.murilomarcineiro.stalker.view.PeopleListAdapter;

import static com.example.murilomarcineiro.stalker.MainActivity.PERSON_KEY;

public class ListActivity extends AppCompatActivity implements PeopleListAdapter.PersonListener{

    private RecyclerView rv_peopleList;
    private PeopleListAdapter peopleListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        rv_peopleList = (RecyclerView) findViewById(R.id.rv_peopleList);
        peopleListAdapter = new PeopleListAdapter(this);
        rv_peopleList.setLayoutManager(new LinearLayoutManager(this));
        rv_peopleList.setHasFixedSize(true);
        rv_peopleList.setAdapter(peopleListAdapter);
        /*ll_PeopleList = (LinearLayout) findViewById(R.id.ll_peopleList);
        this.createList();*/


    }

    /*public void createList(){
        for(final Person person: DAOPerson.getINSTANCE().getPeople()){
            LinearLayout ll_personResume = (LinearLayout) getLayoutInflater().inflate(R.layout.person_resume,null);
            TextView tv_firstName = (TextView) ll_personResume.findViewById(R.id.tv_firstName);
            tv_firstName.setText(person.getFirstName());
            TextView tv_phone = (TextView) ll_personResume.findViewById(R.id.tv_phone);
            tv_phone.setText(person.getLastName());
            TextView tv_age = (TextView) ll_personResume.findViewById(R.id.tv_age);
            tv_age.setText(Integer.toString(person.getAge()));
            ll_personResume.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    *//*if(infoToast != null)
                        infoToast.cancel();
                    infoToast = Toast.makeText(getBaseContext(), person.getFirstName(),Toast.LENGTH_SHORT);
                    infoToast.show();*//*
                    Intent intent = new Intent(getBaseContext(), showPersonActivity.class);
                    intent.putExtra(PERSON_KEY, person);
                    startActivity(intent);
                }
            });
            ll_PeopleList.addView(ll_personResume);
        }
    }*/

    @Override
    public void onClickPersonListener(Person p) {
        Intent intent = new Intent(getBaseContext(), showPersonActivity.class);
        intent.putExtra(PERSON_KEY, (Parcelable) p);
        startActivity(intent);
    }
}
