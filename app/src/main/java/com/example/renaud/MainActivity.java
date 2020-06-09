package com.example.renaud;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

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
    ArrayList<MyItem> myitems = new ArrayList<>();
    MyAdapter adapter = null;
    OkHttpClient client = new OkHttpClient();
    FloatingActionButton fab;
    //  String[] prenom = {"Alexis", "Quentin", " Valentin", "Bastien", "Antoine", "Geoffrey", "Jordan", "Tristan", "Steven", "Jason", "Jimmy", "Lucas", "Théo", "Baptiste", "Axel", "Jessy", "Arthur", "Simon", "Louis", "Gaétan", "Florent", "Michael", "Christophe", "Benoît", "Jérôme", "Stéphane", "Arnaud", "Frédéric", "Laurent", "Ludovic", "Aurélien", "Cédric", "Jean", "Marc", "Gregory", "Olivier", "Fabien", "Loïcé", "Yannick", "Damien"};
    // String[] names = {"Martin", "Bernard", "Thomas", "Petit", "Robert", "Richard", "Durand ", "Dubois", "Moreau", "Laurent", "Simon", "Michel", "Lefebvre", "Leroy", "Roux", "David", "Bertrand", "Morel", "Fournier", "Girard", "Bonnet", "Dupont", "Lambert", "Fontaine", "Rousseau", "Vincent", "Muller", "Lefevre", "Faure", "Andre", "Mercier", "Blanc", "Guerin", "Boyer", "Garnier", "Chevalier", "Francois", "Legrand", "Gauthier", "Garcia"};
    ProgressBar progressBar;


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
            Log.d("URLL", "onCreate: " + run("http://192.168.43.148:8000/api/patient"));


        } catch (IOException e) {
            e.printStackTrace();
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater=MainActivity.this.getLayoutInflater();
                final View view = layoutInflater.inflate(R.layout.alert_dialog, null);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                // new MaterialAlertDialogBuilder(MainActivity.this)
                dialogBuilder.setMessage("Successfully added task")
                        .setTitle("Ajouter un patient")
                        .setPositiveButton(android.R.string.ok, null)
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
        populateListView();
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
                    Log.d("TAG", "onResponse: " + json_data.getString("nom"));
                    resultRow.setSurname(json_data.getString("prenom"));
                    resultRow.setTelephone("Lit n°" + json_data.getString("numerolit"));
//                    JSONArray obj = new JSONArray(json_data.getString("patients"));
//                    JSONObject b = obj.getJSONObject(0);
//                    Log.d("TAG11", "onResponse: " + b.getString("humidite"));
                    myitems.add(resultRow);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return ms;
        }
    }

    private void populateListView() {
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        //   progressDialog.setMessage("Loading");
        //  progressDialog.show();


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
