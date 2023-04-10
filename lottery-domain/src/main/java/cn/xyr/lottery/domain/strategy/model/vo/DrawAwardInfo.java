package cn.xyr.lottery.domain.strategy.model.vo;

/**
 * @className: cn.xyr.lottery.domain.strategy.model.vo.DrawAwardInfo
 * @description: 中奖奖品信息
 * @author: xyr
 * @create: 2023-04-05 21:25
 */
public class DrawAwardInfo {

    /**
     * 奖品Id
     */
    private String rewardId;

    /**
     * 奖品名称
     */
    private String awardName;

    public DrawAwardInfo() {
    }

    public DrawAwardInfo(String rewardId, String awardName) {
        this.rewardId = rewardId;
        this.awardName = awardName;
    }

    public String getRewardId() {
        return rewardId;
    }

    public void setRewardId(String rewardId) {
        this.rewardId = rewardId;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }
}
