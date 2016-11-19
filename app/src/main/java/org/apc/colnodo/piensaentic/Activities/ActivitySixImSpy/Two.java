package org.apc.colnodo.piensaentic.Activities.ActivitySixImSpy;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.apc.colnodo.piensaentic.R;
import org.apc.colnodo.piensaentic.Utils.LocalConstants;
import org.apc.colnodo.piensaentic.Utils.UtilsFunctions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 11/19/16.
 */

public class Two extends Fragment {

    Context mContext;
    List<TextView> mTvValues;
    View mView;
    List<EditText> mEtArray = new ArrayList<>();


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
        mView = inflater.inflate(R.layout.activity_six_two, container, false);
        mEtArray.clear();
        mEtArray.add((EditText)mView.findViewById(R.id.et_plate));
        mEtArray.add((EditText)mView.findViewById(R.id.et_contact));
        chargeFields();
        for (EditText et : mEtArray){

            et.addTextChangedListener(mTextWatcher);
        }
        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            saveFields();
        }
    };

    private void saveFields() {
        int i = 0;
        for (EditText et:mEtArray){
            try{
                UtilsFunctions.saveSharedString(mContext, LocalConstants.IM_SPY_FIELDS_1.get(i),et.getText().toString());
            } catch (Exception ea){
            }
            i++;
        }
    }

    private void chargeFields(){
        int i = 0;
        for (EditText et: mEtArray){
            String text = UtilsFunctions.getSharedString(mContext, LocalConstants.IM_SPY_FIELDS_1.get(i));
            if (text != null){
                et.setText(text);
            }
            i++;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("Fragment", "Visible: " + isVisibleToUser);
        if (isVisibleToUser) {
            try {
                //chargeFields();
            } catch (Exception ea){
                ea.printStackTrace();
            }
        }
    }

}
