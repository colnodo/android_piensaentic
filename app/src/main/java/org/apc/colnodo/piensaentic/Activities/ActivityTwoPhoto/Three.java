package org.apc.colnodo.piensaentic.Activities.ActivityTwoPhoto;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apc.colnodo.piensaentic.IndexManagement.FragmentBookInterface;
import org.apc.colnodo.piensaentic.R;
import org.apc.colnodo.piensaentic.Utils.LocalConstants;

/**
 * Created by apple on 11/12/16.
 */

public class Three extends Fragment implements View.OnClickListener{

    RelativeLayout mFragmentContentSpace;
    ImageView mImStep, mImContinue;
//    TextView mTvTittle;
    private Context mCtx;
    public FragmentBookInterface fragmentBookInterface;

    public Three(){

    }

    public static Three newInstance() {
        Three fragment = new Three();
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
        mImStep =  (ImageView)view.findViewById(R.id.imageView_step);
        mImStep.setVisibility(View.GONE);
        //final LayoutInflater inflater_content = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view_content = inflater.inflate(R.layout.activity_two_three, null, false);
        mFragmentContentSpace.removeAllViews();
        mFragmentContentSpace.addView(view_content);
        TextView textView1 =(TextView)mFragmentContentSpace.findViewById(R.id.textView5);
        TextView textView2 =(TextView)mFragmentContentSpace.findViewById(R.id.textView3);

        Spanned tittleString = null;
        tittleString = Html.fromHtml("<FONT COLOR=#ffffff> <a href=\""+ LocalConstants.URL_WIKIPEDIA +"\">"
                + mCtx.getString(R.string.activitytwo3_wikipedia) + "</a></font>"
        );

        Spanned tittleString2 = null;
        tittleString2 = Html.fromHtml("<FONT COLOR=#ffffff><a href=\""+ LocalConstants.URL_ICANN +"\">"
                + mCtx.getString(R.string.activitytwo3_ICANN) + "</a></font>"
        );

        textView1.setText(tittleString);
        textView2.setText(tittleString2);
        textView1.setClickable(true);
        textView1.setMovementMethod (LinkMovementMethod.getInstance());
        textView2.setClickable(true);
        textView2.setMovementMethod (LinkMovementMethod.getInstance());

        mImContinue = (ImageView)mFragmentContentSpace.findViewById(R.id.iv_lets_continue);
        mImContinue.setOnClickListener(this);
        mImContinue.setOnTouchListener(new View.OnTouchListener() {
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
        return view;
    }


    public void onAttach(Context context) {
        super.onAttach(context);
        mCtx = context;
        fragmentBookInterface = (FragmentBookInterface) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_lets_continue:
                fragmentBookInterface.finishedActivity(true);
                break;
            default:
                break;
        }

    }
}
