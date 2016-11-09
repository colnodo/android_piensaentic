package org.apc.colnodo.piensaentic.Activities.ActivityOnePassword;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apc.colnodo.piensaentic.Activities.AboutMe.*;
import org.apc.colnodo.piensaentic.Activities.AboutMe.One;
import org.apc.colnodo.piensaentic.Activities.ActivityPageTemplate;
import org.apc.colnodo.piensaentic.R;
import org.apc.colnodo.piensaentic.Utils.LocalConstants;
import org.apc.colnodo.piensaentic.Utils.UtilsFunctions;

/**
 * Created by apple on 11/9/16.
 */

public class Four extends Fragment implements View.OnClickListener{
    RelativeLayout mFragmentContentSpace;
    EditText mEtPassword, mEtPasswordConfirm;
    ImageView mImStep, mImCreatePassword;
    TextView mTvTittle;
    int mAlphaButtonOff = 40;
    int mAlphaButtonOn = 255;
    private Context mCtx;
    public One.fragmentValidations mValidationsListener;
    public FragmentActivityActions mActions;

    public Four(){

    }

    public static Four newInstance() {
        Four fragment = new Four();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mValidationsListener.isAllowedToContinue(false);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_basic_template, container, false);
        mFragmentContentSpace = (RelativeLayout)view.findViewById(R.id.basic_content_space);
        mImStep =  (ImageView)view.findViewById(R.id.imageView_step);
        mImStep.setVisibility(View.INVISIBLE);
        final LayoutInflater inflater_content = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view_content = inflater_content.inflate(R.layout.activity_one_four, null, false);
        mFragmentContentSpace.removeAllViews();
        mFragmentContentSpace.addView(view_content);
        mTvTittle = (TextView)mFragmentContentSpace.findViewById(R.id.tv_password_two_tittle);
        mTvTittle.setText(Html.fromHtml("<b>" + UtilsFunctions.getSharedString(mCtx, LocalConstants.USER_NICK_NAME).toUpperCase()+"</b>" +
                getString(R.string.activityone4_tittle)));
        mEtPassword = (EditText)mFragmentContentSpace.findViewById(R.id.et_password);
        mEtPasswordConfirm = (EditText)mFragmentContentSpace.findViewById(R.id.et_password_confirm);
        mEtPassword.addTextChangedListener(textWatcher);
        mEtPasswordConfirm.addTextChangedListener(textWatcher);

        mImCreatePassword = (ImageView)mFragmentContentSpace.findViewById(R.id.im_create_password);
        mImCreatePassword.setImageAlpha(mAlphaButtonOff);
        mImCreatePassword.setClickable(false);

        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        mCtx = context;
        mValidationsListener = (One.fragmentValidations) context;
        mActions = (FragmentActivityActions) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            validatePassword();
        }
    };

    private void validatePassword() {
        try{
            String password = mEtPassword.getText().toString();
            String passwordConfirm = mEtPasswordConfirm.getText().toString();
            if (password.equals(passwordConfirm) && UtilsFunctions.checkRegEx(password, LocalConstants.PASSWORD_REGEX)){
                mImCreatePassword.setImageAlpha(mAlphaButtonOn);
                mImCreatePassword.setClickable(true);
                mImCreatePassword.setOnClickListener(this);
                //mValidationsListener.isAllowedToContinue(true);
            } else {
                mImCreatePassword.setImageAlpha(mAlphaButtonOff);
                mImCreatePassword.setClickable(false);
                mValidationsListener.isAllowedToContinue(false);
            }
        } catch (Exception ea){
            ea.printStackTrace();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("Fragment", "Visible: " + isVisibleToUser);
        if (!isVisibleToUser) {
            //mValidationsListener.isAllowedToContinue(true);
        } else{
            validatePassword();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.im_create_password:
                UtilsFunctions.saveSharedString(mCtx, LocalConstants.USER_PASS, mEtPassword.getText().toString());
                mActions.nextFragment();
                break;
            default:
                break;
        }
    }

    public interface FragmentActivityActions{
        void nextFragment();
    }
}
