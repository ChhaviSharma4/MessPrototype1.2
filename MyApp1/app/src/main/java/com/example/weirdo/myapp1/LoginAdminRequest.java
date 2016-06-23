package com.example.weirdo.myapp1;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Weirdo on 28-04-2016.
 */
public class LoginAdminRequest extends StringRequest {

    private static final String Log_req_url = "http://test1995.net23.net/AdminLogin.php";
    private Map<String, String > params;

    public LoginAdminRequest(String username , String password , Response.Listener<String> listener){
        super(Method.POST, Log_req_url , listener, null);
        params= new HashMap<>();
        params.put("username",username);
        params.put("password",password);


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
