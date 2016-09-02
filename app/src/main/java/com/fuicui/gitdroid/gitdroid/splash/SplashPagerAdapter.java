package com.fuicui.gitdroid.gitdroid.splash;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.fuicui.gitdroid.gitdroid.splash.pager.Pager0;
import com.fuicui.gitdroid.gitdroid.splash.pager.Pager1;
import com.fuicui.gitdroid.gitdroid.splash.pager.Pager2;

/**
 * 作者：yuanchao on 2016/8/23 0023 14:19
 * 邮箱：yuanchao@feicuiedu.com
 */
public class SplashPagerAdapter extends PagerAdapter{

    //添加数据View
    private View[] views;

    public SplashPagerAdapter(Context context) {
        views = new View[]{new Pager0(context),new Pager1(context),new Pager2(context)};
    }

    //
    public View getView(int position){
        return views[position];
    }

    @Override public int getCount() {
        return views==null?0:views.length;
    }

    @Override public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views[position]);
        return views[position];
    }

    @Override public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views[position]);
    }
}
