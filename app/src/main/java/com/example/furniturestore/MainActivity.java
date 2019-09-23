package com.example.furniturestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button logbtn;
    private Button singbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Toast.makeText(MainActivity.this,"Firebase Sucessful",Toast.LENGTH_LONG).show();
        logbtn = (Button) findViewById(R.id.update);
        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });

        singbtn=(Button) findViewById(R.id.delete);
        singbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReg();
            }
        });
    }

    public void openLogin() {
        Intent logbtn = new Intent(this, oriLogin.class);
        startActivity(logbtn);

        singbtn=(Button) findViewById(R.id.delete);
        singbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReg();
            }
        });

    }
    public void openReg(){
        Intent regintent= new Intent(this,regForm.class);
        startActivity(regintent);

    }


}
