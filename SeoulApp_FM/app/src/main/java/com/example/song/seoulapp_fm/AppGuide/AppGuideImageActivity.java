package com.example.song.seoulapp_fm.AppGuide;

import android.os.Bundle;
import android.view.View;

import com.example.song.seoulapp_fm.InfoSettingBaseActivity;
import com.example.song.seoulapp_fm.R;

public class AppGuideImageActivity extends InfoSettingBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_guide_image);
    }

    public void onAppGuideListener(View view) {
        finish();
    }
}