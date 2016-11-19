package org.apc.colnodo.piensaentic.Activities.ActivityOnePassword;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apc.colnodo.piensaentic.IndexManagement.FragmentBookInterface;
import org.apc.colnodo.piensaentic.R;
import org.apc.colnodo.piensaentic.Utils.LocalConstants;
import org.apc.colnodo.piensaentic.Utils.UtilsFunctions;

/**
 * Created by apple on 11/8/16.
 */

public class One extends Fragment {

    RelativeLayout mFragmentContentSpace;
    TextView mTvTittle;
    ImageView mImageView;
    private Context mCtx;
    FragmentBookInterface mFragmentBookInterface;

    public One(){

    }

    public static One newInstance() {
        One fragment = new One();
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
        mImageView =  (ImageView)view.findViewById(R.id.imageView_step);
        mImageView.setImageResource(R.drawable.pantalla_08_paso1);
        mFragmentContentSpace = (RelativeLayout)view.findViewById(R.id.basic_content_space);
        final LayoutInflater inflater_content = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view_content = inflater_content.inflate(R.layout.activity_one_one, null, false);
        mTvTittle = (TextView) view_content.findViewById(R.id.tv_password_one_tittle);
        mTvTittle.setText(Html.fromHtml("<b>" + UtilsFunctions.getSharedString(mCtx, LocalConstants.USER_NICK_NAME).toUpperCase()+"</b>" +
                                        getString(R.string.activityone_tittle)));
        mFragmentContentSpace.removeAllViews();
        mFragmentContentSpace.addView(view_content);
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        mCtx = context;
        mFragmentBookInterface = (FragmentBookInterface) mCtx;
        mFragmentBookInterface.changeMenuItem(R.drawable.hamburguesa);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
