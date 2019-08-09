package com.example.myapplication.MyListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

public class MyListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater; //找LayOut下的xml文件

    public MyListAdapter(Context Context){
        this.mContext = Context;
        this.mLayoutInflater = LayoutInflater.from(Context);
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    static class ViewHolder{
        public ImageView IV_1;
        public TextView TV_title;
        public TextView TV_date;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view  == null){
            view = mLayoutInflater.inflate(R.layout.layout_list_item,null);
            holder = new ViewHolder();
            holder.IV_1=view.findViewById(R.id.list_item_1);
            holder.TV_date = view.findViewById(R.id.TV_Item_2);
            holder.TV_title = view.findViewById(R.id.TV_Item_1);
            view.setTag(holder);
        }else
        {
            holder = (ViewHolder)view.getTag();
        }

        holder.TV_title.setText(String.valueOf(i));
        Glide.with(mContext).load("https://m.baidu.com/se/static/img/iphone/logo_web.png").into(holder.IV_1);
        return view;
    }
}
