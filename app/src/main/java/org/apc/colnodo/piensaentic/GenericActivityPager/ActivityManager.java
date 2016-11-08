package org.apc.colnodo.piensaentic.GenericActivityPager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import org.apc.colnodo.piensaentic.IndexManagement.Home;
import org.apc.colnodo.piensaentic.R;

import java.util.ArrayList;

/**
 * Created by apple on 11/5/16.
 * Class used to extend in the creation of a especific activity. It allows the activity has
 * a pager view, a pager adapter, a pager indicator.
 */

public class ActivityManager extends Fragment implements ViewPager.OnPageChangeListener {

    private RadioGroup mRadioGroup;
    private ArrayList<Fragment> mFragmentList;
    private int mSize;
    private int mBackground;
    private int mPagerIndicator;
    private String mActivityName;
    private RelativeLayout mViewBackground;
    public CustomViewPager mViewPager;
    private int mPagerIndicatorsSpace;
    private boolean mAllowedToContinue = true;



    public ActivityManager(){

    }


    public void setArguments(ArrayList<Fragment> fragmentList, String activityName, int background, int pagerIndicator){
        mFragmentList = fragmentList;
        mSize = mFragmentList.size();
        mBackground = background;
        mPagerIndicator = pagerIndicator;
        mActivityName = activityName;
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mPagerIndicatorsSpace = (int) getResources().getDimension(R.dimen.padding_left_page_indicator);
    }


    private void createPagerIndicators(){
        for (int i = 0; i< mSize; i++){
            RadioButton rbtn = new RadioButton(this.getActivity());
            rbtn.setId(i);
            rbtn.setButtonDrawable(mPagerIndicator);
            rbtn.setPadding(mPagerIndicatorsSpace,0,0,0);
            if (i == 0){
                rbtn.setChecked(true);
            } else {
                rbtn.setChecked(false);
            }
            mRadioGroup.addView(rbtn);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    public void onPageSelected(int position) {
        mRadioGroup.check(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.generic_activity_pager, container, false);
        mViewPager = (CustomViewPager) view.findViewById(R.id.viewPager_activity);
        mViewBackground = (RelativeLayout)view.findViewById(R.id.generic_activity_background);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.radiogroup);
        createPagerIndicators();
        mViewBackground.setBackgroundResource(mBackground);

        try {
            mViewPager.setAdapter(new PagerViewManager(this.getActivity(), getChildFragmentManager(), mFragmentList));
            mViewPager.addOnPageChangeListener(this);
        } catch (Exception ea){
            ea.printStackTrace();
            Intent intent = new Intent();
            intent.setClass(this.getActivity(), Home.class);
            startActivity(intent);
        }
        return view;
    }

}
