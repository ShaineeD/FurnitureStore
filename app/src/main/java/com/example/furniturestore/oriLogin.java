package com.example.furniturestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class oriLogin extends AppCompatActivity {
    private Button chngbtn;
    private EditText nname;
    private EditText ppassword;
    DatabaseReference reff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ori_login);

        nname=(EditText)findViewById(R.id.uname);
        ppassword=(EditText)findViewById(R.id.cpassword);
        chngbtn=(Button)findViewById(R.id.chngbtn);
        reff= FirebaseDatabase.getInstance().getReference("Member");


        chngbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Toast.makeText(oriLogin.this,"nnnnnnnnn",Toast.LENGTH_SHORT).show();
            final String name= nname.getText().toString();
            GetUserName.uname = name;
            final String cpassword=ppassword.getText().toString();
            reff=FirebaseDatabase.getInstance().getReference().child("Member");
            reff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild(name)) {
                        Toast.makeText(oriLogin.this,"hhhhh",Toast.LENGTH_SHORT).show();
                        if (cpassword.equals(dataSnapshot.child(name).child("password").getValue().toString()))

                        {
                            Intent intent4 = new Intent(oriLogin.this, Darshbord.class);
                            intent4.putExtra("name", name);
                            startActivity(intent4);
                            Toast.makeText(getApplicationContext(), "Enter details", Toast.LENGTH_SHORT).show();

                        }else
                        Toast.makeText(getApplicationContext(), "Enter valid password", Toast.LENGTH_SHORT).show();

                    } else
                        Toast.makeText(getApplicationContext(),"Enter valid username",Toast.LENGTH_SHORT).show();

                    //specific login
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            }
        });
    }


    public void openSettings(){
        Intent intent11= new Intent(this,Darshbord.class);
        startActivity(intent11);
    }


        };


