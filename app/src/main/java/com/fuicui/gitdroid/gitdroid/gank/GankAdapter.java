package com.fuicui.gitdroid.gitdroid.gank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fuicui.gitdroid.gitdroid.R;
import com.fuicui.gitdroid.gitdroid.gank.model.GankItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：yuanchao on 2016/8/5 0005 16:17
 * 邮箱：yuanchao@feicuiedu.com
 */
public class GankAdapter extends BaseAdapter {

    private final ArrayList<GankItem> datas;

    public GankAdapter() {
        datas = new ArrayList<GankItem>();
    }

    public void setDatas(List<GankItem> gankItems) {
        datas.clear();
        datas.addAll(gankItems);
    }

    @Override public int getCount() {
        return datas==null?0:datas.size();
    }

    @Override public GankItem getItem(int position) {
        return datas.get(position);
    }

    @Override public long getItemId(int position) {
        return position;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            convertView = layoutInflater.inflate(R.layout.layout_item_gank, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();

        GankItem gankItem = getItem(position);
        viewHolder.gank_item.setText(gankItem.getDesc());

        return convertView;
    }

    static class ViewHolder {
        public TextView gank_item;
        public ViewHolder(View view) {
            gank_item = (TextView) view.findViewById(R.id.gank_item);
        }
    }
}
