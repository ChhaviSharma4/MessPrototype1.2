package com.example.weirdo.myapp1;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Weirdo on 25-04-2016.
 */
public class AdminEnter extends AppCompatActivity {

    ProgressBar progressBar;
    Button aebok , logoutb;
    EditText e1, e2, e3 , ename;
    String roll, item, price;
    AutoCompleteTextView actv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminenter);

        String[] itemList = {"Appy","Fresca","Frooti","Cold Drink","Chips","Ice Cream",
        "Biscuits","cold coffee","coffee","shake","fruit juice"};

       // Toast.makeText(getApplication(), "asdsdsdsdsdsdsdsdsds", Toast.LENGTH_LONG).show();
        e1 = (EditText) findViewById(R.id.editText);
        //e2 = (EditText) findViewById(R.id.editText1);
        e3 = (EditText) findViewById(R.id.editText2);
        ename = (EditText) findViewById(R.id.autoGenName);
        logoutb = (Button) findViewById(R.id.logoutbutton);
        progressBar =(ProgressBar) findViewById(R.id.progressEnter);
        progressBar.setVisibility(View.INVISIBLE);

        actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);


        //Network Call to fetch name
        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                String url = "http://test1995.net23.net/nameviaroll.php?roll=" + e1.getText().toString();

                progressBar.setVisibility(View.VISIBLE);


                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                        url, null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("Tag", response.toString());
                                String n = response.optString("name");
                                ename.setText(n);
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("TAG", "Error: " + error.getMessage());

                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });


// Adding request to request queue
                RequestQueue queue = Volley.newRequestQueue(getApplication());
                queue.add(jsonObjReq);




            }
        });

        aebok = (Button) findViewById(R.id.adminenter);
        aebok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);

                roll = e1.getText().toString();


                ArrayAdapter<String> adapter = new ArrayAdapter<String>
                        (this,android.R.layout.simple_list_item_1,itemList);
                actv.setAdapter(adapter);
                actv.setThreshold(1);
                actv.setOnItemClickListener(new OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent,
                                            View view, int position, long rowId) {
                        item = (String)parent.getItemAtPosition(position);
                        //TODO Do something with the selected text
                    }
                });
                //this.actv.setAdapter(adapter);

                price = e3.getText().toString();


                if(item==null || price == null)
                {
                    Toast.makeText(getApplication(), "Please Enter Complete Data" , Toast.LENGTH_LONG).show();
                }
                else {


                    Response.Listener<String> listener1 = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONObject jsonObject = new JSONObject(response);
                                boolean success = jsonObject.getBoolean("success");

                                if (!success) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getApplication(), "Roll Number Does Not Exist", Toast.LENGTH_SHORT).show();

                                } else {
                                    Response.Listener<String> listener = new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            try {

                                                JSONObject jsonObject = new JSONObject(response);
                                                boolean success = jsonObject.getBoolean("success");

                                                if (success) {
                                                    progressBar.setVisibility(View.INVISIBLE);

                                                    Toast.makeText(getApplication(), "Item Added", Toast.LENGTH_SHORT).show();

                                                } else {
                                                    progressBar.setVisibility(View.INVISIBLE);

                                                    Toast.makeText(getApplication(), "Network Failure", Toast.LENGTH_SHORT).show();

                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    };

                                    RegisterRequest registerRequest = new RegisterRequest(roll, item, price, listener);
                                    RequestQueue queue = Volley.newRequestQueue(getApplication());
                                    queue.add(registerRequest);


                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    RollRequest rollRequest = new RollRequest(roll, listener1);
                    RequestQueue queue = Volley.newRequestQueue(getApplication());
                    queue.add(rollRequest);


                }





            }


        });





        logoutb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String l = new String("unlock");

                progressBar.setVisibility(View.VISIBLE);

                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");


                            if(success){
                                progressBar.setVisibility(View.INVISIBLE);

                                Toast.makeText(getApplication(), "Unlocked", Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(getApplication() , AdminMain.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                                finish();

                            }
                            else{

                                progressBar.setVisibility(View.INVISIBLE);
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
        });


    }

    @Override
    public void onBackPressed() {
        String l = new String("unlock");

        progressBar.setVisibility(View.VISIBLE);
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");


                    if(success){

                        Toast.makeText(getApplication(), "Unlocked", Toast.LENGTH_SHORT).show();

                        progressBar.setVisibility(View.INVISIBLE);
                        Intent i = new Intent(getApplication() , AdminMain.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        finish();

                    }
                    else{

                        progressBar.setVisibility(View.INVISIBLE);
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


