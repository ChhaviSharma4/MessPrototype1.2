package com.example.weirdo.myapp1;


import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Weirdo on 11-04-2016.
 */
public class RegisterRequest extends StringRequest {

    private static final String Reg_req_url = "http://test1995.net23.net/enteritems.php";
    private Map<String, String > params;


    public RegisterRequest(String rollno, String item, String price , Response.Listener<String> listener){
        super(Method.POST, Reg_req_url , listener, null);
        params= new HashMap<>();
        params.put("rollno",rollno);
        params.put("item",item);
        params.put("price",price);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date();
        params.put("date",dateFormat.format(d));
        dateFormat = new SimpleDateFormat("HH:mm");
        d = new Date();
        params.put("time",dateFormat.format(d));


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
