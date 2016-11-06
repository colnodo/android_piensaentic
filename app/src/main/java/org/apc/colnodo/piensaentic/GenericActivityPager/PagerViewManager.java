package org.apc.colnodo.piensaentic.GenericActivityPager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 11/5/16.
 */

public class PagerViewManager extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragmentList;
    private int size;


    public PagerViewManager(Context ctx, FragmentManager fm, ArrayList<Fragment> fragmentsList) {
        super(fm);
        fragmentList = fragmentsList;
        size = fragmentList.size();
    }

    @Override
    public Fragment getItem(int pos) {

        if (size > 0) {
            return fragmentList.get(pos);
        } else return fragmentList.get(0);

    }

    @Override
    public int getCount() {

        return size;

    }
}
