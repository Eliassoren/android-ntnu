package com.example.eliasbrattli.oving1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button fNameButton = (Button)findViewById(R.id.fNameButton);
        Button sNameButton = (Button)findViewById(R.id.sNameButton);
        fNameButton.setEnabled(true);
        sNameButton.setEnabled(true);
        fNameButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String out = "Valgte fornavn";
                Log.i(TAG,out);
                v.setEnabled(false);
            }
        });
        sNameButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String out = "Valgte etternavn";
                Log.i(TAG,out);
                v.setEnabled(false);
            }
        });
    }

}
