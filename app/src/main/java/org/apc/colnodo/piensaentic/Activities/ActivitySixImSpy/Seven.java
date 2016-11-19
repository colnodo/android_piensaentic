package org.apc.colnodo.piensaentic.Activities.ActivitySixImSpy;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.apc.colnodo.piensaentic.Activities.ActivityFourUnknownContact.*;
import org.apc.colnodo.piensaentic.IndexManagement.FragmentBookInterface;
import org.apc.colnodo.piensaentic.R;
import org.apc.colnodo.piensaentic.Utils.LocalConstants;
import org.apc.colnodo.piensaentic.Utils.UtilsFunctions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 11/19/16.
 */

public class Seven extends Fragment implements View.OnClickListener{

    Context mContext;
    List<TextView> mTvValues;
    View mView;
    EditText mEtFindImages;
    FragmentBookInterface mFragmentInterface;


    public Seven(){}

    public static Seven newInstance() {
        Seven fragment = new Seven();
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
        mView = inflater.inflate(R.layout.activity_six_seven, container, false);
        ImageView imageView = (ImageView)mView.findViewById(R.id.iv_lets_continue);
        imageView.setOnClickListener(this);
        mEtFindImages = (EditText)mView.findViewById(R.id.et_images);
        chargeFields();
        mEtFindImages.addTextChangedListener(mTextWatcher);
        return mView;
    }

    private void chargeFields() {
        String text = UtilsFunctions.getSharedString(mContext, LocalConstants.IM_SPY_FIELDS_4);
        if (text != null){
            mEtFindImages.setText(text);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mFragmentInterface = (FragmentBookInterface) mContext;
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
        try{
            UtilsFunctions.saveSharedString(mContext, LocalConstants.IM_SPY_FIELDS_4,mEtFindImages.getText().toString());
        } catch (Exception ea){
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_lets_continue:
                mFragmentInterface.finishedActivity(true);
                mFragmentInterface.changeMenuItem(R.drawable.hamburguesa);
        }
    }

}
