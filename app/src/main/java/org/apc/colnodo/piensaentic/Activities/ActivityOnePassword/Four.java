package org.apc.colnodo.piensaentic.Activities.ActivityOnePassword;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apc.colnodo.piensaentic.Activities.AboutMe.*;
import org.apc.colnodo.piensaentic.Activities.AboutMe.One;
import org.apc.colnodo.piensaentic.Activities.ActivityPageTemplate;
import org.apc.colnodo.piensaentic.IndexManagement.FragmentBookInterface;
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
    public FragmentBookInterface mFragmentInterface;
    public FragmentActivityActions mActions;
    Toast toast;

    public Four(){

    }

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
        mImCreatePassword.setOnTouchListener(new View.OnTouchListener() {
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
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        mCtx = context;
        mActions = (FragmentActivityActions) context;
        mFragmentInterface = (FragmentBookInterface)mCtx;
        mFragmentInterface.isAllowedToContinue(true);
        toast = new Toast(mCtx);
    }


    private void setFieldsValues() {
        mEtPassword.setText(UtilsFunctions.getSharedString(mCtx, LocalConstants.USER_PASS));
        mEtPasswordConfirm.setText(UtilsFunctions.getSharedString(mCtx, LocalConstants.USER_PASS));
        validatePassword();
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
                toast.cancel();
                toast.makeText(mCtx, R.string.next_page, Toast.LENGTH_SHORT).show();
            } else {
                mImCreatePassword.setImageAlpha(mAlphaButtonOff);
                mImCreatePassword.setClickable(false);
                mFragmentInterface.isAllowedToContinue(false);
                if (passwordConfirm.length()>5) {
                    if (!password.equals(passwordConfirm)) {
                        toast.cancel();
                        try {
                            if(toast.getView() == null){
                                toast.makeText(mCtx, R.string.password_not_match, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception ea){
                            ea.printStackTrace();
                        }

                    } else if (UtilsFunctions.checkRegEx(password, LocalConstants.PASSWORD_REGEX)) {
                        toast.cancel();
                        try {
                            if(toast.getView() == null){
                                toast.makeText(mCtx, R.string.password_not_strong, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception ea){
                            ea.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception ea){
            ea.printStackTrace();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("Fragment", "Visible: " + isVisibleToUser);
        if (isVisibleToUser) {
            if(UtilsFunctions.getSharedString(mCtx, LocalConstants.USER_PASS)!= null){
                setFieldsValues();
            }
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
