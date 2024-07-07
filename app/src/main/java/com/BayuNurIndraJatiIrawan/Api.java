package com.BayuNurIndraJatiIrawan;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class Api extends AppCompatActivity {
    private static final String API_URL = "https://worldtimeapi.org/api/timezone/Asia/Jakarta";
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api);

        textViewResult = findViewById(R.id.textViewResult);
        Button buttonCallApi = findViewById(R.id.buttonCallApi);

        buttonCallApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchApiData();
            }
        });
    }

    private void fetchApiData() {
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, API_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Format JSON response untuk ditampilkan
                            String resultText = "Abbreviation: " + response.getString("abbreviation") + "\n"
                                    + "Client IP: " + response.getString("client_ip") + "\n"
                                    + "Datetime: " + response.getString("datetime") + "\n"
                                    + "Day of week: " + response.getInt("day_of_week") + "\n"
                                    + "Day of year: " + response.getInt("day_of_year") + "\n"
                                    + "DST: " + response.getBoolean("dst") + "\n"
                                    + "Raw offset: " + response.getInt("raw_offset") + "\n"
                                    + "Timezone: " + response.getString("timezone") + "\n"
                                    + "Unixtime: " + response.getInt("unixtime") + "\n"
                                    + "UTC datetime: " + response.getString("utc_datetime") + "\n"
                                    + "UTC offset: " + response.getString("utc_offset") + "\n"
                                    + "Week number: " + response.getInt("week_number");

                            // Tampilkan hasilnya di TextView
                            textViewResult.setText(resultText);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            textViewResult.setText("Error: JSON parsing error");
                            Log.e("ApiActivity", "Error parsing JSON", e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textViewResult.setText("Error: " + error.toString());
                Log.e("ApiActivity", "Error fetching data", error);
            }
        });

        queue.add(jsonObjectRequest);
    }
}