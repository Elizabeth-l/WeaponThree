package ada.com.weaponthree.engine;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ada.com.weaponthree.tools.AppInfo;
import ada.com.weaponthree.tools.AppInfoWriter;

/**
 * Created by Ada的花花世界 on 2018/1/21.
 */

public class AppInfoProvider {
    //获取已安装应用
    public static ArrayList<AppInfo> getInstalledApps(Context ctx){
        PackageManager pm = ctx.getPackageManager();
        //获取所有已安装因应用
        List<PackageInfo> installedPackages = pm.getInstalledPackages(0);

        ArrayList<AppInfo> list = new ArrayList<AppInfo>();
        AppInfoWriter appInfoWriter = new AppInfoWriter();
        String time  = appInfoWriter.getCurrentTime();
        appInfoWriter.writeRecordToExternalStorage(time+"\t\n");

        for(PackageInfo packageInfo:installedPackages){
            AppInfo appInfo = new AppInfo();
            String packageName = packageInfo.packageName;//包名
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;//应用信息
            String name = applicationInfo.loadLabel(pm).toString();//应用名

            appInfo.name = name;
            appInfo.packageName = packageName;
            int flag = applicationInfo.flags;//获取应用标记
            //判断是系统应用还是用户应用
            if((flag&ApplicationInfo.FLAG_SYSTEM)==ApplicationInfo.FLAG_SYSTEM){
                appInfo.isRom = true;
                appInfo.isUser = false;
            }else {
                appInfo.isUser = true;
                appInfo.isRom = false;
            }

            Log.d("Applogger",packageName+"  ,   "+name+ appInfo.isRom+","+appInfo.isUser);
            appInfoWriter.writeRecordToExternalStorage(appInfo.toString());

            list.add(appInfo);
        }

        return list;
    }
}
