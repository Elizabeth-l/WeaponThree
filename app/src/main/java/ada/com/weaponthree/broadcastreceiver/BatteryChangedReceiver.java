package ada.com.weaponthree.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import ada.com.weaponthree.tools.BatteryStatusInfo;
import ada.com.weaponthree.tools.BatteryStatusWriter;

/**
 * Created by Ada的花花世界 on 2018/1/19.
 */

public class BatteryChangedReceiver extends BroadcastReceiver {
    BatteryStatusInfo battery;
    BatteryStatusWriter writer;
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        writer = new BatteryStatusWriter();
        //获得当前时间
        String time = writer.getCurrentTime();

        if(Intent.ACTION_BATTERY_LOW.equals(action)){
            Log.d("Applogger","ACTION_BATTERY_LOW "+time);
            //将电量不足的情况写入外部存储
            battery = new BatteryStatusInfo(time,0);
            writer.writeRecordToExternalStorage(battery.toString());
        }else if (Intent.ACTION_BATTERY_OKAY.equals(action)){
            Log.d("Applogger","ACTION_BATTERY_OKAY"+time);
            //将电量正常的情况写入外部存储
            battery = new BatteryStatusInfo(time,1);
            writer.writeRecordToExternalStorage(battery.toString());
        }/*else if(Intent.ACTION_BATTERY_CHANGED.equals(action)){
            Log.d("Applogger","ACTION_BATTERY_CHANGED"+time);
            //将电量改变的状态信息写入外部存储
            battery = new BatteryStatusInfo(time,2);
            writer.writeRecordToExternalStorage(battery.toString());
        }*/
    }
}
