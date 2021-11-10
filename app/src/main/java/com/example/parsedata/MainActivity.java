package com.example.parsedata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    RequestQueue queue;

    String url = "https://www.google.com";
    String apiUrl = "https://jsonplaceholder.typicode.com/todos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);

        getJsonArrayRequest();

        getString(queue);
    }

    private void getJsonArrayRequest() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, apiUrl, null,
                response ->{
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            Log.d("JSON", "onCreate: "+ jsonObject.getInt("id"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                , error ->
                    Log.d("JSON", "OnCreate: Failed!")
                );

        queue.add(jsonArrayRequest);
    }

    private void getString(RequestQueue queue) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            //display the contents of our url
            Log.d("Main","onCreate: "+ response.substring(0,500));
        }, error -> {
            Log.d("Main","onError: "+ "Failed to get info!");
        });

        queue.add(stringRequest);
    }
}