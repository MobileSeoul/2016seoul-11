package com.example.song.seoulapp_fm.Game;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.song.seoulapp_fm.MainActivity;
import com.example.song.seoulapp_fm.PreferencesUtil;
import com.example.song.seoulapp_fm.R;

import java.util.Timer;

/*
* AR 공통 레이아웃
* (카메라 뷰)
*/
public class ARBaseActivity extends GameBaseActivity {

    // 카메라 객체
    private Camera camera_main;
    private CameraPreview preview;
    private FrameLayout frame;      // 카메라 미리보기 frame 뷰

    // 타이머 객체 - 스레드 이용 위해
    public Timer mTimer;

    protected FrameLayout ar_base_content;  // 상속받을 activity의 base layout

    private ARGuideDialog guideDialog = null;
    // 가이드 팝업이 보여지고 있으면 1, 아니면 0
    public static int isGuideShow = 0;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_ar_base);

/*
        // ar 실행 시 service 일시 정지
        // service stop
        intent = new Intent(ARBaseActivity.this, FMService.class);
        stopService(intent);
*/

        if (!pref.getValue(PreferencesUtil.PREF_AR_GUIDE, "D").equals("N"))
            onArBaseBtnListner(null);

        // camera
        Log.e("===========", "ARBaseActivity camera start");
        camera_main = getCameraInstance();

        // 카메라 미리보기를 하는 CameraPreview 객체 생성
        Log.e("===========", "ARBaseActivity camera preview");
        preview = new CameraPreview(this, camera_main);
        Log.e("===========", "ARBaseActivity camera set");

        // frameLayout을 미리보기 스크린으로 사용하기 위해
        frame = (FrameLayout) findViewById(R.id.ar_base_preview);
        // CameraPreview 객체를 FrameLayout의 내용으로 넣어서 FrameLayout을 카메라 미리보기 스크린으로 사용
        frame.addView(preview);

        // base frame
        ar_base_content = (FrameLayout) findViewById(R.id.ar_base_content);
        ar_base_content.addView(LayoutInflater.from(this).inflate(layoutResID, null));

    }

    @Override
    public void setContentView(View view) {
        super.setContentView(R.layout.activity_ar_base);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(R.layout.activity_ar_base);
    }

    // Camera 객체 생성하는 메소드
    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open();
        } catch (Exception e) {
        }

        return c;
    }

    // 이미지 회전 함수
    public Bitmap rotateImage(Bitmap src, float degree) {
        // Matrix 객체 생성
        Matrix matrix = new Matrix();
        // 회전 각도 셋팅
        matrix.postRotate(degree);
        // 이미지와 Matrix 를 셋팅해서 Bitmap 객체 생성
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mTimer != null) mTimer.cancel();
//        if (dialog != null && dialog.isShowing()) dialog.dismiss();
        if (guideDialog != null && guideDialog.isVisible()) guideDialog.dismiss();

/*
        if (pref.getValue(PreferencesUtil.PREF_NOTIFICATION, true)) {
            // ar 종료 시 설정 on 상태 이면 service start
            intent = new Intent(ARBaseActivity.this, FMService.class);
            startService(intent);
        }
*/
    }

    public void onArBaseBtnListner(View view) {
        // 게임 가이드 보기
        if (isGuideShow == 0) {
            isGuideShow = 1;
            guideDialog = new ARGuideDialog();
            guideDialog.setCancelable(false);       // back key 막기
            guideDialog.show(getSupportFragmentManager(), "ArGuide");
        }
    }

    @Override
    public void onBackPressed() {
        // back 버튼 클릭
        if (place_num == -1 || place_num == 12) {
            intent = new Intent(ARBaseActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);

            finish();
        } else
            super.onBackPressed();
    }
}