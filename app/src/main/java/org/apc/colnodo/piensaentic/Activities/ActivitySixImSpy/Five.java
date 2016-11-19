package org.apc.colnodo.piensaentic.Activities.ActivitySixImSpy;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.apc.colnodo.piensaentic.Activities.ActivityThreeMyPocket.*;
import org.apc.colnodo.piensaentic.Activities.ActivityThreeMyPocket.One;
import org.apc.colnodo.piensaentic.R;
import org.apc.colnodo.piensaentic.Utils.LocalConstants;
import org.apc.colnodo.piensaentic.Utils.UtilsFunctions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 11/19/16.
 */

public class Five extends Fragment {

    Context mContext;
    org.apc.colnodo.piensaentic.Activities.ActivityThreeMyPocket.One.HomeLayoutChange changeLayout;
    List<TextView> mTvValues;
    View mView;

    public Five(){}

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
        mView = inflater.inflate(R.layout.activity_six_five, container, false);
        mTvValues.clear();
        mTvValues.add((TextView) mView.findViewById(R.id.tv_plate));
        mTvValues.add((TextView) mView.findViewById(R.id.tv_contact_1));
        mTvValues.add((TextView) mView.findViewById(R.id.tv_site_1));
        mTvValues.add((TextView) mView.findViewById(R.id.tv_site_2));
        mTvValues.add((TextView) mView.findViewById(R.id.tv_site_3));
        mTvValues.add((TextView) mView.findViewById(R.id.tv_contact_2));
        mTvValues.add((TextView) mView.findViewById(R.id.tv_dni));
        mTvValues.add((TextView) mView.findViewById(R.id.tv_contact_3));
        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void chargeValues(List<String> values){
        if (mTvValues.size()> 0){
            for (int i = 0; i< mTvValues.size(); i++){
                mTvValues.get(i).setText(values.get(i));
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("Fragment", "Visible: " + isVisibleToUser);
        if (isVisibleToUser) {
            try {
                chargeFields();
            } catch (Exception ea){
                ea.printStackTrace();
            }
        }
    }

    private void chargeFields(){
        int i = 0;
        for (TextView et: mTvValues){
            String text = UtilsFunctions.getSharedString(mContext, LocalConstants.IM_SPY_FIELDS_TOTAL.get(i));
            if (text != null){
                et.setText(text);
            }
            i++;
        }
    }

}
