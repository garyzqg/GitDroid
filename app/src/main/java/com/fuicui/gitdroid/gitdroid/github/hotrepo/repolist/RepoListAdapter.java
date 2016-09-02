package com.fuicui.gitdroid.gitdroid.github.hotrepo.repolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fuicui.gitdroid.gitdroid.R;
import com.fuicui.gitdroid.gitdroid.github.hotrepo.repolist.model.Repo;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：yuanchao on 2016/8/29 0029 11:12
 * 邮箱：yuanchao@feicuiedu.com
 */
public class RepoListAdapter extends BaseAdapter{

    private ArrayList<Repo> datas;

    public RepoListAdapter() {
        datas = new ArrayList<>();
    }

    public void addAll(Collection<Repo> repos){
        datas.addAll(repos);
        notifyDataSetChanged();
    }

    public void clear(){
        datas.clear();
        notifyDataSetChanged();
    }

    @Override public int getCount() {
        return datas==null?0:datas.size();
    }

    @Override public Repo getItem(int position) {
        return datas.get(position);
    }

    @Override public long getItemId(int position) {
        return position;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_repo,parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        Repo repo = datas.get(position);
        viewHolder.tvRepoName.setText(repo.getFullName());
        viewHolder.tvRepoInfo.setText(repo.getDescription());
        viewHolder.tvRepoStars.setText(repo.getStarCount()+"");
        ImageLoader.getInstance().displayImage(repo.getOwner().getAvatar(),viewHolder.ivIcon);
        return convertView;
    }
    static class ViewHolder{
        @BindView(R.id.ivIcon) ImageView ivIcon;
        @BindView(R.id.tvRepoName) TextView tvRepoName;
        @BindView(R.id.tvRepoInfo) TextView tvRepoInfo;
        @BindView(R.id.tvRepoStars) TextView tvRepoStars;

       ViewHolder(View view) {
           ButterKnife.bind(this,view);
        }
    }

}
