package org.apc.colnodo.piensaentic.GenericActivityPager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by apple on 11/5/16.
 */

public class PagerViewManager extends FragmentPagerAdapter {

    private ArrayList<Fragment> mFragmentList;
    private int mSize;


    public PagerViewManager(Context ctx, FragmentManager fm, ArrayList<Fragment> fragmentsList) {
        super(fm);
        mFragmentList = fragmentsList;
        mSize = mFragmentList.size();
    }

    @Override
    public Fragment getItem(int pos) {

        if (mSize > 0) {
            return mFragmentList.get(pos);
        } else return mFragmentList.get(0);

    }

    @Override
    public int getCount() {

        return mSize;

    }
}
