package com.example.myfirstapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Singleton {
    public ArrayList<Wine> wines;
    public ArrayList<Integer> ids_dispo;
    public ArrayList<Wine> wines_searched;

    public Bitmap current_bitmap;

    private static boolean already_get=false;
    public static String temp="";
    public static String hum="";

    private static final Singleton instance = new Singleton();

    private Singleton()
    {
        System.out.println("Construction du Singleton au premier appel");
        wines = new ArrayList<>();
        ids_dispo = new ArrayList<>();ids_dispo.add(1);ids_dispo.add(2);ids_dispo.add(3);ids_dispo.add(4);
        wines_searched = new ArrayList<>();

    }

    public static final Singleton getInstance()
    {
        return instance;
    }

    public static void log4Me(String message, String functionName){
        Log.d("Debug Luc", functionName + " " + message);
    }

    public static void init(){
        if(!already_get){
            new UtilsAsyncTasks.DownloadWineListAsyncTask().execute("http://10.224.0.211:8000/wine/cellar/list");
            new UtilsAsyncTasks.DownloadCellarTempHumAsyncTask().execute("http://10.224.0.211:8000/wine/cellar/temphum");
            //new UtilsAsyncTasks.GetBitMapAsyncTask().execute("http://10.224.0.211:8000/wine/cellar/bitmaps");
            already_get=true;

        }
    }

    public static void requestGetBitmap(int id){
        new UtilsAsyncTasks.GetBitMapAsyncTask().execute("http://10.224.0.211:8000/wine/cellar/bitmap?id="+id);
    }

    public static void requestPostAWine(String json){
        new UtilsAsyncTasks.POSTAWine().execute("http://10.224.0.211:8000/wine/cellar/addwine?wine="+json);
    }

    public static void requestLightOnLed(int led_position){
        new UtilsAsyncTasks.LightOnLEDAsyncTask().execute("http://10.224.0.211:8000/wine/cellar/led?num_led="+led_position);
    }

    public static void requestSendBitmap(String bitmap){
        new UtilsAsyncTasks.SendBitMapAsyncTask().execute("http://10.224.0.211:8000/wine/cellar/add?bitmap="+bitmap);
    }


    public static void requestGETTempHum(){
        new UtilsAsyncTasks.DownloadCellarTempHumAsyncTask().execute("http://10.224.0.211:8000/wine/cellar/temphum");
    }
    public static void requestDeleteWine(int id){
        new UtilsAsyncTasks.DeleteWineAsyncTask().execute("http://10.224.0.211:8000/wine/cellar/delete?id="+id);
    }



    public void parseWineJSON(String wineJSON){
        try {
            String s = "new String()";
            Log.d("Debug Luc", "la string wineJson est : "+wineJSON);
            JSONArray jsonArray = new JSONArray(wineJSON);
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Wine wine = new Wine();
                wine.id = jsonObject.getInt("id");
                wine.wine_added_date = jsonObject.getString("wine_added_date");
                wine.wine_name = jsonObject.getString("wine_name");
                wine.wine_region = jsonObject.getString("wine_region");
                wine.wine_country = jsonObject.getString("wine_country");
                wine.wine_country = jsonObject.getString("wine_country");
                wine.wine_quantity = jsonObject.getInt("wine_quantity");
                wine.wine_year_date = jsonObject.getInt("wine_year_date");
                wine.category = jsonObject.getString("category");
                wine.led_position = jsonObject.getInt("position");
                wines.add(wine);
                log4Me(wine.toString(),"new wine : ");
            }
            log4Me(s,"parseWineJSON");
        } catch (JSONException e) {
            e.printStackTrace();
            log4Me(e.getMessage(),"parseWineJSON");
        }
    }




    public static String toJSONTransformer(String name, int value){
        return '"'+name+'"'+":"+value;
    }
    public static String toJSONTransformer(String name, String value){
        return '"'+name+'"'+":"+'"'+value+'"';
    }

    public static String getStringFromBitmap(Bitmap bitmapPicture) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmapPicture.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public static Bitmap getBitmapFromString(String stringPicture) {
        String cleanImage = stringPicture.replace("data:image/png;base64,", "").replace("data:image/jpeg;base64,","");
        try {
            byte[] byteArray1;
            byteArray1 = Base64.decode(cleanImage, Base64.DEFAULT);
            System.out.println(byteArray1);
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray1, 0,
                    byteArray1.length);/* w  w  w.ja va 2 s  .  c om*/
            return bmp;

        } catch(Exception e) {
            e.printStackTrace();
            Log.d("StringToBitmapException", "la conversion de la string en bitmap à fail");
            return null;
        }
    }
    public void parseBitmapJSON(String bitmapJSON){
        try {
            String s = "has worked";
            Log.d("Debug Luc", "le json bitmapJson est : " + bitmapJSON);

            JSONArray jsonArray = new JSONArray(bitmapJSON);
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                int id = jsonObject.getInt("id");
                System.out.println("bitmap_string: " + jsonObject.getString("bitmap_string"));
                String bitmapString = jsonObject.getString("bitmap_string");

                wines.get(id - 1).wine_image = getBitmapFromString(bitmapString);
            }
            log4Me(s,"parseBitmapJSON");
        } catch (JSONException e) {
            e.printStackTrace();
            log4Me(e.getMessage(),"parseBitmapJSON");
        }
    }
}