package cn.xyr.lottery.rpc.req;

import java.io.Serializable;

/**
 * @className: cn.xyr.lottery.req.ActivityReq
 * @description: TODO
 * @author: xyr
 * @create: 2023-03-30 21:05
 */
public class ActivityReq implements Serializable {

    private Long activityId;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
