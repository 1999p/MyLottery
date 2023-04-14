package cn.xyr.lottery.domain.activity.model.vo;

/**
 * @description: TODO 变更活动状态对象
 * @author: xyr
 * @date: 2023-04-13 17:35
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
public class AlterStateVO {

    /**
     * 活动Id
     */
    private Long activityId;

    /** 更新前状态 */
    private Integer beforeState;

    /** 更新后状态 */
    private Integer afterState;


    public AlterStateVO() {
    }

    public AlterStateVO(Long activityId, Integer beforeState, Integer afterState) {
        this.activityId = activityId;
        this.beforeState = beforeState;
        this.afterState = afterState;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Integer getBeforeState() {
        return beforeState;
    }

    public void setBeforeState(Integer beforeState) {
        this.beforeState = beforeState;
    }

    public Integer getAfterState() {
        return afterState;
    }

    public void setAfterState(Integer afterState) {
        this.afterState = afterState;
    }


    @Override
    public String toString() {
        return "AlterStateVO{" +
                "activityId=" + activityId +
                ", beforeState=" + beforeState +
                ", afterState=" + afterState +
                '}';
    }
}
