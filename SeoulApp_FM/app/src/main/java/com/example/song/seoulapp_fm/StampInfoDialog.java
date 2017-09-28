package com.example.song.seoulapp_fm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/*
* AR 게임 방법 안내
*/
public class StampInfoDialog extends DialogFragment {

    // preference
    private PreferencesUtil pref;
    // PlaceInfoSetting - 장소 정보 객체
    protected PlaceInfoSetting placeInfoSetting;

    private ImageView dialog_stamp_info_close;
    private ImageView dialog_stamp_info_image;
    private TextView dialog_stamp_info_text_place;
    private TextView dialog_stamp_info_text_date;

    int place_num = 0;
    int image[] = {R.drawable.stamp_yes_1, R.drawable.stamp_yes_2, R.drawable.stamp_yes_3, R.drawable.stamp_yes_4,
            R.drawable.stamp_yes_5, R.drawable.stamp_yes_6, R.drawable.stamp_yes_7, R.drawable.stamp_yes_8, R.drawable.stamp_yes_9,
            R.drawable.stamp_yes_10, R.drawable.stamp_yes_11, R.drawable.stamp_yes_12, R.drawable.stamp_yes_1};

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // preference
        pref = new PreferencesUtil(getContext());
        // PlaceInfoSetting
        placeInfoSetting = new PlaceInfoSetting(getContext());

        LayoutInflater mLayoutInflater = getActivity().getLayoutInflater();
        View view = mLayoutInflater.inflate(R.layout.dialog_stamp_info, null);

        // dialog 종료
        dialog_stamp_info_close = (ImageView) view.findViewById(R.id.dialog_stamp_info_close);
        dialog_stamp_info_image = (ImageView) view.findViewById(R.id.dialog_stamp_info_image);
        dialog_stamp_info_text_place = (TextView) view.findViewById(R.id.dialog_stamp_info_text_place);
        dialog_stamp_info_text_date = (TextView) view.findViewById(R.id.dialog_stamp_info_text_date);

        dialog_stamp_info_close.setOnClickListener(stampInfoListener);
        dialog_stamp_info_image.setImageResource(image[place_num]);

        dialog_stamp_info_text_place.setText(placeInfoSetting.placeInfo[place_num].getPlace_title());
        dialog_stamp_info_text_date.setText(pref.getValue(PreferencesUtil.PREF_STAMP_DATE[place_num], "2016-01-01"));

        // 팝업 dialog 띄우기
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setView(view);

        return dialogBuilder.create();
    }

    // 팝업 리스너
    private View.OnClickListener stampInfoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };

    @Override
    public void onStop() {
        super.onStop();
    }
}