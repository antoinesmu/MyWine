package com.example.myfirstapp;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Post {

    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");

    private static final OkHttpClient client = new OkHttpClient();




    public static void doPost(Wine wine) throws Exception {

        JSONObject jsonObject = new JSONObject();

        RequestBody formbody = new FormBody.Builder().add("wine", wine.toString()).build();

        String postBody = "wine:{\n"
                + "'id':"+",\n"+
                "'wine_name':"+",\n"+
                "'wine_year_date':"+",\n"+
                "'wine_region':"+",\n"+
                "'wine_region':"+",\n"+
                "'wine_consuming_date':"+",\n"+
                "''category'':"+",\n"+
                "''position'':"+",\n"+
                "'wine_country':"+"}";

        Request request = new Request.Builder()
                .url("http://10.224.0.211:8000/wine")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println(response.body().string());
        }
    }

}
