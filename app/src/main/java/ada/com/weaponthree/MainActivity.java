package ada.com.weaponthree;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import ada.com.weaponthree.accessibilityservice.LogAccessibilityService;
import ada.com.weaponthree.engine.AppInfoProvider;
import ada.com.weaponthree.service.RegisterService;
import ada.com.weaponthree.tools.AppInfo;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Applogger";
    private static final int REQUEST_CODE = 2223;

    private AlertDialog.Builder builder;

    ArrayList<AppInfo> mList;//已安装应用的集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"Activity create");
        // 跳转辅助功能服务设置
        builder = new AlertDialog.Builder(this);
        builder.setTitle("请将WeaponThree的无障碍服务设置为开");
        builder.setMessage("谢谢");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                startActivity(intent);
                dialog.dismiss();
                //MainActivity.this.finish();
            }
        });

        /*builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });*/

        Button startButton =(Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Applogger", "startbutton clicked");

                // start Accessibilityservice记录App使用情况
                Intent intent = new Intent(MainActivity.this, LogAccessibilityService.class);
                startService(intent);
                builder.create().show();
            }
        });

        //开启RegisterService服务，记录屏幕开关
        Intent it = new Intent(MainActivity.this, RegisterService.class);
        startService(it);

        //一旦MainActivity启动就会记录已安装的app
        initData();

    }

    private void initData(){

        new Thread(){
            public void run(){
                mList = AppInfoProvider.getInstalledApps(getApplicationContext());
            }
        }.start();

    }

    //申请存储权限
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    findViewById(R.id.empty_page).setVisibility(View.GONE);
                    findViewById(R.id.page).setVisibility(View.VISIBLE);
                } else {
                    findViewById(R.id.page).setVisibility(View.GONE);
                    findViewById(R.id.empty_page).setVisibility(View.VISIBLE);
                    Toast.makeText(this, "没有获得存储权限", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"Activity onStart");

        // 没有拿到存储权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        }

        //开启辅助功能服务
        /*AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("需要开启辅助功能的无障碍服务");
        b.setMessage("去开权限");
        b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                startActivity(intent);
            }
        });*/
       // open();



    }

    /*private void open(){
        try{
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivityForResult(intent,1);
            Toast.makeText(this,"打开辅助功能", Toast.LENGTH_LONG).show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }*/


}
