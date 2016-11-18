package org.apc.colnodo.piensaentic.Activities.ActivityThreeMyPocket;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.apc.colnodo.piensaentic.R;
import org.apc.colnodo.piensaentic.Utils.LocalConstants;
import org.apc.colnodo.piensaentic.Utils.UtilsFunctions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 11/17/16.
 */

public class Four extends Fragment {
    Context mContext;
    One.HomeLayoutChange changeLayout;
    List<TextView> mTvValues;
    View mView;

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
        mView = inflater.inflate(R.layout.activitythree_four, container, false);
        mTvValues.clear();
        mTvValues.add((TextView) mView.findViewById(R.id.pocket_q1));
        mTvValues.add((TextView) mView.findViewById(R.id.pocket_q2));
        mTvValues.add((TextView) mView.findViewById(R.id.pocket_q3));
        mTvValues.add((TextView) mView.findViewById(R.id.pocket_q4));
        mTvValues.add((TextView) mView.findViewById(R.id.pocket_q5));
        mTvValues.add((TextView) mView.findViewById(R.id.phone_q1));
        mTvValues.add((TextView) mView.findViewById(R.id.phone_q2));
        mTvValues.add((TextView) mView.findViewById(R.id.phone_q3));
        mTvValues.add((TextView) mView.findViewById(R.id.phone_q4));
        mTvValues.add((TextView) mView.findViewById(R.id.phone_q5));
        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        changeLayout = (One.HomeLayoutChange) mContext;
        changeLayout.changePagerBackground(R.color.magenta_alpha);
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
                chargeValues(getValues());
            } catch (Exception ea){
                ea.printStackTrace();
            }
        }
    }

    private List<String> getValues(){
        List<String> list = new ArrayList<>();
        String pocket = UtilsFunctions.getSharedString(mContext,LocalConstants.POCKET_SELECTION);
        String phone = UtilsFunctions.getSharedString(mContext,LocalConstants.PHONE_SELECTION);
        String[] pocket_list = pocket.split(LocalConstants.SELECTION_SEPARATOR);
        String[] phone_list = phone.split(LocalConstants.SELECTION_SEPARATOR);

        for (String value:pocket_list){
            list.add(mContext.getResources().getString(R.string.activitythree4_pocket) +" " + value);
        }
        for (String value:phone_list){
            list.add(mContext.getResources().getString(R.string.activitythree4_phone) + " "+value);
        }

        return list;
    }
}
