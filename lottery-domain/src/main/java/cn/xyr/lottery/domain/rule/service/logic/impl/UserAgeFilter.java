package cn.xyr.lottery.domain.rule.service.logic.impl;

import cn.xyr.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.xyr.lottery.domain.rule.service.logic.BaseLogic;
import org.springframework.stereotype.Component;

/**
 * @description: TODO
 * @author: xyr
 * @date: 2023-05-04 20:55
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
@Component
public class UserAgeFilter extends BaseLogic {
    @Override
    public String matterValue(DecisionMatterReq decisionMatter) {
        return decisionMatter.getValMap().get("age").toString();
    }
}
