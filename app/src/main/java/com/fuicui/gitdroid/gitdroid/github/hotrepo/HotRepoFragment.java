package com.fuicui.gitdroid.gitdroid.github.hotrepo;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fuicui.gitdroid.gitdroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotRepoFragment extends Fragment {


    /**
     * 放置的ViewPager主要是为了实现我们Java、go、HTml....展示
     * java---HotRepoListFragment
     */
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private HotRepoAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hot_repo, container, false);
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

        adapter = new HotRepoAdapter(getChildFragmentManager(),getContext());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
