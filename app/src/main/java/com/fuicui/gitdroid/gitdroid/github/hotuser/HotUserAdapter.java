package com.fuicui.gitdroid.gitdroid.github.hotuser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fuicui.gitdroid.gitdroid.R;
import com.fuicui.gitdroid.gitdroid.login.model.User;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 123 on 2016/8/30.
 */
public class HotUserAdapter extends BaseAdapter {

    private List<User> list;

    public HotUserAdapter() {
        list = new ArrayList<>();
    }

    public void addAll(List<User> users){
        list.addAll(users);
        notifyDataSetChanged();
    }

    public void clear(){
        list.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_user,parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        User user = list.get(position);
        viewHolder.tvLoginName.setText(user.getLogin());
        ImageLoader.getInstance().displayImage(user.getAvatar(),viewHolder.ivIcon);
        return convertView;
    }

    static class ViewHolder{
        @BindView(R.id.ivIcon)
        ImageView ivIcon;
        @BindView(R.id.tvLoginName)
        TextView tvLoginName;

        ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }
}
