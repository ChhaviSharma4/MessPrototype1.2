package com.example.weirdo.myapp1;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Weirdo on 28-04-2016.
 */
public class Lockrequest extends StringRequest {

    private static final String Log_req_url = "http://test1995.net23.net/lockrequest.php";
    private Map<String, String > params;

    public Lockrequest( String lock , Response.Listener<String> listener){
        super(Request.Method.POST, Log_req_url , listener, null);
        params= new HashMap<>();
        params.put("lock",lock);


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
