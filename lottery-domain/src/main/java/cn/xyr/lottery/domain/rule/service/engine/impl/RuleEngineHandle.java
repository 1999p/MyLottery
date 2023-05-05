package cn.xyr.lottery.domain.rule.service.engine.impl;

import cn.xyr.lottery.domain.rule.model.aggregates.TreeRuleRich;
import cn.xyr.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.xyr.lottery.domain.rule.model.res.EngineResult;
import cn.xyr.lottery.domain.rule.model.vo.TreeNodeVO;
import cn.xyr.lottery.domain.rule.repository.IRuleRepository;
import cn.xyr.lottery.domain.rule.service.engine.EngineBase;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: TODO
 * @author: xyr
 * @date: 2023-05-04 21:35
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
@Service("ruleEngineHandle")
public class RuleEngineHandle extends EngineBase {

    @Resource
    private IRuleRepository ruleRepository;

    @Override
    public EngineResult process(DecisionMatterReq matter) {
        //决策规则树
        TreeRuleRich treeRuleRich = ruleRepository.queryTreeRuleRich(matter.getTreeId());
        if ( null == treeRuleRich){
            throw new RuntimeException("Tree Rule is null");
        }

        //决策节点
        TreeNodeVO treeNodeInfo = engineDecisionMaker(treeRuleRich, matter);

        //决策结果
        return new EngineResult(matter.getUserId(),treeNodeInfo.getTreeId(),
                treeNodeInfo.getTreeNodeId(), treeNodeInfo.getNodeValue());
    }
}
