package com.example.renaud;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Main2Activity extends AppCompatActivity {
    //private LineChart mChart;
//    private Thread thread;
//    private boolean plotData = true;
    MyItem item = new MyItem();
    OkHttpClient client = new OkHttpClient();
    TextView lit, temperature_ambiante, humidity, temperature_corporelle, mouvement;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        back = findViewById(R.id.back);
        lit = findViewById(R.id.lit1);
        temperature_ambiante = findViewById(R.id.temperature_ambiante);
        humidity = findViewById(R.id.humidity);
        temperature_corporelle = findViewById(R.id.temperature_corporelle);
        mouvement = findViewById(R.id.mouvement);

        int i = getIntent().getExtras().getInt("id");
//        mChart = findViewById(R.id.chart1);
//        mChart.setOnChartValueSelectedListener(this);
//        mChart.getDescription().setEnabled(true);
//        mChart.getDescription().setText("Heart Rate");
//        mChart.getDescription().setTextColor(Color.argb(255, 38, 153, 251));
//        mChart.getDescription().setTextSize(15);
//        mChart.setDrawBorders(false);
//        mChart.setTouchEnabled(false);
//        mChart.setDragEnabled(false);
//        mChart.setScaleEnabled(false);
//        mChart.setDrawGridBackground(false);
//        mChart.setPinchZoom(false);
        // mChart.setBackgroundColor(Color.WHITE);

//        LineData data = new LineData();
//        data.setValueTextColor(Color.WHITE);
//        data.setDrawValues(false);
//        mChart.setData(data);
//
//
//        XAxis xAxis = mChart.getXAxis();
//        xAxis.setEnabled(true);
//        xAxis.setDrawLabels(false);
//        YAxis yAxis = mChart.getAxisRight();
//        yAxis.setEnabled(false);
//
//        YAxis yAxis1 = mChart.getAxisLeft();
//        yAxis1.setEnabled(true);
//        yAxis1.setDrawLabels(false);
        //yAxis1.setEnabled(false);
        //mChart.setData(data);


        // Legend legend = mChart.getLegend();
        // legend.setForm(LegendF);
        //legend.setEnabled(false);
        // mChart.getAxisLeft().setDrawGridLines(false);
        //    setData(100, -17);
//        try {
//            Log.d("URLL", "onCreate: " + run("http://192.168.43.148:8000/api/patient"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            JSONArray array = new JSONArray(run("http://192.168.43.148:8000/api/patient"));
            //JSONObject json_data = array.getJSONObject(0);
            //lit.setText("Lit n°" + json_data.getString("numerolit"));
            // JSONObject obj = new JSONObject(json_data);
            //JSONObject b = obj.getJSONObject(0);

            //  JSONObject obj = new JSONObject(json_data.getString("sensor"));
            //JSONObject b = obj.getJSONObject(0);
            //Log.d("tag11", "onCreate: " + obj);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
        //feedMultiple();
    }

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            String ms = responseBody.string();


            return ms;
        }
    }


//    private void addEntry() {
//        LineData data = mChart.getData();
//
//        if (data != null) {
//
//            ILineDataSet set = data.getDataSetByIndex(0);
//            // set.addEntry(...); // can be called as well
//
//            if (set == null) {
//                set = createSet();
//                data.addDataSet(set);
//            }
//
//            data.addEntry(new Entry(set.getEntryCount(), ((float) (Math.random() * (-40)) + 20f)), 0);
//            //Log.d("entry", "addEntry: " + set.getEntryCount());
//            data.notifyDataChanged();
//
//            // let the chart know it's data has changed
//            mChart.notifyDataSetChanged();
//
//            // limit the number of visible entries
//            mChart.setVisibleXRangeMaximum(60);
//            // mChart.setVisibleYRange(30,70, YAxis.AxisDependency.LEFT);
//
//            // move to the latest entry
//            mChart.moveViewToX(data.getEntryCount());
//
//            // this automatically refreshes the chart (calls invalidate())
//            // chart.moveViewTo(data.getXValCount()-7, 55f,
//            // AxisDependency.LEFT);
//        }
//    }

//    private ILineDataSet createSet() {
//        LineDataSet set = new LineDataSet(null, "Dynamic Data");
//        set.setAxisDependency(YAxis.AxisDependency.LEFT);
//        set.setColor(Color.BLUE);
//        //set.setCircleColor(Color.WHITE);
//        set.setLineWidth(1f);
//        //set.setCircleRadius(4f);
//        //set.setDrawFilled(true);
//        set.setDrawCircles(false);
//        set.setFillAlpha(255);
//
//        //  set.setFillColor(Color.argb(255, 38, 153, 251));
//        //set.setHighLightColor(Color.rgb(244, 117, 117));
//        set.setValueTextColor(Color.WHITE);
//        // set.setValueTextSize(9f);
//        //set.setDrawValues(false);
//        return set;
//    }


//    private void setData(int count, float range) {
//        ArrayList<Entry> yvals = new ArrayList<>();
//        for (int i = 0; i < count; i++) {
//            float val = (float) ((Math.random() * range) + 10);
//            yvals.add(new Entry(i, val));
//
//        }
//        Log.d("valeur", "setData: " + yvals);
////        ArrayList<Entry> yvals1 = new ArrayList<>();
////        for (int i = 0; i < count; i++) {
////            float val = (float) ((Math.random() * range) + 50);
////            yvals1.add(new Entry(i, val));
////        }
//        LineDataSet set1, set2;
//        set1 = new LineDataSet(yvals, "Dataset");
//        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
//        //  set1.setColor(Color.RED);
//        set1.setDrawCircles(false);
//        set1.setLineWidth(1f);
//        set1.setFillAlpha(255);
//        set1.setDrawFilled(true);
//        set1.setDrawValues(false);
//        set1.setFillColor(Color.argb(255, 38, 153, 251));
//
//
////        set2 = new LineDataSet(yvals1, "Dataset1");
////        set2.setAxisDependency(YAxis.AxisDependency.LEFT);
////        set2.setColor(Color.RED);
////        set2.setDrawCircles(false);
////        set2.setLineWidth(1f);
////        set2.setFillAlpha(255);
////        set2.setDrawFilled(true);
////        set2.setFillColor(Color.argb(255, 38, 153, 251));
//
//        LineData data = new LineData(set1);
//        data.setDrawValues(false);
//        mChart.setData(data);
//    }

//    private void feedMultiple() {
//
//        if (thread != null)
//            thread.interrupt();
//
//        final Runnable runnable = new Runnable() {
//
//            @Override
//            public void run() {
//                addEntry();
//            }
//        };
//
//        thread = new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                for (int i = 0; ; i++) {
//
//                    // Don't generate garbage runnables inside the loop.
//                    runOnUiThread(runnable);
//
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//
//        thread.start();
//    }

//    @Override
//    public void onValueSelected(Entry e, Highlight h) {
//
//    }
//
//    @Override
//    public void onNothingSelected() {
//
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        if (thread != null) {
//            thread.interrupt();
//        }
//    }
}
