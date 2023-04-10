package cn.xyr.lottery.domain.strategy.service.draw;

import cn.xyr.lottery.domain.strategy.model.aggregates.StrategyRich;
import cn.xyr.lottery.domain.strategy.respository.IStrategyRepository;
import cn.xyr.lottery.infrastructure.po.Award;

import javax.annotation.Resource;

/**
 * @className: cn.xyr.lottery.domain.strategy.service.draw.DrawStrategySupport
 * @description: TODO 策略支撑类
 * @author: xyr
 * @create: 2023-04-05 20:09
 */
public class DrawStrategySupport extends DrawConfig{

    @Resource
    protected IStrategyRepository strategyRepository;

    /**
     * 查询策略配置信息
     * @param strategyId 策略id
     * @return 策略配置信息
     */
    protected StrategyRich queryStrategyRich(Long strategyId){
        return strategyRepository.queryStrategyRich(strategyId);
    }

    /**
     * 查询奖品详细信息
     * @param awardId 奖品id
     * @return 中奖详情
     */
    protected Award queryAwardInfoByAwardId(String awardId){
        return strategyRepository.queryAwardInfo(awardId);
    }
}
