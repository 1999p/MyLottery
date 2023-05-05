package cn.xyr.lottery.domain.rule.model.req;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 决策物料
 * @author: xyr
 * @date: 2023-05-04 20:09
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
public class DecisionMatterReq {

    /**
     * 规则树Id
     */
    private Long treeId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 决策值
     */
    private Map<String,Object> valMap;

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, Object> getValMap() {
        return valMap;
    }

    public void setValMap(HashMap<String, Object> valMap) {
        this.valMap = valMap;
    }
}
