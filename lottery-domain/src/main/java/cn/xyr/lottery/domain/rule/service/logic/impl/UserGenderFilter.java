package cn.xyr.lottery.domain.rule.service.logic.impl;

import cn.xyr.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.xyr.lottery.domain.rule.service.logic.BaseLogic;
import org.springframework.stereotype.Component;

/**
 * @description: 性别规则
 * @author: xyr
 * @date: 2023-05-04 20:54
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
@Component
public class UserGenderFilter extends BaseLogic {
    @Override
    public String matterValue(DecisionMatterReq decisionMatter) {
        return decisionMatter.getValMap().get("gender").toString();
    }
}
