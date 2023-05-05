package cn.xyr.lottery.domain.rule.service.logic;

import cn.xyr.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.xyr.lottery.domain.rule.model.vo.TreeNodeLineVO;

import java.util.List;

/**
 * @description: 规则过滤器接口 定义了适配的通用接口，逻辑决策器、获取决策值，每一个提供决策能力的节点都必须实现此接口
 * @author: xyr
 * @date: 2023-05-04 19:58
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
public interface LogicFilter {

    /**
     * 逻辑决策器
     * @param matterValue           决策值
     * @param treeNodeLineInfoList  决策节点
     * @return                      下一个节点Id
     */
    Long filter(String matterValue, List<TreeNodeLineVO> treeNodeLineInfoList);

    /**
     * 获取决策值
     * @param decisionMatter 决策物料
     * @return               决策值
     */
    String matterValue(DecisionMatterReq decisionMatter);

}
