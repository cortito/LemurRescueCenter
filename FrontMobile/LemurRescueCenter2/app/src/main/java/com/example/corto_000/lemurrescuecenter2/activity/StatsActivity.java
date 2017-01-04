package com.example.corto_000.lemurrescuecenter2.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.corto_000.lemurrescuecenter2.R;
import com.example.corto_000.lemurrescuecenter2.business.dao.RetrieveLemurWeightValuesTask;
import com.example.corto_000.lemurrescuecenter2.model.LemurModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity implements RetrieveLemurWeightValuesTask.LemurWeightListenner{

    private LineChart lineChart;
    private int j=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        RetrieveLemurWeightValuesTask retrieveLemurWeightTask = new RetrieveLemurWeightValuesTask(this);
        retrieveLemurWeightTask.execute();

    }

    @Override
    public void onLemurWeightRetrieved(LemurModel lemurModel) {

        lineChart = (LineChart) findViewById(R.id.testCharts);

        ArrayList<String> xAxes = new ArrayList<>();
        ArrayList<Entry> yAxes = new ArrayList<>();



        double x = 0;
        xAxes.add(String.valueOf(x));
        for (int i= 0 ; i< lemurModel.getWeight().size(); i++){
            x = x + i;
            xAxes.add(String.valueOf(x));
        }

        for (String s : lemurModel.getWeight()) {
            float buf = Float.parseFloat(s);
            yAxes.add(new Entry(buf,j));
            j++;
        }

        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

        LineDataSet lineDataSet1 = new LineDataSet(yAxes,"poids(Kg)");
        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setColor(Color.BLUE);

        lineDataSets.add(lineDataSet1);

        lineChart.setTouchEnabled(true);
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);

        LineData lineData = new LineData(lineDataSets);
        lineChart.setData(lineData);









    }
}
