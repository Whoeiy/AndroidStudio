package com.senior.courseselectingsystem.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
    public static int BUFFER_SIZE = 4096;
    public static final String TAG = "httpUrils";
    private static final int TIME_OUT = 5 * 1000;

    /**
     *      基于http的网络通信中，激动端的任务是:
     *      1. 构造http请求报文(请求行、请求头、请求体)
     *          get请求, 将请求参数附加到url
     *      2. 发送http请求：将封装好的http请求报文发送到服务器
     *      3. 接收服务器响应结果: 通过输入流
     */

    public static String doGet(String strURL, String param) throws Exception{
        String result = "";
        HttpURLConnection conn = null;

        // 1.1 构造URL字符串，创建URL对象
        if(param != null){
            strURL += "?" + param;
        }
        URL url = new URL(strURL);


        // 1.2 得到HttpURLConnection对象，处理http请求
        conn = (HttpURLConnection) url.openConnection();

        // 2.1 设置请求头部信息
        conn.setReadTimeout(TIME_OUT);  // 超时

        // 2.2 根据上述配置生成请求头部信息
        /* 所有的头部配置都必须在connect()之前完成，之后的配置无效 */
        conn.connect();

        // 3. 连接成功，则发送http请求，并读取响应结果
        if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
            // 3.1 通过输入流接收服务器响应结果
            InputStream is = conn.getInputStream();
            // 3.2 将响应结果转换为需要的数据格式
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = reader.readLine()) != null){
                result += line;
            }
            // 3.3 关闭输入流
            reader.close();     // 关闭缓冲流，后打开的先关闭
            is.close();
        }
        // 4. 断开连接
        conn.disconnect();

        return result;
    }

    /**
     *  post请求：利用OurpotStream将请求参数写入请求体(http正文)
     */
    public static String doPost(String strURL, String param) throws Exception{
        String result = "";
        HttpURLConnection conn = null;

        URL url = new URL(strURL);

        conn = (HttpURLConnection) url.openConnection();

        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        conn.setReadTimeout(TIME_OUT);
        conn.setRequestMethod("POST");

        conn.connect();

        if(param != null){
            OutputStream os = conn.getOutputStream();
            PrintWriter out = new PrintWriter(os);
            out.print(param);
            out.flush();
            out.close();
        }

        if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
            // 3.1 通过输入流接收服务器响应结果
            InputStream is = conn.getInputStream();
            // 3.2 将响应结果转换为需要的数据格式
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = reader.readLine()) != null){
                result += line + "\n";
            }
            // 3.3 关闭输入流
            reader.close(); // 关闭缓冲流，后打开的先关闭
            is.close();
        }

        conn.disconnect();

        return result;
    }
}
