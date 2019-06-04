package deu.healthtracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.github.mikephil.charting.charts.LineChart;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Person defaultPerson;
    BMI defaultBmi;
    EER defaultEER;
    Database db;
    Toolbar mToolbar;
    TabLayout mTabLayout;
    TabItem tabBmi;
    TabItem tabEer;
    TabItem tabChart;
    TabItem tabChart2;
    ViewPager mPager;
    PagerController mPagerController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Database(this);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Health Tracker");
        defaultPerson=new Person("DefaultName","DefaultSurname","01/01/2000",0,0,"Male","");
        db.addPerson(defaultPerson);

        defaultBmi= new BMI(defaultPerson);
        defaultEER= new EER(defaultPerson);

        mTabLayout = findViewById(R.id.tabLayout);
        tabBmi= findViewById(R.id.tab1);
        tabEer= findViewById(R.id.tab2);
        tabChart= findViewById(R.id.tab3);
        tabChart2= findViewById(R.id.tab4);

        mPager = findViewById(R.id.viewpager);

        mPagerController = new PagerController(getSupportFragmentManager(),mTabLayout.getTabCount());
        mPager.setAdapter(mPagerController);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));


    }

    public Database getDb() {
        return db;
    }
}
