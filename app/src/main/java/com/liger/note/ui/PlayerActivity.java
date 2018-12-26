package com.liger.note.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.liger.note.R;
import com.liger.note.base.BaseActivity;

/**
 * @author Liger
 * @date 2018/12/25 00:30
 */
public class PlayerActivity extends BaseActivity {

    public static void launch(Context context) {
        Intent intent = new Intent(context, PlayerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
    }
}
