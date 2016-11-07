package org.apc.colnodo.piensaentic.Activities.AboutMe;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apc.colnodo.piensaentic.Activities.BlankFragment;
import org.apc.colnodo.piensaentic.R;

import java.lang.reflect.Constructor;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by apple on 11/6/16.
 */

public class AboutMeOne extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener{

    private RelativeLayout mFragmentContentSpace;
    private EditText mEtName, mEtNickname, mEtEmail;
    private TextView mTvBirthdate;
    private DatePickerDialog.OnDateSetListener mListener;

    private Context mCtx;

    public AboutMeOne(){}

    public static AboutMeOne newInstance() {
        AboutMeOne fragment = new AboutMeOne();
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
        final LayoutInflater inflater_content = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view_content = inflater_content.inflate(R.layout.about_me_01, null, false);
        mFragmentContentSpace.addView(view_content);
        mEtName = (EditText)mFragmentContentSpace.findViewById(R.id.et_name);
        mEtNickname = (EditText)mFragmentContentSpace.findViewById(R.id.et_nickname);
        mEtEmail = (EditText)mFragmentContentSpace.findViewById(R.id.et_email);
        mTvBirthdate = (TextView) mFragmentContentSpace.findViewById(R.id.tv_birthdate);

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        final DatePickerDialog picker = new DatePickerDialog(mCtx, this, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        mTvBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("AboutMe", "BirthdateClicked");
                picker.show();
            }
        });
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        mCtx = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_birthdate:
                Log.d("AboutMe", "BirthdateClicked");
                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                new DatePickerDialog(this.getActivity().getApplicationContext(), this, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        int _birthYear = year;
        int _month = monthOfYear;
        int _day = dayOfMonth;
        mTvBirthdate.setText(_day + "-" + _month +"-" +_birthYear);
    }
}
