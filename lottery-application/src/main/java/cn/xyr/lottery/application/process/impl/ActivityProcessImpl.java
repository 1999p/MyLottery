package cn.xyr.lottery.application.process.impl;

import cn.xyr.lottery.application.process.IActivityProcess;
import cn.xyr.lottery.application.process.req.DrawProcessReq;
import cn.xyr.lottery.application.process.res.DrawProcessResult;
import cn.xyr.lottery.application.process.res.RuleQuantificationCrowdResult;
import cn.xyr.lottery.common.Constants;
import cn.xyr.lottery.domain.activity.model.req.PartakeReq;
import cn.xyr.lottery.domain.activity.model.res.PartakeResult;
import cn.xyr.lottery.domain.activity.model.vo.DrawOrderVO;
import cn.xyr.lottery.domain.activity.service.partake.IActivityPartake;
import cn.xyr.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.xyr.lottery.domain.rule.model.res.EngineResult;
import cn.xyr.lottery.domain.rule.service.engine.EngineFilter;
import cn.xyr.lottery.domain.strategy.model.req.DrawReq;
import cn.xyr.lottery.domain.strategy.model.res.DrawResult;
import cn.xyr.lottery.domain.strategy.model.vo.DrawAwardVO;
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

    @Resource(name = "ruleEngineHandle")
    private EngineFilter engineFilter;
    @Override
    public DrawProcessResult doDrawProcessResult(DrawProcessReq req) {

        //1.领取活动 activityPartake.doPartake
        PartakeResult partakeResult = activityPartake.doPartake(new PartakeReq(req.getuId(), req.getActivityId()));
        //校验是否领取成功
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(partakeResult.getCode())){
            return new DrawProcessResult(Constants.ResponseCode.LOSING_DRAW.getCode(), Constants.ResponseCode.LOSING_DRAW.getInfo());
        }
        Long strategyId = partakeResult.getStrategyId();
        Long takeId = partakeResult.getTakeId();

        //2. 执行抽奖 drawExec.doDrawExec
        DrawResult drawResult = drawExec.doDrawExec(new DrawReq(req.getuId(), strategyId));
        //校验执行抽象是否成功
        if (Constants.DrawState.FAIL.getCode().equals(drawResult.getDrawState())){
            return new DrawProcessResult(Constants.ResponseCode.LOSING_DRAW.getCode(), Constants.ResponseCode.LOSING_DRAW.getInfo());

        }
        //获取中奖奖品信息
        DrawAwardVO drawAwardVO = drawResult.getDrawAwardInfo();

        //3.结果落库
        activityPartake.recordDrawOrder(buildDrawOrderVO(req,strategyId,takeId,drawAwardVO));

        //4.发送MQ，出发发奖流程

        //5.返回结果
        return new DrawProcessResult(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo(),drawAwardVO);
    }

    @Override
    public RuleQuantificationCrowdResult doRuleQuantificationCrowd(DecisionMatterReq req) {
        //1.量化决策
        EngineResult engineResult = engineFilter.process(req);

        if (!engineResult.isSuccess()){
            return new RuleQuantificationCrowdResult(Constants.ResponseCode.RULE_ERR.getCode(), Constants.ResponseCode.RULE_ERR.getInfo());
        }

        //2.封装结果
        RuleQuantificationCrowdResult ruleQuantificationCrowdResult = new RuleQuantificationCrowdResult(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
        ruleQuantificationCrowdResult.setActivityId(Long.valueOf(engineResult.getNodeValue()));
        return ruleQuantificationCrowdResult;
    }

    private DrawOrderVO buildDrawOrderVO(DrawProcessReq req, Long strategyId, Long takeId, DrawAwardVO drawAwardVO) {
        long orderId = idGeneratorMap.get(Constants.Ids.SnowFlake).nextId();
        DrawOrderVO drawOrderVO = new DrawOrderVO();
        drawOrderVO.setuId(req.getuId());
        drawOrderVO.setTakeId(takeId);
        drawOrderVO.setActivityId(req.getActivityId());
        drawOrderVO.setOrderId(orderId);
        drawOrderVO.setStrategyId(strategyId);
        drawOrderVO.setStrategyMode(drawAwardVO.getStrategyMode());
        drawOrderVO.setGrantType(drawAwardVO.getGrantType());
        drawOrderVO.setGrantDate(drawAwardVO.getGrantDate());
        drawOrderVO.setGrantState(Constants.GrantState.INIT.getCode());
        drawOrderVO.setAwardId(drawAwardVO.getAwardId());
        drawOrderVO.setAwardType(drawAwardVO.getAwardType());
        drawOrderVO.setAwardName(drawAwardVO.getAwardName());
        drawOrderVO.setAwardContent(drawAwardVO.getAwardContent());
        return drawOrderVO;
    }
}
