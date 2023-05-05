package cn.xyr.lottery.infrastructure.repository;

import cn.xyr.lottery.common.Constants;
import cn.xyr.lottery.domain.rule.model.aggregates.TreeRuleRich;
import cn.xyr.lottery.domain.rule.model.vo.TreeNodeLineVO;
import cn.xyr.lottery.domain.rule.model.vo.TreeNodeVO;
import cn.xyr.lottery.domain.rule.model.vo.TreeRootVO;
import cn.xyr.lottery.domain.rule.repository.IRuleRepository;
import cn.xyr.lottery.infrastructure.dao.RuleTreeDao;
import cn.xyr.lottery.infrastructure.dao.RuleTreeNodeDao;
import cn.xyr.lottery.infrastructure.dao.RuleTreeNodeLineDao;
import cn.xyr.lottery.infrastructure.po.RuleTree;
import cn.xyr.lottery.infrastructure.po.RuleTreeNode;
import cn.xyr.lottery.infrastructure.po.RuleTreeNodeLine;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 规则信息仓储服务
 * @author: xyr
 * @date: 2023-05-04 22:13
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
@Repository
public class RuleRepository implements IRuleRepository {

    @Resource
    private RuleTreeDao ruleTreeDao;

    @Resource
    private RuleTreeNodeDao ruleTreeNodeDao;

    @Resource
    private RuleTreeNodeLineDao ruleTreeNodeLineDao;

    @Override
    public TreeRuleRich queryTreeRuleRich(Long treeId) {

        //  1 获取对应的规则树
        RuleTree ruleTree = ruleTreeDao.queryRuleTreeByTreeId(treeId);
        // 1.2创建规则树根配置类 并为其赋值
        TreeRootVO treeRoot = new TreeRootVO();
        treeRoot.setTreeId(ruleTree.getId());
        treeRoot.setTreeRootNodeId(ruleTree.getTreeRootNodeId());
        treeRoot.setTreeName(ruleTree.getTreeName());


        // 树节点 -> 树连接线
        Map<Long, TreeNodeVO> treeNodeMap = new HashMap<>();

        // 查询规则树节点集合
        List<RuleTreeNode> ruleTreeNodeList = ruleTreeNodeDao.queryRuleNodeList(treeId);
        // 遍历规则树节点
        for (RuleTreeNode treeNode : ruleTreeNodeList) {
            // 规则树线信息集合
            List<TreeNodeLineVO> treeNodeLineInfoList = new ArrayList<>();

            // 若为树茎节点 将其封装为规则树连线对象，并获得子节点
            if (Constants.NodeType.STEM.equals(treeNode.getNodeType())) {
                // 规则树节点连线对象
                RuleTreeNodeLine ruleTreeNodeLineReq = new RuleTreeNodeLine();
                ruleTreeNodeLineReq.setTreeId(treeId);
                ruleTreeNodeLineReq.setNodeIdFrom(treeNode.getId());

                // 根据当前规则树节点连线对象，获取当前规则树节点连线集合(子节点)
                List<RuleTreeNodeLine> ruleTreeNodeLineList = ruleTreeNodeLineDao.queryRuleTreeNodeLineList(ruleTreeNodeLineReq);

                // 遍历当前规则树节点连线集合,将树节点连线封装并添加到树节点连线信息集合中
                for (RuleTreeNodeLine nodeLine : ruleTreeNodeLineList){
                    //规则树节点连线封装为规则树线信息 区别是 规则树线信息不包括 树Id和主键Id
                    TreeNodeLineVO treeNodeLineInfo = new TreeNodeLineVO();
                    treeNodeLineInfo.setNodeIdFrom(nodeLine.getNodeIdFrom());
                    treeNodeLineInfo.setNodeIdTo(nodeLine.getNodeIdTo());
                    //设置限定类型
                    treeNodeLineInfo.setRuleLimitType(nodeLine.getRuleLimitType());
                    //设置限定值
                    treeNodeLineInfo.setRuleLimitValue(nodeLine.getRuleLimitValue());

                    //将子节点添加到集合中
                    treeNodeLineInfoList.add(treeNodeLineInfo);
                }

            }

            //设置规则树节点信息
            TreeNodeVO treeNodeInfo = new TreeNodeVO();
            treeNodeInfo.setTreeId(treeNode.getTreeId());
            treeNodeInfo.setTreeNodeId(treeNode.getId());
            treeNodeInfo.setNodeType(treeNode.getNodeType());
            treeNodeInfo.setNodeValue(treeNode.getNodeValue());
            treeNodeInfo.setRuleKey(treeNode.getRuleKey());
            treeNodeInfo.setRuleDesc(treeNode.getRuleDesc());
            treeNodeInfo.setTreeNodeLineInfoList(treeNodeLineInfoList);

            treeNodeMap.put(treeNode.getId(),treeNodeInfo);
        }

        //规则树聚合对象 构造器方式赋值
        TreeRuleRich treeRuleRich = new TreeRuleRich(treeRoot,treeNodeMap);

        // 返回规则树聚合对象
        return treeRuleRich;
    }
}
