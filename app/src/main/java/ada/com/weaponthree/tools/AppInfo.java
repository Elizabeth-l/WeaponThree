package ada.com.weaponthree.tools;

/**
 * Created by Ada的花花世界 on 2018/1/21.
 */

/**
 * 应用信息封装
 */

public class AppInfo {
    public String name;
    public String packageName;

    public boolean isRom;
    public boolean isUser;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringBuffer(packageName).append(",")
                .append(name).append(",").append(isRom).append(",").append(isUser).toString();
    }

}
