package com.example.weirdo.myapp1;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Weirdo on 25-04-2016.
 */
public class AdminMain extends AppCompatActivity {

    Button adminlogin ;
    String auser , apass;
    EditText adminuser , adminpass;
    ProgressBar progressBar ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminlayout);
        progressBar = (ProgressBar) findViewById(R.id.progressNewsList);
        progressBar.setVisibility(View.INVISIBLE);
        adminuser= (EditText) findViewById(R.id.adminuser);
        adminpass= (EditText) findViewById(R.id.adminpass);

        adminlogin = (Button) findViewById(R.id.loginbutton);
        adminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                auser=adminuser.getText().toString();
                apass=adminpass.getText().toString();
          //      setProgressBarVisibility(true);
                progressBar.setVisibility(View.VISIBLE);
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                        //    Toast.makeText(getApplication(), "Incorrect Username/Password", Toast.LENGTH_LONG).show();

                            Log.i("tagconvertstr", "[" + response + "]");
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success=false;
                            success= jsonObject.getBoolean("success");
                            String lock = jsonObject.getString("lock");

                            if (!success){
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplication(), "Incorrect Username/Password", Toast.LENGTH_SHORT).show();
                            }
                            else if(lock.equals("lock"))
                            {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplication(), "Admin have Logged in From another Device", Toast.LENGTH_SHORT).show();

                            }
                            else if(success){


                                SetLock();
                              //  setProgressBarVisibility(false);
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplication(), "Login Success", Toast.LENGTH_SHORT).show();


                                Intent i = new Intent(getApplication() , AdminEnter.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                                finish();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };


                LoginAdminRequest loginRequest= new LoginAdminRequest(auser, apass, listener);
                RequestQueue queue = Volley.newRequestQueue(getApplication());
                queue.add(loginRequest);

            }
        });

    }
    void SetLock(){
        String l = new String("lock");
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                   // Log.i("tagconvertstr", "[" + response + "]");
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");


                        if(success){

                        Toast.makeText(getApplication(), "Lock Set", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(getApplication(), "Lock Error", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };


        Lockrequest lr= new Lockrequest(l, listener);
        RequestQueue queue = Volley.newRequestQueue(getApplication());
        queue.add(lr);

    }
}
