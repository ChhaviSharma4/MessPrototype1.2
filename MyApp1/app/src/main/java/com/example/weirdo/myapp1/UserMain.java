package com.example.weirdo.myapp1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by Weirdo on 28-04-2016.
 */
public class UserMain extends AppCompatActivity {

    ImageButton bv , bp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usermain);
            bv = (ImageButton) findViewById(R.id.viewbill);
            bp = (ImageButton) findViewById(R.id.billpay);

        bv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = getIntent();
                Bundle b = i.getExtras();
                String roll = b.getString("RollNumber");
                 i = new Intent(getApplication() , UserView.class);

                i.putExtra("RollNumber", roll);
                startActivity(i);
                //finish();
            }
        });

        bp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.onlinesbi.com/prelogin/icollecthome.htm?corpID=346985";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}
