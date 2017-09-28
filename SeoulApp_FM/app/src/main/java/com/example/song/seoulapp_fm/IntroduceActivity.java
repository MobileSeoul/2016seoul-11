package com.example.song.seoulapp_fm;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.song.seoulapp_fm.Game.ARCenterTouchActivity;
import com.example.song.seoulapp_fm.Game.NoCenterTouchActivity;

import net.daum.mf.map.api.MapCircle;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

/*
* 관광 명소 소개
* AR 게임 실행 전 설명 화면
*/
public class IntroduceActivity extends FmBaseActivity {

    private final String API_KEY = "f3f1939b77b14fff3c6842ba218b423a";

    private Context context;

    private ScrollView introduce_scroll_view;
    private TextView introduce_text_title;
    private TextView introduce_text_sub_title;
    private TextView introduce_text_address;
    private TextView introduce_text_content;

    private ImageView introduce_image_stamp;
    private ViewGroup introduce_map;

    private MapView mapView;

    private int place_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);

        context = this;

        Intent intent = getIntent();
        place_num = intent.getIntExtra("placeNum", 0) - 1;

        // place_num 에 따라 내용 set
        introduce_scroll_view = (ScrollView) findViewById(R.id.introduce_scroll_view);
        introduce_text_title = (TextView) findViewById(R.id.introduce_text_title);
        introduce_text_sub_title = (TextView) findViewById(R.id.introduce_text_sub_title);
        introduce_text_address = (TextView) findViewById(R.id.introduce_text_address);
        introduce_text_content = (TextView) findViewById(R.id.introduce_text_content);
        introduce_image_stamp = (ImageView) findViewById(R.id.introduce_image_stamp);
        introduce_map = (RelativeLayout) findViewById(R.id.introduce_map);

        setIntroduceInfo();
        new SetIntroduceMapAsync().execute();
    }

    private void setIntroduceInfo() {
        // place_num에 따른 장소 정보 setting
        introduce_text_title.setText(placeInfoSetting.placeInfo[place_num].getPlace_title());
        introduce_text_sub_title.setText(placeInfoSetting.placeInfo[place_num].getPlace_sub_title());
        introduce_text_address.setText(placeInfoSetting.placeInfo[place_num].getPlace_address());
        introduce_text_content.setText(placeInfoSetting.placeInfo[place_num].getPlace_content());

        // 스탬프 획득 현황 이미지 set
        if (pref.getValue(PreferencesUtil.PREF_STAMP[place_num], false))
            introduce_image_stamp.setImageDrawable(placeInfoSetting.placeInfo[place_num].getStamp_yes());
        else
            introduce_image_stamp.setImageDrawable(placeInfoSetting.placeInfo[place_num].getStamp_no());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                introduce_scroll_view.requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_UP:
                introduce_scroll_view.requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.onTouchEvent(event);
    }

    public void onIntroduceListener(View view) {
        switch (view.getId()) {
            case R.id.introduce_btn_start:
//                // 테스트 위해 북한산은 어디서나 실행
//                if (place_num == 0) {
//                    // 실행 테스트
//                    // AR 미 지원 버전에서는 카메라 뷰 없는 Activity 실행
//                    if (Build.VERSION.SDK_INT > 19)
//                        intent = new Intent(IntroduceActivity.this, ARCenterTouchActivity.class);
//                    else
//                        intent = new Intent(IntroduceActivity.this, NoCenterTouchActivity.class);
//                    intent.putExtra("placeNum", place_num);
//                    startActivity(intent);
//                } else {
                    // GPS 사용유무 상태 가져오기
                    if (gpsInfo.isGetLocation()) {
                        gpsInfo.getLocation();      // 클릭 시점의 좌표 가져오기
                        if (gpsInfo.isAbleDistance(place_num)) {
                            // 실행 가능 범위 내에 있으면 AR 게임 시작
                            // AR 미 지원 버전에서는 카메라 뷰 없는 Activity 실행
                            if (Build.VERSION.SDK_INT > 19)
                                intent = new Intent(IntroduceActivity.this, ARCenterTouchActivity.class);
                            else
                                intent = new Intent(IntroduceActivity.this, NoCenterTouchActivity.class);
                            intent.putExtra("placeNum", place_num);
                            startActivity(intent);
                        } else {
/*
                            // 위치 테스트
                            introduce_text_sub_title.setText(placeInfoSetting.placeInfo[place_num].getPlace_latitude() + " / " + placeInfoSetting.placeInfo[place_num].getPlace_longitude());
                            introduce_text_address.setText("현재 위치 : " + gpsInfo.getLatitude() + " / " + gpsInfo.getLongitude());
                            introduce_text_content.setText("result : " + gpsInfo.getDistance(place_num));
*/
                            // 실행 불가
                            myapp.showToast(getString(R.string.toast_no_position));
                        }
                    } else {
                        // GPS 를 사용할수 없으므로
                        myapp.showToast(getString(R.string.toast_no_gps));
                    }
//                }
                break;
        }
    }

    class SetIntroduceMapAsync extends AsyncTask<Void, Void, MapPoint> {

        @Override
        protected void onPreExecute() {
            // 지도 map set
            mapView = new MapView(context);
            mapView.setDaumMapApiKey(API_KEY);
            mapView.setFocusable(true);
//            mapView.setZoomLevel(7, true);

            introduce_map.addView(mapView);
        }

        @Override
        protected MapPoint doInBackground(Void... params) {
            MapPoint point = MapPoint.mapPointWithGeoCoord(placeInfoSetting.placeInfo[place_num].getPlace_latitude(), placeInfoSetting.placeInfo[place_num].getPlace_longitude());

            return point;
        }

        @Override
        protected void onPostExecute(MapPoint mapPoint) {
            // 중심점 변경
            MapCircle circle;
            if (place_num == 0) {
                // 북한산
                mapView.setMapCenterPointAndZoomLevel(mapPoint, 5, true);
                circle = new MapCircle(mapPoint, // center
                        2000, // radius
                        Color.argb(255, 101, 216, 231), // strokeColor
                        Color.argb(30, 10, 113, 132) // fillColor
                );
            } else if (place_num == 1 || place_num == 6 || place_num == 11) {
                // 경복궁, 어린이대공원, 올림픽공원
                mapView.setMapCenterPointAndZoomLevel(mapPoint, 3, true);
                circle = new MapCircle(mapPoint, // center
                        500, // radius
                        Color.argb(255, 101, 216, 231), // strokeColor
                        Color.argb(30, 10, 113, 132) // fillColor
                );
            } else if (place_num == 9 || place_num == 10) {
                // 한강 반포지구, 노량진 수산시장
                mapView.setMapCenterPointAndZoomLevel(mapPoint, 2, true);
                circle = new MapCircle(mapPoint, // center
                        200, // radius
                        Color.argb(255, 101, 216, 231), // strokeColor
                        Color.argb(30, 10, 113, 132) // fillColor
                );
            } else {
                mapView.setMapCenterPointAndZoomLevel(mapPoint, 1, true);
                circle = new MapCircle(mapPoint, // center
                        100, // radius
                        Color.argb(255, 101, 216, 231), // strokeColor
                        Color.argb(30, 10, 113, 132) // fillColor
                );
            }
            mapView.addCircle(circle);
        }
    }
}