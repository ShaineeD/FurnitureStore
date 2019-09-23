package com.example.furniturestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends Darshbord {

    EditText fnamee,pw;
    Member std;
    DatabaseReference reff;
    Button  update, delete;
    String n;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_login);
       Intent i = getIntent();
       n = i.getStringExtra("name");
       fnamee = (EditText) findViewById(R.id.editName);
       pw = (EditText) findViewById(R.id.editPass);
       update = (Button) findViewById(R.id.update);
       delete = (Button) findViewById(R.id.delete);
       std = new Member();
       Toast.makeText(getApplicationContext(), "ffffddddd", Toast.LENGTH_SHORT).show();


       //DatabaseReference upRef= FirebaseDatabase.getInstance().getReference().child("Member");

       update.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Member");
               upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       if (dataSnapshot.hasChild(n)) {
                           try {
                               std.setName(fnamee.getText().toString().trim());
                               std.setPassword(pw.getText().toString().trim());


                               reff = FirebaseDatabase.getInstance().getReference().child("Member").child(n);
                               reff.setValue(std);

                               Toast.makeText(getApplicationContext(), "Profile updated", Toast.LENGTH_SHORT).show();
                           } catch (NumberFormatException e) {

                               Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
                           }
                       } else {
                           Toast.makeText(getApplicationContext(), "No source to update", Toast.LENGTH_LONG).show();
                       }
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });

           }
       });


       delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Member");
               delRef.addListenerForSingleValueEvent(new ValueEventListener() {

                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       if (dataSnapshot.hasChild(n)) {
                           reff = FirebaseDatabase.getInstance().getReference().child("Memeber").child(n);
                           reff.removeValue();


                           Toast.makeText(getApplicationContext(), "Account has been deleted", Toast.LENGTH_SHORT).show();
                       } else
                           Toast.makeText(getApplicationContext(), "No source to delete", Toast.LENGTH_LONG).show();
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }

               });

           }


       });
   }
}
