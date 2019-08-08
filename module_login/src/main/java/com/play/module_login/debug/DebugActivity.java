package com.play.module_login.debug;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.play.library_mvp.base.common.BaseActivity;
import com.play.module_login.fragment.LoginFragment;

public class DebugActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, BaseActivity.class);
        intent.putExtra("fragment", LoginFragment.class.getCanonicalName());
        this.startActivity(intent);
        finish();
    }
}
