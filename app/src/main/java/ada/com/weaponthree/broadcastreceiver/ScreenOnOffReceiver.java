package ada.com.weaponthree.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import ada.com.weaponthree.tools.Utility;

/**
 * Created by Ada的花花世界 on 2018/1/19.
 */

public class ScreenOnOffReceiver extends BroadcastReceiver {

    Utility utility;
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        utility = new Utility();
        String time = utility.getCurrentTime();
        if(Intent.ACTION_SCREEN_ON.equals(action)){
            Log.d("Applogger","Screen ON  "+time);
            utility.writeRecordToExternalStorage(
                    (new StringBuilder()).append("[").append(time).append(" session starts").append("\t\n").toString());

        }else if(Intent.ACTION_SCREEN_OFF.equals(action)){
            Log.d("Applogger","Screen OFF  "+time);
            utility.writeRecordToExternalStorage(
                    (new StringBuilder()).append("session ends ").append(time).append("]").append("\t\n").toString());

        }

    }
}
