package com.example.myfirstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class SettingActivity extends AppCompatActivity {

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        sharedPref = getSharedPreferences("UserPreferences",MODE_PRIVATE);



        Button start_button = (Button) findViewById(R.id.setting_activity_button);
        final EditText username = (EditText) findViewById(R.id.setting_activity_editText);


        final Intent intent = new Intent(this, ListWineActivity.class);

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StoreStringData("username", username.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void StoreStringData(String storage_name, String text){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(storage_name, text);
        editor.commit();
    }


}