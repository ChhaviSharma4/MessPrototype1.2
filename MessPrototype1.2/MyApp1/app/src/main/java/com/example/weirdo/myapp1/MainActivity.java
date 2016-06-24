package com.example.weirdo.myapp1;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ImageButton;
        import android.util.Log;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton admin, user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        admin = (ImageButton) findViewById(R.id.mabadmin);
        user = (ImageButton) findViewById(R.id.mabuser);

        //Toast.makeText(getApplication(), "Welcome Aboard", Toast.LENGTH_LONG).show();
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , AdminMain.class);
                startActivity(i);
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , UserLogin.class);
                startActivity(i);
            }
        });


    }
}
