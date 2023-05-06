package cn.xyr.lottery.rpc.req;

import java.util.Map;

/**
 * @description: 量化人群抽奖请求参数
 * @author: xyr
 * @date: 2023-05-06 10:35
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
public class QuantificationDrawReq {
    /** 用户ID */
    private String uId;
    /** 规则树ID */
    private Long treeId;
    /** 决策值 */
    private Map<String, Object> valMap;

    public QuantificationDrawReq() {
    }

    public QuantificationDrawReq(String uId, Long treeId, Map<String, Object> valMap) {
        this.uId = uId;
        this.treeId = treeId;
        this.valMap = valMap;
    }

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Map<String, Object> getValMap() {
        return valMap;
    }

    public void setValMap(Map<String, Object> valMap) {
        this.valMap = valMap;
    }

}
