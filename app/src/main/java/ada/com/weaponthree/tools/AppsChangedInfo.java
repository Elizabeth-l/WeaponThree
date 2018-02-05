package ada.com.weaponthree.tools;

/**
 * Created by Ada的花花世界 on 2018/1/13.
 */

public class AppsChangedInfo {

    private String packageName;//包名
   // private String appLabel;//App名字
    private String cTime;//当前时间
    private int state;//app的状态，0，1，2分别表示被安装，被卸载，被更新

    public AppsChangedInfo(String packageName,String cTime,int state){
        this.packageName = packageName;
        this.cTime = cTime;
        this.state = state;
    }

    public String getcTime() {
        return cTime;
    }

    public void setcTime(String cTime) {
        this.cTime = cTime;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return new StringBuffer(cTime).append(",")
                .append(packageName).append(",").append(state).toString();
    }

}
