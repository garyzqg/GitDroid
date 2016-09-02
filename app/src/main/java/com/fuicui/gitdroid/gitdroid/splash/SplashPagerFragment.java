package com.fuicui.gitdroid.gitdroid.splash;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.fuicui.gitdroid.gitdroid.R;
import com.fuicui.gitdroid.gitdroid.splash.pager.Pager2;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * 作者：yuanchao on 2016/8/23 0023 11:22
 * 邮箱：yuanchao@feicuiedu.com
 */
public class SplashPagerFragment extends Fragment {


    @BindView(R.id.ivPhoneFont) ImageView ivPhoneFont;
    @BindView(R.id.layoutPhone) FrameLayout layoutPhone;
    @BindView(R.id.viewPager) ViewPager viewPager;
    @BindView(R.id.indicator) CircleIndicator indicator;
    @BindView(R.id.content) FrameLayout frameLayout;

    @BindColor(R.color.colorGreen) int colorGreen;
    @BindColor(R.color.colorRed) int colorRed;
    @BindColor(R.color.colorYellow) int colorYellow;

    private SplashPagerAdapter adapter;

    @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_pager, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new SplashPagerAdapter(getContext());
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);

        //ViewPager的监听
        viewPager.addOnPageChangeListener(pageColorListener);
        viewPager.addOnPageChangeListener(phoneViewListener);

    }

    //设置的ViewPager背景颜色的监听
    private ViewPager.OnPageChangeListener pageColorListener = new ViewPager.OnPageChangeListener() {

        ArgbEvaluator argbEvaluator = new ArgbEvaluator();

        @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //
            if (position == 0) {
                int color = (int) argbEvaluator.evaluate(positionOffset, colorGreen, colorRed);
                frameLayout.setBackgroundColor(color);
                return;
            }
            if (position==1){
                int color = (int) argbEvaluator.evaluate(positionOffset, colorRed, colorYellow);
                frameLayout.setBackgroundColor(color);
                return;
            }
        }

        @Override public void onPageSelected(int position) {

        }

        @Override public void onPageScrollStateChanged(int state) {

        }
    };

    //实现“手机”的动画（缩放，移动，透明度）
    private ViewPager.OnPageChangeListener phoneViewListener = new ViewPager.OnPageChangeListener() {

        @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (position==0){
                //手机缩放的效果
                /**
                 * positionOffset  0-1
                 */
                float scale = 0.5f +positionOffset*0.7f;
                layoutPhone.setScaleY(scale);
                layoutPhone.setScaleX(scale);

                //手机移动的效果
                int scroll = (int) ((positionOffset-1)*1000);
                layoutPhone.setTranslationX(scroll);

                //手机内字体图片透明度的改变
                ivPhoneFont.setAlpha(positionOffset);
            }
            //手机从第二页面切换动画
            if (position==1){
                layoutPhone.setTranslationX(-positionOffsetPixels);
            }
        }

        @Override public void onPageSelected(int position) {
            if (position==2){
                Pager2 pager2 = (Pager2) adapter.getView(position);
                pager2.showAnimation();
            }
        }

        @Override public void onPageScrollStateChanged(int state) {

        }
    };
}
