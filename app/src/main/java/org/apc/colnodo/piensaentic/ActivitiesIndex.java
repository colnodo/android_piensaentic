package org.apc.colnodo.piensaentic;

import android.content.Context;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

/**
 * Created by apple on 11/6/16.
 */

public class ActivitiesIndex {

    private ArrayList<Activity> mIndex = new ArrayList<>();
    private Context mContext;

    public class Activity{

        public Activity(ArrayList<Fragment> fragments, String activity_name,
                        int background_id, int pager_indicator_id ){
            mFragments = fragments;
            mActivity_name = activity_name;
            mBackground_id = background_id;
            mPager_indicator_id = pager_indicator_id;
        }

        public ArrayList<Fragment> mFragments;
        public String mActivity_name;
        public int mBackground_id;
        public int mPager_indicator_id;

    }

    public void setActivities(Context ctx){

        mContext = ctx;

        ArrayList<Fragment> list = new ArrayList<>();
        list.add(BlankFragment.newInstance("Primero", "Primero"));
        list.add(BlankFragment.newInstance("Segundo", "Segundo"));
        list.add(BlankFragment.newInstance("Tercero", "Tercero"));

        ArrayList<Fragment> list2 = new ArrayList<>();
        list2.add(BlankFragment.newInstance("Primero2", "Primero"));
        list2.add(BlankFragment.newInstance("Segundo2", "Segundo"));
        list2.add(BlankFragment.newInstance("Tercero2", "Tercero"));
        ArrayList<Fragment> list3 = new ArrayList<>();

        list3.add(BlankFragment.newInstance("Primero3", "Primero"));
        list3.add(BlankFragment.newInstance("Segundo3", "Segundo"));
        list3.add(BlankFragment.newInstance("Tercero3", "Tercero"));

        Activity act1 = new Activity(list, "Act1",getResourceId("act1_fondo"),getResourceId("select_pager_indicator_blue"));
        Activity act2 = new Activity(list2, "Act2",getResourceId("intro_fondo"),getResourceId("select_pager_indicator_red"));
        Activity act3 = new Activity(list3, "Act3",getResourceId("act1_fondo"),getResourceId("select_pager_indicator_red"));

        mIndex.add(act1);
        mIndex.add(act2);
        //mIndex.add(act3);
    }

    public Activity getActivity(int index){
        return mIndex.get(index);
    }


    public int getResourceId(String resource_name){
        return mContext.getResources().getIdentifier(resource_name,"drawable",mContext.getPackageName());
    }


}
