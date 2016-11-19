package org.apc.colnodo.piensaentic.Activities.ActivityThreeMyPocket;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.apc.colnodo.piensaentic.IndexManagement.FragmentBookInterface;
import org.apc.colnodo.piensaentic.R;

/**
 * Created by apple on 11/16/16.
 */

public class One extends Fragment {

    FragmentBookInterface mFragementInterface;

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
        View view = inflater.inflate(R.layout.activity_three_one, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragementInterface = (FragmentBookInterface) context;
        mFragementInterface.changeMenuItem(R.drawable.hamburguesa);
        mFragementInterface.changePagerBackground(R.color.magenta_alpha);
    }

    public interface HomeLayoutChange {

        void changePagerBackground(int color);

    }

}
