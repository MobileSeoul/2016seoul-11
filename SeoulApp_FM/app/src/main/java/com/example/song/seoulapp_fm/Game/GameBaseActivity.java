package com.example.song.seoulapp_fm.Game;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.song.seoulapp_fm.InfoSettingBaseActivity;
import com.example.song.seoulapp_fm.MainActivity;
import com.example.song.seoulapp_fm.PopupDialog;
import com.example.song.seoulapp_fm.PreferencesUtil;
import com.example.song.seoulapp_fm.R;
import com.example.song.seoulapp_fm.StampActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GameBaseActivity extends InfoSettingBaseActivity {

    // 이미지 애니메이션
    protected Animation animTransRotate;
    protected Animation animTransSizeUp;
    protected Animation animTransTopLeft;
    protected Animation animTransTopRight;
    protected Animation animTransBottomLeft;
    protected Animation animTransBottomRight;

    int width, height;  // 디바이스 화면 사이즈

    private PopupDialog dialog = null;

    protected int place_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_base);

        // 튜토리얼은 -1 (stamp_no_round[12]를 보여준다
        intent = getIntent();
        place_num = intent.getIntExtra("placeNum", -1);

        setAnimation();

        // 디바이스 사이즈
        getDisplayMetrics();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null && dialog.isShowing()) dialog.dismiss();
    }

    // 랜덤 수 생성 (1 ~ count 사이의 정수)
    public int randomNumber(int count) {
        return (int) (Math.random() * count) + 1;
    }

    // today 날짜 구하기
    public String getTodayDate() {

/*
        long time = System.currentTimeMillis();
        SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");
        String result = today.format(new Date(time));
*/

        Date date = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd (E)");
        SimpleDateFormat simpleTime = new SimpleDateFormat("a hh:mm:ss");
        String result = simpleDate.format(date) + "\n" + simpleTime.format(date);

        return result;
    }

    public void setAnimation() {
        // 이미지 애니메이션 효과
        animTransRotate = AnimationUtils.loadAnimation(this, R.anim.anim_translate_rotate);
        animTransSizeUp = AnimationUtils.loadAnimation(this, R.anim.anim_translate_size_up);
        animTransTopLeft = AnimationUtils.loadAnimation(this, R.anim.anim_translate_top_left);
        animTransTopRight = AnimationUtils.loadAnimation(this, R.anim.anim_translate_top_right);
        animTransBottomLeft = AnimationUtils.loadAnimation(this, R.anim.anim_translate_bottom_left);
        animTransBottomRight = AnimationUtils.loadAnimation(this, R.anim.anim_translate_bottom_right);
    }

    // 디바이스 화면 사이즈
    private void getDisplayMetrics() {
        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
        Log.e("==========", "getDisplayMetrics : " + width + "/" + height);
    }

    public void gameSuccessed() {
        // 미션 성공 - 스탬프 획득
        if (dialog == null || !dialog.isShowing()) {
            // 종료 팝업 커스텀 dialog
            if (place_num == -1) {
                // 튜토리얼로 실행 했을 경우 메인으로 이동
                dialog = new PopupDialog(this, this.getString(R.string.dialog_popup_title_tutorial_finish), leftListener);
            } else if (place_num == 12) {
                // 히든 장소로 실행 했을 경우 메인으로 이동
                dialog = new PopupDialog(this, this.getString(R.string.dialog_popup_title_hidden_game_finish), leftListener);
            } else {
                // 스탬프 획득 시 - 스탬프 현황으로 이동 or 메인으로 이동
                dialog = new PopupDialog(this, this.getString(R.string.dialog_popup_title_game_finish), leftListener, rightListener);
            }
        }
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    // 팝업 리스너
    private View.OnClickListener leftListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // 취소 - 메인으로 이동
            intent = new Intent(GameBaseActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            if (place_num == -1 || place_num == 12)
                finish();
        }
    };

    private View.OnClickListener rightListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // 확인 - 스탬프 현황으로 이동
            intent = new Intent(GameBaseActivity.this, MainActivity.class);
            intent = new Intent(GameBaseActivity.this, StampActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);

            finish();
        }
    };

    // AR 게임 성공 애니메이션 & 완료처리
    class ARSuccessedAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Void doInBackground(Void... params) {
            if (place_num != -1 && !pref.getValue(PreferencesUtil.PREF_STAMP[place_num], false)) {
                // 기존 해당 스탬프 획득이 false 이면 true로 변경 / today 날짜(최초 획득 날짜) 저장
                pref.setPreference(PreferencesUtil.PREF_STAMP[place_num], true);
                pref.setPreference(PreferencesUtil.PREF_STAMP_DATE[place_num], getTodayDate());
            }

            try {
                Thread.sleep(4500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void mapPoint) {
            gameSuccessed();
        }
    }
}