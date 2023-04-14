package cn.xyr.lottery.domain.activity.service.stateflow.event;

import cn.xyr.lottery.common.Constants;
import cn.xyr.lottery.common.Result;
import cn.xyr.lottery.domain.activity.service.stateflow.AbstractState;
import org.springframework.stereotype.Component;

/**
 * @description: TODO 审核拒绝状态 可撤销审核；可关闭审核;已拒绝审核不可重复审核
 * @author: xyr
 * @date: 2023-04-13 20:51
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
@Component
public class RefuseState extends AbstractState {
    @Override
    public Result arraignment(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR, "已审核状态不可重复提审");

    }

    @Override
    public Result checkPass(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR, "已审核状态不可重复审核");

    }

    @Override
    public Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR, "活动审核拒绝不可重复审核");

    }

    @Override
    public Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentState) {
        boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constants.ActivityState.REVOKE);

        return isSuccess ? Result.buildResult(Constants.ResponseCode.SUCCESS, "撤销审核完成") :
                Result.buildErrorResult("活动状态变更失败");
    }

    @Override
    public Result close(Long activityId, Enum<Constants.ActivityState> currentState) {
        boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constants.ActivityState.CLOSE);

        return isSuccess ? Result.buildResult(Constants.ResponseCode.SUCCESS, "活动审核关闭完成") :
                Result.buildErrorResult("活动状态变更失败");
    }

    @Override
    public Result open(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR, "非关闭活动不可开启");

    }

    @Override
    public Result doing(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR, "审核拒绝不可执行活动为进行中");

    }
}
