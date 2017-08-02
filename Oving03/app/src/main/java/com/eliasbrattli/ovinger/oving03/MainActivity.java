package com.eliasbrattli.ovinger.oving03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{
    private final String TAG = "MainActivity";
    private TextView txtInfo;
    private EditText nameInputField;
    private EditText dateInputField;
    private Button saveButton;
    private ListView contactListView;
    private ArrayAdapter<String> listAdapter;
    private int pos = -1;
    private ArrayList<String> contactList;
    private String[] editableItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findAllViewsById();
        enableAllViews();
        setAllOnClickListeners();
        initListView();
    }

    @Override
    public void onClick(View v){
        String name;
        String date;
        try{
            switch (v.getId()){
                case R.id.nameInputField:
                    saveButton.setEnabled(true); // Enable button when the user has selected a field, ready to write.
                    if(pos < 0) nameInputField.setText(""); // The user won't lose contents of EditText if editing existing item
                    break;
                case R.id.dateInputField:
                    saveButton.setEnabled(true);
                    if(pos < 0) dateInputField.setText("");
                    break;
                case R.id.saveButton:
                    name = nameInputField.getText().toString().trim();
                    date = dateInputField.getText().toString().trim();
                    saveContactInfo(pos,name,date);
                    txtInfo.setText("Legg til ny kontakt");
                    pos = -1;
                    break;
                default:
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        Log.i(TAG,"Item was clicked");
        final String EDIT_CONTACT = "Endre kontakt";
        try{
               txtInfo.setText(EDIT_CONTACT);
               saveButton.setEnabled(true);
               editableItems = listAdapter.getItem(position).split(" ");
               nameInputField.setText(editableItems[0]);
               dateInputField.setText(editableItems[1]);
               pos = position;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // Init methods --------------------------------------------------------------------------
    private void findAllViewsById(){
        txtInfo = (TextView)findViewById(R.id.txtInfo);
        nameInputField = (EditText)findViewById(R.id.nameInputField);
        dateInputField = (EditText)findViewById(R.id.dateInputField);
        saveButton = (Button)findViewById(R.id.saveButton);
        contactListView = (ListView) findViewById(R.id.contactListView);
    }
    private void enableAllViews(){
        txtInfo.setEnabled(true);
        nameInputField.setEnabled(true);
        dateInputField.setEnabled(true);
        contactListView.setEnabled(true);
        saveButton.setEnabled(false); //Savebutton is only enabled when inputfields have contents
    }

    private void setAllOnClickListeners(){
        saveButton.setOnClickListener(this);
        nameInputField.setOnClickListener(this);
        dateInputField.setOnClickListener(this);
        contactListView.setOnItemClickListener(this);
    }
    private void initListView(){
        contactList = new ArrayList<>();
        listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contactList);
        //listAdapter.add("Navn Fødselsdato");//Simple substitute for a header
        //listAdapter.notifyDataSetChanged();
        contactListView.setAdapter(listAdapter);
        contactListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
    }
    // Functional methods --------------------------------------------------------------------------
    private void saveContactInfo(int pos, String name, String date){
        final String STANDARD_NAME = "Navn";
        final String STANDARD_DATE = "Dato";
        final String MESSAGE_NULL = "Du må legge inn navn og dato";
        final String MESSAGE_STANDARD = "Du må legge inn egen informasjon";
        final String MESSAGE_ADDED_SUCCESS = " har blitt lagt til";
        final String MESSAGE_EDITED_SUCCESS = " ble endret til ";

        // Check for empty strings
        if(!name.isEmpty() && !date.isEmpty()) {
            // Check that user actually added valid info
            if(!name.equals(STANDARD_NAME) && !date.equals(STANDARD_DATE)) {
                if(pos == -1 ) {
                    addToList(name, date);
                    Toast.makeText(getBaseContext(), name + MESSAGE_ADDED_SUCCESS, Toast.LENGTH_LONG).show();
                    saveButton.setEnabled(false);// Disable button after info has been submitted
                }else{
                    boolean nameChanged = !editableItems[0].equals(name);
                    boolean dateChanged = !editableItems[1].equals(date);
                    if(nameChanged || dateChanged) {
                        String[] swappedItems = new String[2];
                        swappedItems[0] = (nameChanged)?editableItems[0]:editableItems[1];
                        swappedItems[1] = (nameChanged)?name:date;
                        put(pos, name, date);
                        Toast.makeText(getBaseContext(), swappedItems[0]+ MESSAGE_EDITED_SUCCESS + swappedItems[1],
                                Toast.LENGTH_LONG).show();
                    }
                    saveButton.setEnabled(false);
                }
            }else{
                Toast.makeText(getBaseContext(),MESSAGE_STANDARD, Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getBaseContext(),MESSAGE_NULL, Toast.LENGTH_LONG).show();
        }
        nameInputField.setText(STANDARD_NAME);
        dateInputField.setText(STANDARD_DATE);
    }
    private void addToList(String name, String date){
        try {
            listAdapter.add(name+" "+date);
            listAdapter.notifyDataSetChanged();
        }catch (NullPointerException npe){
            //listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_selectable_list_item,contactList);
            npe.printStackTrace();
        }

    }
    private void put(int pos,String name,String date){
        try {
            listAdapter.remove(listAdapter.getItem(pos));
            listAdapter.insert(name+" "+date,pos);
            listAdapter.notifyDataSetChanged();
            pos = -1;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
