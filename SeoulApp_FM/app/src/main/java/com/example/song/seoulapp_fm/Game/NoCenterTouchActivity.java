package com.example.song.seoulapp_fm.Game;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.song.seoulapp_fm.PreferencesUtil;
import com.example.song.seoulapp_fm.R;
import com.example.song.seoulapp_fm.Service.FMService;

// thread & 이미지 위치 변경
// 저 사양 (AR 미 지원 기기) 용
public class NoCenterTouchActivity extends GameBaseActivity {

    private ImageView no_center_touch_image;
    private ImageView no_center_touch_image_target;
    private ImageThread imageThread;

    private double targetX, targetY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_center_touch);

        // ar 실행 시 service 일시 정지
        // service stop
        intent = new Intent(NoCenterTouchActivity.this, FMService.class);
        stopService(intent);

        no_center_touch_image = (ImageView) findViewById(R.id.no_center_touch_image);
        no_center_touch_image_target = (ImageView) findViewById(R.id.no_center_touch_image_target);

        // 튜토리얼은 lock 이미지 / 나머지는 해당 장소의 컬러 스탬프
        if (place_num == -1)
            no_center_touch_image.setImageDrawable(placeInfoSetting.placeInfo[12].getStamp_no_round());
        else
            no_center_touch_image.setImageDrawable(placeInfoSetting.placeInfo[place_num].getStamp_yes_round());

        imageThread = new ImageThread();
        imageThread.setDaemon(true);
        imageThread.start();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // 타겟 좌표
        targetX = no_center_touch_image_target.getX();
        targetY = no_center_touch_image_target.getY();
        Log.e("============", "target : " + targetX + "/" + targetY);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 서비스 종료 시 실행
        imageThread.stopForever();
        imageThread = null;//쓰레기 값을 만들어서 빠르게 회수하라고 null을 넣어줌.

        if (pref.getValue(PreferencesUtil.PREF_NOTIFICATION, true)) {
            // ar 종료 시 설정 on 상태 이면 service start
            intent = new Intent(NoCenterTouchActivity.this, FMService.class);
            startService(intent);
        }
    }

    // 이미지 위치 변경
    private void setImagePosition() {
        no_center_touch_image.setX(randomNumber(width));
        no_center_touch_image.setY(randomNumber(height));
    }

    class ImageThread extends Thread {

        boolean isRun = true;

        public void stopForever() {
            synchronized (this) {
                this.isRun = false;
            }
        }

        @Override
        public void run() {
            Looper.prepare();
            while (isRun) {
                Log.e("==========", "ImageThread isRun true");
                handler.sendEmptyMessage(0);

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Looper.loop();
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            setImagePosition();
        }
    };

    public void onNoCenterTouchBtnListner(View view) {
        switch (view.getId()) {
            case R.id.no_center_touch_image:
                Log.e("============", "view : " + view.getX() + "/" + view.getY());
                Log.e("============", Math.abs(view.getX() - targetX) + "/" + Math.abs(view.getY() - targetY));
                if (Math.abs(view.getX() - targetX) < 200 && Math.abs(view.getY() - targetY) < 200) {
                    // 미션 성공
                    // AR 스레드 종료
                    imageThread.stopForever();
                    // 이미지 터치 막기
                    no_center_touch_image.setClickable(false);

                    // 완료 처리 (애니메이션 & 팝업)
                    no_center_touch_image.startAnimation(animTransSizeUp);
                    new ARSuccessedAsync().execute();

                } else
                    myapp.showToast(getString(R.string.no_center_guide_text));

                break;
        }
    }
}