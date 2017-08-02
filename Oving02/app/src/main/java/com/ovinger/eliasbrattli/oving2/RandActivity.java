package com.ovinger.eliasbrattli.oving2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Random;

/**
 * Created by EliasBrattli on 10/10/2016.
 */
public class RandActivity extends AppCompatActivity {
    public static final String TAG ="RandActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //b
        int upperlimit = getIntent().getIntExtra("UpperRandLimit",100);
        int randnum = rand(0,upperlimit);
        //c
        Intent intent = new Intent();
        intent.putExtra("Randomnum",randnum);
        setResult(RESULT_OK,intent);
        //a
        //Toast t = Toast.makeText(this,"Randnum "+randnum,Toast.LENGTH_LONG);
        // t.show();
        finish();
    }

    private int rand(int min, int max){
        Random random = new Random();
        return random.nextInt(max-min)+min;
    }
}
