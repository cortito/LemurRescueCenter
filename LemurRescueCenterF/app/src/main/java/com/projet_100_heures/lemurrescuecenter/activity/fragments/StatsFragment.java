package com.projet_100_heures.lemurrescuecenter.activity.fragments;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.androidifygeeks.library.fragment.PageFragment;
import com.androidifygeeks.library.fragment.TabDialogFragment;
import com.androidifygeeks.library.iface.IFragmentListener;
import com.androidifygeeks.library.iface.ISimpleDialogListener;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.projet_100_heures.lemurrescuecenter.R;
import com.projet_100_heures.lemurrescuecenter.business.dao.RetrieveLemurWeightTask;
import com.projet_100_heures.lemurrescuecenter.model.LemurModel;

import java.util.ArrayList;

public class StatsFragment extends Fragment implements RetrieveLemurWeightTask.LemurWeightListenner, ISimpleDialogListener,IFragmentListener {

    private LineChart lineChart1;
    private LineChart lineChart2;
    private LineChart lineChart3;
    private int j=0;
    private int countdate=0;
    private int firstYear=0;
    private int secondYear=0;
    private int thirdYear=0;
    private int bufferYear=0;
    private int counter=0;
    String[][] dates = new String[50][50];
    Button addWeight;
    private Bundle bundle;
    ViewGroup contain;
    LayoutInflater layoutInflater;

    public StatsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bundle = savedInstanceState;
        contain = container;
        layoutInflater = inflater;
        return inflater.inflate(R.layout.fragment_stats, container, false);
    }

    @Override
    public void onViewCreated(View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        displayChart();
        addWeight = (Button) getActivity().findViewById(R.id.addWeightButton);
        addWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                TabDialogFragment.createBuilder(getContext(),getChildFragmentManager())
                        .setTitle("Ajout d'un poids")
                        .setTabButtonText(new CharSequence[]{"Poids"})
                        .setPositiveButtonText("Ajouter")
                        .setNegativeButtonText("Quitter")
                        .setRequestCode(43)
                        .show();
            }
        });

    }

    public void displayChart(){

        RetrieveLemurWeightTask retrieveLemurWeightTask = new RetrieveLemurWeightTask(this,getActivity());
        retrieveLemurWeightTask.execute();
    }

    @Override
    public void onLemurWeightRetrieved(LemurModel lemurModel) {

        gettYears(lemurModel);
        Log.d("tag", String.valueOf(firstYear) +" "+ String.valueOf(secondYear) +" "+ String.valueOf(thirdYear) );

        lineChart1 = (LineChart) getActivity().findViewById(R.id.chartYear1);
        lineChart2 = (LineChart) getActivity().findViewById(R.id.chartYear2);
        lineChart3 = (LineChart) getActivity().findViewById(R.id.chartYear3);
        generateLineChart(lemurModel,firstYear,lineChart1);
        generateLineChart(lemurModel,secondYear,lineChart2);
        generateLineChart(lemurModel,thirdYear, lineChart3);

    }

    public void gettYears(LemurModel lemurModel){

        for(String y : lemurModel.getWeightDate()){
            String[] dy = y.split("/");
            dates[countdate]= dy;
            countdate++;
        }

        for( int i=0 ; i<countdate;i++ ){
            firstYear = Integer.parseInt(dates[0][1]);
            bufferYear = Integer.parseInt(dates[i][1]);
            if(bufferYear != firstYear) {
                if (bufferYear > firstYear && bufferYear == firstYear + 1) {
                    secondYear = Integer.parseInt(dates[i][1]);
                } else {
                    thirdYear = Integer.parseInt(dates[i][1]);
                }
            }
        }

    }

    public void generateLineChart(LemurModel lemurModel , int year,LineChart lineChart){

        final String[] mMonths = new String[]{
                "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        };

        ArrayList<Entry> yAxes = new ArrayList<>();

        XAxis xAxis = lineChart.getXAxis();
        lineChart.getAxisLeft().setAxisMinimum(Float.parseFloat("0.00"));
        lineChart.getAxisRight().setAxisMinimum(Float.parseFloat("0.00"));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(11f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                return mMonths[(int) value % mMonths.length];
            }
        });

        String[] bufferWeight = new String[50];
        j=0;
        for (String s : lemurModel.getWeight()) {
            bufferWeight[j] = s;
            j++;
        }

        counter = 0;
        for (int i = 0 ; i< countdate ; i++){
            bufferYear = Integer.parseInt(dates[i][1]);
            if(bufferYear == year) {
                float buf = Float.parseFloat(bufferWeight[i]);
                yAxes.add(new Entry(counter, buf));
                counter ++;
            }
        }

        if(yAxes.size() == 0){
            float buf = Float.parseFloat("0.00");
            yAxes.add(new Entry(0,buf));
        }

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
        lineChart.setContentDescription("Poids année :" + year);

        lineDataSets.add(lineDataSet1);

        LineData lineData = new LineData(lineDataSets);
        lineChart.setData(lineData);

    }

    @Override
    public void onFragmentViewCreated(Fragment fragment) {

        int selectedTabPosition = fragment.getArguments().getInt(PageFragment.ARG_DAY_INDEX, 0);
        View rootContainer = fragment.getView().findViewById(R.id.root_container);
        Log.i("tag", "Position: " + selectedTabPosition);

        switch (selectedTabPosition) {
            case 0:
                // add view in container for first tab
                View tabProductDetailLayout = layoutInflater.inflate(R.layout.add_layout_dialog,(ViewGroup) rootContainer);

                EditText test = (EditText) tabProductDetailLayout.findViewById(R.id.date_add);


                break;
        }
    }

    @Override
    public void onFragmentAttached(Fragment fragment) {

    }

    @Override
    public void onFragmentDetached(Fragment fragment) {

    }

    @Override
    public void onNegativeButtonClicked(int requestCode) {

    }

    @Override
    public void onNeutralButtonClicked(int requestCode) {

    }

    @Override
    public void onPositiveButtonClicked(int requestCode) {

    }
}
