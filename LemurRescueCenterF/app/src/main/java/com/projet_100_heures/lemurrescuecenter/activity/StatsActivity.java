package com.projet_100_heures.lemurrescuecenter.activity;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.projet_100_heures.lemurrescuecenter.R;
import com.projet_100_heures.lemurrescuecenter.business.dao.RetrieveLemurWeightTask;
import com.projet_100_heures.lemurrescuecenter.model.LemurModel;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity implements RetrieveLemurWeightTask.LemurWeightListenner {

    private LineChart lineChart;
    private int j=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);

        RetrieveLemurWeightTask retrieveLemurWeightTask = new RetrieveLemurWeightTask(this);
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
            yAxes.add(new Entry(j,buf));
            j++;
        }

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Janvier");
        labels.add("Février");
        labels.add("Mars");
        labels.add("Avril");
        labels.add("Mai");
        labels.add("Juin");
        labels.add("Juillet");
        labels.add("Aout");
        labels.add("Septembre");
        labels.add("Octobre");
        labels.add("Novembre");
        labels.add("Décembre");

        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

        LineDataSet lineDataSet1 = new LineDataSet(yAxes,"poids(Kg)");

        lineDataSet1.setColors(ColorTemplate.COLORFUL_COLORS); //
        lineDataSet1.setColor(Color.BLACK);
        lineDataSet1.setCircleColor(Color.BLACK);
        lineDataSet1.setLineWidth(1f);
        lineDataSet1.setCircleRadius(3f);
        lineDataSet1.setDrawCircleHole(true);
        lineDataSet1.setValueTextSize(9f);
        lineDataSet1.setDrawFilled(true);
        lineDataSet1.setFormLineWidth(1f);
        lineDataSet1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
        lineDataSet1.setFormSize(15.f);

        lineChart.animateY(2000);
        lineChart.setTouchEnabled(true);
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);

        lineDataSets.add(lineDataSet1);

        LineData lineData = new LineData(lineDataSets);
        lineChart.setData(lineData);
    }
}


