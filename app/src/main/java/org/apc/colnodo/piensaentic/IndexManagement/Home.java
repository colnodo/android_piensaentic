package org.apc.colnodo.piensaentic.IndexManagement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.apc.colnodo.piensaentic.Activities.AboutMe.One;
import org.apc.colnodo.piensaentic.Activities.AboutMe.Two;
import org.apc.colnodo.piensaentic.Activities.ActivityOnePassword.Four;
import org.apc.colnodo.piensaentic.GenericActivityPager.ActivityManager;
import org.apc.colnodo.piensaentic.GenericActivityPager.CustomViewPager;
import org.apc.colnodo.piensaentic.R;
import org.apc.colnodo.piensaentic.Utils.UtilsFunctions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 11/5/16.
 */

public class Home extends AppCompatActivity implements View.OnClickListener,
        RightMenuFragment.OnOptionRightMenuClicked, One.fragmentValidations,
        Two.ActivityFinished,CustomViewPager.OnPageChangeListener,
        Four.FragmentActivityActions {


    private String TAG = this.getClass().getSimpleName();
    private DrawerLayout mRightMenu;
    private ImageView mIbMenu;
    private LinearLayout mLyMenuContainer;
    private ActivitiesIndex mIndex = new ActivitiesIndex();
    private List<String> activitiesList = new ArrayList();
    private ActivityManager mActualFragment;
    private CustomViewPager mViewPager;
    private String mActualActivityName;

    private boolean mAllowedToContinue = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTheme(R.style.AppTheme);
        setContentView(R.layout.home);
        mIbMenu = (ImageView)findViewById(R.id.iv_hamburguesa);
        mLyMenuContainer = (LinearLayout)findViewById(R.id.ly_right_menu_container);
        mLyMenuContainer.bringToFront();
        mIbMenu.bringToFront();
        mIbMenu.setOnClickListener(this);
        mRightMenu = (DrawerLayout)findViewById(R.id.drawer_layout);
        mIndex.setActivities(this);
        startActivity(getNextActivityIndex());
        activitiesList = mIndex.getActivitiesList();
        Fragment rightMenu = RightMenuFragment.newInstance(activitiesList);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_ly_content_profile, rightMenu).commit();
    }


    @Override
    public void onRightMenuClicked(int index) {
        startActivity(index);
        mRightMenu.closeDrawer(GravityCompat.END);
    }

    private int getNextActivityIndex(){
        return mIndex.getNextActivity();
    }

    private void startActivity(int activity_number){
        if (activity_number>=0) {
            ActivitiesIndex.Activity activityActual = mIndex.getActivity(activity_number);
            mActualFragment = new ActivityManager();
            mActualFragment.setArguments(activityActual.mFragments, activityActual.mActivity_name,
                    activityActual.mBackground_id, activityActual.mPager_indicator_id);
            getSupportFragmentManager().beginTransaction().replace(R.id.ly_content_home, mActualFragment).commit();
            mActualActivityName = activityActual.mActivity_name;
        } else if(activity_number == -1){
            //TODO: send the credits activity
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.iv_hamburguesa:
                if (!mRightMenu.isDrawerOpen(GravityCompat.END)) {
                    mRightMenu.openDrawer(GravityCompat.END);
                } else
                    mRightMenu.closeDrawer(GravityCompat.END);
                break;
            default:
                break;
        }
    }

    @Override
    public void isAllowedToContinue(boolean mAllowedToContinue) {
        this.mAllowedToContinue = mAllowedToContinue;
        Log.d(TAG, "AllowedToContinueSetted: " + this.mAllowedToContinue);
        mViewPager = mActualFragment.mViewPager;
        mViewPager.setPagingEnabled(this.mAllowedToContinue);
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                Log.d(TAG, "Dragging Started");
                if (!mAllowedToContinue){
                    Toast.makeText(this, R.string.fields_required_missing, Toast.LENGTH_LONG).show();
                }
                break;
            case ViewPager.SCROLL_STATE_IDLE:
                break;
            case ViewPager.SCROLL_STATE_SETTLING:
                break;
            default:
                break;
        }
    }

    @Override
    public void activityFinish(boolean isFinished) {
        saveProgress();
        startActivity(getNextActivityIndex());
    }

    private void saveProgress() {
        UtilsFunctions.saveSharedBoolean(this, mActualActivityName, true);
    }

    @Override
    public void nextFragment() {
        int current = mViewPager.getCurrentItem();
        mViewPager.setCurrentItem(current + 1, true);
    }
}
