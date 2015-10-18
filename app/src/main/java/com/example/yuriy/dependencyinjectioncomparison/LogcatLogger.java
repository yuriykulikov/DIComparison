package com.example.yuriy.dependencyinjectioncomparison;

import android.util.Log;

/**
 * Created by Yuriy on 16.10.2015.
 */
public class LogcatLogger implements ILogger{
    @Override
    public void d(String string) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];
        Log.d(stackTraceElement.getFileName() + "." + stackTraceElement.getMethodName(), string);
    }
}
