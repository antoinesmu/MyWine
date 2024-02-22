package com.example.myfirstapp;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import java.util.ArrayList;


public class Wine {

    public Bitmap wine_image;
    public int id;
    public String wine_name;
    public int wine_year_date;
    public int wine_quantity;
    public String wine_country;
    public String wine_region;
    public int wine_consuming_date;
    public String wine_added_date;
    public String category;
    public int led_position;

    public Wine(Bitmap wine_image, int id, String wine_name, int wine_year_date, int wine_quantity, String wine_country, String wine_region, int wine_consuming_date, String wine_added_date, String category, int led_position) {
        this.wine_image = wine_image;
        this.id = id;
        this.wine_name = wine_name;
        this.wine_year_date = wine_year_date;
        this.wine_quantity = wine_quantity;
        this.wine_country = wine_country;
        this.wine_region = wine_region;
        this.wine_consuming_date = wine_consuming_date;
        this.wine_added_date = wine_added_date;
        this.category = category;
        this.led_position = led_position;
    }


    public Wine(){}
    public Wine(String name){
        this.wine_name=name;
    }

    @NonNull
    @Override
    public String toString() {
        return toJSONString(listJSONElementsCreator());
    }



    private String toJSONTransformer(String name, int value){
        return '"'+name+'"'+":"+value;
    }
    private String toJSONTransformer(String name, String value){
        return '"'+name+'"'+":"+'"'+value+'"';
    }

    private ArrayList<String> listJSONElementsCreator(){
        ArrayList<String> list = new ArrayList<>();
        list.add(toJSONTransformer("id", this.id));
        list.add(toJSONTransformer("wine_name", this.wine_name));
        list.add(toJSONTransformer("wine_year_date", this.wine_year_date));
        list.add(toJSONTransformer("wine_quantity",this.wine_quantity));
        list.add(toJSONTransformer("wine_country", this.wine_country));
        list.add(toJSONTransformer("wine_region", this.wine_region));
        list.add(toJSONTransformer("wine_consuming_date", this.wine_consuming_date));
        list.add(toJSONTransformer("wine_added_date", this.wine_added_date));
        list.add(toJSONTransformer("category", this.category));
        list.add(toJSONTransformer("position", this.led_position));
        return list;
    }

    private String toJSONString(ArrayList<String> list){
        int i=0;
        String json_string="{";
        for (String json_element: list) {
            json_string+=json_element;
            i++;
            if(list.size()!=i){
                json_string+=",\n";
            }else{
                json_string+="\n";
            }
        }
        json_string+="}";
        return json_string;
    }
}
