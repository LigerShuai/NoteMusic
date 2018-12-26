package com.liger.note.ui;

import android.os.Bundle;

import com.liger.note.R;
import com.liger.note.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_fl, new MainFragment(), "main")
                .commit();
    }

}
