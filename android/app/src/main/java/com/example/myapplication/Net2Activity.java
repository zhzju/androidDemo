package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Net2Activity extends AppCompatActivity {

    OkHttpClient client = new OkHttpClient();
    private TextView text3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net2);

        text3 = findViewById(R.id.text3);
        new Thread(runnable).start();
    }

    String runnet(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url).addHeader("Connection","close")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.body().string();
        }
    }
    Runnable runnable = new Runnable(){
        @Override
        public void run(){
            //进行访问网络操作
            try {
                String res = runnet("http://129.204.120.66:3088/teacherCollections");
                Message msg = Message.obtain();
                Bundle data = new Bundle();
                data.putString("value", res);
                msg.setData(data);
                handler.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }};
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            Bundle data = msg.getData();
            //从data中拿出存的数据
            String val = data.getString("value");
            text3.setText(val);
        }
    };
}
