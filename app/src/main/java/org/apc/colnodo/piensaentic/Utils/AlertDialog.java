package org.apc.colnodo.piensaentic.Utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import org.apc.colnodo.piensaentic.R;

/**
 * Created by apple on 11/7/16.
 */

public class AlertDialog extends Dialog implements View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();

    public TextView mTvTittle, mTvNote;
    private ImageView mImAccept, mImNoAccept;
    private Context mCtx;
    private int mType;


    public AlertDialog(Context context, int dialogType) {
        super(context);
        mCtx = context;
        mType = dialogType;
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
            case 1:
                mImNoAccept.setOnClickListener(this);
                mImAccept.setOnClickListener(this);
                break;
            case 2:
                mImAccept.setImageResource(R.drawable.pantalla_07_bot_sigue_estos_pasos);
                mImAccept.setId(R.id.im_generate_pass);
                mTvTittle.setText(R.string.dialog_create_password_tittle);
                mImNoAccept.setVisibility(View.GONE);
                mTvNote.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_treatmen_accept:
                UtilsFunctions.saveSharedBoolean(mCtx, LocalConstants.DATA_TREATMENT_ACCEPTED, true);
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
}
