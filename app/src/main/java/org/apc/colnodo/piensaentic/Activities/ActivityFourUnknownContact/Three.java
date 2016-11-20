package org.apc.colnodo.piensaentic.Activities.ActivityFourUnknownContact;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.apc.colnodo.piensaentic.R;
import org.apc.colnodo.piensaentic.Utils.LocalConstants;
import org.apc.colnodo.piensaentic.Utils.UtilsFunctions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 11/17/16.
 */

public class Three extends Fragment {
    Context mContext;
    List<TextView> mTvValues;
    View mView;
    TextView mTvTittle, mTvText;
    ImageView mImMsg;


    public Three(){}

    public static Three newInstance() {
        Three fragment = new Three();
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
        mView = inflater.inflate(R.layout.activity_four_three, container, false);
        mTvTittle = (TextView) mView.findViewById(R.id.tv_tittle);
        mTvText = (TextView)mView.findViewById(R.id.tv_text);
        mImMsg = (ImageView)mView.findViewById(R.id.iv_msg);
        try {
            selectLayout();
        } catch (Exception ea){
            ea.printStackTrace();
        }
        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("Fragment", "Visible: " + isVisibleToUser);
        if (isVisibleToUser) {
            try {
                selectLayout();
            } catch (Exception ea){
                ea.printStackTrace();
            }
        }
    }

    private void selectLayout() {
        if (UtilsFunctions.getSharedString(mContext, LocalConstants.UNKOWN_CONTACT_FIELDS.get(2))!= null ||
                UtilsFunctions.getSharedString(mContext, LocalConstants.UNKOWN_CONTACT_FIELDS.get(3))!= null){
            setLayout(2);
        } else setLayout(1);

    }

    private void setLayout(int type){
        switch (type){
            case 1:
                mTvTittle.setText(R.string.activityfour3_tittle);
                mTvText.setText(R.string.activityfour3_text);
                mImMsg.setImageResource(R.drawable.pantalla_30_felicitaciones);
                break;
            case 2:
                mTvTittle.setText(getTittleString());
                mTvText.setText(R.string.activityfour4_text);
                mImMsg.setImageResource(R.drawable.pantalla_31_quienes_son);
        }
    }

    public Spanned getTittleString() {
        Spanned tittleString = null;
        if (UtilsFunctions.getSharedString(mContext, LocalConstants.UNKOWN_CONTACT_FIELDS.get(2))!= null
                && UtilsFunctions.getSharedString(mContext, LocalConstants.UNKOWN_CONTACT_FIELDS.get(3))!= null) {
             tittleString = Html.fromHtml(getString(R.string.nickname_tag) + ": <b>" +
                    UtilsFunctions.getSharedString(mContext, LocalConstants.UNKOWN_CONTACT_FIELDS.get(2))+ "</b> <br>" +
                     getString(R.string.nickname_tag) + ": <b>" +
                     UtilsFunctions.getSharedString(mContext, LocalConstants.UNKOWN_CONTACT_FIELDS.get(3))+ "</b>"
             );
        } else if (UtilsFunctions.getSharedString(mContext, LocalConstants.UNKOWN_CONTACT_FIELDS.get(3))!= null){
            tittleString = Html.fromHtml(getString(R.string.nickname_tag) + ": <b>" +
                    UtilsFunctions.getSharedString(mContext, LocalConstants.UNKOWN_CONTACT_FIELDS.get(3))+ "</b> <br>");

        } else if (UtilsFunctions.getSharedString(mContext, LocalConstants.UNKOWN_CONTACT_FIELDS.get(2))!= null){
            tittleString = Html.fromHtml(getString(R.string.nickname_tag) + ": <b>" +
                    UtilsFunctions.getSharedString(mContext, LocalConstants.UNKOWN_CONTACT_FIELDS.get(2))+ "</b> <br>");
        }
        return tittleString;
    }
}
