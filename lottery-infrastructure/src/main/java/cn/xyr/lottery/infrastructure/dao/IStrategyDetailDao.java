package cn.xyr.lottery.infrastructure.dao;

import cn.xyr.lottery.infrastructure.po.StrategyDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @className: cn.xyr.lottery.infrastructure.dao.IStrategyDetailDao
 * @description: TODO
 * @author: xyr
 * @create: 2023-04-03 20:20
 */
@Mapper
public interface IStrategyDetailDao {

    /**
     * 查询策略表详细配置
     * @param strategyId 策略ID
     * @return 返回查询策略表
     */
    List<StrategyDetail> queryStrategyDetailList(Long strategyId);

    /**
     * 查询无库存策略奖品ID
     * @param strategyId 策略ID
     * @return 查询结果
     */
    List<String> queryNoStockStrategyAwardList(Long strategyId);

    /**
     * 扣减库存
     * @param strategyDetailReq 策略ID、奖品ID
     * @return                  返回结果
     */
    int deductStock(StrategyDetail strategyDetailReq);
}
