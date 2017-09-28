package com.example.song.seoulapp_fm;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.tsengvn.typekit.TypekitContextWrapper;

public class InfoSettingBaseActivity extends FragmentActivity {

    protected MyApplication myapp;
    // preference
    protected PreferencesUtil pref;
    // PlaceInfoSetting - 장소 정보 객체
    protected PlaceInfoSetting placeInfoSetting;

    protected FrameLayout info_setting_base_content;
    protected Intent intent = null;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_info_setting_base);

        // 화면 회전 세로모드로 고정
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        myapp = (MyApplication) getApplication();
        // preference
        pref = new PreferencesUtil(this);
        // PlaceInfoSetting
        placeInfoSetting = new PlaceInfoSetting(this);

        info_setting_base_content = (FrameLayout) findViewById(R.id.info_setting_base_content);
        info_setting_base_content.addView(LayoutInflater.from(this).inflate(layoutResID, null));

        // 상태바 색상 변경
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.BLACK);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        // MyApplication에서 설정한 폰트 적용
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}