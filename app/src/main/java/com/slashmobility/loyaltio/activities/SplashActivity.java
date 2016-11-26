package com.slashmobility.loyaltio.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.slashmobility.loyaltio.R;
import com.slashmobility.loyaltio.Constants;
import com.todddavies.components.progressbar.CircularProgressBar;

import java.util.Calendar;
import java.util.TimeZone;

import static java.util.Calendar.FEBRUARY;

/**

 * @author Edgar Navarro
 * @version %I, %G
 * @since 1.0
 */

public class SplashActivity extends Activity {

    private Calendar mConferenceTime  = null;
    private int mHour = 4;
    private int mMinute = 0;
    private int mSecond = 0;
    private int mMonthDay = 12;
    private int mMonth = FEBRUARY;
    private int mYear = 2017;
    private int mDisplayDays;
    private int mDisplayHours;
    private int mDisplayMinutes;
    private int mDisplaySeconds;
    private String mYearEvent;
    private String mMaintextevent;
    private CircularProgressBar mCircularProgressBarDays;
    private CircularProgressBar mCircularProgressBarHours;
    private CircularProgressBar mCircularProgressBarMinutes;
    private CircularProgressBar mCircularProgressBarSeconds;

    private Button mButtonLogin;
    private TextView text_world;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getViews();
        initCountDown();
        initListeners();
    }

    private void getViews(){
        text_world = (TextView) findViewById(R.id.textview_world);
        mYearEvent = getResources().getString(R.string.splash_text_main_year);
        mMaintextevent = getResources().getString(R.string.splash_text_main);

        String text = mMaintextevent + "<font color='#6b6965'>" + mYearEvent+"</font>";
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(text,Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(text);
        }
        text_world.setText(result);


        mConferenceTime = Calendar.getInstance(TimeZone.getTimeZone("GMT+04:00"));

        mCircularProgressBarDays = (CircularProgressBar) findViewById(R.id.circularprogressbardays);
        mCircularProgressBarDays.setSubTitle(Constants.SPLASH.DAYS);
        mCircularProgressBarDays.setMax(60);

        mCircularProgressBarHours = (CircularProgressBar) findViewById(R.id.circularprogressbarhours);
        mCircularProgressBarHours.setSubTitle(Constants.SPLASH.HOURS);
        mCircularProgressBarHours.setMax(24);

        mCircularProgressBarMinutes = (CircularProgressBar) findViewById(R.id.circularprogressbarminutes);
        mCircularProgressBarMinutes.setSubTitle(Constants.SPLASH.MINUTES);
        mCircularProgressBarMinutes.setMax(60);

        mCircularProgressBarSeconds = (CircularProgressBar) findViewById(R.id.circularprogressbarseconds);
        mCircularProgressBarSeconds.setSubTitle(Constants.SPLASH.SECONDS);
        mCircularProgressBarSeconds.setMax(60);

        mButtonLogin = (Button) findViewById(R.id.btn_login);
    }

    private void initListeners(){


        mButtonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent login_activity = new Intent(v.getContext(), LoginActivity.class);
                finish();
                startActivity(login_activity);
            }
        });

    }

    private void initCountDown(){
        mConferenceTime.set(mYear, mMonth, mMonthDay, mHour, mMinute, mSecond);
        long confMillis = mConferenceTime.getTimeInMillis();
        Calendar nowTime  = Calendar.getInstance();
        long nowMillis = nowTime.getTimeInMillis();
        long milliDiff = confMillis - nowMillis;
        int days = (int) (milliDiff/1000)/86400;

        new CountDownTimer(milliDiff, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                mDisplayDays = (int) ((millisUntilFinished / 1000) / 86400);
                mDisplayHours = (int) (((millisUntilFinished / 1000) - (mDisplayDays * 86400)) / 3600);
                mDisplayMinutes = (int) (((millisUntilFinished / 1000) - ((mDisplayDays * 86400) + (mDisplayHours * 3600))) / 60);
                mDisplaySeconds = (int) ((millisUntilFinished / 1000) % 60);

                mCircularProgressBarDays.setTitle(String.valueOf(mDisplayDays));
                mCircularProgressBarDays.setProgress(mDisplayDays);

                mCircularProgressBarHours.setTitle(String.valueOf(mDisplayHours));
                mCircularProgressBarHours.setProgress(mDisplayHours);

                mCircularProgressBarMinutes.setTitle(String.valueOf(mDisplayMinutes));
                mCircularProgressBarMinutes.setProgress(mDisplayMinutes);

                mCircularProgressBarSeconds.setTitle(String.valueOf(mDisplaySeconds));
                mCircularProgressBarSeconds.setProgress(mDisplaySeconds);

                Animation animation = new RotateAnimation(0.0f, 90.0f, 250f, 273f);
                animation.setFillAfter(true);

            }

            @Override
            public void onFinish() {
                mCircularProgressBarDays.setTitle("0");
                mCircularProgressBarDays.setProgress(0);

                mCircularProgressBarHours.setTitle("0");
                mCircularProgressBarHours.setProgress(0);

                mCircularProgressBarMinutes.setTitle("0");
                mCircularProgressBarMinutes.setProgress(0);

                mCircularProgressBarSeconds.setTitle("0");
                mCircularProgressBarSeconds.setProgress(0);
                finish();
                //startActivity(new Intent( getApplicationContext(), MainActivity.class));
            }
        }.start();
    }
}
