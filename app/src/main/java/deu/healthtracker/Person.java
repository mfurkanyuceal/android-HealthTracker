package deu.healthtracker;

import android.widget.EditText;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;

public class Person {

    private int id;
    private String name;
    private String surname;
    private String birthDate;
    private int height;
    private double weight;
    private String sex;
    private String activity;
    private LinkedList<Activity> activityLinkedList;


    public Person(String name, String surname, String birthDate, int height, double weight, String sex,String activity) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.height = height;
        this.weight = weight;
        this.sex = sex;
        this.activity=activity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LinkedList<Activity> getActivityLinkedList() {
        return activityLinkedList;
    }

    public void setActivityLinkedList(LinkedList<Activity> activityLinkedList) {
        this.activityLinkedList = activityLinkedList;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getAge () {

        String [] split= birthDate.split("/");

        int year=Integer.parseInt(split[2]);
        int month=Integer.parseInt(split[1]);
        int day=Integer.parseInt(split[0]);

        GregorianCalendar cal = new GregorianCalendar();
        int y, m, d, a;

        y = cal.get(Calendar.YEAR);
        m = cal.get(Calendar.MONTH);
        d = cal.get(Calendar.DAY_OF_MONTH);
        cal.set(year, month, day);
        a = y - cal.get(Calendar.YEAR);
        if ((m < cal.get(Calendar.MONTH))
                || ((m == cal.get(Calendar.MONTH)) && (d < cal
                .get(Calendar.DAY_OF_MONTH)))) {
            --a;
        }
        if(a < 0)
            throw new IllegalArgumentException("Age < 0");
        return a;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;

    }

    public void setAge(int age) {
        GregorianCalendar cal = new GregorianCalendar();
        int y, m, d;
        y = cal.get(Calendar.YEAR);
        m = cal.get(Calendar.MONTH);
        d = cal.get(Calendar.DAY_OF_MONTH);

        this.birthDate = d+"/"+m+"/"+""+(y-age)+"";

    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
