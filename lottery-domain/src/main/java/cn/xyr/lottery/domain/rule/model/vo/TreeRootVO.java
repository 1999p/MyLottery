package cn.xyr.lottery.domain.rule.model.vo;

/**
 * @description: 规则树根配置
 * 组合模式的特点就像是搭建出一棵二叉树，而库表中则需要把这样一颗二叉树存放进去，那么这里就需要包括：树根、树茎、子叶、果实。
 * 在具体的逻辑实现中则需要通过子叶判断走哪个树茎以及最终筛选出一个果实来。
 * @author: xyr
 * @date: 2023-05-04 19:58
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
public class TreeRootVO {

    /**
     * 规则树ID
     */
    private Long treeId;

    /**
     * 规则树根Id
     */
    private Long treeRootNodeId;

    /**
     * 规则树名称
     */
    private String treeName;

    public TreeRootVO() {
    }

    public TreeRootVO(Long treeId, Long treeRootNodeId, String treeName) {
        this.treeId = treeId;
        this.treeRootNodeId = treeRootNodeId;
        this.treeName = treeName;
    }

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public Long getTreeRootNodeId() {
        return treeRootNodeId;
    }

    public void setTreeRootNodeId(Long treeRootNodeId) {
        this.treeRootNodeId = treeRootNodeId;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    @Override
    public String toString() {
        return "TreeRootVO{" +
                "treeId=" + treeId +
                ", treeRootNodeId=" + treeRootNodeId +
                ", treeName='" + treeName + '\'' +
                '}';
    }
}
