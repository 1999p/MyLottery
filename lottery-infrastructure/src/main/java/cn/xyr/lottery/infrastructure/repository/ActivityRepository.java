package cn.xyr.lottery.infrastructure.repository;

import cn.xyr.lottery.common.Constants;
import cn.xyr.lottery.domain.activity.model.vo.*;
import cn.xyr.lottery.domain.activity.repository.IActivityRepository;
import cn.xyr.lottery.infrastructure.dao.IActivityDao;
import cn.xyr.lottery.infrastructure.dao.IAwardDao;
import cn.xyr.lottery.infrastructure.dao.IStrategyDao;
import cn.xyr.lottery.infrastructure.dao.IStrategyDetailDao;
import cn.xyr.lottery.infrastructure.po.Activity;
import cn.xyr.lottery.infrastructure.po.Award;
import cn.xyr.lottery.infrastructure.po.Strategy;
import cn.xyr.lottery.infrastructure.po.StrategyDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: TODO 活动表仓储服务
 * @author: xyr
 * @date: 2023-04-14 10:37
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
@Component
public class ActivityRepository implements IActivityRepository {

    @Resource
    private IActivityDao activityDao;
    @Resource
    private IAwardDao awardDao;
    @Resource
    private IStrategyDao strategyDao;
    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Override
    public void addActivity(ActivityVO activity) {
        Activity req = new Activity();
        BeanUtils.copyProperties(activity, req);
        activityDao.insert(req);

    }

    @Override
    public void addAward(List<AwardVO> awardList) {
        ArrayList<Award> req = new ArrayList<>();
        for (AwardVO awardVO :awardList) {
            Award award = new Award();
            BeanUtils.copyProperties(awardVO,award);
            req.add(award);
        }

        awardDao.insertList(req);
    }

    @Override
    public void addStrategy(StrategyVO strategy) {
        Strategy req = new Strategy();
        BeanUtils.copyProperties(strategy,req);
        strategyDao.insert(req);
    }


    @Override
    public void addStrategyDetailList(List<StrategyDetailVO> strategyDetailList) {
        ArrayList<StrategyDetail> req = new ArrayList<>();
        for (StrategyDetailVO strategyDetailVO :
                strategyDetailList) {
            StrategyDetail strategyDetail = new StrategyDetail();
            BeanUtils.copyProperties(strategyDetailVO,strategyDetail);
            req.add(strategyDetail);
        }
        strategyDetailDao.insertList(req);
    }

    @Override
    public boolean alterStatus(Long activityId, Enum<Constants.ActivityState> beforeState, Enum<Constants.ActivityState> afterState) {
        AlterStateVO alterStateVO = new AlterStateVO(activityId, ((Constants.ActivityState) beforeState).getCode(), ((Constants.ActivityState) afterState).getCode());
        int count = activityDao.alterState(alterStateVO);

        return 1== count;
    }
}
