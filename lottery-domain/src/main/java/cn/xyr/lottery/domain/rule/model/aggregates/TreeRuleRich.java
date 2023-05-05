package cn.xyr.lottery.domain.rule.model.aggregates;

import cn.xyr.lottery.domain.rule.model.vo.TreeNodeVO;
import cn.xyr.lottery.domain.rule.model.vo.TreeRootVO;

import java.util.Map;

/**
 * @description: 规则树聚合
 * @author: xyr
 * @date: 2023-05-04 20:10
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
public class TreeRuleRich {

    /**
     * 树根信息
     */
    private TreeRootVO treeRoot;

    /**
     * 树节点Id -> 子节点ID
     */
    private Map<Long, TreeNodeVO> treeNodeMap;


    public TreeRuleRich() {
    }

    public TreeRuleRich(TreeRootVO treeRoot, Map<Long, TreeNodeVO> treeNodeMap) {
        this.treeRoot = treeRoot;
        this.treeNodeMap = treeNodeMap;
    }

    public TreeRootVO getTreeRoot() {
        return treeRoot;
    }

    public void setTreeRoot(TreeRootVO treeRoot) {
        this.treeRoot = treeRoot;
    }

    public Map<Long, TreeNodeVO> getTreeNodeMap() {
        return treeNodeMap;
    }

    public void setTreeNodeMap(Map<Long, TreeNodeVO> treeNodeMap) {
        this.treeNodeMap = treeNodeMap;
    }
}
