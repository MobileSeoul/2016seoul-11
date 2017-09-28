package com.example.song.seoulapp_fm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.song.seoulapp_fm.AppGuide.AppGuideImageActivity;
import com.example.song.seoulapp_fm.Service.FMService;

import pl.polidea.view.ZoomView;

public class MainActivity extends FmBaseActivity {

    private Button[] main_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 앱 설치 후 최초 실행 시
        // 최초실행 여부 false 저장 / 알림 설정 true 저장 / 앱 가이드 보기
        if (pref.getValue(PreferencesUtil.PREF_FIRST, true)) {
            pref.setPreference(PreferencesUtil.PREF_FIRST, false);
            pref.setPreference(PreferencesUtil.PREF_NOTIFICATION, true);
            // 앱 가이드 image set
            onMainListener(null);
/*
            // service start
            intent = new Intent(MainActivity.this, FMService.class);
            startService(intent);
*/
        }

        setZoomView();
        setMainIconImage();
    }

    @Override
    protected void onResume() {
        super.onResume();

        setMainIconImage();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (pref.getValue(PreferencesUtil.PREF_NOTIFICATION, true)) {
            // 설정 on 상태 이면 service start
            intent = new Intent(MainActivity.this, FMService.class);
            startService(intent);
        }
    }

    private void setZoomView() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_main_map, null, false);

        main_icon = new Button[12];
        main_icon[0] = (Button) view.findViewById(R.id.main_icon_1);
        main_icon[1] = (Button) view.findViewById(R.id.main_icon_2);
        main_icon[2] = (Button) view.findViewById(R.id.main_icon_3);
        main_icon[3] = (Button) view.findViewById(R.id.main_icon_4);
        main_icon[4] = (Button) view.findViewById(R.id.main_icon_5);
        main_icon[5] = (Button) view.findViewById(R.id.main_icon_6);
        main_icon[6] = (Button) view.findViewById(R.id.main_icon_7);
        main_icon[7] = (Button) view.findViewById(R.id.main_icon_8);
        main_icon[8] = (Button) view.findViewById(R.id.main_icon_9);
        main_icon[9] = (Button) view.findViewById(R.id.main_icon_10);
        main_icon[10] = (Button) view.findViewById(R.id.main_icon_11);
        main_icon[11] = (Button) view.findViewById(R.id.main_icon_12);

        // 메인 지도 Zoom
        ZoomView zoomView = new ZoomView(this);
        zoomView.addView(view);
        zoomView.setMiniMapEnabled(false);       // 좌측 상단 검은색 미니맵 설정
        zoomView.setMaxZoom(2f);            //  줌 Max 배율 설정 (1f로 설정하면 줌 안됨)
        zoomView.setMiniMapCaption("Map ZoomIn");    // 미니맵 내용
        zoomView.setMiniMapCaptionSize(20);         // 미니 맵 글씨 크기 설정
        zoomView.setMiniMapHeight(250);

        FrameLayout container = (FrameLayout) findViewById(R.id.main_map_container);
        container.addView(zoomView);
    }

    private void setMainIconImage() {

        for (int i = 0; i < main_icon.length; i++)
            if (pref.getValue(PreferencesUtil.PREF_STAMP[i], false))
                main_icon[i].setBackground(placeInfoSetting.placeInfo[i].getPlace_yes());
    }

    public void onMainIconListener(View view) {
        intent = new Intent(MainActivity.this, IntroduceActivity.class);
        switch (view.getId()) {
            case R.id.main_icon_1:
                intent.putExtra("placeNum", 1);
                break;
            case R.id.main_icon_2:
                intent.putExtra("placeNum", 2);
                break;
            case R.id.main_icon_3:
                intent.putExtra("placeNum", 3);
                break;
            case R.id.main_icon_4:
                intent.putExtra("placeNum", 4);
                break;
            case R.id.main_icon_5:
                intent.putExtra("placeNum", 5);
                break;
            case R.id.main_icon_6:
                intent.putExtra("placeNum", 6);
                break;
            case R.id.main_icon_7:
                intent.putExtra("placeNum", 7);
                break;
            case R.id.main_icon_8:
                intent.putExtra("placeNum", 8);
                break;
            case R.id.main_icon_9:
                intent.putExtra("placeNum", 9);
                break;
            case R.id.main_icon_10:
                intent.putExtra("placeNum", 10);
                break;
            case R.id.main_icon_11:
                intent.putExtra("placeNum", 11);
                break;
            case R.id.main_icon_12:
                intent.putExtra("placeNum", 12);
                break;
        }
        startActivity(intent);
    }

    public void onMainListener(View view) {
        // 설정 팝업
        intent = new Intent(MainActivity.this, AppGuideImageActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // 메인 Activity에서 back 버튼 클릭 시

        if (base_btn_visible) {
            // base 버튼 리스트 닫기
            base_title_btn_menu.callOnClick();
        } else if (base_text_visible) {
            // base 버튼 리스트 닫기
            base_title_btn_name.callOnClick();
        } else if (dialog == null || !dialog.isShowing()) {
            // 종료 팝업 커스텀 dialog
            dialog = new PopupDialog(this, leftListener, rightListener);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();


            // AR 게임 후 스탬프 획득 성공
            // 스탬프 획득에 성공하였습니다. 스탬프 현황을 확인하시겠습니까?
/*
        // 종료 팝업 띄우기 (기본 팝업)
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.app_name).setMessage("앱을 종료하시겠습니까?").setCancelable(false)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).show();
*/
        }
    }

    // 팝업 리스너
    private View.OnClickListener leftListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // 취소 - dialog 창 닫기
            dialog.dismiss();
        }
    };

    private View.OnClickListener rightListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // 확인 - 종료
            dialog.dismiss();
            finish();
        }
    };
}