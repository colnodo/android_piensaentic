package org.apc.colnodo.piensaentic.IndexManagement;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apc.colnodo.piensaentic.R;

import java.util.List;

/**
 *
 */
public class RightMenuFragment extends Fragment implements View.OnClickListener {

    private LinearLayout mList;
    private static List<String> mActivitiesList;

    private OnOptionRightMenuClicked mListener;

    public RightMenuFragment() {
        // Required empty public constructor
    }

    public static RightMenuFragment newInstance(List<String> activityList) {
        mActivitiesList = activityList;
        RightMenuFragment fragment = new RightMenuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_right_menu, container, false);
        mList = (LinearLayout)view.findViewById(R.id.menu_activities_list);
        fillList();
        return view;
    }

    private void fillList() {
        final LayoutInflater inflater = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mList.removeAllViews();
        for(String name : mActivitiesList){
            final View view = inflater.inflate(R.layout.activity_index_item, null, false);
            TextView text = (TextView)view.findViewById(R.id.activityName);
            text.setText(name);
            view.setOnClickListener(this);
            mList.addView(view);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnOptionRightMenuClicked) {
            mListener = (OnOptionRightMenuClicked) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnOptionRightMenuClicked");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        for (int i = 0; i<mList.getChildCount(); i++){
            if(mList.getChildAt(i).equals(view)){
                mListener.onRightMenuClicked(i);
            }
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnOptionRightMenuClicked {
        // TODO: Update argument type and name
        void onRightMenuClicked(int position);
    }
}
