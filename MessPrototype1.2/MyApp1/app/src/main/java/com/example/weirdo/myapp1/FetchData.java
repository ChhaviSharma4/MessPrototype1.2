package com.example.weirdo.myapp1;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Weirdo on 27-04-2016.
 */
public class FetchData extends StringRequest {

    private static final String Reg_req_url = "http://test1995.net23.net/enteritems.php";
    private Map<String, String > params;

    public FetchData(String item, String price , Response.Listener<String> listener){
        super(Method.POST, Reg_req_url , listener, null);
        params= new HashMap<>();
        params.put("item",item);
        params.put("price",price);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
