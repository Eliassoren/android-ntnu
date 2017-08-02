package no.me.eliasbrattli.ovinger.oving07.util;


import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by EliasBrattli on 18/11/2016.
 */
public class FileUtil {
    private static final String TAG = "FileUtil";
    public static ArrayList<String> readFile(String fileName){
        ArrayList<String> entries = new ArrayList<>();

        try {
            String filePath = new File("").getAbsolutePath();
            filePath = filePath.concat(fileName);
            BufferedReader in = new BufferedReader(new FileReader(filePath));
            for(String line;(line = in.readLine())!= null;){
               entries.add(line.trim());
            }
            //return entries;
        }catch (IOException e){
            Log.e(TAG,e.getMessage());
        }
        return entries;
    }
    public static ArrayList<String> readAuthors(String fileName){
        ArrayList<String> list = new ArrayList<>();
        for(String e:readFile(fileName)){
            list.add(e.split(",")[0]);
        }
        return list;
    }
    public static ArrayList<String> readTitles(String fileName){
        ArrayList<String> list = new ArrayList<>();
        for(String e:readFile(fileName)){
            list.add(e.split(",")[1]);
        }
        return list;
    }
}
