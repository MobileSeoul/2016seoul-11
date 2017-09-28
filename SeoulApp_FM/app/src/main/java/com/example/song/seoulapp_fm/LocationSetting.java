package com.example.song.seoulapp_fm;

import android.content.Context;

/**
 * Created by song on 2016-10-17.
 */
public class LocationSetting {

    private Context mContext;

    public PlaceInfoData[] placeInfo;
    private double[] latitude;
    private double[] longitude;

    public LocationSetting(Context context) {

        mContext = context;

        placeInfo = new PlaceInfoData[13];
        latitude = new double[13];
        longitude = new double[13];

        setStringArray();
        setPlaceStrInfo();
    }

    private void setStringArray() {
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
    }

    private void setPlaceStrInfo() {
        for (int num = 0; num < placeInfo.length; num++) {
            placeInfo[num] = new PlaceInfoData();
            placeInfo[num].setPlace_number(num + 1);
            placeInfo[num].setPlace_latitude(latitude[num]);
            placeInfo[num].setPlace_longitude(longitude[num]);
        }
    }
}