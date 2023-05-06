package cn.xyr.lottery.rpc.req;

import java.io.Serializable;

/**
 * @description: 抽奖请求
 * @author: xyr
 * @date: 2023-05-06 10:34
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
public class DrawReq implements Serializable {
    /** 用户ID */
    private String uId;
    /** 活动ID */
    private Long activityId;

    public DrawReq() {
    }

    public DrawReq(String uId, Long activityId) {
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
