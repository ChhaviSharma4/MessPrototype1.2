package com.example.weirdo.myapp1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Weirdo on 28-04-2016.
 */
public class UserLogin extends AppCompatActivity {
    Button b1;
    EditText e1,e2,e3,e4;
    TextView t1, t2;
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlogin);

        progressBar= (ProgressBar) findViewById(R.id.progressUser);
        progressBar.setVisibility(View.INVISIBLE);
        e1 = (EditText) findViewById(R.id.editname);
        e2 = (EditText) findViewById(R.id.edityear);
        e3 = (EditText) findViewById(R.id.editgb);
        e4 = (EditText) findViewById(R.id.editroll);

        b1 = (Button) findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                final String name, roll;
                name = e1.getText().toString();
                roll = "2K" + e2.getText().toString() + "/HO/" + e3.getText().toString() + "/" + e4.getText().toString();
             //   Toast.makeText(getApplication(), name + " " + roll, Toast.LENGTH_LONG).show();

                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if (success) {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplication(), "Login Success , Shared Lock Activated", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplication() , UserMain.class);
                                i.putExtra("RollNumber", roll);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                                finish();

                            } else {
                                progressBar.setVisibility(View.INVISIBLE);

                                Toast.makeText(getApplication(), "Incorrect Name/Password", Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                UserLoginRequest ulr = new UserLoginRequest(name, roll,listener);
                RequestQueue queue = Volley.newRequestQueue(getApplication());
                queue.add(ulr);







            }
        });
    }
}