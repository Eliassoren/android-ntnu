package no.me.eliasbrattli.ovinger.oving05;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Created by EliasBrattli on 17/11/2016.
 */
public class NetworkClient {
    private final String TAG = "NetworkClient";
    final String ENCODING = "ISO-8859-1";
    private MainActivity activity;
    private String url;
    private RequestType requestType;
    public enum RequestType{
        GET, POST, GET_WITH_HEADER
    }
    public NetworkClient(MainActivity activity, String url){
        this.activity = activity;
        this.url = url;
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
    }
    public void runRequestInThread(RequestType requestType, Map<String,String> requestValues){
        this.requestType = requestType;
        new RequestThread().execute(requestValues);
    }
    private class RequestThread extends AsyncTask<Map<String,String>,String,String>{
        protected String doInBackground(Map<String,String>... v){
            try{
                if(requestType == RequestType.GET){
                    return httpGet(v[0]);
                }else if(requestType == RequestType.POST){
                    return post(v[0]);
                }else{
                    return getWithHeader(v[0]);
                }
            }catch (Exception e){
                Log.e(TAG,e.getMessage());
               // e.printStackTrace();
                return e.getMessage();
            }
        }
        protected void onPostExecute(String response){
            activity.setResponse(response);
        }
    } // End thread class

    /**
     * Sends http GET request
     * @param params
     * @return
     * @throws IOException
     */
    public String httpGet(Map<String,String> params)throws IOException{
        Log.i(TAG,"************ START GET ****************");
        String paramUrl = url +"?"+encodeParams(params);
        String responseStr = "";
        URLConnection connection = new URL(paramUrl).openConnection();
        connection.setRequestProperty("Accept-Charset", ENCODING);
        try(InputStream response = connection.getInputStream()){
            return this.readResponseBody(response,getCharSet(connection));
        }
    }

    /**
     * Send http POST request
     * @param params
     * @return
     * @throws IOException
     */
    public String post(Map<String,String> params) throws IOException{
        Log.i(TAG,"*********** START POST **********");
        URLConnection connection = new URL(url).openConnection();
        connection.setDoOutput(true); // Post trigger
        connection.setRequestProperty("Accept-Charset",ENCODING);
        connection.setRequestProperty("Content-Type","application/x-www-from-urlencoded;charset="+ENCODING);

        try(OutputStream output = connection.getOutputStream()){
            String postString = encodeParams(params);
            output.write(postString.getBytes(ENCODING));
        }
        try(InputStream input = connection.getInputStream()){
            String charset = this.getCharSet(connection);
            return readResponseBody(input,charset);
        }
    }
    public String getWithHeader(Map<String,String> params) throws IOException{
        Log.i(TAG,"******** START GET WITH HEADER *******");
        String paramUrl = url + "?" + encodeParams(params);
        String responseStr = "";
        URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("Accept-Charset",ENCODING);

        for(Map.Entry<String,List<String>> header : connection.getHeaderFields().entrySet() ){
            responseStr += header.getKey() + "=" + header.getValue();
        }

        try(InputStream response = connection.getInputStream()){
            responseStr += readResponseBody(response,getCharSet(connection));
        }
        return responseStr;
    }
    /*så hele urlen blir blablabla.jsp?navn=nokka&kortnummer=nokkaanna*/
    public String encodeParams(Map<String,String> params){
        String code = "";
        for(String key : params.keySet()){
            String val = params.get(key);
            try{
                code += key + "=" +URLEncoder.encode(val,ENCODING);
                code += "&";
            }catch (UnsupportedEncodingException e){
                Log.e(TAG,e.toString());
            }
        }
        return code.substring(0,code.length()-1);
    }
    private String readResponseBody(InputStream inputStream, String charSet){
        String body = "";
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,charSet))){
            for(String line; (line = reader.readLine())!=null;){
                body += line;
            }
        }catch(Exception ex){
            Log.e(TAG,ex.toString());
            body += "******* Problems reading from server ********";
        }
        return body;
    }
    private String getCharSet(URLConnection connection){
        String contentType = connection.getHeaderField("Content-Type");
        String charset = null;
        for(String param: contentType.replace(" ","").split(";")){
            if(param.startsWith("charset=")){
                charset = param.split("=",2)[1];
                break;
            }
        }
        return charset;
    }
}
