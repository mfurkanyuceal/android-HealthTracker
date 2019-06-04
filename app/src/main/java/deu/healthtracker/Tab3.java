package deu.healthtracker;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab3 extends Fragment {

    MainActivity mainActivity;
    RadioButton Male, Female;
    RadioGroup radioGroupGender;
    RadioButton Sedentary, Low_Active, Active, Very_Active;
    RadioGroup radioGroupActivity;
    TextView resultEER;
    TextView Age;
    Button buttonCalcEER;

    String Gender;
    String Activity;

    public Tab3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab3, container, false);
        Male = view.findViewById(R.id.radio_gender_male);
        Female = view.findViewById(R.id.radio_gender_female);
        radioGroupGender = view.findViewById(R.id.radio_group_gender);

        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (Male.isChecked()) {
                    Gender = "Male";
                } else {
                    Gender = "Female";
                }
            }
        });

        Sedentary = view.findViewById(R.id.radio_activity_Sedentary);
        Low_Active = view.findViewById(R.id.radio_activity_Low_Active);
        Active = view.findViewById(R.id.radio_activity_Active);
        Very_Active = view.findViewById(R.id.radio_activity_Very_Active);
        radioGroupActivity = view.findViewById(R.id.radio_group_activity);

        radioGroupActivity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (Sedentary.isChecked()) {
                    Activity = "Sedentary";
                } else if (Low_Active.isChecked()) {
                    Activity = "Low Active";
                } else if (Active.isChecked()) {
                    Activity = "Active";
                } else {
                    Activity = "Very Active";
                }
            }
        });

        Age = view.findViewById(R.id.editTextAge);


        buttonCalcEER = view.findViewById(R.id.buttonCalcEer);
        resultEER = view.findViewById(R.id.textViewResultEER);
        if (!mainActivity.getDb().getPerson().getBirthDate().equalsIgnoreCase("01/01/2000")) {
            Age.setText(String.valueOf(mainActivity.getDb().getPerson().getAge()));
        }

        buttonCalcEER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(controlEmptyInput()) {

                    Person person = mainActivity.getDb().getPerson();
                    person.setActivity(Activity);
                    person.setSex(Gender);
                    person.setAge(Integer.valueOf(Age.getText().toString()));
                    mainActivity.getDb().updatePerson(1,person);
                    int age = Integer.parseInt(Age.getText().toString());
                    if (age < 19 ) {
                        Toast.makeText(getContext(), "Your age is less than 19!", Toast.LENGTH_SHORT).show();
                    }
                    else if(mainActivity.getDb().getPerson().getHeight()==0&&mainActivity.getDb().getPerson().getWeight()==0){
                        Toast.makeText(getContext(), "Please enter your information", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        resultEER.setText(String.valueOf(mainActivity.defaultEER.calculateEER(mainActivity.getDb().getPerson())));
                    }

                }
            }
        });

        return view;
    }


    public boolean controlEmptyInput(){
        if(radioGroupGender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getContext(), "You did not select a gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (radioGroupActivity.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getContext(), "You did not select a activity", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (Age.getText().toString().matches("")) {
            Toast.makeText(getContext(), "You did not enter a Age", Toast.LENGTH_SHORT).show();
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
