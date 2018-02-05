package ada.com.weaponthree.tools;

/**
 * Created by Ada的花花世界 on 2018/1/19.
 */

public class BatteryStatusInfo {

    private String cTime;//当前时间
    private int state;//手机电量状态，0表示低电量，1表示正常，2表示电量改变

    public BatteryStatusInfo(String cTime,int state){

        this.cTime = cTime;
        this.state = state;
    }

    public String getcTime() {
        return cTime;
    }

    public void setcTime(String cTime) {
        this.cTime = cTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return new StringBuffer(cTime).append(",").append(state).toString();
    }

}

