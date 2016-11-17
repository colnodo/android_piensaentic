package org.apc.colnodo.piensaentic.Activities.ActivityThreeMyPocket;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.apc.colnodo.piensaentic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 11/17/16.
 */

public class Nine extends Fragment {

    Context mContext;
    One.HomeLayoutChange changeLayout;
    List<TextView> mTvValues;
    View mView;

    public Nine(){}

    public static Nine newInstance() {
        Nine fragment = new Nine();
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
        mView = inflater.inflate(R.layout.activity_three_six, container, false);
        TextView tv = (TextView) mView.findViewById(R.id.tv_tittle);
        ImageView im_number = (ImageView) mView.findViewById(R.id.im_number);
        ImageView im_graph = (ImageView) mView.findViewById(R.id.im_graph);
        tv.setText(R.string.activitythree9_text);
        im_number.setImageResource(R.drawable.pantalla_26_cuatro);
        im_graph.setImageResource(R.drawable.pantalla_26_imei);
        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        changeLayout = (One.HomeLayoutChange) mContext;
        changeLayout.changePagerBackground(R.color.magenta_alpha);
    }

}

