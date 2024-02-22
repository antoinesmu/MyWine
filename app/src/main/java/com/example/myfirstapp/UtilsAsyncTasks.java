package com.example.myfirstapp;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UtilsAsyncTasks {
    public static class DownloadWineListAsyncTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = null;
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setRequestMethod("GET");
                connection.connect();
                InputStream stream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder result = new StringBuilder();
                String line = "";
                int counter = 0;
                while ((line = reader.readLine())!= null) {

                    result.append(line);
                    counter++;
                    publishProgress(counter/10);

                }
                return result.toString();
            } catch (IOException e) {

                return e.getMessage();
            }

        }

        @Override
        protected void onPostExecute(String result){
            Singleton.log4Me(result,"onPostExecute.GetList");
            Singleton.getInstance().parseWineJSON(result);

        }

        @Override
        protected void onPreExecute(){

        }

        @Override
        protected void onProgressUpdate(Integer... progress) {

        }

    }
    public static class DownloadCellarTempHumAsyncTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = null;
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setRequestMethod("GET");
                connection.connect();
                InputStream stream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder result = new StringBuilder();
                String line = "";
                while ((line = reader.readLine())!= null) {
                    result.append(line);
                }
                return result.toString();
            } catch (IOException e) {

                return e.getMessage();
            }

        }

        @Override
        protected void onPostExecute(String result){
            Singleton.log4Me(result,"onPostExecute.TempHum");
            Singleton.getInstance().temp = result.substring(0,4);
            Singleton.getInstance().hum = result.substring(5,7);
        }

        @Override
        protected void onPreExecute(){

        }

        @Override
        protected void onProgressUpdate(Integer... progress) {

        }

    }

    public static class LightOnLEDAsyncTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = null;
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setRequestMethod("GET");
                connection.connect();
                InputStream stream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder result = new StringBuilder();
                String line = "";
                int counter = 0;
                while ((line = reader.readLine())!= null) {

                    result.append(line);
                    counter++;
                    publishProgress(counter/10);

                }
                return result.toString();
            } catch (IOException e) {

                return e.getMessage();
            }

        }

        @Override
        protected void onPostExecute(String result){
            Singleton.log4Me(result,"onPostExecute.Light");

        }

        @Override
        protected void onPreExecute(){

        }

        @Override
        protected void onProgressUpdate(Integer... progress) {

        }

    }




    public static class GetBitMapAsyncTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = null;
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setRequestMethod("GET");
                connection.connect();
                InputStream stream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder result = new StringBuilder();
                String line = "";
                int counter = 0;
                while ((line = reader.readLine())!= null) {

                    result.append(line);
                    counter++;
                    publishProgress(counter/10);

                }
                return result.toString();
            } catch (IOException e) {

                return e.getMessage();
            }

        }

        @Override
        protected void onPostExecute(String result){
            Singleton.log4Me(result,"onPostExecute.GETBitmap");
            Singleton.getInstance().parseBitmapJSON(result);

        }

        @Override
        protected void onPreExecute(){

        }

        @Override
        protected void onProgressUpdate(Integer... progress) {

        }

    }
    public static class SendBitMapAsyncTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = null;
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setRequestMethod("GET");
                connection.connect();
                InputStream stream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder result = new StringBuilder();
                String line = "";
                int counter = 0;
                while ((line = reader.readLine())!= null) {

                    result.append(line);
                    counter++;
                    publishProgress(counter/10);

                }
                return result.toString();
            } catch (IOException e) {

                return e.getMessage();
            }

        }

        @Override
        protected void onPostExecute(String result){
            Singleton.log4Me(result,"onPostExecute.SendBitmap");
            Singleton.getInstance().parseBitmapJSON(result);

        }

        @Override
        protected void onPreExecute(){

        }

        @Override
        protected void onProgressUpdate(Integer... progress) {

        }

    }
    public static class DeleteWineAsyncTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = null;
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setRequestMethod("GET");
                connection.connect();
                InputStream stream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder result = new StringBuilder();
                String line = "";
                int counter = 0;
                while ((line = reader.readLine())!= null) {

                    result.append(line);
                    counter++;
                    publishProgress(counter/10);

                }
                return result.toString();
            } catch (IOException e) {

                return e.getMessage();
            }

        }

        @Override
        protected void onPostExecute(String result){
            Singleton.log4Me(result,"onPostExecute.DeleteWine");

        }

        @Override
        protected void onPreExecute(){

        }

        @Override
        protected void onProgressUpdate(Integer... progress) {

        }

    }

    public static class POSTAWine extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = null;
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setRequestMethod("GET");
                connection.connect();
                InputStream stream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder result = new StringBuilder();
                String line = "";
                int counter = 0;
                while ((line = reader.readLine())!= null) {

                    result.append(line);
                    counter++;
                    publishProgress(counter/10);

                }
                return result.toString();
            } catch (IOException e) {

                return e.getMessage();
            }

        }

        @Override
        protected void onPostExecute(String result){
            Singleton.log4Me(result,"onPostExecute.PostWine");

        }

        @Override
        protected void onPreExecute(){

        }

        @Override
        protected void onProgressUpdate(Integer... progress) {

        }

    }
}
