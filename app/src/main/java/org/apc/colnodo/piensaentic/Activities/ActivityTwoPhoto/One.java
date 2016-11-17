package org.apc.colnodo.piensaentic.Activities.ActivityTwoPhoto;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apc.colnodo.piensaentic.Activities.AboutMe.Two;
import org.apc.colnodo.piensaentic.R;
import org.apc.colnodo.piensaentic.Utils.LocalConstants;
import org.apc.colnodo.piensaentic.Utils.PhotoPicker;
import org.apc.colnodo.piensaentic.Utils.UtilsFunctions;

/**
 * Created by apple on 11/10/16.
 */

public class One extends Fragment implements  View.OnClickListener{

    private RelativeLayout mFullContentSpace;
    private TextView mTvName, mTvNickname, mTvEmail, mTvBirthdate;
    private ImageView mIvHeader, mImButton;
    private Two.ActivityFinished mIsFinished;
    private Context mCtx;
    private static final int PICK_IMAGE_ID = 234;

    public One(){}

    public static One newInstance() {
        One fragment = new One();
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
        mIvHeader =  (ImageView)view.findViewById(R.id.imageView_step);
        mIvHeader.setImageResource(R.drawable.pantalla_13_titulo);
        mFullContentSpace = (RelativeLayout) view.findViewById(R.id.basic_content_space);
        final LayoutInflater inflater_content = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view_content = inflater_content.inflate(R.layout.act2_one, null, false);
        mFullContentSpace.addView(view_content);
        mImButton = (ImageView) view_content.findViewById(R.id.iv_activity2_button);
        mImButton.setOnClickListener(this);
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        mCtx = context;
        mIsFinished = (Two.ActivityFinished) mCtx;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_activity2_button:
                Intent chooseImageIntent = PhotoPicker.getPickImageIntent(mCtx);
                startActivityForResult(chooseImageIntent, PICK_IMAGE_ID);
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case PICK_IMAGE_ID:
                try {
                    //Bitmap image = PhotoPicker.getImageFromResult(mCtx, resultCode, data);
                    String path = PhotoPicker.getPathFromResult(mCtx, resultCode, data);
                    Log.d("PHOTO_PATH", path);
                    if (path != null) {
                        UtilsFunctions.saveSharedString(mCtx, LocalConstants.PHOTO_PATH, path);
                    }
                }catch (Exception ea){
                    ea.printStackTrace();
                }
                //Log.d("GETTING IMAGE", data.getData().getPath());
                // TODO use bitmap
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }
}

