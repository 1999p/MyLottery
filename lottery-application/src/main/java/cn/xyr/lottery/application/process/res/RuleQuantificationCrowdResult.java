package cn.xyr.lottery.application.process.res;

import cn.xyr.lottery.common.Result;

/**
 * @description: 规则量化过滤结果
 * @author: xyr
 * @date: 2023-05-06 11:35
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
public class RuleQuantificationCrowdResult extends Result {
    /**
     * 活动ID
     */
    private Long activityId;

    public RuleQuantificationCrowdResult(String code, String info) {
        super(code, info);
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
