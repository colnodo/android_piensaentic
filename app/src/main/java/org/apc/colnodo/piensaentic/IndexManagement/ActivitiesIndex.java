package org.apc.colnodo.piensaentic.IndexManagement;

import android.content.Context;
import android.support.v4.app.Fragment;

import org.apc.colnodo.piensaentic.Activities.BlankFragment;
import org.apc.colnodo.piensaentic.R;
import org.apc.colnodo.piensaentic.Utils.UtilsFunctions;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by apple on 11/6/16.
 */

public class ActivitiesIndex {

    private ArrayList<Activity> mIndex = new ArrayList<>();
    private Context mContext;
    private List<String> mActivitiesTittles;
    private List<String> mActivitiesFragments;
    private List<String> mActivitiesPagerIndicators;
    private List<String> mActivitiesBackground;

    public class Activity{

        public Activity(){

        }

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


        mActivitiesTittles = Arrays.asList(mContext.getResources().getStringArray(R.array.activity_name_list));
        mActivitiesFragments = Arrays.asList(mContext.getResources().getStringArray(R.array.activity_fragment_list));
        mActivitiesPagerIndicators = Arrays.asList(mContext.getResources().getStringArray(R.array.activity_pager_list));
        mActivitiesBackground = Arrays.asList(mContext.getResources().getStringArray(R.array.activity_background_list));


        for (int i =0; i< mActivitiesTittles.size(); i++){
            Activity actual = new Activity();
            actual.mActivity_name = mActivitiesTittles.get(i);
            actual.mBackground_id = getResourceId(mActivitiesBackground.get(i));
            actual.mPager_indicator_id = getResourceId(mActivitiesPagerIndicators.get(i));
            actual.mFragments = getFragments(mActivitiesFragments.get(i));
            mIndex.add(actual);
        }
    }

    private ArrayList<Fragment> getFragments(String activitiesFragments) {
        ArrayList<Fragment> list = new ArrayList<>();
        String CurrentString = activitiesFragments;
        String[] separated = CurrentString.split(",");
        for (String fragmentName : separated){
            try {
                Class<?> act = Class.forName("org.apc.colnodo.piensaentic.Activities." + fragmentName);
                Constructor<?> ctor = act.getConstructor();
                Object object = ctor.newInstance(new Objects[]{});
                Fragment fragment = (Fragment) object;
                list.add(fragment);
            } catch(Exception ea){
                ea.printStackTrace();
                list.add(new BlankFragment());
            }
        }
        return list;
    }

    public Activity getActivity(int index){
        return mIndex.get(index);
    }

    public int getNextActivity(){
        for(int i = 0; i < mActivitiesTittles.size(); i++){
            if(!UtilsFunctions.getSharedBoolean(mContext, mActivitiesTittles.get(i))){
                return i;
            }
        }
        return -1;
    }


    public int getResourceId(String resource_name){
        return mContext.getResources().getIdentifier(resource_name,"drawable",mContext.getPackageName());
    }


    public List<String> getActivitiesList(){
        List<String> list = new ArrayList<>();
        for(int i = 0 ; i< mIndex.size() ; i++){
            list.add(mIndex.get(i).mActivity_name);
        }
        return list;
    }


}
