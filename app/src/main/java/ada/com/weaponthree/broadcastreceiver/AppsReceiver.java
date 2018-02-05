package ada.com.weaponthree.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import ada.com.weaponthree.tools.AppsChangedInfo;
import ada.com.weaponthree.tools.AppsReceiverWriter;


/**
 * Created by Ada的花花世界 on 2018/1/13.
 */

public class AppsReceiver extends BroadcastReceiver {

    private static final String TAG = "Applogger";
    AppsReceiverWriter appsReceiverWriter;
    AppsChangedInfo appsChangedInfo;
    String packageName;
    @Override
    public void onReceive(Context context, Intent intent) {
        //用来获取当前时间
        appsReceiverWriter = new AppsReceiverWriter();

        //判断收到的是什么广播
        String action = intent.getAction();

        //判断安装更新卸载的是什么应用

        Uri uri = intent.getData();

        if (Intent.ACTION_PACKAGE_ADDED.equals(action)) {
            Log.d(TAG,"install");
            Toast.makeText(context,uri + "被安装了 " + appsReceiverWriter.getCurrentTime(),Toast.LENGTH_LONG).show();
            //将安装信息写入到外部存储
            packageName = uri.toString();
            appsChangedInfo = new AppsChangedInfo(packageName,appsReceiverWriter.getCurrentTime(),0);
            appsReceiverWriter.writeRecordToExternalStorage(appsChangedInfo.toString());

        } else if (Intent.ACTION_PACKAGE_REMOVED.equals(action)) {
            Toast.makeText(context,uri + "被卸载了 "+ appsReceiverWriter.getCurrentTime(),Toast.LENGTH_LONG).show();
            Log.d(TAG,"removed");
            //将卸载信息写入到外部存储
            packageName = uri.toString();
            appsChangedInfo = new AppsChangedInfo(packageName,appsReceiverWriter.getCurrentTime(),1);
            appsReceiverWriter.writeRecordToExternalStorage(appsChangedInfo.toString());
        } else if (Intent.ACTION_PACKAGE_REPLACED.equals(action)) {
            Log.d(TAG,"repaced");
            Toast.makeText(context,uri + "被更新了 "+ appsReceiverWriter.getCurrentTime(),Toast.LENGTH_LONG).show();
            //将更新信息写入到外部存储
            packageName = uri.toString();
            appsChangedInfo = new AppsChangedInfo(packageName,appsReceiverWriter.getCurrentTime(),2);
            appsReceiverWriter.writeRecordToExternalStorage(appsChangedInfo.toString());
        }
    }
}
