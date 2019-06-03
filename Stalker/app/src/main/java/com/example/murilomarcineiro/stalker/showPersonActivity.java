package com.example.murilomarcineiro.stalker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.murilomarcineiro.stalker.model.Person;

public class showPersonActivity extends AppCompatActivity {

    private TextView tv_fnResult;
    private TextView tv_lnResult;
    private TextView tv_ageResult;
    private TextView tv_jobResult;
    private TextView tv_birthdayResult;
    private TextView tv_phoneResult;
    private TextView tv_descriptionResult;
    private ImageView iv_photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_person);

        tv_fnResult = (TextView) findViewById(R.id.tv_fnResult);
        tv_lnResult = (TextView) findViewById(R.id.tv_lnResult);
        tv_ageResult = (TextView) findViewById(R.id.tv_ageResult);
        tv_jobResult = (TextView) findViewById(R.id.tv_jobResult);
        tv_birthdayResult = (TextView) findViewById(R.id.tv_birthdayResult);
        tv_phoneResult = (TextView) findViewById(R.id.tv_phoneResult);
        tv_descriptionResult = (TextView) findViewById(R.id.tv_descriptionResult);
        iv_photo = (ImageView) findViewById(R.id.iv_photo);

        Intent i = getIntent();
        Person person = i.getParcelableExtra(MainActivity.PERSON_KEY);


        tv_fnResult.setText(person.getFirstName());
        tv_lnResult.setText(person.getLastName());
        tv_ageResult.setText(Integer.toString(person.getAge()));
        tv_birthdayResult.setText(person.getBirthday());
        tv_descriptionResult.setText(person.getDescription());
        tv_phoneResult.setText(person.getPhone());
        tv_jobResult.setText(person.getJob());
//        iv_photo.setImageBitmap(person.getPhoto());
    }
}
