package com.example.song.seoulapp_fm;

import android.graphics.drawable.Drawable;

/**
 * Created by song on 2016-10-15.
 * 각 관광지 정보
 */
public class PlaceInfoData {

    private int place_number;   // 장소 번호
    private String place_title; // 장소명
    private String place_sub_title;   // 장소 부제
    private String place_address;   // 주소
    private double place_latitude;   // 위도
    private double place_longitude;   // 경도
    private String place_content;   // 장소 설명글

    // 메인 지도
    private Drawable place_yes;
    private Drawable place_no;
    // introduce 스탬프 획득 여부
    private Drawable stamp_yes;
    private Drawable stamp_no;
    // 스탬프
    private Drawable stamp_yes_round;
    private Drawable stamp_no_round;

    public int getPlace_number() {
        return place_number;
    }

    public void setPlace_number(int place_number) {
        this.place_number = place_number;
    }

    public String getPlace_title() {
        return place_title;
    }

    public void setPlace_title(String place_title) {
        this.place_title = place_title;
    }

    public String getPlace_sub_title() {
        return place_sub_title;
    }

    public void setPlace_sub_title(String place_sub_title) {
        this.place_sub_title = place_sub_title;
    }

    public String getPlace_address() {
        return place_address;
    }

    public void setPlace_address(String place_address) {
        this.place_address = place_address;
    }

    public double getPlace_latitude() {
        return place_latitude;
    }

    public void setPlace_latitude(double place_latitude) {
        this.place_latitude = place_latitude;
    }

    public double getPlace_longitude() {
        return place_longitude;
    }

    public void setPlace_longitude(double place_longitude) {
        this.place_longitude = place_longitude;
    }

    public String getPlace_content() {
        return place_content;
    }

    public void setPlace_content(String place_content) {
        this.place_content = place_content;
    }

    public Drawable getPlace_yes() {
        return place_yes;
    }

    public void setPlace_yes(Drawable place_yes) {
        this.place_yes = place_yes;
    }

    public Drawable getPlace_no() {
        return place_no;
    }

    public void setPlace_no(Drawable place_no) {
        this.place_no = place_no;
    }

    public Drawable getStamp_yes() {
        return stamp_yes;
    }

    public void setStamp_yes(Drawable stamp_yes) {
        this.stamp_yes = stamp_yes;
    }

    public Drawable getStamp_no() {
        return stamp_no;
    }

    public void setStamp_no(Drawable stamp_no) {
        this.stamp_no = stamp_no;
    }

    public Drawable getStamp_yes_round() {
        return stamp_yes_round;
    }

    public void setStamp_yes_round(Drawable stamp_yes_round) {
        this.stamp_yes_round = stamp_yes_round;
    }

    public Drawable getStamp_no_round() {
        return stamp_no_round;
    }

    public void setStamp_no_round(Drawable stamp_no_round) {
        this.stamp_no_round = stamp_no_round;
    }
}