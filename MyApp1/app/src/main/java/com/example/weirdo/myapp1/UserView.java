package com.example.weirdo.myapp1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.*;


public class UserView extends AppCompatActivity {


    private List<Item> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ItemsAdapter mAdapter;
    String roll;
    TextView t;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userview);
        t = (TextView) findViewById(R.id.Bill);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        roll = b.getString("RollNumber");


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new ItemsAdapter(itemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        prepareMovieData();

    }

    private void prepareMovieData() {



        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Integer sum= 0;
                    String s= response;

                    Log.d("hh",s);

                    JSONArray jsonArray = new JSONArray(s);

                    for(int i = 0; i < jsonArray.length(); i++)
                    {

                        JSONArray j = jsonArray.getJSONArray(i);
                        String a = j.getString(1);
                        String b = j.getString(2);
                        String c = j.getString(3);
                        String d = j.getString(4);
                        Item item = new Item(a,b,c,d);
                        itemList.add(item);
                        sum+= parseInt(b);
                       // t.setText("BILL: " + sum);
                       // Toast.makeText(getApplication(), sum+" ", Toast.LENGTH_LONG).show();
                    }


                     t.setText("BILL: " + sum);
                    //t.setText(String.valueOf(sum));


                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                       // t.setText("Bill: ");
                    mAdapter.notifyDataSetChanged();
                }
            }
        };

        ItemViaRollRequest itemViaRollRequest = new ItemViaRollRequest(roll,listener);
        RequestQueue queue = Volley.newRequestQueue(getApplication());
        queue.add(itemViaRollRequest);


        mAdapter.notifyDataSetChanged();
    }



}
