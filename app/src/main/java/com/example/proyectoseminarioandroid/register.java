package com.example.proyectoseminarioandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class register extends AppCompatActivity {
    EditText nombre,email,pass,sex,fecha,direc;
    Button reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nombre=findViewById(R.id.nombreRegister);
        email=findViewById(R.id.emailRegister);
        pass=findViewById(R.id.passwordRegister);
        reg=findViewById(R.id.btnlogin);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(register.this,login.class);
                enviar();
                startActivity(in);
            }
        });
    }

    private void enviar() {
        AsyncHttpClient client=new AsyncHttpClient();
        final RequestParams req=new RequestParams();
        req.put("nombre",nombre.getText().toString());
        req.put("email",email.getText().toString());
        req.put("password",pass.getText().toString());
        client.post(inicio.ip+"/user",req,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    String r=response.getString("message");
                    Toast.makeText(getApplicationContext(),r,Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
