package org.apc.colnodo.piensaentic.Activities.ActivityThreeMyPocket;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.apc.colnodo.piensaentic.Activities.AboutMe.*;
import org.apc.colnodo.piensaentic.Activities.AboutMe.Two;
import org.apc.colnodo.piensaentic.IndexManagement.FragmentBookInterface;
import org.apc.colnodo.piensaentic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 11/17/16.
 */

public class Ten extends Fragment implements View.OnClickListener{


    Context mContext;
    One.HomeLayoutChange changeLayout;
    public FragmentBookInterface fragmentBookInterface;
    ImageView mImLetsContinue;
    View mView;

    public Ten(){}

    public static Ten newInstance() {
        Ten fragment = new Ten();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_three_ten, container, false);
        mImLetsContinue = (ImageView) mView.findViewById(R.id.iv_lets_continue);
        mImLetsContinue.setOnClickListener(this);
        mImLetsContinue.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        view.setAlpha(0.5f);
                        break;
                    case MotionEvent.ACTION_UP:
                        view.setAlpha(1f);
                    default :
                        view.setAlpha(1f);
                }
                return false;
            }
        });
        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        changeLayout = (One.HomeLayoutChange) mContext;
        fragmentBookInterface = (FragmentBookInterface) context;
        changeLayout.changePagerBackground(R.color.magenta_alpha);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_lets_continue:
                fragmentBookInterface.finishedActivity(true);
        }
    }
}
