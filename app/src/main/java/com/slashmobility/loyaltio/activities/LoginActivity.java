package com.slashmobility.loyaltio.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.slashmobility.loyaltio.R;

/**

 * @author Edgar Navarro
 * @version %I, %G
 * @since 1.0
 */

public class LoginActivity extends Activity {

    private ImageView mButtonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getViews();
        initListeners();
    }

    private void getViews(){
        mButtonLogin = (ImageView) findViewById(R.id.go_back);
    }

    private void initListeners(){

        mButtonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent splash_activity = new Intent(v.getContext(), SplashActivity.class);
                finish();
                startActivity(splash_activity);
            }
        });

    }
}
