package com.example.song.seoulapp_fm;

import android.app.Application;
import android.widget.Toast;

import com.tsengvn.typekit.Typekit;

/**
 * Created by song on 2016-10-01.
 * Application 공통 적용 사항
 * - 커스텀 폰트
 * - toast 객체
 */
public class MyApplication extends Application {

    Toast toast;

    @Override
    public void onCreate() {
        super.onCreate();

        Typekit.getInstance().addNormal(Typekit.createFromAsset(this, "fonts/SeoulNamsanEB.ttf"));
    }

    public void showToast(String message) {
        // 토스트 중복 생성 방지 위해
        if (toast == null)
            toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        else
            toast.setText(message);

        toast.show();
    }

    public void cancelToast() {
        // 실행 중인 토스트 제거
        if (toast != null)
            toast.cancel();
    }
}