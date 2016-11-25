package org.apc.colnodo.piensaentic.Utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import org.apc.colnodo.piensaentic.IndexManagement.FragmentBookInterface;
import org.apc.colnodo.piensaentic.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by apple on 11/7/16.
 */

public class AlertDialog extends Dialog implements View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();

    public TextView mTvTittle, mTvNote;
    private ImageView mImAccept, mImNoAccept;
    private Context mCtx;
    private int mType;
    public static List<Pair<String, String>> mMetaTagsList = new ArrayList<>();
    FragmentBookInterface mFragmentInterface;
    private Tracker mTracker;


    public AlertDialog(Context context, int dialogType) {
        super(context);
        mCtx = context;
        mType = dialogType;
        mFragmentInterface = (FragmentBookInterface) mCtx;
    }

    public AlertDialog(Context context, int dialogType, List<Pair<String, String>> list) {
        super(context);
        mCtx = context;
        mType = dialogType;
        mMetaTagsList = list;
        PiensaEnTic application = (PiensaEnTic) mCtx.getApplicationContext();
        mTracker = application.getDefaultTracker();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.generic_dialog);
        setContentType();
    }

    private void setContentType() {
        mImAccept = (ImageView)findViewById(R.id.iv_treatmen_accept);
        mImNoAccept = (ImageView) findViewById(R.id.iv_treatmen_no_accept);
        mTvTittle = (TextView)findViewById(R.id.tv_dialog_tittle);
        mTvNote = (TextView)findViewById(R.id.tv_dialog_note);

        switch (mType){
            case LocalConstants.TREATMENT_DIALOG:
                Spanned tittleString = null;
                tittleString = Html.fromHtml(mCtx.getString(R.string.dialog_treatment_tittle1)+
                        " <a href=\""+LocalConstants.URL_TERMS_CONDITION +"\">"
                        + mCtx.getString(R.string.dialog_treatment_tittle2) + "</a>"
                        + " " + mCtx.getString(R.string.dialog_treatment_tittle3)
                );
                mTvTittle.setText(tittleString);
                mTvTittle.setClickable(true);
                mTvTittle.setMovementMethod (LinkMovementMethod.getInstance());
                mImNoAccept.setOnClickListener(this);
                mImAccept.setOnClickListener(this);
                break;
            case LocalConstants.CREATE_PASS_DIALOG:
                mImAccept.setImageResource(R.drawable.pantalla_07_bot_sigue_estos_pasos);
                mImAccept.setId(R.id.im_generate_pass);
                mTvTittle.setText(R.string.dialog_create_password_tittle);
                mImNoAccept.setVisibility(View.GONE);
                mTvNote.setVisibility(View.GONE);
                break;
            case LocalConstants.META_TAG_DIALOG:
                LinearLayout content = (LinearLayout) findViewById(R.id.dialog_ly_full_content);
                content.removeAllViews();
                for (Pair<String,String> pair : mMetaTagsList) {
                    final LayoutInflater inflater_content = (LayoutInflater) mCtx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View view_content = inflater_content.inflate(R.layout.meta_tag_item, null, false);
                    content.addView(view_content);
                    TextView tv = (TextView) view_content.findViewById(R.id.tv_meta_item);
                    tv.setText(pair.first + ": " + pair.second);
                }
            default:
                break;
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_treatmen_accept:
                UtilsFunctions.saveSharedBoolean(mCtx, LocalConstants.DATA_TREATMENT_ACCEPTED, true);
                mFragmentInterface.registerUserAccepted();
                dismiss();
                break;
            case R.id.iv_treatmen_no_accept:
                UtilsFunctions.resetSharedPreferences(mCtx);
                getOwnerActivity().finish();
                System.exit(0);
                break;
            case R.id.im_generate_pass:
                UtilsFunctions.saveSharedBoolean(mCtx,LocalConstants.GENERATE_PASSWORD_ACCEPTED, true);
                dismiss();
            default:
                break;
        }
    }

    public static void setMetaTags(){

    }
}
