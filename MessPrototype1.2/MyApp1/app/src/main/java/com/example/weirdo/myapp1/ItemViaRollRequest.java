package com.example.weirdo.myapp1;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ItemViaRollRequest extends StringRequest {

    private static final String Roll_url = "http://test1995.net23.net/userFetch.php";
        private Map<String, String > params;

        public ItemViaRollRequest( String roll , Response.Listener<String> listener){
            super(Request.Method.POST, Roll_url , listener, null);
            params= new HashMap<>();
            params.put("roll",roll);


        }

        @Override
        public Map<String, String> getParams() {
            return params;
        }
    }


