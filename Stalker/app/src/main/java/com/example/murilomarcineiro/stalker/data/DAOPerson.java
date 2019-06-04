package com.example.murilomarcineiro.stalker.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.murilomarcineiro.stalker.model.Person;

import java.util.ArrayList;

public class DAOPerson {
    public static final void insert(DBHelper dbHelper, Person person){
        ContentValues cv = new ContentValues();
        cv.put(DBSchema.Person.FIRST_NAME, person.getFirstName());
        cv.put(DBSchema.Person.LAST_NAME, person.getLastName());
        cv.put(DBSchema.Person.AGE, person.getAge());
        cv.put(DBSchema.Person.JOB, person.getJob());
        cv.put(DBSchema.Person.BIRTHDAY, person.getBirthday());
        cv.put(DBSchema.Person.PHONE, person.getPhone());
        cv.put(DBSchema.Person.DESCRIPTION, person.getDescription());

        // INSERT INTO contact (c_name, c_pnumber) VALUES ("fulano", "1893928");
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(DBSchema.Person.TABLE, null, cv);
        db.close();

        long id = 0;
        for(Person p : DAOPerson.getPeople(dbHelper)){
            if(p.getId()>id){
                id = p.getId();
            }
        }
        DAOPath.insert(dbHelper,person.getPaths(),id);
    }

    public static final void remove(DBHelper dbHelper, long id){
        String[] args = new String[] {
                Long.toString(id)
        };
        DAOPath.remove(dbHelper,id);
        // DELETE FROM contact WHERE _id = 1234;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DBSchema.Person.TABLE, DBSchema.Person._ID+" = ?", args);
        db.close();
    }

    public static final void update(DBHelper dbHelper, Person person){
        ContentValues cv = new ContentValues();
        cv.put(DBSchema.Person.FIRST_NAME, person.getFirstName());
        cv.put(DBSchema.Person.LAST_NAME, person.getLastName());
        cv.put(DBSchema.Person.AGE, person.getAge());
        cv.put(DBSchema.Person.JOB, person.getJob());
        cv.put(DBSchema.Person.BIRTHDAY, person.getBirthday());
        cv.put(DBSchema.Person.PHONE, person.getPhone());
        cv.put(DBSchema.Person.DESCRIPTION, person.getDescription());
        DAOPath.remove(dbHelper,person.getId());
        DAOPath.insert(dbHelper,person.getPaths(),person.getId());
        String[] args = new String[] {
                Long.toString(person.getId())
        };
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update(DBSchema.Person.TABLE, cv, DBSchema.Person._ID+" = ?", args);
        db.close();
    }

    public static final ArrayList<Person> getPeople(DBHelper dbHelper) throws SQLException{
        ArrayList<Person> people = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DBSchema.Person.TABLE,null,null,null,null,null, DBSchema.Person.FIRST_NAME);
        while (cursor.moveToNext()){
            long id = cursor.getLong(cursor.getColumnIndex(DBSchema.Person._ID));
            String firstName = cursor.getString(cursor.getColumnIndex(DBSchema.Person.FIRST_NAME));
            String lastName = cursor.getString(cursor.getColumnIndex(DBSchema.Person.LAST_NAME));
            int age = cursor.getInt(cursor.getColumnIndex(DBSchema.Person.AGE));
            String job = cursor.getString(cursor.getColumnIndex(DBSchema.Person.JOB));
            String birthday = cursor.getString(cursor.getColumnIndex(DBSchema.Person.BIRTHDAY));
            String phone = cursor.getString(cursor.getColumnIndex(DBSchema.Person.PHONE));
            String description = cursor.getString(cursor.getColumnIndex(DBSchema.Person.DESCRIPTION));

            ArrayList<String> photos = DAOPath.getPaths(dbHelper, id);
            Person person = new Person(id,lastName,job,age,phone,birthday,description,photos,firstName);
            people.add(person);
        }
        return people;
    }


    /* private static DAOPerson INSTANCE;
    private ArrayList<Person> people;

    private DAOPerson(){
        this.people = new ArrayList<>();
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
    }*/


}
