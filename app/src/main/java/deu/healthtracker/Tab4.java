package deu.healthtracker;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab4 extends Fragment {


    MainActivity mainActivity;
    private LineChart lineChart;
    EditText Date,BloodPressure,BloodSugar,HeartRate,Weight;
    Button buttonSend;
    ArrayList<Entry>bloodPressure;
    ArrayList<Entry>bloodSugar;
    ArrayList<Entry>heartRate;
    ArrayList<Entry>weight;
    List<Activity> list;
    String[] xAxes;


    public Tab4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_tab4,
                container, false);

        lineChart =view.findViewById(R.id.chart);
        createChart();

        Date = view.findViewById(R.id.editTextDate3);
        BloodPressure = view.findViewById(R.id.editTextBloodPressure3);
        BloodSugar = view.findViewById(R.id.editTextBloodSugar3);
        HeartRate = view.findViewById(R.id.editTextHearthRate3);
        Weight = view.findViewById(R.id.editTextWeight3);

        buttonSend = view.findViewById(R.id.buttonChartAdd);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(controlEmptyInput()){
                    Activity activity=new Activity(Date.getText().toString(),Double.parseDouble(BloodPressure.getText().toString()),Double.parseDouble(BloodSugar.getText().toString()),Double.parseDouble(HeartRate.getText().toString()),Double.parseDouble(Weight.getText().toString()));
                    mainActivity.getDb().addActivity(activity);
                    createChart();
                    Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                    Date.getText().clear();BloodPressure.getText().clear();BloodSugar.getText().clear();HeartRate.getText().clear();Weight.getText().clear();
                }
            }
        });

        return view;
    }


    public void createChart(){
        list=mainActivity.getDb().getActivityList();


        getdataValues();


        LineDataSet lineDataSet1=new LineDataSet(bloodPressure,"Blood Pressure");
        lineDataSet1.setColor(Color.RED);
        lineDataSet1.setLineWidth(3f);
        LineDataSet lineDataSet2=new LineDataSet(bloodSugar,"Blood Sugar");
        lineDataSet2.setColor(Color.GREEN);
        lineDataSet2.setLineWidth(3f);
        LineDataSet lineDataSet3=new LineDataSet(heartRate,"Heart Rate");
        lineDataSet3.setColor(Color.BLUE);
        lineDataSet3.setLineWidth(3f);
        LineDataSet lineDataSet4=new LineDataSet(weight,"Weight");
        lineDataSet4.setColor(Color.YELLOW);
        lineDataSet4.setLineWidth(3f);

        ArrayList<ILineDataSet>dataSets=new ArrayList<>();

        dataSets.add(lineDataSet1);
        dataSets.add(lineDataSet2);
        dataSets.add(lineDataSet3);
        dataSets.add(lineDataSet4);

        LineData data=new LineData(dataSets);

        lineChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxes));
        Description description = lineChart.getDescription();
        description.setText("Activity Line Chart");

        lineChart.setData(data);
        lineChart.animateXY(1000, 1000);
        lineChart.invalidate();



    }


    private void getdataValues(){

        bloodPressure=new ArrayList<>();
        bloodSugar=new ArrayList<>();
        heartRate=new ArrayList<>();
        weight=new ArrayList<>();

        xAxes=new String[list.size()];

        for (int i = 0; i <list.size() ; i++) {
            float pressure=Float.parseFloat(Double.toString(list.get(i).getBloodPressure()));
            float sugar=Float.parseFloat(Double.toString(list.get(i).getBloodSugar()));
            float heartrate=Float.parseFloat(Double.toString(list.get(i).getHeartRate()));
            float weightt=Float.parseFloat(Double.toString(list.get(i).getWeight()));
            xAxes[i]=list.get(i).getDate();

            bloodPressure.add(new Entry(i,pressure));
            bloodSugar.add(new Entry(i,sugar));
            heartRate.add(new Entry(i,heartrate));
            weight.add(new Entry(i,weightt));
        }

    }

    public boolean controlEmptyInput(){
        if (Date.getText().toString().matches("")) {
            Toast.makeText(getContext(), "You did not enter a Date", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (BloodPressure.getText().toString().matches("")) {
            Toast.makeText(getContext(), "You did not enter a BloodPressure", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (BloodSugar.getText().toString().matches("")) {
            Toast.makeText(getContext(), "You did not enter a BloodSugar", Toast.LENGTH_SHORT).show();
            return false;


        }
        else if (HeartRate.getText().toString().matches("")) {
            Toast.makeText(getContext(), "You did not enter a HeartRate", Toast.LENGTH_SHORT).show();
            return false;

        }
        else if (Weight.getText().toString().matches("")) {
            Toast.makeText(getContext(), "You did not enter a Weight", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mainActivity = (MainActivity) context;
        }
    }
    @Override
    public void onDetach() {
        mainActivity = null;
        super.onDetach();
    }
}