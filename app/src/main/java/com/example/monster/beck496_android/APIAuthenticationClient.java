package com.example.monster.beck496_android;

import android.util.Log;

import com.example.monster.beck496_android.Base64Encoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;


public class APIAuthenticationClient {


    private String baseUrl;
    private String username;
    private String password;
    private String urlResource;
    private String httpMethod;
    private String urlPath;
    private String lastResponse;
    private String payload;
    private HashMap<String, String> parameters;
    private Map<String, List<String>> headerFields;

    public APIAuthenticationClient(String baseUrl, String username, String password) {
        setBaseUrl(baseUrl);
        this.username = username;
        this.password = password;
        this.urlResource = "";
        this.urlPath = "";
        this.httpMethod = "GET";
        parameters = new HashMap<>();
        lastResponse = "";
        payload = "";
        headerFields = new HashMap<>();
        System.setProperty("jsse.enabledSNIExtension", "false");
    }

    private APIAuthenticationClient setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;

    }

    public APIAuthenticationClient setUrlResource(String urlResource) {
        this.urlResource = urlResource;
        return this;
    }

    public APIAuthenticationClient setUrlPath(String urlPath) {
        this.urlPath = urlPath;
        return this;
    }

    public APIAuthenticationClient setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }

    public String getLastResponse() {
        return lastResponse;
    }

    public Map<String, List<String>> getHeaderFields() {
        return headerFields;
    }

    public APIAuthenticationClient setParameters(HashMap<String, String> parameters) {
        this.parameters = parameters;
        return this;
    }

    public APIAuthenticationClient clearParameters() {
        this.parameters.clear();
        return this;
    }

    public APIAuthenticationClient setParameter(String key, String value) {
        this.parameters.put(key, value);
        return this;
    }

    public APIAuthenticationClient removeParameter(String key) {
        this.parameters.remove(key);
        return this;
    }

    public APIAuthenticationClient clearAll() {
        parameters.clear();
        baseUrl = "";
        this.username = "";
        this.password = "";
        this.urlResource = "";
        this.urlPath = "";
        this.httpMethod = "";
        lastResponse = "";
        payload = "";
        headerFields.clear();
        return this;
    }

    public JSONObject getLastResponseAsJsonObject() {
        try {
            return new JSONObject(String.valueOf(lastResponse));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray getLastResponseAsJsonArray() {
        try {
            return new JSONArray(String.valueOf(lastResponse));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getPayloadAsString() {
        StringBuilder stringBuffer = new StringBuilder();
        Iterator it = parameters.entrySet().iterator();
        int count = 0;
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (count > 0) {
                stringBuffer.append("&");
            }
            stringBuffer.append(pair.getKey()).append("=").append(pair.getValue());

            it.remove();
            count++;
        }
        return stringBuffer.toString();
    }

    public String execute(String username, String password) {
        String line;
        StringBuilder outputStringBuilder = new StringBuilder();

        try {
            StringBuilder urlString = new StringBuilder(baseUrl + urlResource);



            if(httpMethod.equals("GET")) {
                //beckdoors.com/Android_API/login.php?username=deneme&password=deneme
                payload = "username=" + username + "&" + "password=" + password;
                urlString.append("?" + payload);
            }

            URL url = new URL(urlString.toString());
            Log.d(TAG, "URL: " + url.toString());
            Log.d(TAG, "Payload: " + payload);

            String encoding = Base64Encoder.encode(username + ":" + password);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod(httpMethod);
            connection.setRequestProperty("Authorization", "Basic" + encoding);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");

            if (httpMethod.equals("POST") || httpMethod.equals("PUT")) {
                payload = getPayloadAsString();
                Log.d(TAG, "Payload: " + payload);

                connection.setDoInput(true);
                connection.setDoOutput(true);

                try {
                    OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
                    writer.write(payload);

                    headerFields = connection.getHeaderFields();

                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    while((line = br.readLine()) != null) {
                        outputStringBuilder.append(line);
                    }
                } catch (Exception e ){}
                connection.disconnect();
            }
            else {
                Log.d(TAG, "API execute");
                InputStream content = (InputStream) connection.getInputStream();
                Log.d(TAG, "content: " + content);
                headerFields = connection.getHeaderFields();
                Log.d(TAG, "header fierls:" + headerFields.toString());
                BufferedReader in = new BufferedReader(new InputStreamReader(content));

                while ((line = in.readLine()) != null) {
                    outputStringBuilder.append(line);
                }
                Log.d(TAG, "string: " + outputStringBuilder.toString());
                Log.d(TAG, "connection: " + connection.getResponseMessage());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!outputStringBuilder.toString().equals("")) {
            lastResponse = outputStringBuilder.toString();
        }


        return outputStringBuilder.toString();
    }
}
