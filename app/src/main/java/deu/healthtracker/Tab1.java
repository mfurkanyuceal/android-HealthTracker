package deu.healthtracker;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1 extends Fragment {

    MainActivity mainActivity;
    EditText Name;
    EditText Surname;
    EditText Date;
    EditText Height;
    EditText Weight;
    Button personInfoSendButton;
    String Gender;
    RadioButton Male, Female;
    RadioGroup radioGroup;

    public Tab1() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1,
                container, false);



        // Inflate the layout for this fragment
        Name = view.findViewById(R.id.editTextName);
        Surname = view.findViewById(R.id.editTextSurname);
        Date = view.findViewById(R.id.editTextDate3);
        Height = view.findViewById(R.id.editTextHeight);
        Weight = view.findViewById(R.id.editTextWeight);

        personInfoSendButton = view.findViewById(R.id.button);


        Male = view.findViewById(R.id.radio_gender_male);
        Female = view.findViewById(R.id.radio_gender_female);
        radioGroup = view.findViewById(R.id.radio_group_gender);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (Male.isChecked()) {
                    Gender = "Male";
                } else {
                    Gender = "Female";
                }
            }
        });
//veriler default sa edittextleri doldurmuyor.

        if(!mainActivity.getDb().getPerson().getName().equalsIgnoreCase("DefaultName")){
            Name.setText(String.valueOf(mainActivity.getDb().getPerson().getName()));
            Surname.setText(String.valueOf(mainActivity.getDb().getPerson().getSurname()));
            Date.setText(String.valueOf(mainActivity.getDb().getPerson().getBirthDate()));
            Height.setText(String.valueOf(mainActivity.getDb().getPerson().getHeight()));
            Weight.setText(String.valueOf(mainActivity.getDb().getPerson().getWeight()));
            if (String.valueOf(mainActivity.getDb().getPerson().getSex()).equalsIgnoreCase("Male")){
                Male.setChecked(true);
            }else Female.setChecked(true);
        }



        

        personInfoSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(controlEmptyInput()){
                    Person newPerson = new Person(Name.getText().toString(), Surname.getText().toString(), Date.getText().toString(), Integer.parseInt(Height.getText().toString()), Double.parseDouble(Weight.getText().toString()), Gender, null);
                    mainActivity.getDb().updatePerson(1,newPerson);
                    Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();

                }


                }
        });

        return view;

    }

    public boolean controlEmptyInput(){
        if (Name.getText().toString().matches("")) {
            Toast.makeText(getContext(), "You did not enter a Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (Surname.getText().toString().matches("")) {
            Toast.makeText(getContext(), "You did not enter a Surname", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (Date.getText().toString().matches("")) {
            Toast.makeText(getContext(), "You did not enter a Date", Toast.LENGTH_SHORT).show();
            return false;


        }
        else if (Height.getText().toString().matches("")) {
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
