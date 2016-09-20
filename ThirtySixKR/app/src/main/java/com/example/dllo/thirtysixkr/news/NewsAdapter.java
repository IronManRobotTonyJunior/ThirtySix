package com.example.dllo.thirtysixkr.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.thirtysixkr.R;

import java.util.ArrayList;

public class NewsAdapter extends BaseAdapter {
    Context context;
    ArrayList<NewsBean> beans;

    public NewsAdapter(Context context) {
        this.context = context;
    }

    public void setBeans(ArrayList<NewsBean> beans) {
        this.beans = beans;
    }

    @Override
    public int getCount() {
        return beans == null ? 0 : beans.size();
    }

    @Override
    public Object getItem(int position) {
        return beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_news,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
    class ViewHolder{

        private final TextView tvTitle;
        private final TextView tvName;
        private final TextView tvTime;
        private final TextView tvSource;
        private final ImageView ivItem;

        public ViewHolder(View v) {
            tvTitle = (TextView) v.findViewById(R.id.tv_item_title);
            tvName = (TextView) v.findViewById(R.id.tv_item_name);
            tvTime = (TextView) v.findViewById(R.id.tv_item_time);
            tvSource = (TextView) v.findViewById(R.id.tv_item_source);
            ivItem = (ImageView) v.findViewById(R.id.iv_item_news);

        }
    }
}
