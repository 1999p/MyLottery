package cn.xyr.lottery.domain.strategy.respository;

import cn.xyr.lottery.domain.strategy.model.aggregates.StrategyRich;
import cn.xyr.lottery.infrastructure.po.Award;
import cn.xyr.lottery.infrastructure.po.Strategy;

import java.util.List;

/**
 * @className: cn.xyr.lottery.domain.strategy.respository.IStrategyRepository
 * @description: TODO 策略表仓储服务
 * @author: xyr
 * @create: 2023-04-03 20:52
 */
public interface IStrategyRepository {

    StrategyRich queryStrategyRich(Long strategyId);

    Award queryAwardInfo(String awardId);


    /**
     * 查询
     *
     * @param strategyId 策略id
     * @return 集合
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
