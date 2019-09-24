package com.example.furniturestore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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
    EditText rpassword;
    String CustomerT , SellerT , CarpenterT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_form);

        CustomerT = "Customer";
        SellerT = "Seller";
        CarpenterT = "Carpenter";


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
        rpassword = (EditText)findViewById(R.id.repassword1);
        member=new Member();


        reff= FirebaseDatabase.getInstance().getReference().child("Member");

            regbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(regForm.this, "xxxxx", Toast.LENGTH_LONG).show();

        String pid = reff.push().getKey();
        String email=editMail.getText().toString().trim();
        String pass=editPass.getText().toString().trim();
        try {
            if (TextUtils.isEmpty(editName.getText().toString()))
                Toast.makeText(getApplicationContext(), "enter name", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(editMail.getText().toString()))
                Toast.makeText(getApplicationContext(), "enter email", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(editPass.getText().toString()))
                Toast.makeText(getApplicationContext(), "enter password", Toast.LENGTH_SHORT).show();
            else if (!(editPass.getText().toString()).equals(rpassword.getText().toString()))
                Toast.makeText(getApplicationContext(), "password entered missmatch", Toast.LENGTH_SHORT).show();
            else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

                editMail.setError("Invalid Email");
                editMail.setFocusable(true);
            }else if(pass.length()<6){
                editPass.setError("Password length 6 charaacters");
                editPass.setFocusable(true);
            }

            else {
                member.setName(editName.getText().toString().trim());
                member.setEmail(editMail.getText().toString().trim());
                member.setPassword(editPass.getText().toString().trim());
                member.setType(spinner1.getSelectedItem().toString());
                member.setRePassword(rpassword.getText().toString().trim());
                member.setPushId(pid);
                System.out.println(pid);
                GetUserName.uname=member.getName();
                FirebaseDatabase.getInstance().getReference().child("Member").child(member.getName()).setValue(member);
                Toast.makeText(regForm.this, "Account Created Succesfully", Toast.LENGTH_LONG).show();
               if(member.getType().equals(CustomerT)){
                startActivity(new Intent(regForm.this,Darshbord.class));
               } else if(member.getType().equals(CarpenterT)){
                   startActivity(new Intent(regForm.this,Camera.class)); //update with prper intents
               }else if(member.getType().equals(SellerT)){
                   startActivity(new Intent(regForm.this,Darshbord.class));
               }
            }
        }
        catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(),"Invalid",Toast.LENGTH_SHORT).show();
        }
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