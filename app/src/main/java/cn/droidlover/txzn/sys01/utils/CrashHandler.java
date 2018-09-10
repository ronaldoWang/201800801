package cn.droidlover.txzn.sys01.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ronaldo on 2018/8/19.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {


    private static final String TAG = "CrashHandler";
    private static final boolean DEBUG = true;
    //异常文件的路径
    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/CrashTest/log/";
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".trace";

    private static CrashHandler sInstance = new CrashHandler();

    private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;
    private Context mContext;

    private CrashHandler() {
    }


    public static CrashHandler getsInstance() {
        return sInstance;
    }

    /**
     * 初始化操作
     *
     * @param context
     */
    public void init(Context context) {

        mUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context.getApplicationContext();
    }


    /**
     * 这个方法是最为关键的，当程序中未有被捕获的异常的时候，会调用 uncaughtException
     *
     * @param thread    未捕获异常的线程
     * @param throwable 未捕获的异常
     */
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        //将捕获的异常写到SD卡中去
        write2SD(throwable);
        //将异常提交到服务器端
        upload2Server();
        throwable.printStackTrace();
        //如果系统自己提供的处理异常的机制就交给系统自己来处理，否则自己处理
        if (mUncaughtExceptionHandler != null) {
            mUncaughtExceptionHandler.uncaughtException(thread, throwable);
        } else {
            Process.killProcess(Process.myPid());
        }
    }


    private void write2SD(Throwable throwable) {

        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            if (DEBUG) {
                Log.w(TAG, "sdcard unmounted, skip dump exception!");
            }
        }
        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        long current = SystemClock.currentThreadTimeMillis();
        String currentTime = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(new Date(current));
        File file = new File(PATH + FILE_NAME + FILE_NAME_SUFFIX);
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            writer.println(currentTime);
            writer.println("===================================================");
            writer.println(getPhoneInfo(writer));
            writer.println(throwable.getMessage());
            writer.println("===================================================");
            throwable.printStackTrace(writer);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取手机的硬件信息
     *
     * @return
     */
    private String getPhoneInfo(PrintWriter writer) {

        PackageManager packageManager = mContext.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
            writer.println("App Vision:" + packageInfo.versionName);//当前APP的版本号
            writer.println("OS Vision:" + Build.VERSION.RELEASE + "__SDK INT:" + Build.VERSION.SDK_INT);//当前操作系统版本
            writer.println("Vendor:" + Build.MANUFACTURER);//手机制造厂商
            writer.println("Moudle:" + Build.MODEL);//手机型号
            writer.println("CUP ABI:" + Build.CPU_ABI);//手CPU型号
            writer.println("===================================================");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 上传服务器端
     */
    private void upload2Server() {
        upload2Server();
    }

}
