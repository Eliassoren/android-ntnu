package no.me.eliasbrattli.ovinger.oving05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public final String TAG = "MainActivity";
    private TextView textView;
    private EditText nameInputField;
    private EditText numInputField;
    private Button sendButton;
    private Button resetButton;
    private String name;
    private String cardNumber;
    private String value;
    private String response;
    private NetworkClient client;
    private final String NAME_PARAM = "navn";
    private final String CARD_PARAM = "kortnummer";
    private final String NUM_PARAM = "tall";
    private final String URL = "http://tomcat.stud.iie.ntnu.no/studtomas/tallspill.jsp";
    private int count; // When count exceeds 3, request a reset.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findAllViewsById();
        enableAllViews();
        setAllOnClickListeners();
        client = new NetworkClient(this,URL);
    }
    @Override
    public void onClick(View view){
        try {
            switch (view.getId()){
                default:break;
                case R.id.sendButton:
                    Log.i(TAG,"Sendbutton clicked");
                    client.runRequestInThread(NetworkClient.RequestType.GET,createRequestValues());
                    textView.setText(response);
                    if(count < 3) {
                        count++;
                        sendButton.setEnabled(false);
                        nameInputField.setEnabled(false);
                    }else{
                        count = 0;
                        enableAllViews();
                    }
                    break;
                case R.id.resetButton:
                    Log.i(TAG,"Resetbutton clicked");
                    reset();
                    break;
                case R.id.nameInputField:
                    sendButton.setEnabled(true);
                    nameInputField.setText("");
                    break;
                case R.id.numInputField:
                    sendButton.setEnabled(true);
                    numInputField.setText("");
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private String[] sendInput(){
        String[] input;
        if(count < 1) {
            String in = nameInputField.getText().toString().trim()+" ";
            in += numInputField.getText().toString().trim();
            Log.i(TAG,in);
            input = in.split("\\s+");
            Log.i(TAG,input[0]);
            Log.i(TAG,input[1]);
        }else{
            input = new String[1];
            input[0] = numInputField.getText().toString().trim();
            Log.i(TAG,input[0]);
        }


        return input;
    }
    public Map<String,String> createRequestValues(){
        String[] input = sendInput();
        Map<String,String> values = new HashMap<>();
        if(input.length > 1){
            values.put(NAME_PARAM,input[0]);
            values.put(CARD_PARAM,input[1]);
            Log.i(TAG,values.get(NAME_PARAM));
            Log.i(TAG,values.get(CARD_PARAM));
        }else{
            values.put(NUM_PARAM,input[0]);
            Log.i(TAG,values.get(NUM_PARAM));
        }

        return values;
    }
    public void setResponse(String response){
        this.response = response;
        Log.i(TAG,response);
    }

    private void reset(){
        enableAllViews();
        count = 0;
        textView.setText("Velkommen til betting");
        nameInputField.setText("Navn");
        numInputField.setText("Kortnummer");
        client = new NetworkClient(this,URL);
    }

    /**
     * Methods for initiating GUI
     */
    private void findAllViewsById(){
        textView = (TextView)findViewById(R.id.textView);
        nameInputField = (EditText)findViewById(R.id.nameInputField);
        numInputField = (EditText)findViewById(R.id.numInputField);
        sendButton = (Button)findViewById(R.id.sendButton);
        resetButton = (Button)findViewById(R.id.resetButton);
    }
    private void enableAllViews(){
        textView.setEnabled(true);
        nameInputField.setEnabled(true);
        numInputField.setEnabled(true);
        sendButton.setEnabled(true);
        resetButton.setEnabled(true);
    }
    private void setAllOnClickListeners(){
        nameInputField.setOnClickListener(this);
        numInputField.setOnClickListener(this);
        sendButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
    }
}
