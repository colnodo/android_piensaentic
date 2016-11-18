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

public class Two extends Fragment {


    String TAG = this.getClass().getSimpleName();

    Context mContext;
    One.HomeLayoutChange changeLayout;
    List<RadioGroup> mRgQuestion;
    EditText mEtQuestion6;
    View mView;

    public Two(){}

    public static Two newInstance() {
        Two fragment = new Two();
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
        mView = inflater.inflate(R.layout.activity_three_two, container, false);
        mRgQuestion.clear();
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
        String pocket_selection = new String();
        if (selection.size()>0) {

            for (String value : selection) {
                pocket_selection = pocket_selection + value + LocalConstants.SELECTION_SEPARATOR;
            }
            Log.d(TAG, pocket_selection);
            UtilsFunctions.saveSharedString(mContext, LocalConstants.POCKET_SELECTION, pocket_selection);
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
//        if (mEtQuestion6.getText()!= null) {
//            list.add(mEtQuestion6.getText().toString());
//        } else {
//            list.add(" ");
//        }
        return list;
    }

}
