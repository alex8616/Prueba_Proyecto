package com.example.proyectoseminarioandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class login extends AppCompatActivity implements View.OnClickListener {
    EditText email,pass;
    TextView reg;
    Button log;
    Context con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.password);
        log=findViewById(R.id.btnlogin);
        reg=findViewById(R.id.registrate);
        reg.setOnClickListener(this);
        log.setOnClickListener(this);
        con=this;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnlogin: {
                Toast.makeText(login.this, "Ingresando", Toast.LENGTH_SHORT).show();
                sendLogin();
                break;
            }
            case R.id.registrate: {
                Intent registro = new Intent(login.this, register.class);
                startActivity(registro);
                break;
            }
        }

    }

    private void sendLogin() {

        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams req=new RequestParams();
        req.put("email",email.getText().toString());
        req.put("password",pass.getText().toString());
        client.post(inicio.ip+"/user/login",req,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    String res=response.getString("message");
                    if(res.equals("auth succes")){
                        inicio.token=response.getString("token");
                        Toast.makeText(con,""+res,Toast.LENGTH_SHORT).show();
                        Intent in;
                        if(response.getBoolean("admin")){
                            in=new Intent(con,admin.class);
                        }else{
                            in=new Intent(con,MainActivity.class);
                        }
                        inicio.id_u=response.getString("id");
                        inicio.email=response.getString("email");
                        inicio.nombre=response.getString("nombre");
                        startActivity(in);
                    }else{
                        Toast.makeText(con,""+res,Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    }


}
