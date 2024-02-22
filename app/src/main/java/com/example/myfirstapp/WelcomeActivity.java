package com.example.myfirstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WelcomeActivity extends AppCompatActivity {

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Singleton.init();

        sharedPref = getSharedPreferences("UserPreferences",MODE_PRIVATE);



        //if username already filled then user goes straight to the next activity
        if(!sharedPref.getString("username","").equals("")){
            Intent intent = new Intent(this, ListWineActivity.class);
            startActivity(intent);

        }else{ //else he filled for the first and last time his username
            setContentView(R.layout.activity_welcome);

            Button start_button = (Button) findViewById(R.id.welcome_activity_button);
            final EditText username = (EditText) findViewById(R.id.welcome_activity_editText);


            final Intent intent = new Intent(this, ListWineActivity.class);

            start_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    StoreStringData("username", username.getText().toString());
                    startActivity(intent);
                }
            });
        }

    }
    private void StoreStringData(String storage_name, String text){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(storage_name, text);
        editor.commit();
    }


}
