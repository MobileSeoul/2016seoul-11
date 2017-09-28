package com.example.song.seoulapp_fm;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.song.seoulapp_fm.Game.ARCenterTouchActivity;
import com.example.song.seoulapp_fm.Game.NoCenterTouchActivity;

public class FmBaseActivity extends InfoSettingBaseActivity implements View.OnTouchListener {

    protected ImageView base_title_btn_name;
    protected Button base_title_btn_menu;
    protected View base_dim_view;
    protected FrameLayout base_content;
    protected RelativeLayout base_text_app_introduce;
    protected LinearLayout base_btn_layout;
    protected Button base_btn_1;
    protected Button base_btn_2;
    protected Button base_btn_3;
    protected Boolean base_text_visible = false;
    protected Boolean base_btn_visible = false;

    protected PopupDialog dialog = null;

    protected GpsInfo gpsInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_fm_base);

        base_title_btn_name = (ImageView) findViewById(R.id.base_title_btn_name);
        base_title_btn_menu = (Button) findViewById(R.id.base_title_btn_menu);
        base_dim_view = (View) findViewById(R.id.base_dim_view);
        base_content = (FrameLayout) findViewById(R.id.base_content);
        base_text_app_introduce = (RelativeLayout) findViewById(R.id.base_text_app_introduce);
        base_btn_layout = (LinearLayout) findViewById(R.id.base_btn_layout);
        base_btn_1 = (Button) findViewById(R.id.base_btn_1);
        base_btn_2 = (Button) findViewById(R.id.base_btn_2);
        base_btn_3 = (Button) findViewById(R.id.base_btn_3);

        base_dim_view.setOnTouchListener(this);
        base_text_visible = false;
        base_text_app_introduce.setVisibility(View.GONE);
        base_btn_visible = false;
        base_btn_layout.setVisibility(View.GONE);

        base_content.addView(LayoutInflater.from(this).inflate(layoutResID, null));

        // gps 위경도 테스트
        gpsInfo = new GpsInfo(FmBaseActivity.this);
    }

    public void onBaseBtnListner(View view) {
        switch (view.getId()) {
            case R.id.base_title_btn_name:
                if (base_btn_visible)
                    // base 버튼 리스트 닫기
                    base_title_btn_menu.callOnClick();

                // 타이틀 바 로고 클릭
                // 앱 설명 글 보이기
                if (base_text_visible) {
                    // 설명 닫기
                    base_dim_view.setVisibility(View.GONE);
                    base_text_visible = false;
                    base_text_app_introduce.setVisibility(View.GONE);
                } else {
                    // 설명 보기
                    base_dim_view.setVisibility(View.VISIBLE);
                    base_text_visible = true;
                    base_text_app_introduce.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.base_title_btn_menu:
                if (base_text_visible)
                    // 앱 설명 글 닫기
                    base_title_btn_name.callOnClick();

                // 타이틀 바 메뉴 버튼 클릭
                // base 버튼 리스트 보이기
                if (base_btn_visible) {
                    // 버튼 닫기
                    base_dim_view.setVisibility(View.GONE);
                    base_btn_visible = false;
                    base_btn_layout.setVisibility(View.GONE);
                } else {
                    // 버튼 보기
                    base_dim_view.setVisibility(View.VISIBLE);
                    base_btn_visible = true;
                    base_btn_layout.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.base_btn_1:
                // 모든 activity 종료 후 메인 화면으로 이동
                intent = new Intent(FmBaseActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                // base 버튼 리스트 닫기
                base_title_btn_menu.callOnClick();
                break;
            case R.id.base_btn_2:
                // 스탬프 현황 보기
                intent = new Intent(FmBaseActivity.this, StampActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                // base 버튼 리스트 닫기
                base_title_btn_menu.callOnClick();
                break;
            case R.id.base_btn_3:
                // 게임 튜토리얼 실행

                // 튜토리얼 최초 실행 시
                if (!pref.getValue(PreferencesUtil.PREF_TUTORIAL, "N").equals("Y"))
                    pref.setPreference(PreferencesUtil.PREF_TUTORIAL, "Y");

                // AR 미 지원 버전에서는 카메라 뷰 없는 Activity 실행
                if (Build.VERSION.SDK_INT > 19)
                    intent = new Intent(FmBaseActivity.this, ARCenterTouchActivity.class);
                else
                    intent = new Intent(FmBaseActivity.this, NoCenterTouchActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                // base 버튼 리스트 닫기
                base_title_btn_menu.callOnClick();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (base_btn_visible) {
            // base 버튼 리스트 열려 있으면 닫기
            base_title_btn_menu.callOnClick();
        } else if (base_text_visible) {
            // 앱 설명 글이 열려 있으면 닫기
            base_title_btn_name.callOnClick();
        } else {
            // 열려있지 않으면 해당 activity 종료
            super.onBackPressed();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }
}