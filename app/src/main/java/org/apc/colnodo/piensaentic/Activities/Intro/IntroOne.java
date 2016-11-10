package org.apc.colnodo.piensaentic.Activities.Intro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apc.colnodo.piensaentic.Activities.AboutMe.Two;
import org.apc.colnodo.piensaentic.Activities.ActivityOnePassword.Five;
import org.apc.colnodo.piensaentic.GenericActivityPager.ActivityManager;
import org.apc.colnodo.piensaentic.GenericActivityPager.CustomViewPager;
import org.apc.colnodo.piensaentic.IndexManagement.ActivitiesIndex;
import org.apc.colnodo.piensaentic.IndexManagement.Home;
import org.apc.colnodo.piensaentic.IndexManagement.RightMenuFragment;
import org.apc.colnodo.piensaentic.R;
import org.apc.colnodo.piensaentic.Utils.LocalConstants;
import org.apc.colnodo.piensaentic.Utils.UtilsFunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by apple on 11/9/16.
 */

    public class IntroOne extends Fragment implements View.OnClickListener{

        public RelativeLayout mFragmentContentSpace;
        ImageView mIvLogo;
        public TextView mTvTittle;
        public Two.ActivityFinished mFinished;
        Context mCtx;
        private String mType = null;

        public IntroOne(){

        }

        public static IntroOne newInstance() {
            IntroOne fragment = new IntroOne();
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_basic_template, container, false);
            mFragmentContentSpace = (RelativeLayout)view.findViewById(R.id.basic_content_space);
            mIvLogo =  (ImageView)view.findViewById(R.id.imageView_step);
            mIvLogo.setImageResource(R.drawable.intro_logo);
            final View view_content = inflater.inflate(R.layout.text_view_tittle, null, false);
            mTvTittle = (TextView)view_content.findViewById(R.id.tv_tittle);
            mTvTittle.setTextColor(mCtx.getResources().getColor(R.color.red_active_pager));
            mFragmentContentSpace.removeAllViews();
            mFragmentContentSpace.addView(view_content);
            switch (mType){
                case LocalConstants.INTRO_ONE:
                    mTvTittle.setText(R.string.intro1_tittle);
                    break;
                case LocalConstants.INTRO_TWO:
                    final View view1 = inflater.inflate(R.layout.image_view, null, false);
                    mFragmentContentSpace.removeAllViews();
                    mFragmentContentSpace.addView(view1);
                    ImageView im = (ImageView) mFragmentContentSpace.findViewById(R.id.iv_letstart);
                    im.setOnClickListener(this);
                    break;
                default:
                    break;
            }
            return view;
        }


        public void onAttach(Context context) {
            super.onAttach(context);
            mCtx = context;
            mFinished = (Two.ActivityFinished) mCtx;
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.iv_letstart:
                    mFinished.activityFinish(true);
            }
        }

        public void setType(String type){
            mType = type;
        }

}
