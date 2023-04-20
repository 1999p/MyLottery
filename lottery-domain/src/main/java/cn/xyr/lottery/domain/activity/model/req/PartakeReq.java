package cn.xyr.lottery.domain.activity.model.req;

import java.util.Date;

/**
 * @description: TODO 参与活动请求
 * @author: xyr
 * @date: 2023-04-19 21:04
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
public class PartakeReq {

    /** 用户ID */
    private String uId;
    /** 活动ID */
    private Long activityId;

    /**
     * 活动领取时间
     */
    private Date partakeDate;

    public PartakeReq() {
    }

    public PartakeReq(String uId, Long activityId) {
        this.uId = uId;
        this.activityId = activityId;
        this.partakeDate = new Date();

    }

    public PartakeReq(String uId, Long activityId, Date partakeDate) {
        this.uId = uId;
        this.activityId = activityId;
        this.partakeDate = partakeDate;
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

    public Date getPartakeDate() {
        return partakeDate;
    }

    public void setPartakeDate(Date partakeDate) {
        this.partakeDate = partakeDate;
    }
}
