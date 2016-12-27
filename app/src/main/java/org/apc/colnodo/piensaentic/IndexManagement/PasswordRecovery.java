package org.apc.colnodo.piensaentic.IndexManagement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import org.apc.colnodo.piensaentic.Activities.Intro.IntroActivity;
import org.apc.colnodo.piensaentic.R;
import org.apc.colnodo.piensaentic.Utils.LocalConstants;
import org.apc.colnodo.piensaentic.Utils.PiensaEnTic;
import org.apc.colnodo.piensaentic.Utils.RequestTask;
import org.apc.colnodo.piensaentic.Utils.ServerRequest;
import org.apc.colnodo.piensaentic.Utils.UtilsFunctions;

/**
 * Created by apple on 12/27/16.
 */

public class PasswordRecovery extends Activity implements RequestTask.OnRequestCompleted, View.OnClickListener{
    private String mMail;
    private EditText mEtMail;
    private Context mCtx;
    private Tracker mTracker;
    private ImageView mIvPasswordRecovery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PiensaEnTic application = (PiensaEnTic) getApplication();
        mTracker = application.getDefaultTracker();
        mTracker.setScreenName("Password Recovery");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
//
//        if ( mMail == null){
//            Intent intent = new Intent(this, IntroActivity.class);
//            startActivity(intent);
//            this.finish();
//        }
        setContentView(R.layout.password_recovery);
        mCtx = this;
        mIvPasswordRecovery = (ImageView)findViewById(R.id.bt_password_recovery);
        mEtMail = (EditText)findViewById(R.id.et_recovery);
        mIvPasswordRecovery.setOnClickListener(this);

        mIvPasswordRecovery.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        view.setAlpha(0.5f);
                        break;
                    case MotionEvent.ACTION_UP:
                        view.setAlpha(1f);
                    default :
                        view.setAlpha(1f);
                }
                return false;
            }
        });

    }


    @Override
    public void onRequestResponse(Object response, int taskId) {

        Toast.makeText(this, R.string.password_recovery_sent, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, LogginActivity.class);
        startActivity(intent);
        this.finish();

    }

    @Override
    public void onRequestError(int errorCode, String errorMsg, int taskId) {

        Log.d(this.getLocalClassName(), "error code:" + errorMsg  + " errorMsg: " + errorMsg);

        switch (errorCode){
            case LocalConstants.ERROR_USER_NOT_EXISTS:
                Toast.makeText(this, R.string.user_not_exists, Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_password_recovery:
                validateFields();
                break;
            default:
                break;
        }
    }

    private void validateFields() {
        try{
            String mail = mEtMail.getText().toString();
            if (mail.matches(Patterns.EMAIL_ADDRESS.toString())){
                new ServerRequest.PasswordRecoveryActivity(this, LocalConstants.PASSWORD_RECOVERY_TASK, mail, UtilsFunctions.getSharedString(this,LocalConstants.USER_PASS)).executePost();
            } else {
                Toast.makeText(this, R.string.invalid_mail, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ea){
            Toast.makeText(this, R.string.empty_mail, Toast.LENGTH_SHORT).show();
        }
    }
}
