package ada.com.weaponthree.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

import ada.com.weaponthree.broadcastreceiver.BatteryChangedReceiver;
import ada.com.weaponthree.broadcastreceiver.ScreenOnOffReceiver;

/**
 * Created by Ada的花花世界 on 2018/1/19.
 */

public class RegisterService extends Service {
    ScreenOnOffReceiver sreceiver;
    BatteryChangedReceiver breceiver;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {

        super.onCreate();
        //注册广播接收者

        //创建广播接收者对象
        sreceiver = new ScreenOnOffReceiver();
        breceiver = new BatteryChangedReceiver();
        //创建意图过滤器对象
        IntentFilter sfilter = new IntentFilter();
        IntentFilter bfilter = new IntentFilter();
        //指定接收什么广播
        sfilter.addAction(Intent.ACTION_SCREEN_OFF);
        sfilter.addAction(Intent.ACTION_SCREEN_ON);
       // bfilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        bfilter.addAction(Intent.ACTION_BATTERY_LOW);
        bfilter.addAction(Intent.ACTION_BATTERY_OKAY);
        //注册广播接收者
        registerReceiver(sreceiver,sfilter);
        registerReceiver(breceiver,bfilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
