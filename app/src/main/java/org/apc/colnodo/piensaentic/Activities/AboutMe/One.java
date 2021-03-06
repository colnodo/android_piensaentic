package org.apc.colnodo.piensaentic.Activities.AboutMe;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apc.colnodo.piensaentic.IndexManagement.FragmentBookInterface;
import org.apc.colnodo.piensaentic.R;
import org.apc.colnodo.piensaentic.Utils.LocalConstants;
import org.apc.colnodo.piensaentic.Utils.UtilsFunctions;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by apple on 11/6/16.
 */

public class One extends Fragment implements View.OnClickListener,
        DatePickerDialog.OnDateSetListener {

    private String TAG = this.getClass().getSimpleName();

    private RelativeLayout mFragmentContentSpace;
    private EditText mEtName, mEtNickname, mEtEmail;
    private TextView mTvBirthdate;
    private String mName = null, mNickname = null, mEmail = null, mBirthdate = null;
    public FragmentBookInterface mFragementBookInterface;
    private boolean mAllowedToContinue;

    private DatePickerDialog.OnDateSetListener mListener;

    private Context mCtx;

    public One(){

    }

    public static One newInstance() {
        One fragment = new One();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAllowedToContinue = false;
        mFragementBookInterface.isAllowedToContinue(mAllowedToContinue);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_basic_template, container, false);
        mFragmentContentSpace = (RelativeLayout)view.findViewById(R.id.basic_content_space);
        final LayoutInflater inflater_content = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view_content = inflater_content.inflate(R.layout.about_me_01, null, false);
        mFragmentContentSpace.addView(view_content);
        mEtName = (EditText)mFragmentContentSpace.findViewById(R.id.et_name);
        mEtNickname = (EditText)mFragmentContentSpace.findViewById(R.id.et_nickname);
        mEtEmail = (EditText)mFragmentContentSpace.findViewById(R.id.et_email);
        mTvBirthdate = (TextView) mFragmentContentSpace.findViewById(R.id.tv_birthdate);

        mEtEmail.addTextChangedListener(textWatcher);
        mEtName.addTextChangedListener(textWatcher);
        mEtNickname.addTextChangedListener(textWatcher);

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        final DatePickerDialog picker = new DatePickerDialog(mCtx, this, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        mTvBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picker.show();
            }
        });

        if (UtilsFunctions.getSharedString(mCtx, LocalConstants.USER_NAME)!= null){
            setFieldsValues();
        }
        return view;
    }

    private void setFieldsValues() {
        mEtName.setText(UtilsFunctions.getSharedString(mCtx, LocalConstants.USER_NAME));
        mEtNickname.setText(UtilsFunctions.getSharedString(mCtx, LocalConstants.USER_NICK_NAME));
        mEtEmail.setText(UtilsFunctions.getSharedString(mCtx, LocalConstants.USER_EMAIL));
        mTvBirthdate.setText(UtilsFunctions.getSharedString(mCtx, LocalConstants.USER_BIRTHDATE));
        mFragementBookInterface.isAllowedToContinue(true);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        mCtx = context;
        mFragementBookInterface = (FragmentBookInterface) context;
        mFragementBookInterface.changeMenuItem(R.drawable.hamburguesa);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        int _birthYear = year;
        int _month = monthOfYear + 1;
        int _day = dayOfMonth;
        mTvBirthdate.setText(_day + "-" + _month +"-" +_birthYear);
        validateFields();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("Fragment", "Visible: " + isVisibleToUser);
        if (!isVisibleToUser) {
            saveFields();
        }
    }

    private void saveFields() {
        try {
            mName = mEtName.getText().toString().trim();
            mNickname = mEtNickname.getText().toString().trim();
            mEmail = mEtEmail.getText().toString().trim();
            mBirthdate = mTvBirthdate.getText().toString();
            UtilsFunctions.saveSharedString(mCtx, LocalConstants.USER_NAME, mName);
            UtilsFunctions.saveSharedString(mCtx, LocalConstants.USER_EMAIL, mEmail);
            UtilsFunctions.saveSharedString(mCtx, LocalConstants.USER_NICK_NAME, mNickname);
            UtilsFunctions.saveSharedString(mCtx, LocalConstants.USER_BIRTHDATE, mBirthdate);
        } catch (Exception ea){
            ea.printStackTrace();
        }

    }

    private void validateFields() {
        Log.d(TAG,isEmpty(mEtName) + ": " + isEmpty(mEtNickname) +  ": " + !isEmail(mEtEmail) +  ": " + !isValidBirthdate(mTvBirthdate) );
        if (isEmpty(mEtName) || isEmpty(mEtNickname)||
                !isEmail(mEtEmail)|| !isValidBirthdate(mTvBirthdate)) {
            mFragementBookInterface.isAllowedToContinue(false);
        } else {
            mFragementBookInterface.isAllowedToContinue(true);
            Toast.makeText(mCtx, R.string.next_page, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidBirthdate(TextView birthdate) {
        String date = birthdate.getText().toString();
        return UtilsFunctions.checkRegEx(date, LocalConstants.DATE_REGEX);
    }

    private boolean isEmail(EditText email) {
        if (isEmpty(email))
            return false;
        else {
            String mail = email.getText().toString();
            Log.d(TAG, "Is Email: "+ mail.matches(Patterns.EMAIL_ADDRESS.toString()));
            return mail.matches(Patterns.EMAIL_ADDRESS.toString());
        }
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
            validateFields();
        }
    };

    public boolean isEmpty(EditText view){
        String string = view.getText().toString();
        Log.d(TAG, "Is string:" + string.trim().length());
        return string.trim().length() == 0;
    }

}
