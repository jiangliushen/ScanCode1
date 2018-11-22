package com.bomesc.scancode.io;

import com.zhiliantiandi.twodtest.R;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.String;
import java.lang.StringBuffer;
import java.lang.System;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class SaveFileToSDCard {

    private static final String TAG = "SaveFileToSDCard";

    public SaveFileToSDCard(){}

    public boolean saveFile(ArrayList<HashMap<String, String>> mapArrayList){
        StringBuffer mResult = new StringBuffer();//缓冲区
        mResult.append("Time\t\t\tResult\r\n");
        Log.d(TAG, "saveFile format start");
        for (int i = 0; i < mapArrayList.size(); i++){
            //mResult = mapArrayList.get(i).get("decodeTime") + "\t\t\t" + mapArrayList.get(i).get("decodeResult") + "\r\n";
            mResult.append(mapArrayList.get(i).get("decodeTime"));
            mResult.append("\t\t\t");
            mResult.append(mapArrayList.get(i).get("decodeResult"));
            mResult.append("\r\n");
        }
        Log.d(TAG, "saveFile format end");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");//时间格式
        Date date = new Date(System.currentTimeMillis());//获取当前时间
        String fileName = dateFormat.format(date) + ".txt";//以当前时间配置文件名
        File sdcardDir = Environment.getExternalStorageDirectory();//获取应用程序在外部的存储目录
        String path = sdcardDir.getPath()+"/results";//配置路径文件名
        File resultPath = new File(path);//文件路径
        if (!resultPath.exists()){//如果不存在路径
            resultPath.mkdir();//创建目录
        }
        File saveFile = new File(resultPath, fileName);
        Log.d(TAG, "saveFile create file end");
        try {
            FileOutputStream outStream = new FileOutputStream(saveFile);//文件输出流
            outStream.write(mResult.toString().getBytes());//outStream写入缓冲区数据
            outStream.close();//关闭流
            Log.d(TAG, "saveFile save file end");
            return true;
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

}