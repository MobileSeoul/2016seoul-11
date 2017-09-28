package com.example.song.seoulapp_fm.Game;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;

import com.example.song.seoulapp_fm.PreferencesUtil;
import com.example.song.seoulapp_fm.R;

/*
* AR 게임 튜토리얼
*/
public class ARTutorialActivity extends ARBaseActivity {

    private ImageView ar_tutorial_image_1;
    private ImageView ar_tutorial_image_2;
    private ImageView ar_tutorial_image_3;
    private ImageView ar_tutorial_image_4;
    private ImageView ar_tutorial_image_5;

    private int mDegree = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar_tutorial);

        ar_tutorial_image_1 = (ImageView) findViewById(R.id.ar_tutorial_image_center);
        ar_tutorial_image_2 = (ImageView) findViewById(R.id.ar_tutorial_image_top_left);
        ar_tutorial_image_3 = (ImageView) findViewById(R.id.ar_tutorial_image_top_right);
        ar_tutorial_image_4 = (ImageView) findViewById(R.id.ar_tutorial_image_bottom_left);
        ar_tutorial_image_5 = (ImageView) findViewById(R.id.ar_tutorial_image_bottom_right);
        ar_tutorial_image_2.setImageBitmap(rotateImage(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), 135));
        ar_tutorial_image_3.setImageBitmap(rotateImage(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), 225));
        ar_tutorial_image_4.setImageBitmap(rotateImage(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), 45));
        ar_tutorial_image_5.setImageBitmap(rotateImage(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), 315));

        new CountDownTimer(20 * 1000, 1000) {
            // 총 실행 시간, 인터벌(실행 간격)
            @Override
            public void onTick(long millisUntilFinished) {
                // 모두 View.GONE 시킨 후 랜덤하게 하나만 visible
                ar_tutorial_image_2.setVisibility(View.GONE);
                ar_tutorial_image_3.setVisibility(View.GONE);
                ar_tutorial_image_4.setVisibility(View.GONE);
                ar_tutorial_image_5.setVisibility(View.GONE);
                switch (randomNumber(5)) {
                    case 1:
//                        onArTutorialBtnListner(ar_tutorial_image_1);
//                        ar_tutorial_btn.callOnClick();
                        break;
                    case 2:
                        ar_tutorial_image_2.setVisibility(View.VISIBLE);
                        ar_tutorial_image_2.startAnimation(animTransTopLeft);
                        break;
                    case 3:
                        ar_tutorial_image_3.setVisibility(View.VISIBLE);
                        ar_tutorial_image_3.startAnimation(animTransTopRight);
                        break;
                    case 4:
                        ar_tutorial_image_4.setVisibility(View.VISIBLE);
                        ar_tutorial_image_4.startAnimation(animTransBottomLeft);
                        break;
                    case 5:
                        ar_tutorial_image_5.setVisibility(View.VISIBLE);
                        ar_tutorial_image_5.startAnimation(animTransBottomRight);
                        break;
                }
            }

            @Override
            public void onFinish() {
                // time over
                finish();
            }
        }.start();
/*
        mTask = new TimerTask() {
            @Override
            public void run() {
                // 모두 View.GONE 시킨 후 랜덤하게 하나만 visible
                ar_first_image_2.setVisibility(View.GONE);
                ar_first_image_3.setVisibility(View.GONE);
                ar_first_image_4.setVisibility(View.GONE);
                ar_first_image_5.setVisibility(View.GONE);
                switch (randomNumber(4)) {
                    case 1:
                        ar_first_image_2.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        ar_first_image_3.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        ar_first_image_4.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        ar_first_image_5.setVisibility(View.VISIBLE);
                        break;
                }
            }
        };

        mTimer = new Timer();
        mTimer.schedule(mTask, 1000, 3000);
*/
    }

    public void onArTutorialBtnListner(View view) {
        switch (view.getId()) {
            case R.id.ar_tutorial_image_center:
//                mDegree += 40;
//                ar_tutorial_image_1.setImageBitmap(rotateImage(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), mDegree));

                // 미션 성공 > 스탬프 획득 stamp[num] = true
                if (!pref.getValue(PreferencesUtil.PREF_STAMP[place_num], false)) {
                    // 기존 해당 스탬프 획득이 false 이면 true로 변경 / today 날짜(최초 획득 날짜) 저장
                    pref.setPreference(PreferencesUtil.PREF_STAMP[place_num], true);
                    pref.setPreference(PreferencesUtil.PREF_STAMP_DATE[place_num], getTodayDate());
                    // 메인 지도 아이콘 refresh - setMainIconImage
                }
                gameSuccessed();
                break;
        }
    }
}