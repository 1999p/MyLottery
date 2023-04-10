package cn.xyr.lottery.domain.strategy.model.req;

/**
 * @className: cn.xyr.lottery.domain.strategy.model.req.DrawReq
 * @description: TODO
 * @author: xyr
 * @create: 2023-04-03 20:46
 */
public class DrawReq {

    // 用户ID
    private String uId;

    // 策略ID
    private Long strategyId;

    public DrawReq() {
    }

    public DrawReq(String uId, Long strategyId) {
        this.uId = uId;
        this.strategyId = strategyId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

}
