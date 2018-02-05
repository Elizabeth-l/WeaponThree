package ada.com.weaponthree.accessibilityservice;

import android.os.PowerManager;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.accessibilityservice.AccessibilityService;

import ada.com.weaponthree.tools.Utility;
import ada.com.weaponthree.tools.Process;

import static android.view.accessibility.AccessibilityEvent.TYPE_VIEW_CLICKED;
import static android.view.accessibility.AccessibilityEvent.TYPE_VIEW_LONG_CLICKED;

/**
 * Created by Ada的花花世界 on 2018/1/14.
 */

public class LogAccessibilityService extends AccessibilityService {

    private PowerManager powerManager;
    private boolean isScreenOn = false;

    private static final String TAG = "Applogger";
    //
    private CharSequence mWindowClassName;
    private String mCurrentPackage;
    private String mCurrentActivity;

    Utility utility;
    Process process;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int type = event.getEventType();
        Log.d(TAG, "onAccessibilityEvent type: " + type);
        switch (type) {
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                mWindowClassName = event.getClassName();//获得当前窗口所在的类
                //获得当前窗口所在的App的包名
                mCurrentPackage = event.getPackageName() == null ? "" : event.getPackageName().toString();
                //获得当前事件的时间
                //eventTime = event.getEventTime();
                //将Long类型的时间转换成date型
                SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                String sTime = sdf.format(date);
                String eTime = "null";

                Log.d(TAG, "activity: " + mWindowClassName + " ,package: " + mCurrentPackage + " ,time: "
                        + sTime );
                //将当前窗口信息写入外部存储器
                mCurrentActivity = mWindowClassName.toString();
                process = new Process(mCurrentPackage,mCurrentActivity,sTime,eTime);
                utility = new Utility();
                utility.writeRecordToExternalStorage(process.toString());

                break;
            case TYPE_VIEW_CLICKED:
                Log.d(TAG,"short clicked");
                break;
            case TYPE_VIEW_LONG_CLICKED:
                Log.d(TAG,"long clicked");
                break;
            default:
                break;
        }
    }

    @Override
    public void onInterrupt() {
        Log.d(TAG, "onInterrupt");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        //start logging thread
        //Logging();
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.d(TAG, "onServiceConnected");
    }
    //Logging用来记录手机锁屏和亮屏
   /* private void Logging() {
        Log.d(TAG, "start logging ... ");
        utility = new Utility();

        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    // get PowerManager
                    powerManager = (PowerManager) getSystemService(POWER_SERVICE);
                    if (powerManager.isInteractive() ^ isScreenOn) {
                        isScreenOn = !isScreenOn;

                        if (isScreenOn) {
                            utility.writeRecordToExternalStorage(
                                    (new StringBuilder()).append("[").append(utility.getCurrentTime()).append(" session starts").append("\t\n").toString());
                            Log.d(TAG, "session starts" + utility.getCurrentTime());
                        } else {
                            utility.writeRecordToExternalStorage(
                                    (new StringBuilder()).append("session ends ").append(utility.getCurrentTime()).append("]").append("\t\n").toString());
                            Log.d(TAG, "session ends" + utility.getCurrentTime());
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Log.e(TAG, "InterruptedException in log thread");
                        break;
                    }
                }


            }
        }).start();
    }*/
}



