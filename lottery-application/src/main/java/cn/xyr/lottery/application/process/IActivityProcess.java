package cn.xyr.lottery.application.process;

import cn.xyr.lottery.application.process.req.DrawProcessReq;
import cn.xyr.lottery.application.process.res.DrawProcessResult;
import cn.xyr.lottery.application.process.res.RuleQuantificationCrowdResult;
import cn.xyr.lottery.domain.rule.model.req.DecisionMatterReq;

/**
 * @description: 活动抽奖流程编排接口
 * @author: xyr
 * @date: 2023-05-03 22:15
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
public interface IActivityProcess {

    /**
     * 执行抽奖流程
     *
     * @param req 抽奖请求
     * @return 抽奖结果
     */
    DrawProcessResult doDrawProcessResult(DrawProcessReq req);

    /**
     * 规则量化人群，返回可参与的活动ID
     *
     * @param req 规则请求
     * @return  量化结果，用户可以参与的活动ID
     */
    RuleQuantificationCrowdResult doRuleQuantificationCrowd(DecisionMatterReq req);

}
