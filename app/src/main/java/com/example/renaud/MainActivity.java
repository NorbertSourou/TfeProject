package com.example.renaud;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {
    ListView mainactivity;
    EditText nameAlert, surnameAlert, litAlert;
    ArrayList<MyItem> myitems = new ArrayList<MyItem>();
    MyAdapter adapter = null;
    OkHttpClient client = new OkHttpClient();
    FloatingActionButton fab;
    String e = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = findViewById(R.id.fab);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        // String m = "";
        try {
            Log.d("URLL", "onCreate: " + run("http://192.168.1.120:8000/api/patient"));


        } catch (IOException e) {
            e.printStackTrace();
        }
        populateListView();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nameAlert = findViewById(R.id.editTextName);
                surnameAlert = findViewById(R.id.editTextSurname);
                LayoutInflater layoutInflater = MainActivity.this.getLayoutInflater();
                final View view1 = layoutInflater.inflate(R.layout.alert_dialog, null);
                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                // new MaterialAlertDialogBuilder(MainActivity.this)
                dialogBuilder
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Log.d("&&&&&", "onClick: "+nameAlert.getText().toString());
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, null)
                        .setView(view1)
                        .setCancelable(false)
                        .show();
            }
        });

        // mainactivity = findViewById(R.id.list_view);
        // new getJson().execute();
        //mainactivity = findViewById(R.id.list_view);
       /* for (int i = 0; i < 40; i++) {
            myitems.add(new MyItem(names[i], prenom[i]));
        }*/
        //adapter = new MyAdapter(MainActivity.this, myitems);
        //mainactivity.setAdapter(adapter);

    }

    //    private class getJson extends AsyncTask<Void, Void, Void> {
//
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            OkHttpClient client = new OkHttpClient();
//            String url = "http://192.168.43.155:8000/api/patient";
//            Request request = new Request.Builder()
//                    .url(url)
//                    .build();
//            client.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    call.cancel();
//                    e.printStackTrace();
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    if (response.isSuccessful()) {
//                        final String myResponse = response.body().string();
//
//                        MainActivity.this.runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                try {
//                                    JSONArray array = new JSONArray(myResponse);
//                                    MyItem resultRow = new MyItem();
//                                    for (int i = 0; i < array.length(); i++) {
//                                        JSONObject json_data = array.getJSONObject(i);
//                                        resultRow.setName(json_data.getString("nom"));
//                                        Log.d("TAG", "onResponse: " + json_data.getString("nom"));
//                                        resultRow.setSurname(json_data.getString("prenom"));
//                                        Log.d("TAG", "onResponse: " + json_data.getString("prenom"));
//                                        myitems.add(resultRow);
//                                    }
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        });
//                    }
//                }
//            });
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            populateListView();
//            super.onPostExecute(result);
//        }
//    }
    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            String ms = responseBody.string();
            //String ms = response.body().string();
            // Log.d("URL", "run: " + ms);

            try {
                JSONArray array = new JSONArray(ms);
                MyItem resultRow = new MyItem();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject json_data = array.getJSONObject(i);
                    resultRow.setName(json_data.getString("nom"));
                    resultRow.setSurname(json_data.getString("prenom"));
                    Log.d("1234", "run: " + resultRow);
                    // resultRow.setTelephone("Lit n°" + json_data.getString("numerolit"));
                    resultRow.setTelephone("Lit n°" + 1);
//                    JSONArray obj = new JSONArray(json_data.getString("patients"));
//                    JSONObject b = obj.getJSONObject(0);
                    myitems.add(resultRow);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private void populateListView() {

        ListView mainactivity = findViewById(R.id.list_view);
        adapter = new MyAdapter(MainActivity.this, myitems);
        mainactivity.setAdapter(adapter);
        mainactivity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("id", i);
                startActivityForResult(intent, 0);
            }
        });


    }
}
