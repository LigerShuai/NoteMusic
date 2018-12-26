package com.liger.note.permission;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import com.liger.note.NoteApp;
import com.liger.note.R;
import com.liger.note.base.BaseActivity;

/**
 * @author Liger
 * @date 2018/12/25 02:06
 */
public class PermissionActivity extends BaseActivity implements IPermissionCheck {

    protected static final int KEY_PERMISSION = 0x001;
    private PermissionHelper mPermissionHelper;
    private NoteApp mApp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApp = NoteApp.getInstance();
        initPermission();
    }

    private void initPermission() {
        mPermissionHelper = new PermissionHelper(this);
        mPermissionHelper.requestPermissions(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (mPermissionHelper.requestPermissionsResult(requestCode, permissions, grantResults)) {
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public int getPermissionsRequestCode() {
        return KEY_PERMISSION;
    }

    @Override
    public String[] getPermissions() {
        String[] permission = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        return concat(permission, getNeedPermissions());
    }

    @Override
    public void requestPermissionsSuccess() {
        mApp.initLibs();
        if (mApp.firstActivity() == null) {
            finish();
            return;
        }
        startActivityAndFinish(mApp.firstActivity());
    }

    @Override
    public void requestPermissionsFail() {
        new AlertDialog
                .Builder(this)
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.Permission_note)
                .setMessage(R.string.Permission_msg)
                .setPositiveButton(R.string.Permission_retry, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPermissionHelper.requestPermissions(PermissionActivity.this);
                    }
                })
                .show();
    }

    private void startActivityAndFinish(Class<?> cls) {
        startActivity(new Intent(this, cls));
        finish();
    }

    private String[] concat(String[] a, String[] b) {
        if (a == null || b == null) {
            return new String[0];
        }
        String[] c = new String[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    private String[] getNeedPermissions() {
        return mApp.allNeedPermissions();
    }

}
