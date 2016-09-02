package com.fuicui.gitdroid.gitdroid;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * 作者：yuanchao on 2016/8/26 0026 17:17
 * 邮箱：yuanchao@feicuiedu.com
 */
public class GitDroidApplication extends Application {

    @Override public void onCreate() {
        super.onCreate();

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ic_avatar)
                .showImageOnFail(R.drawable.ic_avatar)
                .cacheInMemory(true)//打开内存缓存
                .cacheOnDisk(true)//打开硬盘缓存
                .build();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheSize(1024 * 1024 * 10)
                .defaultDisplayImageOptions(options)
                .build();

        ImageLoader.getInstance().init(configuration);

    }
}
