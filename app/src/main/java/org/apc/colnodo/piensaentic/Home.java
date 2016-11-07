package org.apc.colnodo.piensaentic;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import org.apc.colnodo.piensaentic.GenericActivityPager.ActivityManager;
import org.apc.colnodo.piensaentic.IndexManagement.ActivitiesIndex;
import org.apc.colnodo.piensaentic.IndexManagement.RightMenuFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 11/5/16.
 */

public class Home extends AppCompatActivity implements RightMenuFragment.OnOptionRightMenuClicked{


    private Toolbar mToolbar;
    private DrawerLayout mRightMenu;
    private ActivitiesIndex mIndex = new ActivitiesIndex();
    private List<String> activitiesList = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTheme(R.style.AppTheme);
        setContentView(R.layout.home);

        mRightMenu = (DrawerLayout)findViewById(R.id.drawer_layout);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar bar = getSupportActionBar();
        bar.setDisplayShowTitleEnabled(false);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.general_menu, menu);
        return true;
    }

    private int getNextActivityIndex(){
        return 0;
    }

    private void startActivity(int activity_number){
        ActivitiesIndex.Activity activityActual = mIndex.getActivity(activity_number);
        ActivityManager actualFragment = new ActivityManager();
        actualFragment.setArguments(activityActual.mFragments, activityActual.mActivity_name,
                activityActual.mBackground_id, activityActual.mPager_indicator_id);

        FrameLayout frame = (FrameLayout)findViewById(R.id.ly_content_home);
        getSupportFragmentManager().beginTransaction().replace(R.id.ly_content_home, actualFragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                if (!mRightMenu.isDrawerOpen(GravityCompat.END)) {
                    mRightMenu.openDrawer(GravityCompat.END);
                } else
                    mRightMenu.closeDrawer(GravityCompat.END);
                return true;
            default:
                break;
        }

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

}
