package cn.xyr.lottery.application.process.req;

/**
 * @description: 抽奖请求
 * @author: xyr
 * @date: 2023-05-03 22:19
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
public class DrawProcessReq {

    /** 用户ID */
    private String uId;
    /** 活动ID */
    private Long activityId;

    public DrawProcessReq() {
    }

    public DrawProcessReq(String uId, Long activityId) {
        this.uId = uId;
        this.activityId = activityId;
    }


    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
