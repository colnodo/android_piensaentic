package org.apc.colnodo.piensaentic.GenericActivityPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import org.apc.colnodo.piensaentic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 11/5/16.
 * Class used to extend in the creation of a especific activity. It allows the activity has
 * a pager view, a pager adapter, a pager indicator.
 */

public class ActivityManager extends Fragment implements ViewPager.OnPageChangeListener{

    private RadioGroup mRadioGroup;
    private ArrayList<Fragment> mFragmentList;
    private int mSize;
    private int mBackground;
    private int mPagerIndicator;
    private String mActivityName;
    private RelativeLayout mViewBackground;
    private ViewPager viewPager;
    private int mPagerIndicatorsSpace;


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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

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
        viewPager = (ViewPager) view.findViewById(R.id.viewPager_activity);
        mViewBackground = (RelativeLayout)view.findViewById(R.id.generic_activity_background);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.radiogroup);
        createPagerIndicators();
        //mViewBackground.setBackground(getResources().getDrawable(mBackground));
        mViewBackground.setBackgroundResource(mBackground);
        viewPager.setAdapter(new PagerViewManager(this.getActivity(), getChildFragmentManager(), mFragmentList));
        viewPager.addOnPageChangeListener(this);
        return view;
    }
}
