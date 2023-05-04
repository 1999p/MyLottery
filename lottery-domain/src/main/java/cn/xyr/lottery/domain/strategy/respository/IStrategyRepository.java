package cn.xyr.lottery.domain.strategy.respository;

import cn.xyr.lottery.domain.strategy.model.aggregates.StrategyRich;
import cn.xyr.lottery.domain.strategy.model.vo.AwardBriefVO;


import java.util.List;

/**
 * @className: cn.xyr.lottery.domain.strategy.respository.IStrategyRepository
 * @description: TODO 策略表仓储服务
 * @author: xyr
 * @create: 2023-04-03 20:52
 */
public interface IStrategyRepository {


    /**
     * 查询策略信息
     *
     * @param strategyId 策略ID
     * @return           策略信息
     */
    StrategyRich queryStrategyRich(Long strategyId);

    /**
     * 查询奖励配置
     *
     * @param awardId   奖励ID
     * @return          奖励信息
     */
    AwardBriefVO queryAwardInfo(String awardId);


    /**
     * 查询无库存奖品
     *
     * @param strategyId 策略ID
     * @return           无库存奖品
     */
    List<String> queryNoStockStrategyAwardList(Long strategyId);

    /**
     * 扣减库存
     *
     * @param strategyId 策略ID
     * @param awardId    奖品ID
     * @return           扣减结果
     */
    boolean deductStock(Long strategyId, String awardId);

}
