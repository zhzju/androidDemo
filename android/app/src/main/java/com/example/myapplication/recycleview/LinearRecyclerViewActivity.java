package com.example.myapplication.recycleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.recycleview.LinearAdapter.OnClickChanged;

public class LinearRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRvMain;
    private static  Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_recycler_view);
        mRvMain = findViewById(R.id.RV_1);
        mRvMain.setLayoutManager(new LinearLayoutManager(LinearRecyclerViewActivity.this));
        mRvMain.addItemDecoration(new MyDecoration());
        mRvMain.setAdapter(new LinearAdapter(LinearRecyclerViewActivity.this, new OnClickChanged() {
            @Override
            public void onClick(int pos) {
                if (toast == null){
                    toast = Toast.makeText(getApplicationContext(),"click"+pos,Toast.LENGTH_SHORT);
                }else {
                    toast.setText("click"+pos);
                }
                toast.show();
            }
        }));

    }

    class MyDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0,0,0,getResources().getDimensionPixelOffset(R.dimen.grab_pixel));
        }
    }
}
