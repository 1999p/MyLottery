package cn.xyr.lottery.domain.rule.service.engine;

import cn.xyr.lottery.common.Constants;
import cn.xyr.lottery.domain.rule.model.aggregates.TreeRuleRich;
import cn.xyr.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.xyr.lottery.domain.rule.model.res.EngineResult;
import cn.xyr.lottery.domain.rule.model.vo.TreeNodeVO;
import cn.xyr.lottery.domain.rule.model.vo.TreeRootVO;
import cn.xyr.lottery.domain.rule.service.logic.LogicFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @description: 规则引擎基础类
 * @author: xyr
 * @date: 2023-05-04 21:18
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
public class EngineBase extends EngineConfig implements EngineFilter {

    private Logger logger = LoggerFactory.getLogger(EngineBase.class);

    @Override
    public EngineResult process(DecisionMatterReq matter) {
        throw new RuntimeException("未实现规则引擎服务");
    }

    /**
     * 决策执行
     *
     * @param treeRuleRich 规则树聚合
     * @param matter       决策物料
     * @return 规则树节点信息
     */
    protected TreeNodeVO engineDecisionMaker(TreeRuleRich treeRuleRich, DecisionMatterReq matter) {
        TreeRootVO treeRoot = treeRuleRich.getTreeRoot();
        Map<Long, TreeNodeVO> treeNodeMap = treeRuleRich.getTreeNodeMap();

        // 规则树根Id
        Long rootNodeId = treeRoot.getTreeRootNodeId();
        // 获得根节点信息
        TreeNodeVO treeNodeInfo = treeNodeMap.get(rootNodeId);

        // 节点类型[NodeType]；1子叶、2果实
        while (Constants.NodeType.STEM.equals(treeNodeInfo.getNodeType())) {
            // 获取节点规则key
            String ruleKey = treeNodeInfo.getRuleKey();
            // 获取规则key对应的逻辑过滤器
            LogicFilter logicFilter = logicFilterMap.get(ruleKey);
            // 通过逻辑过滤器，获取决策值
            String matterValue = logicFilter.matterValue(matter);
            // 获取下一个节点
            Long nextNode = logicFilter.filter(matterValue, treeNodeInfo.getTreeNodeLineInfoList());
            // 获取下一个节点信息
            treeNodeInfo = treeNodeMap.get(nextNode);
            logger.info("决策树引擎=>{} userId：{} treeId：{} treeNode：{} ruleKey：{} matterValue：{}",
                    treeRoot.getTreeName(), matter.getUserId(), matter.getTreeId(), treeNodeInfo.getTreeNodeId(), ruleKey, matterValue);
        }
        return treeNodeInfo;
    }
}
