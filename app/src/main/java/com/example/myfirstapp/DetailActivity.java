package com.example.myfirstapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class DetailActivity extends AppCompatActivity {

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Singleton.requestGetBitmap(position+1);

        position = getIntent().getIntExtra("wine_position",0);

        Singleton.requestLightOnLed(Singleton.getInstance().wines.get(position).led_position-1);

        ((TextView) findViewById(R.id.detail_wine_name)).setText("name : "+Singleton.getInstance().wines.get(position).wine_name);
        ((TextView) findViewById(R.id.detail_wine_date)).setText("wine date : "+Singleton.getInstance().wines.get(position).wine_year_date);
        ((TextView) findViewById(R.id.detail_wine_added_date)).setText("added in may cellar in : "+Singleton.getInstance().wines.get(position).wine_added_date);
        ((TextView) findViewById(R.id.detail_wine_quantity)).setText("quantity : "+Singleton.getInstance().wines.get(position).wine_quantity);
        ((TextView) findViewById(R.id.detail_wine_country)).setText("country : "+Singleton.getInstance().wines.get(position).wine_country);
        ((TextView) findViewById(R.id.detail_wine_consumption_date)).setText("consumption date : "+Singleton.getInstance().wines.get(position).wine_consuming_date);
        ((TextView) findViewById(R.id.detail_wine_region)).setText("region : "+Singleton.getInstance().wines.get(position).wine_region);
        ((TextView) findViewById(R.id.detail_wine_category)).setText("wine : "+Singleton.getInstance().wines.get(position).category);
        Singleton.log4Me("the Bitmap is "+Singleton.getInstance().wines.get(position).wine_image, "DetailActivity.onCreate");
        if(Singleton.getInstance().wines.get(position).wine_image!=null){
            System.out.println("we should see a bitmap");
            ((ImageView) findViewById(R.id.detail_wine_image_view)).setImageBitmap(Singleton.getInstance().wines.get(position).wine_image);
        }

        final Intent intent = new Intent(this, ListWineActivity.class);

        ((Button) findViewById(R.id.detail_activity_back_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        AlertDialog.Builder myPopUp = new AlertDialog.Builder(DetailActivity.this);

        ((Button) findViewById(R.id.detail_activity_delete_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myPopUp.setTitle("Withdraw the Wine from the Cellar");
                myPopUp.setMessage("Do you want to withdraw this wine ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                int id_del = 0;

                                Singleton.getInstance().ids_dispo.add(position+1);
                                for (int i=0; i<Singleton.getInstance().wines.size(); i++) {
                                    if(Singleton.getInstance().wines.get(position).id==Singleton.getInstance().wines.get(i).id){
                                        id_del = i;
                                    }
                                }
                                Singleton.requestDeleteWine(Singleton.getInstance().wines.get(position).id);
                                Singleton.getInstance().wines.remove(id_del);
                                finish();
                                Toast.makeText(getApplicationContext(),"you choose yes action for withdraw a wine",
                                        Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(),"you choose no action for withdraw a wine",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                AlertDialog alert = myPopUp.create();
                alert.setTitle("Withdraw the Wine from the Cellar");
                alert.show();
            }
        });
    }
}