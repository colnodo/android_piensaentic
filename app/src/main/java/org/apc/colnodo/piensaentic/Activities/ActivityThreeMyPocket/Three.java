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

import org.apc.colnodo.piensaentic.R;
import org.apc.colnodo.piensaentic.Utils.LocalConstants;
import org.apc.colnodo.piensaentic.Utils.UtilsFunctions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 11/16/16.
 */

public class Three extends Fragment {

    String TAG = this.getClass().getSimpleName();
    Context mContext;
    One.HomeLayoutChange changeLayout;
    List<RadioGroup> mRgQuestion;
    EditText mEtQuestion6;
    View mView;

    public Three(){}

    public static Three newInstance() {
        Three fragment = new Three();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRgQuestion = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_three_three, container, false);
        mRgQuestion.add((RadioGroup) mView.findViewById(R.id.q1));
        mRgQuestion.add((RadioGroup) mView.findViewById(R.id.q2));
        mRgQuestion.add((RadioGroup) mView.findViewById(R.id.q3));
        mRgQuestion.add((RadioGroup) mView.findViewById(R.id.q4));
        mRgQuestion.add((RadioGroup) mView.findViewById(R.id.q5));
        mEtQuestion6 = (EditText)mView.findViewById(R.id.q6);
        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        changeLayout = (One.HomeLayoutChange) mContext;
        changeLayout.changePagerBackground(R.color.magenta_alpha);
    }

    public void saveSelection(List<String> selection){
        if (selection.size()>0) {
            String phone_selection = "";
            for (String value : selection) {
                phone_selection = phone_selection + value + LocalConstants.SELECTION_SEPARATOR;
            }
            Log.d(TAG, phone_selection);
            UtilsFunctions.saveSharedString(mContext, LocalConstants.PHONE_SELECTION, phone_selection);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("Fragment", "Visible: " + isVisibleToUser);
        if (!isVisibleToUser) {
            try {
                saveSelection(getSelection());
            } catch (Exception ea){
                ea.printStackTrace();
            }
        }
    }

    private List<String> getSelection(){
        List<String> list = new ArrayList<>();
        for(int i = 0; i<mRgQuestion.size(); i++){
            int rbCheckedId = mRgQuestion.get(i).getCheckedRadioButtonId();
            RadioButton rbChecked = (RadioButton) mView.findViewById(rbCheckedId);
            list.add(rbChecked.getText().toString());
        }
        if (mEtQuestion6.getText()!= null) {
            list.add(mEtQuestion6.getText().toString());
        } else {
            list.add(" ");
        }
        return list;
    }

}
