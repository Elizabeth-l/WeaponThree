package ada.com.weaponthree.tools;

import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by Ada的花花世界 on 2018/1/19.
 */

public class BatteryStatusWriter {
    public String filePath;

    //构造函数，为filePath赋初值
    public BatteryStatusWriter() {
        // 获得储存路径，app安装/更新和卸载的信息
        File sdCardDir = Environment.getExternalStorageDirectory();
        if (sdCardDir != null) {
            filePath = sdCardDir.toString() + "/BatteryStatusInfo.txt";
        } else {
            throw new RuntimeException("sd card not found");
        }
    }

    //返回当前时间
    public String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        return new StringBuffer(String.valueOf(year))
                .append("-").append(month).append("-").append(day).append(" ")
                .append(hour).append(":").append(minute).append(":").append(second)
                .toString();
    }

    //将信息写入外部存储空间
    public void writeRecordToExternalStorage(String record) {
        //
        //调用writeRecordToFile
        writeRecordToFile(record, filePath);
    }

    private void writeRecordToFile(String record, String fileName) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName, true);
            //每写一条记录就换行
            fileWriter.write(record + "\r\n");
            fileWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e1) {
                    throw new RuntimeException("fail to close file");
                }
            }
        }
    }

}
