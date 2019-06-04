package com.example.murilomarcineiro.stalker;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.murilomarcineiro.stalker.model.Person;
import com.example.murilomarcineiro.stalker.view.EditPersonDialogFragment;
import com.example.murilomarcineiro.stalker.view.RemovePersonDialogFragment;

public class ShowPersonActivity extends AppCompatActivity {

    private TextView tv_fnResult;
    private TextView tv_lnResult;
    private TextView tv_ageResult;
    private TextView tv_jobResult;
    private TextView tv_birthdayResult;
    private TextView tv_phoneResult;
    private TextView tv_descriptionResult;
    private Person person;

    public static final String MODE_KEY = "com.example.murilomarcineiro.stalker.MODE";
    public static final String PERSON_KEY = "com.example.murilomarcineiro.stalker.PERSON";
    public static final int MODE_EDIT = 1;
    public static final int MODE_REMOVE = 2;
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

        Intent i = getIntent();
        this.person = i.getParcelableExtra(MainActivity.PERSON_KEY);


        tv_fnResult.setText(person.getFirstName());
        tv_lnResult.setText(person.getLastName());
        tv_ageResult.setText(Integer.toString(person.getAge()));
        tv_birthdayResult.setText(person.getBirthday());
        tv_descriptionResult.setText(person.getDescription());
        tv_phoneResult.setText(person.getPhone());
        tv_jobResult.setText(person.getJob());
//        iv_photo.setImageBitmap(person.getPhoto());
    }

    public void onClickEdit(View view){
        FragmentManager fragmentManager = getSupportFragmentManager();
        EditPersonDialogFragment ecdf = EditPersonDialogFragment.newInstance(this.person);
        ecdf.show(fragmentManager, "ecdf");
    }

    public void onClickSeePhotos(View view){
        Intent intent = new Intent(this,PhotosActivity.class);
        intent.putExtra(NewActivity.LIST_PATH_KEY,this.person.getPaths());
        startActivity(intent);
    }

    public void onClickRemove(View view){
        FragmentManager fragmentManager = getSupportFragmentManager();
        RemovePersonDialogFragment removePersonDialogFragment = RemovePersonDialogFragment.newInstance(this.person);
        removePersonDialogFragment.show(fragmentManager, "rpdf");
    }
}
