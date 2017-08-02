package no.me.eliasbrattli.ovinger.oving06.client06;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by EliasBrattli on 11/11/2016.
 */
public class Client extends Thread {
    private static final String TAG = "Client";
    private static final String IP = "10.0.2.2";
    private static int PORT = 12345;
    private String input;
    private String output = "";
    private ReceivedValueListener listener;
    public Client(String input, Context activity){
        try {
            listener = (ReceivedValueListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+" must implement abstract method onReceivedValue");
        }
        this.input = input;
    }

    public void run(){
        try(Socket socket = new Socket(IP,PORT); PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
            Log.v(TAG,"C: Connected to server"+ socket.toString());
            printWriter.println(input);
            output = bufferedReader.readLine();
            Log.i(TAG,output);
            listener.onReceivedValue(output);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    public String getOutput(){
        return output;
    }
    public interface ReceivedValueListener{
        void onReceivedValue(String val);
    }
}
