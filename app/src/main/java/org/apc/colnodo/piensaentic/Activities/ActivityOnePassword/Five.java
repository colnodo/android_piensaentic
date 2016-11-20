package org.apc.colnodo.piensaentic.Activities.ActivityOnePassword;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.apc.colnodo.piensaentic.Activities.AboutMe.*;
import org.apc.colnodo.piensaentic.Activities.AboutMe.Two;
import org.apc.colnodo.piensaentic.IndexManagement.FragmentBookInterface;
import org.apc.colnodo.piensaentic.R;
import org.apc.colnodo.piensaentic.Utils.LocalConstants;
import org.apc.colnodo.piensaentic.Utils.UtilsFunctions;

/**
 * Created by apple on 11/9/16.
 */

public class Five extends Fragment implements View.OnClickListener{

    RelativeLayout mFragmentContentSpace;
    ImageView mImStep, mImCreatePassword;
    TextView mTvTittle;
    private Context mCtx;
    public FragmentBookInterface fragmentBookInterface;

    public Five(){

    }

    public static Five newInstance() {
        Five fragment = new Five();
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
        mImStep.setVisibility(View.INVISIBLE);
        //final LayoutInflater inflater_content = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view_content = inflater.inflate(R.layout.activity_one_four, null, false);
        mFragmentContentSpace.removeAllViews();
        mFragmentContentSpace.addView(view_content);
        mTvTittle = (TextView)mFragmentContentSpace.findViewById(R.id.tv_password_two_tittle);
        mTvTittle.setText(R.string.activityone5_tittle);
        //final LayoutInflater inflater_content = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view_image = inflater.inflate(R.layout.im_fantastic, null, false);
        LinearLayout lyContent = (LinearLayout)mFragmentContentSpace.findViewById(R.id.ly_create_password);
        lyContent.removeAllViews();
        lyContent.addView(view_image);
        mImCreatePassword = (ImageView)mFragmentContentSpace.findViewById(R.id.im_create_password);
        mImCreatePassword.setImageResource(R.drawable.pantalla_12_continuemos);
        mImCreatePassword.setOnClickListener(this);
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
            case R.id.im_create_password:
                fragmentBookInterface.finishedActivity(true);
                break;
            default:
                break;
        }

    }
}
