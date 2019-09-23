package com.example.furniturestore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class regForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
  EditText editName,editMail,editPass;
  Spinner spinner1;
  Button regbtn;
    DatabaseReference reff;
    Member member;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_form);



        Spinner spinner=findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this,R.array.Type,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        editName=(EditText)findViewById(R.id.editName);
        editMail=(EditText)findViewById(R.id.editMail);
        editPass=(EditText)findViewById(R.id.editPass);
        spinner1=(Spinner)findViewById(R.id.spinner1);
        regbtn=(Button)findViewById(R.id.regbtn);
        member=new Member();


        reff= FirebaseDatabase.getInstance().getReference().child("Member");

            regbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(regForm.this, "xxxxx", Toast.LENGTH_LONG).show();
                    String pid = reff.push().getKey();
                    member.setName(editName.getText().toString().trim());
                    member.setEmail(editMail.getText().toString().trim());
                    member.setPassword(editPass.getText().toString().trim());
                    member.setType(spinner1.getSelectedItem().toString());
                    member.setPushId(pid);
                    System.out.println(pid);
                    FirebaseDatabase.getInstance().getReference().child("Member").child(pid).setValue(member);
                    Toast.makeText(regForm.this, "Account Created Succesfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(regForm.this,Darshbord.class));
                }
            });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}