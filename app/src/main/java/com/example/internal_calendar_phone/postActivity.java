package com.example.internal_calendar_phone;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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
        Toast.makeText(context, "Mensagem aqui?: " + message, Toast.LENGTH_SHORT).show();
        String url = "http://localhost:3000/api/phone/msg";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Sucesso na resposta do servidor
                        Toast.makeText(postActivity.this, "POST request successful!", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Erro na resposta do servidor
                        Toast.makeText(postActivity.this, "POST request failed!", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("param1", "value1");
                params.put("param2", "value2");
                params.put("msgBody", message); // Adiciona o msgBody aos parâmetros do POST
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(postRequest);
    }
}
