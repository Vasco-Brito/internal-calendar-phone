package com.example.internal_calendar_phone;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class postActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        TextView messageTextView = findViewById(R.id.messageTextView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Registrar o receptor de SMS aqui, se necessário
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Desregistrar o receptor de SMS aqui, se necessário
    }

    public void sendPostRequest(Context context, String message) {
        String url = "http://192.168.1.86:3000/api/phone/msg";

        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("param1", "value1");
            jsonParams.put("param2", "value2");
            jsonParams.put("msgBody", message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, jsonParams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Sucesso na resposta do servidor
                        Toast.makeText(context, "POST request successful!", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Erro na resposta do servidor
                        String responseBody = "";
                        if (error.networkResponse != null && error.networkResponse.data != null) {
                            try {
                                responseBody = new String(error.networkResponse.data, "utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.e("PostActivity", "Error response: " + responseBody);
                        Toast.makeText(context.getApplicationContext(), "POST request failed!", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        requestQueue.add(postRequest);
    }
}
