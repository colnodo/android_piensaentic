package org.apc.colnodo.piensaentic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.apc.colnodo.piensaentic.GenericActivityPager.ActivityManager;
import org.apc.colnodo.piensaentic.IndexManagement.ActivitiesIndex;
import org.apc.colnodo.piensaentic.IndexManagement.RightMenuFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 11/5/16.
 */

public class Home extends AppCompatActivity implements View.OnClickListener, RightMenuFragment.OnOptionRightMenuClicked{


    private DrawerLayout mRightMenu;
    private ImageView mIbMenu;
    private LinearLayout mLyMenuContainer;
    private ActivitiesIndex mIndex = new ActivitiesIndex();
    private List<String> activitiesList = new ArrayList();


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
        return 0;
    }

    private void startActivity(int activity_number){
        ActivitiesIndex.Activity activityActual = mIndex.getActivity(activity_number);
        ActivityManager actualFragment = new ActivityManager();
        actualFragment.setArguments(activityActual.mFragments, activityActual.mActivity_name,
                activityActual.mBackground_id, activityActual.mPager_indicator_id);
        getSupportFragmentManager().beginTransaction().replace(R.id.ly_content_home, actualFragment).commit();
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
}
