package com.example.song.seoulapp_fm.Game;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.example.song.seoulapp_fm.PreferencesUtil;
import com.example.song.seoulapp_fm.R;
import com.matthewtamlin.sliding_intro_screen_library.DotIndicator;

/*
* AR 게임 방법 안내
*/
public class ARGuideDialog extends DialogFragment {

    // preference
    private PreferencesUtil pref;

    // pager
    private GuidePagerAdapter adapter;

    private ViewPager ar_guide_pager;
    private DotIndicator ar_guide_indicator;

    private ImageView dialog_ar_guid_close;
    private CheckBox dialog_ar_guide_check;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // pager
        adapter = new GuidePagerAdapter(getContext());

        LayoutInflater mLayoutInflater = getActivity().getLayoutInflater();
        View view = mLayoutInflater.inflate(R.layout.dialog_ar_guide, null);
        ar_guide_pager = (ViewPager) view.findViewById(R.id.ar_guide_pager);
        ar_guide_pager.setAdapter(adapter);

        ar_guide_indicator = (DotIndicator) view.findViewById(R.id.ar_guide_indicator);
//        ar_guide_indicator.setSelectedDotColor(Color.RED);
        ar_guide_indicator.setSelectedDotColor(R.color.myColorBase);
        ar_guide_indicator.setUnselectedDotColor(Color.GRAY);
        ar_guide_indicator.setNumberOfItems(adapter.getCount());

        ar_guide_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                ar_guide_indicator.setSelectedItem(ar_guide_pager.getCurrentItem(), true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        // 페이지 스크롤 애니메이션 효과
        ar_guide_pager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                float normalizedposition = Math.abs(1 - Math.abs(position));
                page.setAlpha(normalizedposition);  //View의 투명도 조절
                page.setScaleX(normalizedposition / 2 + 0.5f); //View의 x축 크기조절
                page.setScaleY(normalizedposition / 2 + 0.5f); //View의 y축 크기조절
                page.setRotationY(position * 80);   //View의 Y축(세로축) 회전 각도
            }
        });

        // dialog 종료
        dialog_ar_guid_close = (ImageView) view.findViewById(R.id.dialog_ar_guid_close);
        dialog_ar_guid_close.setOnClickListener(guidePopupListener);

        // checkbox - preference 저장
        dialog_ar_guide_check = (CheckBox) view.findViewById(R.id.dialog_ar_guide_check);

        // preference - arGuide 다시 보지 않기 체크 상태이면 디폴트 값이 체크
        pref = new PreferencesUtil(getContext());
        if (pref.getValue(PreferencesUtil.PREF_AR_GUIDE, "D").equals("N"))
            dialog_ar_guide_check.setChecked(true);

        // 팝업 dialog 띄우기
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setView(view);

        return dialogBuilder.create();
    }

    // 팝업 리스너
    private View.OnClickListener guidePopupListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // isGuideShow = 0 저장
            ARBaseActivity.isGuideShow = 0;
            // dialog_ar_guide_check 체크 여부 preference에 저장
            if (dialog_ar_guide_check.isChecked())
                pref.setPreference(PreferencesUtil.PREF_AR_GUIDE, "N");
            else
                pref.setPreference(PreferencesUtil.PREF_AR_GUIDE, "Y");

            dismiss();
        }
    };

    @Override
    public void onStop() {
        super.onStop();
    }
}