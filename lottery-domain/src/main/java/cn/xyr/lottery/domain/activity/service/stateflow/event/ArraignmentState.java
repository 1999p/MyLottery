package cn.xyr.lottery.domain.activity.service.stateflow.event;

import cn.xyr.lottery.common.Constants;
import cn.xyr.lottery.common.Result;
import cn.xyr.lottery.domain.activity.service.stateflow.AbstractState;
import org.springframework.stereotype.Component;

/**
 * @description: TODO 提审状态
 * @author: xyr
 * @date: 2023-04-13 20:15
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
@Component
public class ArraignmentState extends AbstractState {
    /**
     * 活动提审
     *
     * @param activityId   活动Id
     * @param currentState 当前状态
     * @return 执行结果
     */
    @Override
    public Result arraignment(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR,"待审核状态不可重复提审");
    }

    /**
     * 审核通过
     *
     * @param activityId   活动Id
     * @param currentState 当前状态
     * @return 执行结果
     */
    @Override
    public Result checkPass(Long activityId, Enum<Constants.ActivityState> currentState) {
        boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constants.ActivityState.PASS);
        return isSuccess ? Result.buildResult(Constants.ResponseCode.SUCCESS,"活动审核通过完成") :
                Result.buildErrorResult("活动状态变更失败");
    }

    /**
     * 审核拒绝
     *
     * @param activityId   活动Id
     * @param currentState 当前状态
     * @return 执行结果
     */
    @Override
    public Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentState) {
        boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constants.ActivityState.REFUSE);
        return isSuccess ? Result.buildResult(Constants.ResponseCode.SUCCESS,"活动审核拒绝完成") :
                Result.buildErrorResult("活动状态变更失败");
    }

    /**
     * 撤审撤销
     *
     * @param activityId   活动Id
     * @param currentState 当前状态
     * @return 执行结果
     */
    @Override
    public Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentState) {
        boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constants.ActivityState.REVOKE);

        return isSuccess ? Result.buildResult(Constants.ResponseCode.SUCCESS,"活动审核撤销回到编辑中") :
                Result.buildErrorResult("活动状态变更失败");
    }

    /**
     * 活动关闭
     *
     * @param activityId   活动Id
     * @param currentState 当前状态
     * @return 执行结果
     */
    @Override
    public Result close(Long activityId, Enum<Constants.ActivityState> currentState) {
        boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constants.ActivityState.CLOSE);

        return isSuccess ? Result.buildResult(Constants.ResponseCode.SUCCESS,"活动审核关闭完成") :
                Result.buildErrorResult("活动状态变更失败");
    }

    /**
     * 活动开启
     *
     * @param activityId   活动Id
     * @param currentState 当前状态
     * @return 执行结果
     */
    @Override
    public Result open(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR, "非关闭活动不可开启");
    }

    /**
     * 活动执行
     *
     * @param activityId   活动Id
     * @param currentState 当前状态
     * @return 执行结果
     */
    @Override
    public Result doing(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR,"待审核活动不可执行活动中变更");
    }
}
