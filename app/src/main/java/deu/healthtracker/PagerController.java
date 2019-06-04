package deu.healthtracker;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerController extends FragmentPagerAdapter {
    int TabCounts;

    public PagerController(FragmentManager fm, int tabCounts) {
        super(fm);
        this.TabCounts = tabCounts;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new Tab1();
            case 1:
                return new Tab2();
            case 2:
                return new Tab3();
            case 3:
                return new Tab4();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return TabCounts;
    }
}
