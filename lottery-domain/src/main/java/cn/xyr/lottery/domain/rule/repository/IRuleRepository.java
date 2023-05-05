package cn.xyr.lottery.domain.rule.repository;

import cn.xyr.lottery.domain.rule.model.aggregates.TreeRuleRich;

/**
 * @description: 规则信息仓储服务接口
 * @author: xyr
 * @date: 2023-05-04 20:56
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
public interface IRuleRepository {

    /**
     * 查询规则决策树配置
     * @param treeId 决策树Id
     * @return       决策树配置
     */
    TreeRuleRich queryTreeRuleRich(Long treeId);
}
