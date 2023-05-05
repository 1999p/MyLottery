package cn.xyr.lottery.domain.rule.service.logic;

import cn.xyr.lottery.common.Constants;
import cn.xyr.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.xyr.lottery.domain.rule.model.vo.TreeNodeLineVO;

import java.util.List;

/**
 * @description: 规则基础抽象类
 *              实现了接口方法，同时定义了基本的决策方法；
 *              定义了抽象方法，让每一个实现接口的类都必须按照规则提供决策值，这个决策值用于做逻辑比对
 * @author: xyr
 * @date: 2023-05-04 20:23
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
public abstract class BaseLogic implements LogicFilter{

    /**
     * 逻辑决策器
     * @param matterValue          决策值
     * @param treeNodeLineInfoList 决策节点
     * @return 下一个节点id
     */
    @Override
    public Long filter(String matterValue, List<TreeNodeLineVO> treeNodeLineInfoList) {
        for (TreeNodeLineVO nodeLine : treeNodeLineInfoList){
            //进行决策判断
            if (decisionLogic(matterValue,nodeLine)){
                //返回下一个节点
                return nodeLine.getNodeIdTo();
            }
        }
        //若都不符合，返回空节点值
        return Constants.Global.TREE_NULL_NODE;
    }

    /** 获取规则比对值
     * @param decisionMatter 决策物料
     * @return 比对值
     */
    @Override
    public abstract String matterValue(DecisionMatterReq decisionMatter);

    /**
     * 决策逻辑判断  RuleLimitType判断 =/>/</>=/<=/enum
     * @param matterValue 决策物料
     * @param nodeLine 规则树线信息
     * @return true/false
     */
    private boolean decisionLogic(String matterValue,TreeNodeLineVO nodeLine){
        switch (nodeLine.getRuleLimitType()){
            case Constants.RuleLimitType.EQUAL:
                return matterValue.equals(nodeLine.getRuleLimitValue());
            case Constants.RuleLimitType.GT:
                return Double.parseDouble(matterValue) > Double.parseDouble(nodeLine.getRuleLimitValue());
            case Constants.RuleLimitType.LT:
                return Double.parseDouble(matterValue) < Double.parseDouble(nodeLine.getRuleLimitValue());
            case Constants.RuleLimitType.GE:
                return Double.parseDouble(matterValue) >= Double.parseDouble(nodeLine.getRuleLimitValue());
            case Constants.RuleLimitType.LE:
                return Double.parseDouble(matterValue) <= Double.parseDouble(nodeLine.getRuleLimitValue());
            default:
                return false;

        }
    }
}
