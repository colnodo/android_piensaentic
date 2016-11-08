package org.apc.colnodo.piensaentic.Utils;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.apc.colnodo.piensaentic.R;

/**
 * Created by apple on 11/7/16.
 */

public class AlertDialog extends Dialog implements View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();

    private ImageView mImAccept, mImNoAccept;
    private Context mCtx;


    public AlertDialog(Context context) {
        super(context);
        mCtx = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.data_treatment_dialog);
        mImAccept = (ImageView)findViewById(R.id.iv_treatmen_accept);
        mImNoAccept = (ImageView) findViewById(R.id.iv_treatmen_no_accept);
        mImAccept.setOnClickListener(this);
        mImNoAccept.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_treatmen_accept:
                UtilsFunctions.saveSharedBoolean(mCtx, LocalConstants.DATA_TREATMENT_ACCEPTED, true);
                dismiss();
                break;
            case R.id.iv_treatmen_no_accept:
                getOwnerActivity().finish();
                System.exit(0);
                break;
            default:
                break;
        }
    }
}
