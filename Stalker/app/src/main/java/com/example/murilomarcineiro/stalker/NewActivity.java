package com.example.murilomarcineiro.stalker;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.murilomarcineiro.stalker.data.DAOPerson;
import com.example.murilomarcineiro.stalker.data.DBHelper;
import com.example.murilomarcineiro.stalker.model.Person;

import java.util.ArrayList;

public class NewActivity extends AppCompatActivity {

    private EditText et_firstName;
    private EditText et_lastName;
    private EditText et_age;
    private EditText et_job;
    private EditText et_description;
    private EditText et_birthday;
    private EditText et_phone;
//    private ImageView iv_camView;
    private ArrayList<String> photos = new ArrayList<>();
//    public static final int CAMERA_REQUEST_CODE = 1234;//  < 2^16 - 1
    public static final String LIST_PATH_KEY = "com.example.murilomarcineiro.person.paths";

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
//        iv_camView = (ImageView) findViewById(R.id.im_photo);

        if(getIntent().getExtras()!=null){
            ArrayList<String> a = (ArrayList<String>) getIntent().getExtras().get(this.LIST_PATH_KEY);
            if(a!=null) {
                this.photos = a;
//                Toast.makeText(this, photos.get(0), Toast.LENGTH_SHORT).show();
            }
            Person p = (Person) getIntent().getExtras().get(MainActivity.PERSON_KEY);
            if(p!=null){
                et_firstName.setText(p.getFirstName());
                et_lastName.setText(p.getLastName());
                et_age.setText(Integer.toString(p.getAge()));
                et_job.setText(p.getJob());
                et_description.setText(p.getDescription());
                et_birthday.setText(p.getBirthday());
                et_phone.setText(p.getPhone());
            }
        }
    }

    public void onClickSave(View view){
        String firstName = et_firstName.getText().toString();
        String lastName = et_lastName.getText().toString();
        int age = Integer.parseInt(et_age.getText().toString());
        String job = et_job.getText().toString();
        String birthday = et_birthday.getText().toString();
        String phone = et_phone.getText().toString();
        String description = et_description.getText().toString();


        if(!firstName.isEmpty() && !lastName.isEmpty()){
            DBHelper dbHelper = new DBHelper(this);
            Person p = new Person(Long.parseLong(Integer.toString(DAOPerson.getPeople(dbHelper).size())), lastName, job, age, phone, birthday, description, this.photos, firstName);
            DAOPerson.insert(dbHelper, p);
            finish();
        }

        finish();
    }

    public void onClickPhoto(View view){
        Intent intent = new Intent(this, GalleryActivity.class);
        Bundle b = getIntent().getExtras();
        if(b!=null){
            if(b.get(MainActivity.PERSON_KEY)!=null){

            } else {
                if(!this.photos.isEmpty()) {
                    intent.putExtra(this.LIST_PATH_KEY, this.photos);
                }
            }
        }
        String firstName = et_firstName.getText().toString();
        String lastName = et_lastName.getText().toString();
        String i = et_age.getText().toString();
        int age = 0;
        if(!i.isEmpty())
            age = Integer.parseInt(i);
        String job = et_job.getText().toString();
        String birthday = et_birthday.getText().toString();
        String phone = et_phone.getText().toString();
        String description = et_description.getText().toString();
        Person p = new Person(0, lastName, job, age, phone, birthday, description, this.photos, firstName);
        intent.putExtra(MainActivity.PERSON_KEY, (Parcelable) p);
        startActivity(intent);
        finish();
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK && data != null){
            Bundle extras = data.getExtras();
            if(extras != null && extras.containsKey("data")){
                Bitmap bmp = (Bitmap) extras.get("data");
                iv_camView.setImageBitmap(bmp);
            }
        }
    }*/

    public void onClickCancel(View view){
        this.finish();
    }
}
