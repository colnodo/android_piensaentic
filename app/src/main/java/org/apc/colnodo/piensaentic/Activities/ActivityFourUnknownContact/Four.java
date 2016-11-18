package org.apc.colnodo.piensaentic.Activities.ActivityFourUnknownContact;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.apc.colnodo.piensaentic.IndexManagement.FragmentBookInterface;
import org.apc.colnodo.piensaentic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 11/17/16.
 */

public class Four extends Fragment implements View.OnClickListener{
    Context mContext;
    List<TextView> mTvValues;
    View mView;
    FragmentBookInterface mFragmentInterface;

    public Four(){}

    public static Four newInstance() {
        Four fragment = new Four();
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
        mView = inflater.inflate(R.layout.activity_four_five, container, false);
        ImageView imageView = (ImageView)mView.findViewById(R.id.iv_lets_continue);
        imageView.setOnClickListener(this);
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
