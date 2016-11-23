package org.apc.colnodo.piensaentic.Activities.Intro;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import org.apc.colnodo.piensaentic.Activities.AboutMe.Two;
import org.apc.colnodo.piensaentic.GenericActivityPager.ActivityManager;
import org.apc.colnodo.piensaentic.GenericActivityPager.CustomViewPager;
import org.apc.colnodo.piensaentic.IndexManagement.ActivitiesIndex;
import org.apc.colnodo.piensaentic.IndexManagement.FragmentBookInterface;
import org.apc.colnodo.piensaentic.IndexManagement.Home;
import org.apc.colnodo.piensaentic.R;
import org.apc.colnodo.piensaentic.Utils.LocalConstants;
import org.apc.colnodo.piensaentic.Utils.PiensaEnTic;
import org.apc.colnodo.piensaentic.Utils.UtilsFunctions;

import java.util.ArrayList;

import static android.R.attr.name;

public class IntroActivity extends AppCompatActivity implements FragmentBookInterface{

    private ActivityManager mActualFragment;
    private final static String INTRO_ACTIVITY_NAME = "Intro";
    private final static String INTRO_ACTIVITY_BACKGROUND = "intro_fondo";
    private final static String INTRO_ACTIVITY_PAGER_INDICATOR = "select_pager_indicator_red";
    private Tracker mTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        PiensaEnTic application = (PiensaEnTic) getApplication();
        mTracker = application.getDefaultTracker();
        mTracker.setScreenName(INTRO_ACTIVITY_NAME);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        startIntroPager();
        if (UtilsFunctions.getSharedBoolean(this, LocalConstants.INTRO_VIEWED)){
            finishedActivity(true);
        }
    }

    private void startIntroPager(){
        ActivitiesIndex.Activity intro = new ActivitiesIndex.Activity();
        intro.mActivity_name = INTRO_ACTIVITY_NAME;
        intro.mPager_indicator_id = getResources().getIdentifier(INTRO_ACTIVITY_PAGER_INDICATOR,"drawable",getPackageName());
        intro.mBackground_id = getResources().getIdentifier(INTRO_ACTIVITY_BACKGROUND,"drawable",getPackageName());
        intro.mFragments = getFragments();

        mActualFragment = new ActivityManager();
        mActualFragment.setArguments(intro.mFragments, intro.mActivity_name,
                intro.mBackground_id, intro.mPager_indicator_id);
        getSupportFragmentManager().beginTransaction().replace(R.id.ly_content_intro, mActualFragment).commit();
    }


    private ArrayList<Fragment> getFragments(){
        ArrayList<Fragment> list = new ArrayList<>();
        IntroOne one = IntroOne.newInstance();
        IntroOne two = IntroOne.newInstance();
        one.setType(LocalConstants.INTRO_ONE);
        two.setType(LocalConstants.INTRO_TWO);
        list.add(one);
        list.add(two);
        return list;
    }

    @Override
    public void finishedActivity(boolean is_finished) {
        if (is_finished){
            UtilsFunctions.saveSharedBoolean(this, LocalConstants.INTRO_VIEWED,true);
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
            this.finish();
        }

    }

    @Override
    public void changeMenuItem(int r_id) {

    }

    @Override
    public void isAllowedToContinue(boolean mAllowedToContinue) {

    }

    @Override
    public void changePagerBackground(int color) {

    }

    @Override
    public void registerUserAccepted() {

    }
}
