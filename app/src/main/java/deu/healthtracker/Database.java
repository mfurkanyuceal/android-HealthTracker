package deu.healthtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "HealthTracker";
    private static final int VERSION = 1;

    public Database(Context c) {
        super(c, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PERSON_INFO (id Integer primary key autoincrement,name Text,surname Text,birthDate Text,height Integer,weight Real, sex Text,activity Text )");
        db.execSQL("CREATE TABLE ACTIVITIES( id integer primary key autoincrement,pid integer,date text,bloodPressure real,bloodSugar real,heartRate real,weight real)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists  PERSON_INFO");
        db.execSQL("drop table if exists  ACTIVITIES");
        onCreate(db);
    }


    public void updatePerson(int personid, Person person) {

        SQLiteDatabase wrdb = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", person.getName());
        values.put("surname", person.getSurname());
        values.put("birthDate", person.getBirthDate());
        values.put("height", person.getHeight());
        values.put("weight", person.getWeight());
        values.put("sex", person.getSex());
        values.put("activity", person.getActivity());

        wrdb.update("PERSON_INFO", values, "id" + "=?", new String[]{Integer.toString(personid)});

        wrdb.close();
    }


    public void deletePerson(int personid) {

        SQLiteDatabase rddb = getReadableDatabase();

        rddb.delete("PERSON_INFO", "id" + "=?", new String[]{Integer.toString(personid)});

    }


    public void addPerson(Person person) {
        SQLiteDatabase dbwr = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", person.getName());
        values.put("surname", person.getSurname());
        values.put("birthDate", person.getBirthDate());
        values.put("height", person.getHeight());
        values.put("weight", person.getWeight());
        values.put("sex", person.getSex());
        values.put("activity", person.getActivity());

        int lastid = (int) dbwr.insertOrThrow("PERSON_INFO", null, values);
        person.setId(lastid);
    }

    public Person getPerson() {

        SQLiteDatabase dbwr = getWritableDatabase();

        String columns[] = {"name", "surname", "birthDate", "height", "weight", "sex", "activity"};
        Cursor c = dbwr.query("PERSON_INFO", columns, null, null, null, null, null);
        c.moveToFirst();

        String name = c.getString(0);
        String surname = c.getString(1);
        String birthDate = c.getString(2);
        int height = c.getInt(3);
        Double weight = c.getDouble(4);
        String sex = c.getString(5);
        String activity = c.getString(6);


        Person person = new Person(name, surname, birthDate, height, weight, sex, activity);


        return person;
    }


    public void addActivity(Activity activity) {
        SQLiteDatabase dbwr = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("pid", 1);
        values.put("date", activity.getDate());
        values.put("bloodPressure", activity.getBloodPressure());
        values.put("bloodSugar", activity.getBloodSugar());
        values.put("heartRate", activity.getHeartRate());
        values.put("weight", activity.getWeight());

        int lastid = (int) dbwr.insertOrThrow("ACTIVITIES", null, values);
        activity.setId(lastid);

    }

    public void deleteActivity(int activityid) {

        SQLiteDatabase rddb = getReadableDatabase();

        rddb.delete("ACTIVITIES", "id" + "=?", new String[]{Integer.toString(activityid)});

    }

    private String[] activityColumns = {"date", "bloodPressure", "bloodSugar", "heartRate", "weight"};

    private Cursor getActivityData(int activityid) {

        SQLiteDatabase dbwr = getWritableDatabase();

        Cursor read = dbwr.query("ACTIVITIES", activityColumns, "id" + "=?", new String[]{Integer.toString(activityid)}, null, null, null);

        return read;

    }

    public Activity getActivity(int activityid) {

        Cursor cursor = getActivityData(activityid);

        Activity activity = new Activity("", 0, 0, 0, 0);

        activity.setDate(cursor.getString(cursor.getColumnIndex("date")));
        activity.setBloodPressure(cursor.getDouble(cursor.getColumnIndex("bloodPressure")));
        activity.setBloodSugar(cursor.getDouble(cursor.getColumnIndex("bloodSugar")));
        activity.setHeartRate(cursor.getInt(cursor.getColumnIndex("heartRate")));
        activity.setWeight(cursor.getDouble(cursor.getColumnIndex("weight")));

        return activity;
    }


    public List<Activity> getActivityList() {

        List<Activity> activityList = new ArrayList<>();

        SQLiteDatabase dbwr = getWritableDatabase();

        String columns[] = {"date", "bloodPressure", "bloodSugar", "heartRate", "weight"};
        Cursor c = dbwr.query("ACTIVITIES", columns, null, null, null, null, "id");
        c.moveToFirst();
        while (!c.isAfterLast()) {
            String date = c.getString(0);
            Double bloodPressure = c.getDouble(1);
            Double bloodSugar = c.getDouble(2);
            Double heartRate = c.getDouble(3);
            Double weight = c.getDouble(4);

            Activity k = new Activity(date, bloodPressure, bloodSugar, heartRate, weight);
            activityList.add(k);
            c.moveToNext();
        }

        return activityList;
    }


}
