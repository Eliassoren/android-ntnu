package no.me.eliasbrattli.ovinger.oving06.server;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by EliasBrattli on 11/11/2016.
 */
public class Server extends Thread{
    private static final String TAG = "ServerThread";
    private static int PORT = 12345;

    public void run() {
        Log.i(TAG, "starting server....");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            Log.i(TAG,"wait for client....");
            for (;;) {
                Log.i(TAG,"waiting for new client...");
                try (Socket socket = serverSocket.accept();
                     PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    Log.v(TAG, "client connected....");

                    String line = bufferedReader.readLine();
                    Log.i(TAG, "Message from client: " + line+"....");
                    printWriter.write(add(line));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    public String add(String line){
        String out = "";
        if(!line.isEmpty()) {
            String[] input = line.split(",");
            int res = Integer.parseInt(input[0].trim()) + Integer.parseInt(input[1].trim());
            out +=res;
        }
        return out;
    }
}
