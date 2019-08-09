package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUIFloatLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

public class TestMarginActivity extends AppCompatActivity {

    QMUIGroupListView mGroupListView;
    TextView DetailTextView1,DetailTextView2,Item1Number,Item2Number,Item3Number,Item4Number;
    private QMUIBottomSheet.BottomListSheetBuilder bottomsheet1,bottomsheet2;
    private QMUIBottomSheet Bottomsheet1,Bottomsheet2;
    View.OnClickListener monClickListener;

    Button button1,button2,button3;

    QMUIFloatLayout mFloatLayout;
    QMUIFloatLayout mFloatLayout2;
    final static int A = 1;
    final static int B = 2;
    final static int LoadBtn = 3;
    final static int UnloadBtn = 4;
    final static int ReturnPhotoBtn = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_margin);
        mGroupListView = findViewById(R.id.userInterFace);

        QMUICommonListItemView Transport = mGroupListView.createItemView("运输公司");
        Transport.setOrientation(QMUICommonListItemView.HORIZONTAL);
        Transport.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        Transport.setDetailText("请选择运输公司");
        DetailTextView1 = Transport.getDetailTextView();
        DetailTextView1.setMaxLines(1);
        Transport.setId(A);

        QMUICommonListItemView Client = mGroupListView.createItemView("客户名称");
        Client.setOrientation(QMUICommonListItemView.HORIZONTAL);
        Client.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        Client.setDetailText("请选择客户");
        DetailTextView2 = Client.getDetailTextView();
        DetailTextView2.setMaxLines(1);
        Client.setId(B);

        bottomsheet1 = new QMUIBottomSheet.BottomListSheetBuilder(this)
                .setOnSheetItemClickListener(new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        DetailTextView1.setText(tag);
                    }
                });
        bottomsheet1.addItem("杭州远鉴信息科技有限责任公司十大噶士大夫噶的身份噶士大夫噶发噶法国");
        bottomsheet1.addItem("111");
        bottomsheet1.addItem("222");


        bottomsheet2 = new QMUIBottomSheet.BottomListSheetBuilder(this)
                .setOnSheetItemClickListener(new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        DetailTextView2.setText(tag);
                    }
                });
        bottomsheet2.addItem("杭州远鉴信息科技有限责任公司按时发士大夫士大夫");
        bottomsheet2.addItem("111");
        bottomsheet2.addItem("222");

        monClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof QMUICommonListItemView) {
                    int ID = ((QMUICommonListItemView) v).getId();
                    switch (ID){
                        case A:
                            Bottomsheet1 = bottomsheet1.build();
                            Bottomsheet1.show();
                            break;
                        case B:
                            Bottomsheet2 = bottomsheet2.build();
                            Bottomsheet2.show();
                            break;
                        default:
                            break;
                    }
                }
            }
        };

        QMUIGroupListView.newSection(this)
                .addItemView(Transport, monClickListener)
                .addItemView(Client, monClickListener)
                .addTo(mGroupListView);

        mFloatLayout = findViewById(R.id.floatLayout);

        int ViewSize = QMUIDisplayHelper.dpToPx(60);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewSize, ViewSize);

        mFloatLayout.addView(creatInfoView("7","运单总数",1), lp);
        mFloatLayout.addView(creatInfoView("0","装车总数",2), lp);
        mFloatLayout.addView(creatInfoView("0","卸车总数",3), lp);
        mFloatLayout.addView(creatInfoView("0","回单总数",4), lp);
        mFloatLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        mFloatLayout2 = findViewById(R.id.floatLayout2);
        int ViewSize2 = QMUIDisplayHelper.dpToPx(80);
        ViewGroup.LayoutParams lp2 = new ViewGroup.LayoutParams(ViewSize2, ViewSize2);
        mFloatLayout2.addView(creatInfoView2(R.drawable.load,"扫描装车"), lp2);
        mFloatLayout2.addView(creatInfoView2(R.drawable.unload,"扫描卸车"), lp2);
        mFloatLayout2.addView(creatInfoView2(R.drawable.returnpic,"回单拍照"), lp2);
        mFloatLayout2.setGravity(Gravity.CENTER_HORIZONTAL);


        button1.setId(LoadBtn);
        button2.setId(UnloadBtn);
        button3.setId(ReturnPhotoBtn);
        onClick();
    }

    View creatInfoView(String itemnumber, String itemName ,int ID)
    {
        LayoutInflater mLayoutInflater = LayoutInflater.from(getApplicationContext());
        View v = mLayoutInflater.inflate(R.layout.designed_textview,null);
        TextView ItemText;
        ItemText = v.findViewById(R.id.item_number);
        ItemText.setText(itemnumber);
        TextView ItemName = v.findViewById(R.id.item_name);
        ItemName.setText(itemName);
        switch (ID){
            case 1:
                Item1Number = ItemText;
                break;
            case 2:
                Item2Number = ItemText;
                break;
            case 3:
                Item3Number = ItemText;
                break;
            case 4:
                Item4Number = ItemText;
                break;
        }
        return v;
    }

    View creatInfoView2(int ID, String itemName)
    {
        LayoutInflater mLayoutInflater = LayoutInflater.from(getApplicationContext());
        View v = mLayoutInflater.inflate(R.layout.designed_button,null);
        Button button;
        button = new Button(v.getContext());
        button = v.findViewById(R.id.Item_button);
        button.setText(itemName);
        Drawable drawable = getResources().getDrawable(ID);
// 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),drawable.getMinimumHeight());
        button.setCompoundDrawables(null,drawable,null,null);
        switch (ID)
        {
            case R.drawable.load:
                button1 = button;
                break;
            case R.drawable.unload:
                button2 = button;
                break;
            case R.drawable.returnpic:
                button3 = button;
                break;
            default:
                break;
        }

        return v;
    }

    void onClick()
    {
        Onclick click = new Onclick();
        button1.setOnClickListener(click);
        button2.setOnClickListener(click);
        button3.setOnClickListener(click);
    }

    private class Onclick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case LoadBtn:
                    Item2Number.setText(""+(Integer.valueOf(Item2Number.getText().toString())+1));
                    break;
                case UnloadBtn:
                    Item3Number.setText(""+(Integer.valueOf(Item3Number.getText().toString())+1));
                    break;
                case ReturnPhotoBtn:
                    Item4Number.setText(""+(Integer.valueOf(Item4Number.getText().toString())+1));
                    break;
                default:
                    break;
            }
        }
    }
}
