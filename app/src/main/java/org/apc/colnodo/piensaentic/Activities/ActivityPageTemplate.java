package org.apc.colnodo.piensaentic.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.apc.colnodo.piensaentic.Activities.AboutMe.One;
import org.apc.colnodo.piensaentic.Activities.ActivityOnePassword.Four;
import org.apc.colnodo.piensaentic.R;

/**
 * Created by apple on 11/9/16.
 */

public class ActivityPageTemplate extends Fragment {

    RelativeLayout mFragmentContentSpace;
    public FragmentActivityActions mActions;
    private Context mCtx;

    public ActivityPageTemplate(){

    }

    public static ActivityPageTemplate newInstance() {
        ActivityPageTemplate fragment = new ActivityPageTemplate();
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
        mFragmentContentSpace = (RelativeLayout) view.findViewById(R.id.basic_content_space);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCtx = context;
        mActions = (FragmentActivityActions)context;
    }

    public interface FragmentActivityActions{
        void nextFragment();
        void setPagerOf();
        void activityFinished();
    }
}
