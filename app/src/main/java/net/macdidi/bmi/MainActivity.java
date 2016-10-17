package net.macdidi.bmi;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.math.BigDecimal;


public class MainActivity extends ActionBarActivity {

    private Context context;
    RadioButton male, female;
    EditText height, weight;
    Button calculate;
    RadioGroup sexual;
    String sex;
    int age;

    private Spinner sprAge;
    private Spinner sprHeight;
    private Spinner sprWeight;

    private ArrayAdapter<String> adpAge;
    private ArrayAdapter<CharSequence> adpHeight;
    private ArrayAdapter<CharSequence> adpWeight;


    private String heightKind = "";
    private String weightKind = "";
    private String[] ageAry = new String[19];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        final bmiCalculate bmiCalte = new bmiCalculate();
        for(int i = 0; i < 19; i++){
            if(i == 18){
                ageAry[i] = "Over 18.";
            }else{
                ageAry[i] = String.valueOf(i);
            }

        }
        calculate = (Button)this.findViewById(R.id.calculatorBtn);
        height    = (EditText)this.findViewById(R.id.height);
        weight    = (EditText)this.findViewById(R.id.weight);
        sexual    = (RadioGroup)this.findViewById(R.id.sexual);
        male      = (RadioButton)this.findViewById(R.id.rdoBtnMale);
        female    = (RadioButton)this.findViewById(R.id.rdoBtnFemale);

        sprHeight = (Spinner)findViewById(R.id.sprHeight);
        adpHeight = ArrayAdapter.createFromResource(this,R.array.height, android.R.layout.simple_spinner_item);
        sprHeight.setAdapter(adpHeight);

        sprWeight = (Spinner)findViewById(R.id.sprWeight);
        adpWeight = ArrayAdapter.createFromResource(this,R.array.weight, android.R.layout.simple_spinner_item);
        sprWeight.setAdapter(adpWeight);

        sprAge    = (Spinner)findViewById(R.id.sprAge);
        adpAge = new ArrayAdapter<>(this, R.layout.spinner, ageAry);
        adpAge.setDropDownViewResource(R.layout.spinner);
        sprAge.setAdapter(adpAge);

        if(male.isChecked()){
            sex = "Male";
        }else if(female.isChecked()){
            sex = "Female";
        }

        sprAge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                age = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sexual.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rdoBtnMale){
                    sex = "Male";
                }else if(checkedId == R.id.rdoBtnFemale){
                    sex = "Female";
                }
            }
        });

        sprHeight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                heightKind = String.valueOf(parent.getSelectedItemId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sprWeight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                weightKind = String.valueOf(parent.getSelectedItemId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BigDecimal result = bmiCalte.transWeight(weight.getText().toString(), weightKind).divide(bmiCalte.transHeight(height.getText().toString(), heightKind).pow(2),2,BigDecimal.ROUND_HALF_UP);
                ageSexBMI bmiRlt = new ageSexBMI(context);
                String status = bmiRlt.getBMIRlt(age, sex, result.doubleValue());
                Toast.makeText(v.getContext().getApplicationContext(), "BMI = " + result + ", " + status, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
