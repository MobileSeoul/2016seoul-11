package com.example.song.seoulapp_fm;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by song on 2016-10-17.
 * 메인 종료 팝업
 */
public class PopupDialog extends Dialog {

    private TextView dialog_text_title;
    protected Button dialog_btn_1;
    protected Button dialog_btn_2;

    private String mTitle;
    private View.OnClickListener mLeftListener;
    private View.OnClickListener mRightListener;

    public PopupDialog(Context context) {
        super(context);
    }

    public PopupDialog(Context context, View.OnClickListener listener) {
        super(context);
        mTitle = context.getString(R.string.dialog_popup_title_finish);
        mLeftListener = listener;
    }

    public PopupDialog(Context context, View.OnClickListener leftListener, View.OnClickListener rightListener) {
        super(context);
        mTitle = context.getString(R.string.dialog_popup_title_finish);
        mLeftListener = leftListener;
        mRightListener = rightListener;
    }

    // 팝업 타이틀 문구 변경 시
    public PopupDialog(Context context, String title, View.OnClickListener listener) {
        super(context);
        mTitle = title;
        mLeftListener = listener;
    }

    public PopupDialog(Context context, String title, View.OnClickListener leftListener, View.OnClickListener rightListener) {
        super(context);
        mTitle = title;
        mLeftListener = leftListener;
        mRightListener = rightListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_popup);

        dialog_text_title = (TextView) findViewById(R.id.dialog_text_title);
        dialog_text_title.setText(mTitle);

        dialog_btn_1 = (Button) findViewById(R.id.dialog_btn_1);
        dialog_btn_2 = (Button) findViewById(R.id.dialog_btn_2);

        if (mRightListener == null) {
            // 버튼이 하나인 팝업
            dialog_btn_1.setText("확인");
            dialog_btn_1.setBackground(getContext().getResources().getDrawable(R.drawable.dialog_background_bottom));
            dialog_btn_1.setOnClickListener(mLeftListener);
            dialog_btn_2.setVisibility(View.GONE);
        } else {
            // 버튼이 두개인 팝업
            dialog_btn_1.setOnClickListener(mLeftListener);
            dialog_btn_2.setOnClickListener(mRightListener);
        }
    }

    @Override
    public void onBackPressed() {
        // 팝업 창 띄운 상태에선 back 키 막기
        return;
    }
}