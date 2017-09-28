package com.example.song.seoulapp_fm;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by song on 2016-10-17.
 */
public class PlaceInfoSetting {

    private Context mContext;

    public PlaceInfoData[] placeInfo;
    private String[] title;
    private String[] subTitle;
    private String[] address;
    private double[] latitude;
    private double[] longitude;
    private String[] content;

    private Drawable[] place_yes;
    private Drawable[] place_no;
    private Drawable[] stamp_yes;
    private Drawable[] stamp_no;
    private Drawable[] stamp_yes_round;
    private Drawable[] stamp_no_round;


    public PlaceInfoSetting(Context context) {

        mContext = context;

        placeInfo = new PlaceInfoData[13];
        title = new String[13];
        subTitle = new String[13];
        address = new String[13];
        latitude = new double[13];
        longitude = new double[13];
        content = new String[13];

        place_yes = new Drawable[12];
        place_no = new Drawable[12];
        stamp_yes = new Drawable[12];
        stamp_no = new Drawable[12];
        stamp_yes_round = new Drawable[13];
        stamp_no_round = new Drawable[13];

        setStringArray();
        setPlaceStrInfo();
    }

    private void setStringArray() {
        title[0] = mContext.getString(R.string.place_1_title);
        title[1] = mContext.getString(R.string.place_2_title);
        title[2] = mContext.getString(R.string.place_3_title);
        title[3] = mContext.getString(R.string.place_4_title);
        title[4] = mContext.getString(R.string.place_5_title);
        title[5] = mContext.getString(R.string.place_6_title);
        title[6] = mContext.getString(R.string.place_7_title);
        title[7] = mContext.getString(R.string.place_8_title);
        title[8] = mContext.getString(R.string.place_9_title);
        title[9] = mContext.getString(R.string.place_10_title);
        title[10] = mContext.getString(R.string.place_11_title);
        title[11] = mContext.getString(R.string.place_12_title);
        title[12] = mContext.getString(R.string.place_13_title);

        subTitle[0] = mContext.getString(R.string.place_1_sub_title);
        subTitle[1] = mContext.getString(R.string.place_2_sub_title);
        subTitle[2] = mContext.getString(R.string.place_3_sub_title);
        subTitle[3] = mContext.getString(R.string.place_4_sub_title);
        subTitle[4] = mContext.getString(R.string.place_5_sub_title);
        subTitle[5] = mContext.getString(R.string.place_6_sub_title);
        subTitle[6] = mContext.getString(R.string.place_7_sub_title);
        subTitle[7] = mContext.getString(R.string.place_8_sub_title);
        subTitle[8] = mContext.getString(R.string.place_9_sub_title);
        subTitle[9] = mContext.getString(R.string.place_10_sub_title);
        subTitle[10] = mContext.getString(R.string.place_11_sub_title);
        subTitle[11] = mContext.getString(R.string.place_12_sub_title);
        subTitle[12] = mContext.getString(R.string.place_13_sub_title);

        address[0] = mContext.getString(R.string.place_1_address);
        address[1] = mContext.getString(R.string.place_2_address);
        address[2] = mContext.getString(R.string.place_3_address);
        address[3] = mContext.getString(R.string.place_4_address);
        address[4] = mContext.getString(R.string.place_5_address);
        address[5] = mContext.getString(R.string.place_6_address);
        address[6] = mContext.getString(R.string.place_7_address);
        address[7] = mContext.getString(R.string.place_8_address);
        address[8] = mContext.getString(R.string.place_9_address);
        address[9] = mContext.getString(R.string.place_10_address);
        address[10] = mContext.getString(R.string.place_11_address);
        address[11] = mContext.getString(R.string.place_12_address);
        address[12] = mContext.getString(R.string.place_13_address);

        latitude[0] = Double.parseDouble(mContext.getString(R.string.place_1_latitude));
        latitude[1] = Double.parseDouble(mContext.getString(R.string.place_2_latitude));
        latitude[2] = Double.parseDouble(mContext.getString(R.string.place_3_latitude));
        latitude[3] = Double.parseDouble(mContext.getString(R.string.place_4_latitude));
        latitude[4] = Double.parseDouble(mContext.getString(R.string.place_5_latitude));
        latitude[5] = Double.parseDouble(mContext.getString(R.string.place_6_latitude));
        latitude[6] = Double.parseDouble(mContext.getString(R.string.place_7_latitude));
        latitude[7] = Double.parseDouble(mContext.getString(R.string.place_8_latitude));
        latitude[8] = Double.parseDouble(mContext.getString(R.string.place_9_latitude));
        latitude[9] = Double.parseDouble(mContext.getString(R.string.place_10_latitude));
        latitude[10] = Double.parseDouble(mContext.getString(R.string.place_11_latitude));
        latitude[11] = Double.parseDouble(mContext.getString(R.string.place_12_latitude));
        latitude[12] = Double.parseDouble(mContext.getString(R.string.place_13_latitude));

        longitude[0] = Double.parseDouble(mContext.getString(R.string.place_1_longitude));
        longitude[1] = Double.parseDouble(mContext.getString(R.string.place_2_longitude));
        longitude[2] = Double.parseDouble(mContext.getString(R.string.place_3_longitude));
        longitude[3] = Double.parseDouble(mContext.getString(R.string.place_4_longitude));
        longitude[4] = Double.parseDouble(mContext.getString(R.string.place_5_longitude));
        longitude[5] = Double.parseDouble(mContext.getString(R.string.place_6_longitude));
        longitude[6] = Double.parseDouble(mContext.getString(R.string.place_7_longitude));
        longitude[7] = Double.parseDouble(mContext.getString(R.string.place_8_longitude));
        longitude[8] = Double.parseDouble(mContext.getString(R.string.place_9_longitude));
        longitude[9] = Double.parseDouble(mContext.getString(R.string.place_10_longitude));
        longitude[10] = Double.parseDouble(mContext.getString(R.string.place_11_longitude));
        longitude[11] = Double.parseDouble(mContext.getString(R.string.place_12_longitude));
        longitude[12] = Double.parseDouble(mContext.getString(R.string.place_13_longitude));

        content[0] = mContext.getString(R.string.place_1_content);
        content[1] = mContext.getString(R.string.place_2_content);
        content[2] = mContext.getString(R.string.place_3_content);
        content[3] = mContext.getString(R.string.place_4_content);
        content[4] = mContext.getString(R.string.place_5_content);
        content[5] = mContext.getString(R.string.place_6_content);
        content[6] = mContext.getString(R.string.place_7_content);
        content[7] = mContext.getString(R.string.place_8_content);
        content[8] = mContext.getString(R.string.place_9_content);
        content[9] = mContext.getString(R.string.place_10_content);
        content[10] = mContext.getString(R.string.place_11_content);
        content[11] = mContext.getString(R.string.place_12_content);
        content[12] = mContext.getString(R.string.place_13_content);

        place_yes[0] = mContext.getResources().getDrawable(R.drawable.place_1);
        place_yes[1] = mContext.getResources().getDrawable(R.drawable.place_2);
        place_yes[2] = mContext.getResources().getDrawable(R.drawable.place_3);
        place_yes[3] = mContext.getResources().getDrawable(R.drawable.place_4);
        place_yes[4] = mContext.getResources().getDrawable(R.drawable.place_5);
        place_yes[5] = mContext.getResources().getDrawable(R.drawable.place_6);
        place_yes[6] = mContext.getResources().getDrawable(R.drawable.place_7);
        place_yes[7] = mContext.getResources().getDrawable(R.drawable.place_8);
        place_yes[8] = mContext.getResources().getDrawable(R.drawable.place_9);
        place_yes[9] = mContext.getResources().getDrawable(R.drawable.place_10);
        place_yes[10] = mContext.getResources().getDrawable(R.drawable.place_11);
        place_yes[11] = mContext.getResources().getDrawable(R.drawable.place_12);

        place_no[0] = mContext.getResources().getDrawable(R.drawable.place_black_1);
        place_no[1] = mContext.getResources().getDrawable(R.drawable.place_black_2);
        place_no[2] = mContext.getResources().getDrawable(R.drawable.place_black_3);
        place_no[3] = mContext.getResources().getDrawable(R.drawable.place_black_4);
        place_no[4] = mContext.getResources().getDrawable(R.drawable.place_black_5);
        place_no[5] = mContext.getResources().getDrawable(R.drawable.place_black_6);
        place_no[6] = mContext.getResources().getDrawable(R.drawable.place_black_7);
        place_no[7] = mContext.getResources().getDrawable(R.drawable.place_black_8);
        place_no[8] = mContext.getResources().getDrawable(R.drawable.place_black_9);
        place_no[9] = mContext.getResources().getDrawable(R.drawable.place_black_10);
        place_no[10] = mContext.getResources().getDrawable(R.drawable.place_black_11);
        place_no[11] = mContext.getResources().getDrawable(R.drawable.place_black_12);

        stamp_yes[0] = mContext.getResources().getDrawable(R.drawable.stamp_yes_1);
        stamp_yes[1] = mContext.getResources().getDrawable(R.drawable.stamp_yes_2);
        stamp_yes[2] = mContext.getResources().getDrawable(R.drawable.stamp_yes_3);
        stamp_yes[3] = mContext.getResources().getDrawable(R.drawable.stamp_yes_4);
        stamp_yes[4] = mContext.getResources().getDrawable(R.drawable.stamp_yes_5);
        stamp_yes[5] = mContext.getResources().getDrawable(R.drawable.stamp_yes_6);
        stamp_yes[6] = mContext.getResources().getDrawable(R.drawable.stamp_yes_7);
        stamp_yes[7] = mContext.getResources().getDrawable(R.drawable.stamp_yes_8);
        stamp_yes[8] = mContext.getResources().getDrawable(R.drawable.stamp_yes_9);
        stamp_yes[9] = mContext.getResources().getDrawable(R.drawable.stamp_yes_10);
        stamp_yes[10] = mContext.getResources().getDrawable(R.drawable.stamp_yes_11);
        stamp_yes[11] = mContext.getResources().getDrawable(R.drawable.stamp_yes_12);

        stamp_no[0] = mContext.getResources().getDrawable(R.drawable.stamp_no_1);
        stamp_no[1] = mContext.getResources().getDrawable(R.drawable.stamp_no_2);
        stamp_no[2] = mContext.getResources().getDrawable(R.drawable.stamp_no_3);
        stamp_no[3] = mContext.getResources().getDrawable(R.drawable.stamp_no_4);
        stamp_no[4] = mContext.getResources().getDrawable(R.drawable.stamp_no_5);
        stamp_no[5] = mContext.getResources().getDrawable(R.drawable.stamp_no_6);
        stamp_no[6] = mContext.getResources().getDrawable(R.drawable.stamp_no_7);
        stamp_no[7] = mContext.getResources().getDrawable(R.drawable.stamp_no_8);
        stamp_no[8] = mContext.getResources().getDrawable(R.drawable.stamp_no_9);
        stamp_no[9] = mContext.getResources().getDrawable(R.drawable.stamp_no_10);
        stamp_no[10] = mContext.getResources().getDrawable(R.drawable.stamp_no_11);
        stamp_no[11] = mContext.getResources().getDrawable(R.drawable.stamp_no_12);

        stamp_yes_round[0] = mContext.getResources().getDrawable(R.drawable.stamp_1);
        stamp_yes_round[1] = mContext.getResources().getDrawable(R.drawable.stamp_2);
        stamp_yes_round[2] = mContext.getResources().getDrawable(R.drawable.stamp_3);
        stamp_yes_round[3] = mContext.getResources().getDrawable(R.drawable.stamp_4);
        stamp_yes_round[4] = mContext.getResources().getDrawable(R.drawable.stamp_5);
        stamp_yes_round[5] = mContext.getResources().getDrawable(R.drawable.stamp_6);
        stamp_yes_round[6] = mContext.getResources().getDrawable(R.drawable.stamp_7);
        stamp_yes_round[7] = mContext.getResources().getDrawable(R.drawable.stamp_8);
        stamp_yes_round[8] = mContext.getResources().getDrawable(R.drawable.stamp_9);
        stamp_yes_round[9] = mContext.getResources().getDrawable(R.drawable.stamp_10);
        stamp_yes_round[10] = mContext.getResources().getDrawable(R.drawable.stamp_11);
        stamp_yes_round[11] = mContext.getResources().getDrawable(R.drawable.stamp_12);
        stamp_yes_round[12] = mContext.getResources().getDrawable(R.drawable.stamp_13);

        stamp_no_round[0] = mContext.getResources().getDrawable(R.drawable.stamp_black_1);
        stamp_no_round[1] = mContext.getResources().getDrawable(R.drawable.stamp_black_2);
        stamp_no_round[2] = mContext.getResources().getDrawable(R.drawable.stamp_black_3);
        stamp_no_round[3] = mContext.getResources().getDrawable(R.drawable.stamp_black_4);
        stamp_no_round[4] = mContext.getResources().getDrawable(R.drawable.stamp_black_5);
        stamp_no_round[5] = mContext.getResources().getDrawable(R.drawable.stamp_black_6);
        stamp_no_round[6] = mContext.getResources().getDrawable(R.drawable.stamp_black_7);
        stamp_no_round[7] = mContext.getResources().getDrawable(R.drawable.stamp_black_8);
        stamp_no_round[8] = mContext.getResources().getDrawable(R.drawable.stamp_black_9);
        stamp_no_round[9] = mContext.getResources().getDrawable(R.drawable.stamp_black_10);
        stamp_no_round[10] = mContext.getResources().getDrawable(R.drawable.stamp_black_11);
        stamp_no_round[11] = mContext.getResources().getDrawable(R.drawable.stamp_black_12);
        stamp_no_round[12] = mContext.getResources().getDrawable(R.drawable.stamp_lock);
    }

    private void setPlaceStrInfo() {
        for (int num = 0; num < placeInfo.length; num++) {
            placeInfo[num] = new PlaceInfoData();
            placeInfo[num].setPlace_number(num + 1);
            placeInfo[num].setPlace_title(title[num]);
            placeInfo[num].setPlace_sub_title(subTitle[num]);
            placeInfo[num].setPlace_address(address[num]);
            placeInfo[num].setPlace_latitude(latitude[num]);
            placeInfo[num].setPlace_longitude(longitude[num]);
            placeInfo[num].setPlace_content(content[num]);

            placeInfo[num].setStamp_yes_round(stamp_yes_round[num]);
            placeInfo[num].setStamp_no_round(stamp_no_round[num]);
        }
        for (int num = 0; num < placeInfo.length - 1; num++) {
            placeInfo[num].setPlace_yes(place_yes[num]);
            placeInfo[num].setPlace_no(place_no[num]);
            placeInfo[num].setStamp_yes(stamp_yes[num]);
            placeInfo[num].setStamp_no(stamp_no[num]);
        }
    }
}