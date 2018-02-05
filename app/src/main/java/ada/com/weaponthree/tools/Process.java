package ada.com.weaponthree.tools;

/**
 * Created by Ada的花花世界 on 2018/1/10.
 */
//每一个进程的信息

public class Process {
        private String packageName;//包名
        private String activityName;//当前窗口的活动名
        private String startTime;//App开始使用时间
        private String endTime;//App结束使用时间

        public Process(String packageName,String activityName,String startTime, String endTime) {
            this.packageName = packageName;
            this.activityName = activityName;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String processName) {
            this.packageName = packageName;
        }

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        @Override
        public String toString() {
            return new StringBuffer(startTime).append(",")
                    .append(packageName).append(",").append(activityName).append(",").append(endTime).toString();
        }

}


