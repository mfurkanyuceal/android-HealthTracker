package deu.healthtracker;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab2 extends Fragment {

    MainActivity mainActivity;
    EditText Height,Weight;
    TextView resultBmi;
    Button buttonCalcBmi;


    public Tab2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);
        // Inflate the layout for this fragment
        mainActivity.defaultBmi.calculateBMI(mainActivity.defaultPerson);
        Height = view.findViewById(R.id.editTextHeight2);
        Weight = view.findViewById(R.id.editTextWeight2);
        buttonCalcBmi = view.findViewById(R.id.buttonCalcBmi);
        resultBmi = view.findViewById(R.id.textViewResultBmi);

        if(mainActivity.getDb().getPerson().getHeight()!=0){
            Height.setText(String.valueOf(mainActivity.getDb().getPerson().getHeight()));
        }
        if(mainActivity.getDb().getPerson().getWeight()!=0){
            Weight.setText(String.valueOf(mainActivity.getDb().getPerson().getWeight()));
        }

        buttonCalcBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (controlEmptyInput()) {

                Person person =mainActivity.getDb().getPerson();
                person.setHeight(Integer.parseInt(Height.getText().toString()));
                person.setWeight(Double.parseDouble(Weight.getText().toString()));
                mainActivity.getDb().updatePerson(1,person);
                BMI bmi= new BMI();
                Double bmiCalculated = bmi.calculateBMI(Integer.parseInt(Height.getText().toString()),Double.parseDouble(Weight.getText().toString()));
                DecimalFormat df = new DecimalFormat("#.##");
                resultBmi.setText(df.format(bmiCalculated)+"\n"+bmi.calculateCategory(bmiCalculated));
             }
            }
        });

        return view;
    }

    public boolean controlEmptyInput(){
        if (Height.getText().toString().matches("")) {
            Toast.makeText(getContext(), "You did not enter a Height", Toast.LENGTH_SHORT).show();
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
