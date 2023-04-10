package cn.xyr.lottery.domain.strategy.respository.impl;

import cn.xyr.lottery.domain.strategy.model.aggregates.StrategyRich;
import cn.xyr.lottery.domain.strategy.respository.IStrategyRepository;
import cn.xyr.lottery.infrastructure.dao.IAwardDao;
import cn.xyr.lottery.infrastructure.dao.IStrategyDao;
import cn.xyr.lottery.infrastructure.dao.IStrategyDetailDao;
import cn.xyr.lottery.infrastructure.po.Award;
import cn.xyr.lottery.infrastructure.po.Strategy;
import cn.xyr.lottery.infrastructure.po.StrategyDetail;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @className: cn.xyr.lottery.domain.strategy.respository.impl.StrategyRepository
 * @description: TODO 策略表仓储服务
 * @author: xyr
 * @create: 2023-04-03 20:53
 */
@Component
public class StrategyRepository implements IStrategyRepository {

    @Resource
    private IStrategyDao strategyDao;

    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Resource
    private IAwardDao awardDao;

    @Override
    public StrategyRich queryStrategyRich(Long strategyId) {
        Strategy strategy = strategyDao.queryStrategy(strategyId);
        List<StrategyDetail> strategyDetailList = strategyDetailDao.queryStrategyDetailList(strategyId);
        return new StrategyRich(strategyId, strategy, strategyDetailList);
    }

    @Override
    public Award queryAwardInfo(String awardId) {
        return awardDao.queryAwardInfo(awardId);
    }

    /**
     * 查询无库存策略奖品ID
     * @param strategyId 策略ID
     * @return 查询结果
     */
    @Override
    public List<String> queryNoStockStrategyAwardList(Long strategyId) {
        return strategyDetailDao.queryNoStockStrategyAwardList(strategyId);
    }

    /**
     * 扣减库存
     *
     * @param strategyId 策略ID
     * @param awardId    奖品ID
     * @return 扣减结果
     */
    @Override
    public boolean deductStock(Long strategyId, String awardId) {
        StrategyDetail req = new StrategyDetail();
        req.setStrategyId(strategyId);
        req.setAwardId(awardId);
        int count = strategyDetailDao.deductStock(req);
        return count == 1;
    }

}
