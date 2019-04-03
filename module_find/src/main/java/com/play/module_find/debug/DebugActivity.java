package com.play.module_find.debug;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.play.library_base.base.BaseContainerActivity;
import com.play.module_find.R;
import com.play.module_find.fragment.FindFragment;

public class DebugActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, BaseContainerActivity.class);
        intent.putExtra("fragment", FindFragment.class.getCanonicalName());
        this.startActivity(intent);
        finish();
    }
}
