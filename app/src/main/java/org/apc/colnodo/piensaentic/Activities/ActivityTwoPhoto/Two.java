package org.apc.colnodo.piensaentic.Activities.ActivityTwoPhoto;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apc.colnodo.piensaentic.R;
import org.apc.colnodo.piensaentic.Utils.AlertDialog;
import org.apc.colnodo.piensaentic.Utils.LocalConstants;
import org.apc.colnodo.piensaentic.Utils.UtilsFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 11/10/16.
 */

public class Two extends Fragment implements View.OnClickListener {

    private final String TAG = getClass().getSimpleName();
    private RelativeLayout mFullContentSpace;
    private LinearLayout mLyFullContentSpace;
    private ImageView mIvHeader, mImButton;
    private Context mCtx;
    private ExifInterface mMetaData;
    private static final int PICK_IMAGE_ID = 234;
    private static final int PHOTO_HEIGHT = 288;
    private static final int PHOTO_WIDTH = 267;
    private List<Pair<String, String>> mMetaTagsList = new ArrayList<>();

    public Two(){}

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
        mIvHeader =  (ImageView)view.findViewById(R.id.imageView_step);
        mIvHeader.setVisibility(View.GONE);
        mLyFullContentSpace = (LinearLayout)view.findViewById(R.id.ly_full_content_template);
        mFullContentSpace = (RelativeLayout) view.findViewById(R.id.basic_content_space);
        //final LayoutInflater inflater_content = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view_content = inflater.inflate(R.layout.act2_one, null, false);
//        final LayoutInflater inflater_content = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view_2 = inflater.inflate(R.layout.photo_edge, null, false);
        mLyFullContentSpace.addView(view_2, 0);
        mFullContentSpace.addView(view_content);
        TextView tv = (TextView)mFullContentSpace.findViewById(R.id.tv_act2_tittle);
        tv.setText(R.string.activitytwo2_tittle);
        mImButton = (ImageView) view_content.findViewById(R.id.iv_activity2_button);
        mImButton.setImageResource(R.drawable.pantalla_14_bot_revisar);
        mImButton.setOnClickListener(this);
        return view;
    }

    private void chargeImage() {
        String path = UtilsFunctions.getSharedString(mCtx, LocalConstants.PHOTO_PATH);
        if (path!= null) {
            try {
                final BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 8;
                File file = new File(path);
                Bitmap src = BitmapFactory.decodeFile(path, options);
                int height = src.getHeight();
                int width = src.getWidth();
                int side = width;
                if (height < width) {
                    side = height;
                }
                Bitmap square = Bitmap.createBitmap(src, 0, 0, side, side);
                Bitmap b = Bitmap.createScaledBitmap(square, PHOTO_WIDTH, PHOTO_HEIGHT, false);
                ImageView im = (ImageView) mLyFullContentSpace.findViewById(R.id.iv_photo);
                im.setImageBitmap(b);
                mMetaData = new ExifInterface(file.getAbsolutePath());
                setMetaTagList();
                Log.d(TAG, "Image Real Path: " + path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        mCtx = context;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_activity2_button:
                AlertDialog dialog = new AlertDialog(mCtx, LocalConstants.META_TAG_DIALOG, mMetaTagsList);
                dialog.show();
                break;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("Fragment", "Visible: " + isVisibleToUser);
        if (isVisibleToUser) {
            chargeImage();
        }
    }



    private void setMetaTagList(){
        mMetaTagsList.clear();
        for (int i = 0; i< LocalConstants.META_TAG_LIST.size(); i++){
            if (mMetaData.getAttribute(LocalConstants.META_TAG_LIST.get(i).first) != null) {
                try {
                    mMetaTagsList.add(new Pair<>(LocalConstants.META_TAG_LIST.get(i).second,
                            mMetaData.getAttribute(LocalConstants.META_TAG_LIST.get(i).first)));
                } catch (Exception ea) {
                    ea.printStackTrace();
                }
                Log.d(TAG, "MetaTag: " + mMetaTagsList.get(mMetaTagsList.size()-1).first + ": " + mMetaTagsList.get(mMetaTagsList.size()-1).second);
            }
        }
    }

}
