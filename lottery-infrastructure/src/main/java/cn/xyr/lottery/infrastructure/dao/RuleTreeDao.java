package cn.xyr.lottery.infrastructure.dao;

import cn.xyr.lottery.infrastructure.po.RuleTree;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 规则树配置DAO
 * @author: xyr
 * @date: 2023-05-04 22:14
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
@Mapper
public interface RuleTreeDao {

    /**
     * 规则树查询
     * @param id id
     * @return 规则树
     */
    RuleTree queryRuleTreeByTreeId(Long id);


    /**
     * 规则树简要信息查询
     * @param treeId 规则树ID
     * @return       规则树
     */
    RuleTree queryRuleTreeSummaryInfo(Long treeId);

}
