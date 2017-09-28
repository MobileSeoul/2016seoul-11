package com.example.song.seoulapp_fm.Service;

/**
 * Created by FAD00187 on 2016-10-24.
 */

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.example.song.seoulapp_fm.GpsInfo;

public class ServiceThread extends Thread {

    Handler handler;
    Message message;

    boolean isRun = true;
    boolean isShow = false;

    int ablePosition = -1;

    private GpsInfo gpsInfo;


    public ServiceThread(Context context, Handler handler) {
        this.handler = handler;
        message = new Message();
        gpsInfo = new GpsInfo(context);
    }

    public void stopForever() {
        synchronized (this) {
            this.isRun = false;
        }
    }

    public void run() {
        Looper.prepare();
        //반복적으로 수행할 작업을 한다.
        while (isRun) {
            Log.e("==========", "ServiceThread isRun true");
            if (gpsInfo.isGetLocation()) {      // gps 사용 가능 여부

                // 현재 위치 setting
                gpsInfo.getLocation();

                if (!isShow) {
                    ablePosition = gpsInfo.ablePosition();
                    if (ablePosition != -1) {
                        // 노티 띄우는 조건
                        message = new Message();
                        message.arg1 = 1;
                        message.arg2 = ablePosition;
                        handler.sendMessage(message);//쓰레드에 있는 핸들러에게 메세지를 보냄
                        isShow = true;
                    }
                } else {
                    if (!gpsInfo.isAbleOne()) {
                        // 노티 삭제 조건
                        message = new Message();
                        message.arg1 = 0;
                        handler.sendMessage(message);//쓰레드에 있는 핸들러에게 메세지를 보냄
                        isShow = false;
                    }
                }
            } else {
                if (isShow) {
                    // gps 사용 불가 && 노티 isShow 이면 노티 삭제!!
                    message = new Message();
                    message.arg1 = 0;
                    handler.sendMessage(message);//쓰레드에 있는 핸들러에게 메세지를 보냄
                    isShow = false;
                }
            }

            try {
                // 딜레이
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Looper.loop();
    }

/*
    private String getTime() {

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String result = simpleDateFormat.format(date);

        return result;
    }
*/
}