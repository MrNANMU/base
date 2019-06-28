package com.dasong.common.utils;

import android.util.Log;

public class LogUtils {

    public static void i(String msg){
        Log.i(getLogInvokeLocation(),msg);
    }

    public static void v(String msg){
        Log.v(getLogInvokeLocation(),msg);
    }

    public static void d(String msg){
        Log.d(getLogInvokeLocation(),msg);
    }

    public static void w(String msg){
        Log.w(getLogInvokeLocation(),msg);
    }
    
    public static void e(String msg){
        Log.e(getLogInvokeLocation(),msg);
    }
    
    public static void wtf(String msg){
        Log.wtf(getLogInvokeLocation(),msg);
    }

    //获取日志输出位置
    public static String getLogInvokeLocation(){
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        String className = stackTraceElement.getClassName().split("\\.")[stackTraceElement.getClassName().split("\\.").length-1];
        return stackTraceElement.getFileName()+" "+stackTraceElement.getLineNumber()+className+"."+stackTraceElement.getMethodName()+"()";
    }
}
