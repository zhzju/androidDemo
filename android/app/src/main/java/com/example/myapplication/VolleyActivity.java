package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class VolleyActivity extends AppCompatActivity {
    TextView text;
    private RequestQueue mRequestQueue;
//    RequestQueue mQueue = Volley.newRequestQueue(this);
    Response.ErrorListener x = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
//            mRequestQueue = getRequestQueue();
//            mRequestQueue.add(stringRequest);
            NetworkResponse response = error.networkResponse;
            text.setText(error.getMessage());
            if (error instanceof ServerError && response != null){
            try {

                String res = new String(response.data,
                        HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                String content = new String(res.getBytes("ISO-8859-1"), "UTF-8");
                text.setText(response.data.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }}
        }};
    StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://111.231.112.116/HYSoft/HYDriverClient/response.php?ClientNumber=555",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        String content = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                        text.setText(content);

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                }
            }, x);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        text = findViewById(R.id.text);
        getRequestQueue();

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(1, 1000 ,1.0f));
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://129.204.120.66:3088/teacherCollections", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        mRequestQueue.add(stringRequest);
    }
    public void getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() 是关键, 它避免了你
            //传递进Activity或BroadcastReceiver导致的内存泄漏
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }
}
