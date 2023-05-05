package cn.xyr.lottery.domain.rule.service.engine;

import cn.xyr.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.xyr.lottery.domain.rule.model.res.EngineResult;

/**
 * @description: 规则过滤器引擎
 * @author: xyr
 * @date: 2023-05-04 21:13
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
public interface EngineFilter {

    /**
     * 规则过滤接口
     *
     * @param matter 规则决策物料
     * @return 规则决策结果
     */
    EngineResult process(final DecisionMatterReq matter);
}
