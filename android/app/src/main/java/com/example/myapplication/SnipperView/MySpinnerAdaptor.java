package com.example.myapplication.SnipperView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.MyListView.MyListAdapter;
import com.example.myapplication.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MySpinnerAdaptor extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater; //找LayOut下的xml文件
    private String[] ListIn;

    public MySpinnerAdaptor(Context Context,String[] List){
        this.mContext = Context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.ListIn = List;
    }

    @Override
    public int getCount() {
        return ListIn.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MySpinnerAdaptor.ViewHolder holder = null;
        if(view  == null){
            view = mLayoutInflater.inflate(R.layout.spinner_layout,null);
            holder = new MySpinnerAdaptor.ViewHolder();
            holder.Sp_Tv_1=view.findViewById(R.id.sn_tv_1);
            view.setTag(holder);
        }else
        {
            holder = (MySpinnerAdaptor.ViewHolder)view.getTag();
        }

        holder.Sp_Tv_1.setText(ListIn[i]);
        return view;
    }

    static class ViewHolder{
        public TextView Sp_Tv_1;
    }
}
