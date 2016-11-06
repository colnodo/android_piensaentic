package org.apc.colnodo.piensaentic;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.FrameLayout;

import org.apc.colnodo.piensaentic.GenericActivityPager.ActivityManager;

/**
 * Created by apple on 11/5/16.
 */

public class Home extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener{


    private Toolbar toolbar;
    private ActivitiesIndex mIndex = new ActivitiesIndex();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTheme(R.style.AppTheme);
        setContentView(R.layout.home);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.hamburguesa);
        setSupportActionBar(toolbar);
        mIndex.setActivities(this);
        startActivity(getNextActivityIndex());
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.general_menu, menu);
        return true;
    }

    private int getNextActivityIndex(){
        return 1;
    }

    private void startActivity(int activity_number){
        ActivitiesIndex.Activity activityActual = mIndex.getActivity(activity_number);
        ActivityManager actualFragment = new ActivityManager();
        actualFragment.setArguments(activityActual.mFragments, activityActual.mActivity_name,
                activityActual.mBackground_id, activityActual.mPager_indicator_id);
        FrameLayout frame = (FrameLayout)findViewById(R.id.ly_content_home);
        getSupportFragmentManager().beginTransaction().replace(R.id.ly_content_home, actualFragment).commit();
    }
}
