package com.example.song.seoulapp_fm;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by song on 2016-10-01.
 */
public class GpsInfo extends Service implements LocationListener {
    private final Context mContext;

    // LocationSetting - 장소 정보 객체 (위경도만)
    protected LocationSetting locationSetting;

    // 현재 GPS 사용유무
    boolean isGPSEnabled = false;
    // 네트워크 사용유무
    boolean isNetworkEnabled = false;
    // GPS 상태값
    boolean isGetLocation = false;
    public Location location;
    public double lat; // 위도
    public double lon; // 경도

    // 최소 GPS 정보 업데이트 거리 5미터
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 5;
    // 최소 GPS 정보 업데이트 시간 5초
    private static final long MIN_TIME_BW_UPDATES = 1000 * 5;
    protected LocationManager locationManager;

    public GpsInfo(Context context) {
        this.mContext = context;
        locationSetting = new LocationSetting(context);
        getLocation();
    }

    // 현재 위치 가져오기
    public Location getLocation() {
        try {
            locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
            // GPS 정보 가져오기
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            // 현재 네트워크 상태 값 알아오기
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!isGPSEnabled && !isNetworkEnabled) {
                // GPS 와 네트워크사용이 가능하지 않을때 소스 구현
//                Toast.makeText(mContext, "gps 사용 불가", Toast.LENGTH_SHORT).show();
            } else {
                this.isGetLocation = true;
                // 네트워크 정보로 부터 위치값 가져오기
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            // 위도 경도 저장
                            lat = location.getLatitude();
                            lon = location.getLongitude();
                        }
                    }
                }
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                // 위도 경도 저장
                                lat = location.getLatitude();
                                lon = location.getLongitude();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }

    // 두 지점 사이 거리
    public double getDistance(int position) {
        // 인덱스 position인 명소와 현재 위치 거리

        Location positionLocation = new Location("position");
        positionLocation.setLatitude(locationSetting.placeInfo[position].getPlace_latitude());
        positionLocation.setLongitude(locationSetting.placeInfo[position].getPlace_longitude());

        // 위치 안 잡혔을 때 실행되지 않도록 하기 위해 default 값은 10000
        double distance = 100000.0;
        if (location != null)
            distance = location.distanceTo(positionLocation);

//        Log.e("**************", "getDistance (" + position + ") : " + distance);
        return distance;
    }

    // 현재 위치와 명소 거리가 실행 가능 범위인가
    public boolean isAbleDistance(int position) {
        if (getDistance(position) < 100)
            return true;
        else
            return false;
    }

    // 아이콘이 나타나는 특정 위치 확인
    public boolean isCenter(int position) {
        if (getDistance(position) < 30)
            return true;
        else
            return false;
    }

    // 12 곳 중 한 곳이라도 실행 가능한가
    public boolean isAbleOne() {

        // 현재 위치
        Log.e("**************", "isAbleOne 현재 위치 : " + getLatitude() + " / " + getLongitude());

        for (int i = 0; i < locationSetting.placeInfo.length; i++) {
            // 히든 장소인 창동 플랫폼 제외하고
//        for (int i = 0; i < placeInfoSetting.placeInfo.length - 1; i++) {
            if (isAbleDistance(i))
                return true;
        }
        return false;
    }

    // 12 곳 중 한 곳이라도 실행 가능한가
    public int ablePosition() {

        // 현재 위치
        Log.e("**************", "isAbleOne 현재 위치 : " + getLatitude() + " / " + getLongitude());

        for (int i = 0; i < locationSetting.placeInfo.length; i++) {
            // 히든 장소인 창동 플랫폼 제외하고
//        for (int i = 0; i < placeInfoSetting.placeInfo.length - 1; i++) {
            if (isAbleDistance(i))
                return i;
        }
        return -1;
    }

    /**
     * GPS 종료
     */
    public void stopUsingGPS() {
        if (locationManager != null) {
            locationManager.removeUpdates(GpsInfo.this);
        }
    }

    /**
     * 위도값을 가져옵니다.
     */
    public double getLatitude() {
        if (location != null) {
            lat = location.getLatitude();
        }
        return lat;
    }

    /**
     * 경도값을 가져옵니다.
     */
    public double getLongitude() {
        if (location != null) {
            lon = location.getLongitude();
        }
        return lon;
    }

    /**
     * GPS 정보가 켜져있는지 확인합니다.
     */
    public boolean isGetLocation() {
        // GPS 정보 가져오기
        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (isGPSEnabled)
            this.isGetLocation = true;
        else
            this.isGetLocation = false;

        return this.isGetLocation;
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }

    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }

    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }
}