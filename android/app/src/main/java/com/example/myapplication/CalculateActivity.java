package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CalculateActivity extends AppCompatActivity {
    private TextView tv_x;
    private Button[] btn_list = new Button[14];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        tv_x = findViewById(R.id.tv_1);
        btn_list[0] = findViewById(R.id.btn_0);
        btn_list[0].setText("0");
        btn_list[1] = findViewById(R.id.btn_1);
        btn_list[1].setText("1");
        btn_list[2] = findViewById(R.id.btn_2);
        btn_list[2].setText("2");
        btn_list[3] = findViewById(R.id.btn_3);
        btn_list[3].setText("3");
        btn_list[4] = findViewById(R.id.btn_4);
        btn_list[4].setText("4");
        btn_list[5] = findViewById(R.id.btn_5);
        btn_list[5].setText("5");
        btn_list[6] = findViewById(R.id.btn_6);
        btn_list[6].setText("6");
        btn_list[7] = findViewById(R.id.btn_7);
        btn_list[7].setText("7");
        btn_list[8] = findViewById(R.id.btn_8);
        btn_list[8].setText("8");
        btn_list[9] = findViewById(R.id.btn_9);
        btn_list[9].setText("9");
        btn_list[10] = findViewById(R.id.btn_plus);
        btn_list[10].setText("+");
        btn_list[11] = findViewById(R.id.btn_minus);
        btn_list[11].setText("-");
        btn_list[12] = findViewById(R.id.btn_back);
        btn_list[12].setText("<-");
        btn_list[13] = findViewById(R.id.btn_equal);
        btn_list[13].setText("==");
        for (Button btn : btn_list)
        {
            btn.setTextSize(25);
        }
        setListeners();
    }
    private  void setListeners(){
        Onclick onClick = new Onclick();
        for (Button btn : btn_list)
        {
            btn.setOnClickListener(onClick);
        }
    }

    private class Onclick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_0 : SetTextToTextView("0");break;
                case R.id.btn_1 : SetTextToTextView("1");break;
                case R.id.btn_2 : SetTextToTextView("2");break;
                case R.id.btn_3 : SetTextToTextView("3");break;
                case R.id.btn_4 : SetTextToTextView("4");break;
                case R.id.btn_5 : SetTextToTextView("5");break;
                case R.id.btn_6 : SetTextToTextView("6");break;
                case R.id.btn_7 : SetTextToTextView("7");break;
                case R.id.btn_8 : SetTextToTextView("8");break;
                case R.id.btn_9 : SetTextToTextView("9");break;
                case R.id.btn_plus : SetTextToTextView("+");break;
                case R.id.btn_minus : SetTextToTextView("-");break;
                case R.id.btn_back : SetTextBack();break;
                case R.id.btn_equal : break;
                default: break;
            }
        }
    }

    private void SetTextToTextView(String text)
    {
        CharSequence nowString;
        String FinalText;
        nowString = tv_x.getText();
        FinalText = nowString.toString() + text;
        tv_x.setText(FinalText);
    }

    private void SetTextBack()
    {
        CharSequence nowString;
        nowString = tv_x.getText();
        if (nowString.length() == 0)
        {
            Toast.makeText(CalculateActivity.this,"显示屏无内容",Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            tv_x.setText(nowString.subSequence(0,nowString.length()-1));
        }
    }
}
