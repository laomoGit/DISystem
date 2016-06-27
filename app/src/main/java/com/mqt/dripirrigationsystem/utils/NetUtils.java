package com.mqt.dripirrigationsystem.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/6/22.
 */
public class NetUtils {

    private final static String GONE = "GONE";
    public  final static String GET="GET";
    public  final static String POST="POST";

    /**
     *
     * @param httpUrl url字符串
     * @return 返回字符串
     */
    public static String sendGetRequest(String httpUrl,String method,String params) throws Exception {
        URL url = null;
        InputStream is = null;
        String response = "";

        if (GET.equals(method)) {
            httpUrl = httpUrl + "?" + params;
            LogInfo.info(httpUrl);
        }
        url = new URL(httpUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(GET);
        connection.setConnectTimeout(8000);
        connection.setReadTimeout(8000);
        connection.setDoInput(true);

        if(!GET.equals(method)){
            connection.setDoOutput(true);
            // 此方法在正式链接之前设置才有效。
            connection.setRequestMethod(method);
            connection.setUseCaches(false);
            connection.setDoOutput(true);

            connection.getOutputStream().write(params.getBytes("UTF-8"));
        }
        connection.connect();

        int responseCode = connection.getResponseCode();
        if(responseCode >= 200 && responseCode < 300){
            is = connection.getInputStream();
        }else{
            is = connection.getErrorStream();
        }
        response = inputStreamToString(is);
        connection.disconnect();

        Log.v("tag", "res:" + response);
        NetUtils.checkResponse(response,responseCode);

        return response;
    }

    /**
     *
     * @param response 网络响应字符串
     * @param responseCode 服务器返回的状态码
     */
    private static void checkResponse(String response, int responseCode) throws Exception {
        if (response == null) {
            throw new Exception("unknow exception null response!");
        }
        if(responseCode!=200){
            throw new Exception("网络链接失败！");

        }
    }

    /**
     *
     * @param is 输入流
     * @return
     */
    private static String inputStreamToString(InputStream is) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is),1024);
        String line = "";
        try {
            while((line = br.readLine())!=null){
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
