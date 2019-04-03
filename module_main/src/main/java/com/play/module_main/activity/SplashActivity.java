package com.play.module_main.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goMain();
            }
        }, 3 * 1000);
    }


    /**
     * 进入主页面
     */
    private void goMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
