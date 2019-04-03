package com.play.module_news.debug;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.play.library_base.base.BaseContainerActivity;
import com.play.module_news.R;
import com.play.module_news.fragmnet.NewsFragment;

public class DebugActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, BaseContainerActivity.class);
        intent.putExtra("fragment", NewsFragment.class.getCanonicalName());
        this.startActivity(intent);
        finish();
    }
}