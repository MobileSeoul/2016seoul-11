package com.example.song.seoulapp_fm;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by song on 2016-10-17.
 * 디바이스에 상태 정보 저장
 */
public class PreferencesUtil {


    // 앱 최초 실행
    // 최초 실행 true / 다음부턴 false
    public static String PREF_FIRST = "PREF_FIRST";
    // 알림 동의 여부
    // 동의 시 true, 디폴트 true
    public static String PREF_NOTIFICATION = "PREF_NOTIFICATION";

    // 튜토리얼 실행 여부 확인
    // 실행 완료 시 Y
    public static String PREF_TUTORIAL = "PREF_TUTORIAL";
    // ar guide 다시 보지 않기 여부 확인
    // Y면 AR 실행 시 guide 팝업 띄움
    // 다시보지 않기 체크 시 N
    public static String PREF_AR_GUIDE = "PREF_AR_GUIDE";

    // 스탬프 획득 현황
    // 획득 완료 true / 미획득 false
    public static String[] PREF_STAMP = {"PREF_STAMP_1", "PREF_STAMP_2", "PREF_STAMP_3",
            "PREF_STAMP_4", "PREF_STAMP_5", "PREF_STAMP_6", "PREF_STAMP_7", "PREF_STAMP_8",
            "PREF_STAMP_9", "PREF_STAMP_10", "PREF_STAMP_11", "PREF_STAMP_12", "PREF_STAMP_13"};
    // 스탬프 최초 획득 날짜
    public static String[] PREF_STAMP_DATE = {"PREF_STAMP_DATE_1", "PREF_STAMP_DATE_2", "PREF_STAMP_DATE_3",
            "PREF_STAMP_DATE_4", "PREF_STAMP_DATE_5", "PREF_STAMP_DATE_6", "PREF_STAMP_DATE_7", "PREF_STAMP_DATE_8",
            "PREF_STAMP_DATE_9", "PREF_STAMP_DATE_10", "PREF_STAMP_DATE_11", "PREF_STAMP_DATE_12", "PREF_STAMP_DATE_13"};

    public Context mContext;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public PreferencesUtil(Context context) {
        mContext = context;
        preferences = mContext.getSharedPreferences(String.valueOf(R.string.app_name), Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setPreference(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public void setPreference(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void setPreference(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public String getValue(String key, String dftValue) {
        return preferences.getString(key, dftValue);
    }

    public boolean getValue(String key, boolean dftValue) {
        return preferences.getBoolean(key, dftValue);
    }

    public int getValue(String key, int dftValue) {
        return preferences.getInt(key, dftValue);
    }
}