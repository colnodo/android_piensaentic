package org.apc.colnodo.piensaentic.Activities.InfoAndCredits;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.apc.colnodo.piensaentic.IndexManagement.FragmentBookInterface;
import org.apc.colnodo.piensaentic.R;
import org.apc.colnodo.piensaentic.Utils.LocalConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 11/19/16.
 */

public class Credits extends Fragment {

    Context mContext;
    List<TextView> mTvValues;
    View mView;
    FragmentBookInterface mFragmentInterface;

    public Credits(){}

    public static Credits newInstance() {
        Credits fragment = new Credits();
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
        mView = inflater.inflate(R.layout.contact, container, false);
        TextView mTextView = (TextView) mView.findViewById(R.id.tv_text);
        Spanned tittleString = null;
        tittleString = Html.fromHtml(getString(R.string.infoscreen_text1) +
                " "+
                "<FONT COLOR=#7d1836><a href=\""+ LocalConstants.COLNODO +"\">"
                + getString(R.string.infoscreen_text2) + "</a></font>"
                + " " + getString(R.string.infoscreen_text3)
                + " <FONT COLOR=#7d1836><a href= \""+LocalConstants.APC +"\">"
                + getString(R.string.infoscreen_text4) + "</a></font><br><br>"
                + getString(R.string.infoscreen_text5)
                + "<br><FONT COLOR=#7d1836><a href= \""+LocalConstants.GITHUB +"\">"
                + getString(R.string.infoscreen_text6) + "</a></font>"
        );
        mTextView.setText(tittleString);
        mTextView.setClickable(true);
        mTextView.setMovementMethod (LinkMovementMethod.getInstance());
        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mFragmentInterface = (FragmentBookInterface) mContext;
        mFragmentInterface.changeMenuItem(R.drawable.hamburguesa);
    }
}
