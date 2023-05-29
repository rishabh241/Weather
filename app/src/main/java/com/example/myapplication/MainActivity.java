package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView city,temptext,desc,humid,press,wind,direction;
    ProgressBar pg;
    ArrayList<String> arr = new ArrayList<>();
    public class DownloadTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream in = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();

                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                String weatherInfo = jsonObject.getString("weather");
                String tempInfo = jsonObject.getString("main");
                String name = jsonObject.getString("name");
                String windd = jsonObject.getString("wind");
                JSONArray jsonArray = new JSONArray(weatherInfo);
                String message = "";
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String desc = jsonObject1.getString("description");
//                    String pressure = jsonObject1.getString("pressure");
//                    String humidity = jsonObject1.getString("humidity");
                    message += desc;
                }
                JSONObject jsonObject2= new JSONObject(tempInfo);
                String temp1 = jsonObject2.getString("temp");
                String humid1 = jsonObject2.getString("humidity");
                String pressure = jsonObject2.getString("pressure");
                Double temp= Double.parseDouble(temp1);
                temp-=273.15;
                String string = String.format("%.0f",temp);

                JSONObject jsonObject3 = new JSONObject(windd);
                String speed = jsonObject3.getString("speed");
                String deg = jsonObject3.getString("deg");
                Double speed1= Double.parseDouble(speed);
                speed1*=3.6;
                String string1 = String.format("%.0f",speed1);
                if(pg!=null){
                    pg.setVisibility(View.GONE);
                }

                temptext.setText(string+"°C");
                desc.setText(message);
                humid.setText(humid1+"%");
                press.setText(pressure+"hPa");
                city.setText(name);
                wind.setText(string1+"km/h");
                direction.setText(deg+"°");

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Not Exist", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        city = findViewById(R.id.cityName);
        temptext = findViewById(R.id.temp);
        desc = findViewById(R.id.weatherCon);
        humid = findViewById(R.id.humidity);
        press = findViewById(R.id.pressure);
        wind = findViewById(R.id.wind);
        direction = findViewById(R.id.direction);
        pg = findViewById(R.id.pgb);
         arr.add("Aligarh");
         arr.add("Agra");
         arr.add("Noida");
         arr.add("Delhi");
//         task.execute("https://api.openweathermap.org/data/2.5/weather?q=aligarh&appid=656b788b39bfa362d8685f16e450eb0e");


        String city =getIntent().getStringExtra("key");
        String city1 =getIntent().getStringExtra("city");
        if(city!=null && city1==null){
            DownloadTask task1 = new DownloadTask();
            task1.execute("https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=656b788b39bfa362d8685f16e450eb0e");
        }else{
            DownloadTask task= new DownloadTask();
            task.execute("https://api.openweathermap.org/data/2.5/weather?q=Delhi&appid=656b788b39bfa362d8685f16e450eb0e");

        }

        if(city1!=null ){
            DownloadTask task1 = new DownloadTask();
            task1.execute("https://api.openweathermap.org/data/2.5/weather?q="+city1+"&appid=656b788b39bfa362d8685f16e450eb0e");
            arr.add(city1);
        }else if(city==null){
            DownloadTask task= new DownloadTask();
            task.execute("https://api.openweathermap.org/data/2.5/weather?q=Delhi&appid=656b788b39bfa362d8685f16e450eb0e");

        }


    }
    public void getWeather(View view){
        Intent intent = new Intent(getApplicationContext(),search.class);
//        intent.putExtra("arr",arr);
        startActivity(intent);
        finish();
    }

}