package com.example.myapplication.MyListView;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.R;

public class ListViewActivity extends AppCompatActivity {

    ListView mLV1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        mLV1 = findViewById(R.id.LV_1);
        MyListAdapter My_Adapter = new MyListAdapter(ListViewActivity.this);
        mLV1.setAdapter(My_Adapter);
        mLV1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyListAdapter.ViewHolder holder;
                holder = (MyListAdapter.ViewHolder)adapterView.getAdapter().getView(i,view,null).getTag();
                Toast.makeText(ListViewActivity.this,"点击pos"+holder.TV_date.getText()
                        ,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
