package no.me.eliasbrattli.ovinger.oving06.client06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ClientActivity extends AppCompatActivity implements View.OnClickListener, Client.ReceivedValueListener{
    private static final String TAG = "ClientActivity";
    private Button okButton;
    private TextView textView;
    private EditText numInput1;
    private EditText numInput2;
    private final String START_MESSAGE = "Client started...";
    private String input1;
    private String input2;
    private String output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findAllViewsById();
        enableAllViews();
        setOnclickListener();
        new Client("1,1",this).start();
    }
    @Override
    public void onClick(View view){
        try{
            switch (view.getId()){
                default: break;
                case R.id.okButton:
                    Log.i(TAG,"Button clicked");
                    input1 = numInput1.getText().toString().trim();
                    input2 = numInput2.getText().toString().trim();
                    new Client(input1+","+input2,this).start();
                    //Log.i(TAG,output);
                    if(output != null)
                    textView.setText("Summen er "+output.trim());
                    else
                    textView.setText("Ingen kontakt med server");
                    break;
                case R.id.numInput1:
                    // Could empty text field
                    break;
                case R.id.numInput2:
                    // Could empty text field
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onReceivedValue(String val){
        output = val;
    }
    private void findAllViewsById(){
        okButton = (Button)findViewById(R.id.okButton);
        textView = (TextView) findViewById(R.id.textView);
        numInput1 = (EditText) findViewById(R.id.numInput1);
        numInput2 = (EditText) findViewById(R.id.numInput2);
    }
    private void enableAllViews(){
        okButton.setEnabled(true);
        textView.setEnabled(true);
        numInput1.setEnabled(true);
        numInput2.setEnabled(true);
    }
    private void setOnclickListener(){
        okButton.setOnClickListener(this);
    }
}
