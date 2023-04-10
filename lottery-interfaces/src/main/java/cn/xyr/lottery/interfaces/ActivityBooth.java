package cn.xyr.lottery.interfaces;



import cn.xyr.lottery.infrastructure.dao.IActivityDao;
import cn.xyr.lottery.common.Constants;
import cn.xyr.lottery.common.Result;
import cn.xyr.lottery.infrastructure.po.Activity;
import cn.xyr.lottery.rpc.IActivityBooth;
import cn.xyr.lottery.rpc.dto.ActivityDto;
import cn.xyr.lottery.rpc.req.ActivityReq;
import cn.xyr.lottery.rpc.res.ActivityRes;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * @className: cn.xyr.lottery.interfaces.ActivityBooth
 * @description: TODO 活动展台
 * @author: xyr
 * @create: 2023-03-30 20:58
 */
@Service
public class ActivityBooth implements IActivityBooth {

    @Resource
    private IActivityDao activityDao;

    @Override
    public ActivityRes queryActivityById(ActivityReq req) {
        Activity activity = activityDao.queryActivityById(req.getActivityId());

        ActivityDto activityDto = new ActivityDto();
        activityDto.setActivityId(activity.getActivityId());
        activityDto.setActivityName(activity.getActivityName());
        activityDto.setActivityDesc(activity.getActivityDesc());
        activityDto.setBeginDateTime(activity.getBeginDateTime());
        activityDto.setEndDateTime(activity.getEndDateTime());
        activityDto.setStockCount(activity.getStockCount());
        activityDto.setTakeCount(activity.getTakeCount());

        return new ActivityRes(new Result(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo()),activityDto);
    }
}
