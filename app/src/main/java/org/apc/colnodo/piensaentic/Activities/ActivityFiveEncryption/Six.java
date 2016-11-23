package org.apc.colnodo.piensaentic.Activities.ActivityFiveEncryption;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.apc.colnodo.piensaentic.IndexManagement.FragmentBookInterface;
import org.apc.colnodo.piensaentic.R;
import org.apc.colnodo.piensaentic.Utils.LocalConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 11/18/16.
 */

public class Six extends Fragment implements View.OnClickListener {
    Context mContext;
    List<TextView> mTvValues;
    View mView;
    FragmentBookInterface mFragmentInterface;


    public Six(){}

    public static Six newInstance() {
        Six fragment = new Six();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mTvValues = new ArrayList<>();
        mView = inflater.inflate(R.layout.activity_five_six, container, false);
        ImageView im = (ImageView)mView.findViewById(R.id.iv_lets_continue);
        TextView tv = (TextView)mView.findViewById(R.id.tv_text);
        Spanned tittleString = null;
        tittleString = Html.fromHtml(getString(R.string.activityfive6_text) +
                " <br>"+
                "<FONT COLOR=#fad648><a href=\""+ getString(R.string.activityfive6_link) +"\">"
                + getString(R.string.activityfive6_link) + "</a></font><br>"
        );
        tv.setText(tittleString);
        tv.setClickable(true);
        tv.setMovementMethod (LinkMovementMethod.getInstance());
        im.setOnClickListener(this);
        im.setOnTouchListener(new View.OnTouchListener() {
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
        mFragmentInterface = (FragmentBookInterface) mContext;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_lets_continue:
                mFragmentInterface.finishedActivity(true);
        }
    }
}
