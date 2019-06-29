package com.play.module_login.debug;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.play.library_base.base.BaseContainerActivity;
import com.play.module_login.fragment.LoginFragment;

public class DebugActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, BaseContainerActivity.class);
        intent.putExtra("fragment", LoginFragment.class.getCanonicalName());
        this.startActivity(intent);
        finish();
    }
}
