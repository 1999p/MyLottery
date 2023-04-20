package cn.xyr.lottery.domain.activity.model.res;

import cn.xyr.lottery.common.Result;

/**
 * @description: TODO 活动参与结果
 * @author: xyr
 * @date: 2023-04-19 21:06
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
public class PartakeResult extends Result {


    /**
     * 策略Id
     */
    private Long strategyId;

    public PartakeResult(String code, String info) {
        super(code, info);
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }
}
