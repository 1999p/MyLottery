package cn.xyr.lottery.domain.activity.service.partake;

import cn.xyr.lottery.domain.activity.model.req.PartakeReq;
import cn.xyr.lottery.domain.activity.model.vo.ActivityBillVO;
import cn.xyr.lottery.domain.activity.repository.IActivityRepository;

import javax.annotation.Resource;

/**
 * @description: TODO 活动领取模操作，一些通用的数据服务
 * @author: xyr
 * @date: 2023-04-19 21:15
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
public class ActivityPartakeSupport {

    @Resource
    protected IActivityRepository activityRepository;

    protected ActivityBillVO queryActivityBill(PartakeReq req){
        return activityRepository.queryActivityBill(req);
    }
}
