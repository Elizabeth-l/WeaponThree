package ada.com.weaponthree.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import ada.com.weaponthree.MainActivity;

/**
 * Created by Ada的花花世界 on 2018/1/10.
 */

public class BootBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "Applogger";
    //开机自启动服务
    @Override
    public void onReceive(Context context, Intent intent) {
    //
        //启动AppLogger应用
        //判断收到的是什么广播
        String action = intent.getAction();

        if (Intent.ACTION_BOOT_COMPLETED.equals(action)) {
            //如果是开机则启动MainActivity
            Log.d(TAG, "ACTION_BOOT_COMPLETED");

            // start Activity
            Intent it = new Intent(context, MainActivity.class);
            it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(it);
        }
       /* Intent it = new Intent(context, MainActivity.class);

        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(it);*/


    }
}
