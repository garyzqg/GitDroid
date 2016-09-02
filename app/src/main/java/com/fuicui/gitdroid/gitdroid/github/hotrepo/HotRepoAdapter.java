package com.fuicui.gitdroid.gitdroid.github.hotrepo;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fuicui.gitdroid.gitdroid.github.hotrepo.repolist.HotRepoListFragment;

import java.util.List;

/**
 * 作者：yuanchao on 2016/8/24 0024 11:36
 * 邮箱：yuanchao@feicuiedu.com
 */
public class HotRepoAdapter extends FragmentPagerAdapter {

    private List<Language> languages;

    public HotRepoAdapter(FragmentManager fm,Context context) {
        super(fm);
        languages = Language.getDefaultLanguage(context);
    }

    @Override public Fragment getItem(int position) {
        return HotRepoListFragment.getInstance(languages.get(position));
    }

    @Override public int getCount() {
        return languages==null?0:languages.size();
    }

    @Override public CharSequence getPageTitle(int position) {
        return languages.get(position).getName();
    }
}
