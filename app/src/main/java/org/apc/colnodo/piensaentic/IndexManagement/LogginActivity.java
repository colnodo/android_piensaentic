package org.apc.colnodo.piensaentic.IndexManagement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import org.apc.colnodo.piensaentic.Activities.Intro.IntroActivity;
import org.apc.colnodo.piensaentic.R;
import org.apc.colnodo.piensaentic.Utils.LocalConstants;
import org.apc.colnodo.piensaentic.Utils.PiensaEnTic;
import org.apc.colnodo.piensaentic.Utils.UtilsFunctions;

public class LogginActivity extends Activity {

    private String mPassword;
    private EditText mEtPassword;
    private Context mCtx;
    private Tracker mTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPassword = UtilsFunctions.getSharedString(this, LocalConstants.USER_PASS);

        PiensaEnTic application = (PiensaEnTic) getApplication();
        mTracker = application.getDefaultTracker();
        mTracker.setScreenName("Loggin");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        if ( mPassword == null){
            Intent intent = new Intent(this, IntroActivity.class);
            startActivity(intent);
            this.finish();
        }
        setContentView(R.layout.activity_loggin);
        mCtx = this;
        mEtPassword = (EditText)findViewById(R.id.et_loggin);
        mEtPassword.addTextChangedListener(mTextWatcher);

        }


    public TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            try {
                if (mEtPassword.getText().toString().equals(mPassword)){
                    Intent intent = new Intent(mCtx, Home.class);
                    startActivity(intent);
                    Log.d("Loggin", "Password Correct" + mPassword + " "+ mEtPassword.getText().toString());
                }
            } catch (Exception ea){
                ea.printStackTrace();
            }
            Log.d("Loggin", "Password Correct" + mPassword + " "+ mEtPassword.getText().toString());

        }

        @Override
        public void afterTextChanged(Editable editable) {


        }
    };


}
