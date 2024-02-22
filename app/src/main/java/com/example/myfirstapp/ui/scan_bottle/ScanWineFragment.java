package com.example.myfirstapp.ui.scan_bottle;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProvider;

import com.example.myfirstapp.ListWineActivity;
import com.example.myfirstapp.R;
import com.example.myfirstapp.Singleton;
import com.example.myfirstapp.Wine;

import java.util.Date;


public class ScanWineFragment extends Fragment {

    private ScanWineViewModel scanWineViewModel;
    private static final int pic_id = 123;
    private ScanWineFragment activity;

    Button camera_open_id;
    ImageView click_image_id;

    Bitmap photo;

    Wine new_wine;

    public static int CODE_CAMERA = 0;
    private static String TAG = "Luc";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        activity = this;

        scanWineViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ScanWineViewModel.class);
        View root = inflater.inflate(R.layout.fragment_scan_bottle, container, false);

        camera_open_id= root.findViewById(R.id.camera_open);
        click_image_id = root.findViewById(R.id.image_view_scan_bottle);

        final Intent intent = new Intent(this.getContext(), ListWineActivity.class);

        for (Wine wine : Singleton.getInstance().wines) {
            if(wine.id==1){
                    Singleton.getInstance().ids_dispo.remove(0);
            }else if(wine.id==2){
                Singleton.getInstance().ids_dispo.remove(1);
            }else if(wine.id==3){
                Singleton.getInstance().ids_dispo.remove(2);
            }else if(wine.id==4){
                Singleton.getInstance().ids_dispo.remove(3);
            }
        }


        camera_open_id.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Camera permission has not been granted.

                    requestCallPermission();

                } else {

                    // Camera permissions is already available, show the camera preview.
                    Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(camera_intent, pic_id);
                }
            }
        });

        ((Button) root.findViewById(R.id.scan_wine_add_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Singleton.getInstance().wines.size()>=4) {

                    AlertDialog.Builder myPopUp = new AlertDialog.Builder(activity.getActivity());
                    myPopUp.setTitle("Wine Not Added | Cellar is Full");
                    myPopUp.setMessage("Your have already 4 wines into your cellar");
                    myPopUp.show();

                } else {
                    String wine_country = ((EditText) root.findViewById(R.id.scan_wine_country)).getText().toString();
                    String wine_name = ((EditText) root.findViewById(R.id.scan_wine_name)).getText().toString();
                    String wine_region = ((EditText) root.findViewById(R.id.scan_wine_region)).getText().toString();
                    String wine_category = ((EditText) root.findViewById(R.id.scan_wine_category)).getText().toString();
                    int wine_year = Integer.parseInt(((EditText) root.findViewById(R.id.scan_wine_year)).getText().toString());
                    int wine_quantity = 1;
                    int wine_consumption_date = Integer.parseInt(((EditText) root.findViewById(R.id.scan_wine_consumption_date)).getText().toString());
                    Date date = new Date();
                    String wine_added_date = date.toString();
                    wine_added_date = wine_added_date.substring(7, 10) + " " + wine_added_date.substring(4, 7) + " " + wine_added_date.substring(24, 28);
                    int id = -1;
                    if(!Singleton.getInstance().ids_dispo.isEmpty()){
                        id = Singleton.getInstance().ids_dispo.get(0);
                    }
                    new_wine = new Wine(photo, id, wine_name, wine_year, wine_quantity, wine_country, wine_region, wine_consumption_date, wine_added_date, wine_category, id);
                    Singleton.log4Me("New Wine added : " + new_wine.toString(), "ScanWineFragment : AddButton.onClick :");
                    Singleton.getInstance().wines.add(new_wine);

                    String bitmap_json = createBitmapJsonString(id);
                    Singleton.log4Me("New Bitmap added : " + bitmap_json, "ScanWineFragment : AddButton.onClick :");

                    Singleton.requestLightOnLed(id-1);
                    Singleton.requestPostAWine(new_wine.toString());
                    Singleton.requestSendBitmap(bitmap_json);
                    AlertDialog.Builder myPopUp = new AlertDialog.Builder(activity.getActivity());
                    myPopUp.setTitle("Wine Added");
                    myPopUp.setMessage("Your Wine has been added");
                    myPopUp.setNeutralButton("Merci", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(intent);
                        }
                    });
                    myPopUp.show();
                }
            }
        });

        return root;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        photo = (Bitmap) data.getExtras().get("data");
        click_image_id.setImageBitmap(photo);

    }

    public void requestCallPermission(){
        Log.i(TAG, "CALL permission has NOT been granted. Requesting permission.");
        // Camera permission has not been granted yet. Request it directly.
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},
                CODE_CAMERA);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == CODE_CAMERA) {
            // BEGIN_INCLUDE(permission_result)
            // Received permission result for camera permission.
            Log.i(TAG, "Received response for Camera permission request.");
            // Check if the only required permission has been granted
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Camera permission has been granted, preview can be displayed
                Log.i(TAG, "CAMERA permission has not been granted. Showing preview.");
            } else {
                Log.i(TAG, "CAMERA permission was NOT granted.");


            }
            // END_INCLUDE(permission_result)

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private String createBitmapJsonString(int id){
        String jsonBitmap="{\n";
        jsonBitmap+=Singleton.toJSONTransformer("id", id)+",\n";
        jsonBitmap+=Singleton.toJSONTransformer("bitmap_string", Singleton.getStringFromBitmap(photo))+"\n}";
        return jsonBitmap;
    }
}