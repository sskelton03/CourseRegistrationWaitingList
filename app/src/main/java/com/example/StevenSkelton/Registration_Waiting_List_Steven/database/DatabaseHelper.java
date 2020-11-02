package com.example.StevenSkelton.Registration_Waiting_List_Steven.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.StevenSkelton.Registration_Waiting_List_Steven.database.model.CourseName;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "courseDatabase_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create Course table
        db.execSQL(CourseName.CREATE_TABLE);
    }


    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + CourseName.tName);

        // Create tables again
        onCreate(db);
    }


    public long insertStudent(String PriorityCase, String TableStudent, String TableCourse) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them

        values.put(CourseName.priorityCase, PriorityCase);
        values.put(CourseName.tableStudent, TableStudent);
        values.put(CourseName.tableCourse, TableCourse);

        // insert row
        long TableID = db.insert(CourseName.tName, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return TableID;
    }


    public CourseName getStudent(long TableID) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(CourseName.tName,
                new String[]{CourseName.tableID, CourseName.tableTIME, CourseName.priorityCase, CourseName.tableStudent, CourseName.tableCourse},
                CourseName.tableID + "=?",
                new String[]{String.valueOf(TableID)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare course object
        CourseName TableCourse = new CourseName(
                cursor.getInt(cursor.getColumnIndex(CourseName.tableID)),
                cursor.getString(cursor.getColumnIndex(CourseName.tableTIME)),
                cursor.getString(cursor.getColumnIndex(CourseName.priorityCase)),
                cursor.getString(cursor.getColumnIndex(CourseName.tableStudent)),
                cursor.getString(cursor.getColumnIndex(CourseName.tableCourse)));

        // close the db connection
        cursor.close();

        return TableCourse;
    }


    public List<CourseName> getAllSubject() {
        List<CourseName> Subject = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + CourseName.tName + " ORDER BY " +
                CourseName.tableTIME + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CourseName course = new CourseName();
                course.setTABLEID(cursor.getInt(cursor.getColumnIndex(CourseName.tableID)));
                course.setTableCOURSE(cursor.getString(cursor.getColumnIndex(CourseName.tableCourse)));
                course.setTableSTUDENT(cursor.getString(cursor.getColumnIndex(CourseName.tableStudent)));
                course.setPriorityCASE(cursor.getString(cursor.getColumnIndex(CourseName.priorityCase)));
                course.setTableTIME(cursor.getString(cursor.getColumnIndex(CourseName.tableTIME)));

                Subject.add(course);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return Subject list
        return Subject;
    }


    public int getStudentsCount() {
        String countQuery = "SELECT  * FROM " + CourseName.tName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }


    public int updateCourseName(CourseName TableCourse) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CourseName.tableCourse, TableCourse.getTableCOURSE());
        values.put(CourseName.tableStudent, TableCourse.getTableSTUDENT());
        values.put(CourseName.priorityCase, TableCourse.getPriorityCASE());

        // updating row
        return db.update(CourseName.tName, values, CourseName.tableID + " = ?",
                new String[]{String.valueOf(TableCourse.getTABLEID())});
    }


    public void deleteCourse(CourseName TableCourse) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CourseName.tName, CourseName.tableID + " = ?",
                new String[]{String.valueOf(TableCourse.getTABLEID())});
        db.close();
    }
}
