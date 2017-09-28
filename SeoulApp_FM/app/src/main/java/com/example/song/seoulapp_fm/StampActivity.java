package com.example.song.seoulapp_fm;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/*
* 스탬프 획득 현황
* 미획득 스탬프 - 흑백 (lock) / 획득 완료 스탬프 - 컬러
* 각 스탬프 이미지 클릭 시 해당 명소 설명 팝업
*/
public class StampActivity extends FmBaseActivity {

    private ImageView[] stamp_image;

    int count = 0;
    int shoes[][];
    int putShoes[][];

    private StampInfoDialog stampDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp);

        stamp_image = new ImageView[12];
        stamp_image[0] = (ImageView) findViewById(R.id.stamp_image_1);
        stamp_image[1] = (ImageView) findViewById(R.id.stamp_image_2);
        stamp_image[2] = (ImageView) findViewById(R.id.stamp_image_3);
        stamp_image[3] = (ImageView) findViewById(R.id.stamp_image_4);
        stamp_image[4] = (ImageView) findViewById(R.id.stamp_image_5);
        stamp_image[5] = (ImageView) findViewById(R.id.stamp_image_6);
        stamp_image[6] = (ImageView) findViewById(R.id.stamp_image_7);
        stamp_image[7] = (ImageView) findViewById(R.id.stamp_image_8);
        stamp_image[8] = (ImageView) findViewById(R.id.stamp_image_9);
        stamp_image[9] = (ImageView) findViewById(R.id.stamp_image_10);
        stamp_image[10] = (ImageView) findViewById(R.id.stamp_image_11);
        stamp_image[11] = (ImageView) findViewById(R.id.stamp_image_12);

        setStampImage();
    }

    private void setStampImage() {
        for (int i = 0; i < stamp_image.length; i++) {
            if (pref.getValue(PreferencesUtil.PREF_STAMP[i], false))
                stamp_image[i].setImageDrawable(placeInfoSetting.placeInfo[i].getStamp_yes_round());
        }
    }

    public void onStampListener(View view) {
        // StampInfoDialog 보기
        stampDialog = new StampInfoDialog();
        stampDialog.setCancelable(false);       // back key 막기
        int position = 0;

        switch (view.getId()) {
            case R.id.stamp_image_1:
                position = 0;
                break;
            case R.id.stamp_image_2:
                position = 1;
                break;
            case R.id.stamp_image_3:
                position = 2;
                break;
            case R.id.stamp_image_4:
                position = 3;
                break;
            case R.id.stamp_image_5:
                position = 4;
                break;
            case R.id.stamp_image_6:
                position = 5;
                break;
            case R.id.stamp_image_7:
                position = 6;
                break;
            case R.id.stamp_image_8:
                position = 7;
                break;
            case R.id.stamp_image_9:
                position = 8;
                break;
            case R.id.stamp_image_10:
                position = 9;
                break;
            case R.id.stamp_image_11:
                position = 10;
                break;
            case R.id.stamp_image_12:
                position = 11;
                break;
        }

        // 해당 인덱스 스탬프 획득 시 stampDialog / 미획득 시 확인 불가 안내 토스트
        if (pref.getValue(PreferencesUtil.PREF_STAMP[position], false)) {
            // 스탬프 획득 true
            myapp.cancelToast();
            stampDialog.place_num = position;
            stampDialog.show(getSupportFragmentManager(), "StampInfo");
        } else {
            myapp.showToast(this.getString(R.string.toast_stamp_no_get));
        }
    }
}