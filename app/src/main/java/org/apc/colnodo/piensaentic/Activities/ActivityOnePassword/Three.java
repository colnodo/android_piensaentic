package org.apc.colnodo.piensaentic.Activities.ActivityOnePassword;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apc.colnodo.piensaentic.R;

/**
 * Created by apple on 11/9/16.
 */

public class Three extends Fragment {
    RelativeLayout mFragmentContentSpace;
    TextView mTvTittle, mTvPasswordTag, mTVPasswordField;
    ImageView mImStep, mImPhrase;
    private Context mCtx;

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
        mImStep.setImageResource(R.drawable.pantalla_10_paso3);
        final LayoutInflater inflater_content = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view_content = inflater_content.inflate(R.layout.activity_one_two, null, false);
        mFragmentContentSpace.removeAllViews();
        mFragmentContentSpace.addView(view_content);
        mImPhrase = (ImageView) mFragmentContentSpace.findViewById(R.id.im_password2);
        mImPhrase.setVisibility(View.GONE);
        mTvTittle = (TextView) mFragmentContentSpace.findViewById(R.id.tv_password_two_tittle);
        mTvPasswordTag = (TextView) mFragmentContentSpace.findViewById(R.id.tv_password_two_note);
        mTVPasswordField = (TextView)mFragmentContentSpace.findViewById(R.id.tv_password_two_dummy);
        mTvTittle.setText(R.string.activityone3_tittle);
        mTvPasswordTag.setText(R.string.password_strong);
        mTVPasswordField.setText(R.string.password_dummy_strong);

        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        mCtx = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
