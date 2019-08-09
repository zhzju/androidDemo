package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheetItemView;

import java.util.ArrayList;
import java.util.List;

public class drawerActivity extends AppCompatActivity {

    private QMUITopBar RegisterTopBar;
    private QMUIAlphaImageButton Back_Button;
    private QMUIBottomSheet.BottomListSheetBuilder bottomsheet;
    private QMUIBottomSheet BottomSheet;
    private TextView drawertext;
    private ListView listView;
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.app_color_blue));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        RegisterTopBar = findViewById(R.id.main_topbar);
        RegisterTopBar.setTitle(R.string.app_name);
        Back_Button = RegisterTopBar.addLeftBackImageButton();

        drawertext = findViewById(R.id.drawer_text);
        listView = findViewById(R.id.drawer_listview);
        drawerLayout=findViewById(R.id.drawer);

        bottomsheet = new QMUIBottomSheet.BottomListSheetBuilder(drawerActivity.this)
                .setOnSheetItemClickListener(new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Item " + tag, Toast.LENGTH_SHORT).show();
                    }
                });
        bottomsheet.addItem("杭州远鉴信息科技有限责任公司以及大家都知道的组hi奥德 啊实打实发生发射点发吧");
        bottomsheet.addItem("111");
        bottomsheet.addItem("222");
        final List<String> list = new ArrayList<String>();
        list.add("18867110210");
        list.add("浙江高盛输变电股份有限公司");
        list.add("杭州平云运输有限公司");
        list.add("切换用户");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BottomSheet = bottomsheet.build();
                BottomSheet.show();
            }
        });
        Back_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }

}
