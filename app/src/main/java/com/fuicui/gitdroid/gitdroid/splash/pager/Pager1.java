package com.fuicui.gitdroid.gitdroid.splash.pager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.fuicui.gitdroid.gitdroid.R;

/**
 * 作者：yuanchao on 2016/7/26 0026 10:53
 * 邮箱：yuanchao@feicuiedu.com
 */
public class Pager1 extends FrameLayout {


    public Pager1(Context context) {
        this(context, null);
    }

    public Pager1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Pager1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.content_pager_1, this, true);
    }


}
