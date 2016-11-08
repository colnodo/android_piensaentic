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
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apc.colnodo.piensaentic.R;
import org.apc.colnodo.piensaentic.Utils.AlertDialog;
import org.apc.colnodo.piensaentic.Utils.LocalConstants;
import org.apc.colnodo.piensaentic.Utils.UtilsFunctions;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by apple on 11/7/16.
 */

public class AboutMeTwo extends Fragment implements View.OnClickListener {

    private LinearLayout mFullContentSpace;
    private TextView mTvName, mTvNickname, mTvEmail, mTvBirthdate;


    private Context mCtx;

    public AboutMeTwo(){}

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
        mFullContentSpace = (LinearLayout) view.findViewById(R.id.ly_full_content_template);
        mFullContentSpace.removeAllViews();
        final LayoutInflater inflater_content = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view_content = inflater_content.inflate(R.layout.about_me_02, null, false);
        mFullContentSpace.addView(view_content);

        mTvName = (TextView) mFullContentSpace.findViewById(R.id.tv_name_about2);
        mTvNickname = (TextView) mFullContentSpace.findViewById(R.id.tv_nick_name_about2);
        mTvEmail = (TextView) mFullContentSpace.findViewById(R.id.tv_mail_about2);
        mTvBirthdate = (TextView) mFullContentSpace.findViewById(R.id.tv_birthdate_tittle_about2);

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
            case R.id.iv_protect_your_inf_about2:

                break;
            default:
                break;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);

        Log.d("Fragment", "Visible: " + isVisibleToUser);
        if (isVisibleToUser) {
            if (!UtilsFunctions.getSharedBoolean(mCtx, LocalConstants.DATA_TREATMENT_ACCEPTED)){
                AlertDialog dialog = new AlertDialog(mCtx);
                dialog.show();
            }
            mTvName.setText(UtilsFunctions.getSharedString(mCtx, LocalConstants.USER_NAME));
            mTvNickname.setText(UtilsFunctions.getSharedString(mCtx, LocalConstants.USER_NICK_NAME).toUpperCase());
            mTvEmail.setText(UtilsFunctions.getSharedString(mCtx, LocalConstants.USER_EMAIL));
            String[] separated = UtilsFunctions.getSharedString(mCtx, LocalConstants.USER_BIRTHDATE).split("-");
            String birthdate = String.format(getResources().getString(R.string.aboutme2_birthdate_tittle), separated[0], separated[1], separated[2]);
            mTvBirthdate.setText(birthdate);
        }
    }
}
