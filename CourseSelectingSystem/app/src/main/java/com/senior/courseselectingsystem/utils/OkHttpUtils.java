package com.senior.courseselectingsystem.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtils {

    private static String result;

    /**
     * 同步get请求，必须在子线程中执行http请求
     * @param strUrl
     * @param params
     * @return
     * @throws Exception
     */
    public static String doGetSync(String strUrl, HashMap<String, String> params) throws Exception{
        result = null;

        // 处理参数
        if(params != null){
            int pos = 0;
            StringBuilder tmpParams = new StringBuilder();
            for(String key : params.keySet()){
                if(pos > 0){
                    tmpParams.append("&");
                }
                tmpParams.append(String.format("%s=%s", key, params.get(key)));
                pos++;
            }
            strUrl += "?" + tmpParams.toString();
        }

        // 创建OKHttpClient客户端
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.SECONDS)
                .build();

        // 创建一个Request对象，描述了OkHif(ttp将要发送的请求
        Request request = new Request.Builder()
                .get()
                .url(strUrl)
                .build();

        // 创建一个Call对象，用于执行请求并获取响应
        Call call = client.newCall(request);

        // 获取Resourse对象
        Response response = call.execute();

        if(response.isSuccessful()){
            // 获取响应结果
            result = response.body().string();
        }else{
            throw new IOException("Unexpected code: " + response.code() + "--" + response.message());
        }

        return result;
    }

    /**
     * 同步post请求，必须在子线程中执行http请求
     * @param strUrl
     * @param params
     * @return
     * @throws Exception
     */
    public static String doPostSync(String strUrl, HashMap<String, String> params) throws Exception{
        result = null;

        // 构造表单对象
        // 创建一个FormBody.Builder
        FormBody.Builder builder = new FormBody.Builder();
        for(String key : params.keySet()){
            // 追加表单信息
            builder.add(key, params.get(key));
        }
        // 生成表单对象
        RequestBody formBody = builder.build();
        // 创建request对象
        Request request = new Request.Builder()
                .url(strUrl)
                .post(formBody)     // 封装表单数据
                .build();

        // 创建OKHttpClient客户端
        OkHttpClient client = new OkHttpClient();

        Response response = client.newCall(request).execute();
        if(response.isSuccessful()){
            result = response.body().string();
        }else{
            throw new IOException(("Unexpected code: " + response.code() + "--" + response.message()));
        }

        return result;
    }

    /**
     * 异步get请求: 必须在子线程中执行http请求
     * @param strUrl
     * @param params
     * @param myhandler
     */
    public static void doGetAsync(String strUrl, HashMap<String, String> params, final Handler myhandler){

        result  = null;

        // 处理参数
        if(params != null){
            int pos = 0;
            StringBuilder tmpParams = new StringBuilder();
            for(String key : params.keySet()){
                if(pos > 0){
                    tmpParams.append("&");
                }
                tmpParams.append(String.format("%s=%s", key, params.get(key)));
                pos++;
            }
            strUrl += "?" + tmpParams.toString();
        }

        // 创建OKHttpClient客户端
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.SECONDS)
                .build();

        // 创建一个Request对象，描述了OkHif(ttp将要发送的请求
        Request request = new Request.Builder()
                .get()
                .url(strUrl)
                .build();

        // 创建一个Call对象，用于执行请求并获取响应
        Call call = client.newCall(request);

        // 获取Response对象: 在子线程中完成
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 获取响应结果
                if(response.isSuccessful()){
                    result = response.body().string();
                }else{
                    throw new IOException(("Unexpected code: " + response.code() + "--" + response.message()));
                }

                // 向主线程传递响应结果
                Message msg = myhandler.obtainMessage();
                msg.obj = result;
                myhandler.sendMessage(msg);
            }
        });
    }

    public static void doPostAsync(String strUrl, HashMap<String, String> params, final Handler myhandler){
        result = null;

        // 构造表单对象
        // 创建一个FormBody.Builder
        FormBody.Builder builder = new FormBody.Builder();
        for(String key : params.keySet()){
            // 追加表单信息
            builder.add(key, params.get(key));
        }
        // 生成表单对象
        RequestBody formBody = builder.build();
        // 创建request对象
        Request request = new Request.Builder()
                .url(strUrl)
                .post(formBody)     // 封装表单数据
                .build();

        // 创建OKHttpClient客户端
        OkHttpClient client = new OkHttpClient();

        // 获取Response：在子线程中完成
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    // 获取响应结果
                    result = response.body().string();
                    Log.i("===result", result);
                }else{
                    throw new IOException(("Unexpected code: " + response.code() + "--" + response.message()));
                }

                // 向主线程传递响应结果
                Message msg = myhandler.obtainMessage();
                msg.obj = result;
                myhandler.sendMessage(msg);
            }
        });
    }

    public static void doUploadPostAsync(String strUrl, String path, final Handler myhandler){
        result = null;

        String filename = path.substring(path.lastIndexOf("/") + 1, path.length());
        File file = new File(path);
        MediaType contentType = MediaType.parse("multipart/form-data"); // 上传文件的Content-Type
        RequestBody filebody = RequestBody.create(contentType, file); // 上传文件的请求体
        RequestBody formbody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", filename, filebody)
                .build();
        // 创建request对象
        Request request = new Request.Builder()
                .url(strUrl)
                .post(formbody)
                .build();

        // 创建OKHttpClient客户端
        OkHttpClient client = new OkHttpClient();

        // 获取Response：在子线程中完成
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    // 获取响应结果
                    result = response.body().string();
                    Log.i("===result", result);
                }else{
                    throw new IOException(("Unexpected code: " + response.code() + "--" + response.message()));
                }

                // 向主线程传递响应结果
                Message msg = myhandler.obtainMessage();
                msg.obj = result;
                myhandler.sendMessage(msg);
            }
        });
    }
}

