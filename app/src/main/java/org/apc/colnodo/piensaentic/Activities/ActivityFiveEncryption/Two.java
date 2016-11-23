package org.apc.colnodo.piensaentic.Activities.ActivityFiveEncryption;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import org.apc.colnodo.piensaentic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 11/18/16.
 */

public class Two extends Fragment implements View.OnClickListener{
    Context mContext;
    List<TextView> mTvValues;
    View mView;
    ImageView mIvSendMessage;


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
        mTvValues = new ArrayList<>();
        mView = inflater.inflate(R.layout.activity_five_two, container, false);
        mIvSendMessage = (ImageView)mView.findViewById(R.id.iv_send_message);
        mIvSendMessage.setOnClickListener(this);
        mIvSendMessage.setOnTouchListener(new View.OnTouchListener() {
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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_send_message:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(
                        Intent.EXTRA_TEXT,
                        getString(R.string.activityfive2_share_txt));
                startActivity(intent);
        }
    }
}
