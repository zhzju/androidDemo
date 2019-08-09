package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.SmsSendere.SmsSingleSender;
import com.github.qcloudsms.SmsSenderUtil;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.System.currentTimeMillis;

public class TencentCloudActivity extends AppCompatActivity {
    // 短信应用 SDK AppID
    int appid = 1400241187; // SDK AppID 以1400开头
    // 短信应用 SDK AppKey
    String appkey = "a7d0653bb9f9e2dfc62fd0d172101740";
    // 需要发送短信的手机号码
    String[] phoneNumbers = {"18867110210"};
    private RequestQueue mRequestQueue;
    int Checkcode = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tencent_cloud);
//        https://yun.tim.qq.com/v5/tlssmssvr/sendsms?sdkappid=xxxxx&random=xxxx
//        try {
//            Checkcode = generateCheckcode();
//            long random = (new Random(currentTimeMillis()/1000)).nextInt(900000) + 100000;
//            long now = currentTimeMillis()/1000;
//            String phoneNumber = phoneNumbers[0];
//            JSONObject body = new JSONObject()
//                    .put("tel", (new JSONObject()).put("nationcode", "86").put("mobile", phoneNumber))
//                    .put("type", 1)
//                    .put("msg", String.format("【海悦司机客户端】您的验证码是: %s",String.valueOf(Checkcode)))
//                    .put("sig", Encrypt("appkey="+this.appkey+"&random="+random+"&time="+now+"&mobile="+phoneNumber,""))
//                    .put("time",now)
//                    .put("extend", "")
//                    .put("ext","");;
//
//            JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, String.format("https://yun.tim.qq.com/v5/tlssmssvr/sendsms?sdkappid=%s&random=%s",String.valueOf(appid),String.valueOf(random)),
//                    body,
//                    new Response.Listener<JSONObject>() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            Log.d("TAG", "response -> " + response.toString());
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Log.e("TAG", error.getMessage(), error);
//                }
//            }) {
//                @Override
//                public Map<String, String> getHeaders() {
//                    HashMap<String, String> headers = new HashMap<String, String>();
//                    headers.put("Accept", "application/json");
//                    headers.put("Content-Type", "application/json; charset=UTF-8");
//                    return headers;
//                }
//            };
//            getRequestQueue();
//            mRequestQueue.add(jsonRequest);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        try {
            SignDemo m = new SignDemo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private int generateCheckcode(){
        int checkcode;
        Random rm = new Random(currentTimeMillis());
        checkcode = rm.nextInt(8999)+1000;
        return checkcode;
    }
    private static String Encrypt(String strSrc, String encName) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try {
            if (encName == null || encName.equals("")) {
                encName = "SHA-256";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }
    private static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }
    public void getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() 是关键, 它避免了你
            //传递进Activity或BroadcastReceiver导致的内存泄漏
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }
}
