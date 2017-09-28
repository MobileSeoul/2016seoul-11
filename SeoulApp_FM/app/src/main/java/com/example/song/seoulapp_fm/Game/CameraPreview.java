package com.example.song.seoulapp_fm.Game;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/*
* Camera 객체 호출 클래스
*/
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

    SurfaceHolder mHolder;
    Camera mCamera;

    public CameraPreview(Context context) {
        super(context);

//        mHolder = getHolder();
//        mHolder.addCallback(this);
//        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public CameraPreview(Context context, Camera camera) {
        super(context);

        this.mCamera = camera;

        mHolder = getHolder();
        mHolder.addCallback(this);
    }

    @Override
    // 서피스 생성 시 호출 - 카메라 미리보기 시작
    public void surfaceCreated(SurfaceHolder holder) {
        mCamera = Camera.open();
        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (IOException exception) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
            // TODO: add more exception handling logic here
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setPreviewSize(width, height);
//        mCamera.setParameters(parameters);
        mCamera.startPreview();
        mCamera.setDisplayOrientation(90);
    }

    @Override
    // 서피스 제거 시 호출 - 카메라 미리보기 끝냄 및 카메라 장비의 점유 해제
    public void surfaceDestroyed(SurfaceHolder holder) {
        // 카메라 미리보기를 끝내기 위해 setPreviewCallback() 메소드에 null 저장
        mCamera.setPreviewCallback(null);
        mCamera.stopPreview();  // 카메라 미리보기 끝냄
        mCamera.release();  // 카메라 장비 점유 해제
        mCamera = null; // camera 객체 제거
    }
}