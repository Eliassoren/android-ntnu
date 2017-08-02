package com.ovinger.eliasbrattli.oving2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener{
    private final String TAG = "CalculatorActivity";
    TextView num1;
    TextView num2;
    EditText upperLimEdit1;
    EditText inputEdit1;
    Button addBtn;
    Button multiplyBtn;
    TextView ansInfo;
    TextView upperlimInfo;
    private final int requestCode1 = 1;
    private final int requestCode2 = 2;
    private int randval1 = 3;
    private int randval2 = 5;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        findAllViews();
        intent = new Intent("com.ovinger.eliasbrattli.oving2.RandActivity");
        intent.putExtra("UpperRandLimit",200);
        addBtn.setOnClickListener(this);
        multiplyBtn.setOnClickListener(this);
    }
    //C
    @Override
    public void onClick(View v){
            try {
                int input = 0;
                int correctAns = 0;
                switch (v.getId()) {
                    case R.id.multiplyBtn:
                        input = Integer.parseInt(inputEdit1.getText().toString().trim());
                        correctAns = Integer.parseInt(num1.getText().toString().trim());
                        correctAns *= Integer.parseInt(num2.getText().toString().trim());
                        Log.i(TAG,"Input: "+input);
                        if (input == correctAns) {
                            Toast.makeText(getBaseContext(), getString(R.string.correct), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getBaseContext(), getString(R.string.incorrect) + " " + correctAns, Toast.LENGTH_LONG).show();
                        }
                        insertRnd(intent);
                        break;
                    case R.id.addBtn:
                        input = Integer.parseInt(inputEdit1.getText().toString().trim());
                        correctAns = Integer.parseInt(num1.getText().toString().trim());
                        correctAns += Integer.parseInt(num2.getText().toString().trim());
                        Log.i(TAG,"Input "+input);
                        if (input == correctAns) {
                            Toast.makeText(getBaseContext(), getString(R.string.correct), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getBaseContext(), getString(R.string.incorrect) + " " + correctAns, Toast.LENGTH_LONG).show();
                        }
                        break;
                    default:
                        break;
                    }
                //D
                intent.putExtra("UpperRandLimit",Integer.parseInt(upperLimEdit1.getText().toString().trim()));
                insertRnd(intent);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    private void insertRnd(Intent intent){
        startActivityForResult(intent,requestCode1);
        startActivityForResult(intent,requestCode2);
    }
   private void findAllViews(){
       num1 = (TextView) findViewById(R.id.num1);
       num1.setEnabled(true);
       num2 = (TextView) findViewById(R.id.num2);
       num2.setEnabled(true);
       upperLimEdit1 = (EditText) findViewById(R.id.upperLimEdit);
       upperLimEdit1.setEnabled(true);
       inputEdit1 = (EditText) findViewById(R.id.inputEdit);
       inputEdit1.setEnabled(true);
       addBtn = (Button) findViewById(R.id.addBtn);
       addBtn.setEnabled(true);
       multiplyBtn = (Button) findViewById(R.id.multiplyBtn);
       multiplyBtn.setEnabled(true);
       ansInfo = (TextView) findViewById(R.id.ans1);
       ansInfo.setEnabled(true);
       upperlimInfo = (TextView) findViewById(R.id.upperlim);
       upperlimInfo.setEnabled(true);
   }
    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data) {
        try {
            if (resultCode == RESULT_OK) {
                if (requestCode == requestCode1) {
                    randval1 = data.getIntExtra("Randomnum", -1);
                    num1.setText(Integer.toString(randval1));
                }
                if (requestCode == requestCode2) {
                    randval2 = data.getIntExtra("Randomnum", -1);
                    num2.setText(Integer.toString(randval2));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
