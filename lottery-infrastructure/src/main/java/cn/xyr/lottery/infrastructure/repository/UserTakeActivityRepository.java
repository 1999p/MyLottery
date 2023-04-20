package cn.xyr.lottery.infrastructure.repository;

import cn.xyr.lottery.domain.activity.repository.IUserTakeActivityRepository;
import cn.xyr.lottery.infrastructure.dao.IUserTakeActivityCountDao;
import cn.xyr.lottery.infrastructure.dao.IUserTakeActivityDao;
import cn.xyr.lottery.infrastructure.po.UserTakeActivity;
import cn.xyr.lottery.infrastructure.po.UserTakeActivityCount;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @description: TODO 用户参与活动仓储
 * @author: xyr
 * @date: 2023-04-19 23:08
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
@Component
public class UserTakeActivityRepository implements IUserTakeActivityRepository {

    @Resource
    private IUserTakeActivityCountDao userTakeActivityCountDao;

    @Resource
    private IUserTakeActivityDao userTakeActivityDao;

    @Override
    public int subtractionLeftCount(Long activityId, String activityName, Integer takeCount, Integer userTakeLeftCount, String uId, Date partakeDate) {
        if (null == userTakeLeftCount){
            UserTakeActivityCount userTakeActivityCount = new UserTakeActivityCount();
            userTakeActivityCount.setuId(uId);
            userTakeActivityCount.setActivityId(activityId);
            userTakeActivityCount.setTotalCount(takeCount);
            userTakeActivityCount.setLeftCount(takeCount - 1);
            userTakeActivityCountDao.insert(userTakeActivityCount);
            return 1;
        }else {
            UserTakeActivityCount userTakeActivityCount = new UserTakeActivityCount();
            userTakeActivityCount.setuId(uId);
            userTakeActivityCount.setActivityId(activityId);
            return  userTakeActivityCountDao.updateLeftCount(userTakeActivityCount);
        }

    }

    @Override
    public void takeActivity(Long activityId, String activityName, Integer takeCount, Integer userTakeLeftCount, String uId, Date takeDate, Long takeId) {
        UserTakeActivity userTakeActivity = new UserTakeActivity();
        userTakeActivity.setuId(uId);
        userTakeActivity.setTakeId(takeId);
        userTakeActivity.setActivityId(activityId);
        userTakeActivity.setActivityName(activityName);
        userTakeActivity.setTakeDate(takeDate);
        if (null == userTakeLeftCount){
            userTakeActivity.setTakeCount(1);
        }else {
            userTakeActivity.setTakeCount(takeCount - userTakeLeftCount);
        }
        String uuid = uId + "_" +activityId + "_" + userTakeActivity.getTakeCount();
        userTakeActivity.setUuid(uuid);
        userTakeActivityDao.insert(userTakeActivity);
    }
}
