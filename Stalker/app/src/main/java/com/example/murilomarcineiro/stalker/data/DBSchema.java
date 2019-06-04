package com.example.murilomarcineiro.stalker.data;

import android.provider.BaseColumns;

public class DBSchema {
    public static final class Person implements BaseColumns{
        public static final String TABLE = "person";
        public static final String FIRST_NAME =  "c_firstName";
        public static final String LAST_NAME = "c_lastName";
        public static final String AGE = "c_age";
        public static final String JOB = "c_job";
        public static final String BIRTHDAY = "c_birthday";
        public static final String PHONE = "c_phone";
        public static final String DESCRIPTION = "c_description";

        public static final String createQuery(){
            return "CREATE TABLE "+TABLE+" ("+
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FIRST_NAME + " TEXT NOT NULL, " +
                    LAST_NAME + " TEXT NOT NULL, " +
                    AGE + " INTEGER, " +
                    JOB + " TEXT NOT NULL, " +
                    BIRTHDAY + " TEXT NOT NULL, " +
                    PHONE + " TEXT NOT NULL, " +
                    DESCRIPTION + " TEXT NOT NULL" +
                    ");";
        }
    }

    public static final class Path implements BaseColumns{
        public static final String TABLE = "paths";
        public static final String PATH = "c_path";
        public static final String PERSON = "c_person";

        public static final String createQuery(){
            return "CREATE TABLE "+TABLE+" ("+
                    PATH + " TEXT NOT NULL, "+
                    PERSON + " INTEGER, " +
                    "FOREIGN KEY("+PERSON+") REFERENCES "+ Person.TABLE+"("+Person._ID+")"+
                    ");";
        }
    }
}
