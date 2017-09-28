package com.example.song.seoulapp_fm.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.example.song.seoulapp_fm.Game.ARCenterTouchActivity;
import com.example.song.seoulapp_fm.IntroduceActivity;
import com.example.song.seoulapp_fm.MyApplication;
import com.example.song.seoulapp_fm.Game.NoCenterTouchActivity;
import com.example.song.seoulapp_fm.R;

// 2016. 10. 24
// background service
// 위치 접근 여부 판단

/*
 noti 설정
 앱 최초 실행 시 start
 ar 실행 시 service stop
 ar 종료 시 service (설정 on 상태 이면) start
 설정 변경 시 start / stop
*/

public class FMService extends Service {

    MyApplication myapp;

    NotificationManager notificationManager;
    FMServiceHandler handler;
    ServiceThread thread;
    Notification notifi;

    Intent intent = null;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        // 최초 생성 시 한 번 실행
        myapp = (MyApplication) getApplication();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 백그라운드에서 실행되는 동작
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        handler = new FMServiceHandler();
        thread = new ServiceThread(getApplication(), handler);
        thread.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        // 서비스 종료 시 실행
        thread.stopForever();
        thread = null;//쓰레기 값을 만들어서 빠르게 회수하라고 null을 넣어줌.
    }

    class FMServiceHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (msg.arg1 == 1) {
                // 노티 띄우기
                if (msg.arg2 == 12) {
                    // 히든 - 창동플랫폼은 바로 실행
                    // AR 미 지원 버전에서는 카메라 뷰 없는 Activity 실행
                    if (Build.VERSION.SDK_INT > 19)
                        intent = new Intent(FMService.this, ARCenterTouchActivity.class);
                    else
                        intent = new Intent(FMService.this, NoCenterTouchActivity.class);
                    intent.putExtra("placeNum", msg.arg2);
                } else {
                    intent = new Intent(FMService.this, IntroduceActivity.class);
                    intent.putExtra("placeNum", msg.arg2 + 1);
                }

                PendingIntent pendingIntent = PendingIntent.getActivity(FMService.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                notifi = new Notification.Builder(getApplicationContext())
                        .setContentTitle(getString(R.string.app_name))
                        .setContentText(getString(R.string.noti_content_text))
                        .setSmallIcon(R.drawable.noti_icon)
                        .setTicker("알림!!!")
                        .setContentIntent(pendingIntent)
                        .build();

                // 진동 추가
                notifi.defaults = Notification.DEFAULT_VIBRATE;
                //확인하면 자동으로 알림이 제거 되도록
                notifi.flags = Notification.FLAG_AUTO_CANCEL;
                // 노티 띄우기
                notificationManager.notify(777, notifi);
            } else if (msg.arg1 == 0) {
                // 노티 지우기
                notificationManager.cancel(777);
                //토스트 띄우기
                myapp.showToast(getString(R.string.noti_toast_out));
            }
        }
    }
}
