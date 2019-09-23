package com.example.furniturestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Darshbord extends AppCompatActivity {

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_darshbord);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.example_menu,menu);
        return true;
    }
    @Override
   public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (item.getItemId()){
            case R.id.Settings:
                startActivity(new Intent(this,login.class));
                return true;

            case R.id.Logout:
                startActivity(new Intent(this,MainActivity.class));
                return true;

            case R.id.Profile:
                startActivity(new Intent(this,veiw.class));
                return true;


            default:return super.onOptionsItemSelected(item);
        }

    }
}
