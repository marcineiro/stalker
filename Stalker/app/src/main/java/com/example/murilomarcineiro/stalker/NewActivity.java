package com.example.murilomarcineiro.stalker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.murilomarcineiro.stalker.data.DAOPerson;
import com.example.murilomarcineiro.stalker.model.Person;

public class NewActivity extends AppCompatActivity {

    private EditText et_firstName;
    private EditText et_lastName;
    private EditText et_age;
    private EditText et_job;
    private EditText et_description;
    private EditText et_birthday;
    private EditText et_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        et_firstName = (EditText) findViewById(R.id.et_firstName);
        et_lastName = (EditText) findViewById(R.id.et_lastName);
        et_age = (EditText) findViewById(R.id.et_age);
        et_job = (EditText) findViewById(R.id.et_job);
        et_description = (EditText) findViewById(R.id.et_description);
        et_birthday = (EditText) findViewById(R.id.et_birthday);
        et_phone = (EditText) findViewById(R.id.et_phone);
    }

    public void onClickSave(View view){
        String firstName = et_firstName.getText().toString();
        String lastName = et_lastName.getText().toString();
        int age = Integer.parseInt(et_age.getText().toString());
        String job = et_job.getText().toString();
        String birthday = et_birthday.getText().toString();
        String phone = et_phone.getText().toString();
        String description = et_description.getText().toString();
        Person p = new Person(firstName,lastName,job,age,phone,birthday,description);

        DAOPerson.getINSTANCE().addPerson(p);
        /*Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.PERSON_KEY, DAOPerson.getINSTANCE().getPeople());
        startActivity(intent);*/
        this.finish();
    }

    public void onClickCancel(View view){
        this.finish();
    }
}
