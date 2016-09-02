package com.fuicui.gitdroid.gitdroid.splash.pager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.fuicui.gitdroid.gitdroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：yuanchao on 2016/7/26 0026 10:53
 * 邮箱：yuanchao@feicuiedu.com
 */
public class Pager2 extends FrameLayout {

    @BindView(R.id.ivBubble1) ImageView ivBubble1;
    @BindView(R.id.ivBubble2) ImageView ivBubble2;
    @BindView(R.id.ivBubble3) ImageView ivBubble3;

    /*三个构造方法
       * 1.一般仅在代码中使用
       * 2.在布局中也有使用
       * 3.在布局中使用，并且设置了Style
       * */
    public Pager2(Context context) {
        this(context, null);
    }

    public Pager2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Pager2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.content_pager_2, this, true);
        ButterKnife.bind(this);
        ivBubble1.setVisibility(GONE);
        ivBubble2.setVisibility(GONE);
        ivBubble3.setVisibility(GONE);
    }

    public void showAnimation() {
        /**
         * YoYo.with(Techniques.Tada)
         .duration(700)
         .playOn(findViewById(R.id.edit_area));
         */

        if (ivBubble1.getVisibility()!=VISIBLE){
            postDelayed(new Runnable() {
                @Override public void run() {
                    ivBubble1.setVisibility(VISIBLE);
                    YoYo.with(Techniques.FadeInLeft).duration(300).playOn(ivBubble1);
                }
            },100);
            postDelayed(new Runnable() {
                @Override public void run() {
                    ivBubble2.setVisibility(VISIBLE);
                    YoYo.with(Techniques.FadeInLeft).duration(300).playOn(ivBubble2);
                }
            },600);
            postDelayed(new Runnable() {
                @Override public void run() {
                    ivBubble3.setVisibility(VISIBLE);
                    YoYo.with(Techniques.FadeInLeft).duration(300).playOn(ivBubble3);
                }
            },1100);

        }
    }
}
