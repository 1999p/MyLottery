package cn.xyr.lottery.application.process.impl;

import cn.xyr.lottery.application.process.IActivityProcess;
import cn.xyr.lottery.application.process.req.DrawProcessReq;
import cn.xyr.lottery.application.process.res.DrawProcessResult;
import cn.xyr.lottery.common.Constants;
import cn.xyr.lottery.domain.activity.model.req.PartakeReq;
import cn.xyr.lottery.domain.activity.model.res.PartakeResult;
import cn.xyr.lottery.domain.activity.model.vo.DrawOrderVO;
import cn.xyr.lottery.domain.activity.service.partake.IActivityPartake;
import cn.xyr.lottery.domain.strategy.model.req.DrawReq;
import cn.xyr.lottery.domain.strategy.model.res.DrawResult;
import cn.xyr.lottery.domain.strategy.model.vo.DrawAwardInfo;
import cn.xyr.lottery.domain.strategy.service.draw.IDrawExec;
import cn.xyr.lottery.domain.support.ids.IIdGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description: 活动抽奖流程编排
 * @author: xyr
 * @date: 2023-05-03 22:25
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
@Service
public class ActivityProcessImpl implements IActivityProcess {

    @Resource
    private IActivityPartake activityPartake;

    @Resource
    private IDrawExec drawExec;

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    @Override
    public DrawProcessResult doDrawProcessResult(DrawProcessReq req) {
        //1.领取活动
        PartakeResult partakeResult = activityPartake.doPartake(new PartakeReq(req.getuId(), req.getActivityId()));
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(partakeResult.getCode())){
            return new DrawProcessResult(Constants.ResponseCode.LOSING_DRAW.getCode(), Constants.ResponseCode.LOSING_DRAW.getInfo());
        }
        Long strategyId = partakeResult.getStrategyId();
        Long takeId = partakeResult.getTakeId();

        //2. 执行抽奖
        DrawResult drawResult = drawExec.doDrawExec(new DrawReq(req.getuId(), strategyId, String.valueOf(takeId)));
        if (Constants.DrawState.FAIL.getCode().equals(drawResult.getDrawState())){
            return new DrawProcessResult(Constants.ResponseCode.LOSING_DRAW.getCode(), Constants.ResponseCode.LOSING_DRAW.getInfo());

        }
        //获取中奖奖品信息
        DrawAwardInfo drawAwardInfo = drawResult.getDrawAwardInfo();

        //3.结果落库
        activityPartake.recordDrawOrder(buildDrawOrderVO(req,strategyId,takeId,drawAwardInfo));
        //4.发送MQ，出发发奖流程

        //5.返回结果
        return new DrawProcessResult(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
    }

    private DrawOrderVO buildDrawOrderVO(DrawProcessReq req,Long strategyId,Long takeId,DrawAwardInfo drawAwardInfo) {
        long orderId = idGeneratorMap.get(Constants.Ids.SnowFlake).nextId();
        DrawOrderVO drawOrderVO = new DrawOrderVO();
        drawOrderVO.setuId(req.getuId());
        drawOrderVO.setTakeId(takeId);
        drawOrderVO.setActivityId(req.getActivityId());
        drawOrderVO.setOrderId(orderId);
        drawOrderVO.setStrategyId(strategyId);
        drawOrderVO.setStrategyMode(drawAwardInfo.getStrategyMode());
        drawOrderVO.setGrantType(drawAwardInfo.getGrantType());
        drawOrderVO.setGrantDate(drawOrderVO.getGrantDate());
        drawOrderVO.setGrantState(drawOrderVO.getGrantState());
        drawOrderVO.setAwardId(drawAwardInfo.getAwardId());
        drawOrderVO.setAwardType(drawAwardInfo.getAwardType());
        drawOrderVO.setAwardName(drawOrderVO.getAwardName());
        drawOrderVO.setAwardContent(drawOrderVO.getAwardContent());
        return drawOrderVO;
    }
}
