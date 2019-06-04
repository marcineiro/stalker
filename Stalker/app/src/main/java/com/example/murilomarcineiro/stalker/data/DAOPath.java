package com.example.murilomarcineiro.stalker.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DAOPath {

    public static final void insert(DBHelper dbHelper, ArrayList<String> paths, long id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues cv;

        for(int i = 0;i<paths.size();i++){
            cv = new ContentValues();
            cv.put(DBSchema.Path.PATH, paths.get(i));
            cv.put(DBSchema.Path.PERSON, Long.toString(id));
            db.insert(DBSchema.Path.TABLE,null,cv);
        }
        db.close();
    }

    public static void remove(DBHelper dbHelper, long id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] args = new String[]{Long.toString(id)};

        db.delete(DBSchema.Path.TABLE, DBSchema.Path.PERSON+" = ?", args);
        db.close();
    }

    public static final ArrayList<String> getPaths(DBHelper dbHelper, long id){
        ArrayList<String> paths = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] args = new String[]{
                Long.toString(id)
        };

        Cursor cursor = db.query(DBSchema.Path.TABLE,null,DBSchema.Path.PERSON+" = ?", args,null, null,DBSchema.Path.PATH);
        while (cursor.moveToNext()){
            paths.add(cursor.getString(cursor.getColumnIndex(DBSchema.Path.PATH)));
        }
        return paths;
    }
}
