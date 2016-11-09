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
 * Created by apple on 11/8/16.
 */

public class Two extends Fragment{

    RelativeLayout mFragmentContentSpace;
    TextView mTvTittle;
    ImageView mImageView;
    private Context mCtx;

    public Two(){

    }

    public static Two newInstance() {
        Two fragment = new Two();
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
        mImageView =  (ImageView)view.findViewById(R.id.imageView_step);
        mImageView.setImageResource(R.drawable.pantalla_09_paso2);
        final LayoutInflater inflater_content = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view_content = inflater_content.inflate(R.layout.activity_one_two, null, false);
        mFragmentContentSpace.removeAllViews();
        mFragmentContentSpace.addView(view_content);
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
