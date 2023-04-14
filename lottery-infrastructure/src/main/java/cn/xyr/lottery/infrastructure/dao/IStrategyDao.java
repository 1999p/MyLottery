package cn.xyr.lottery.infrastructure.dao;

import cn.xyr.lottery.infrastructure.po.Strategy;
import org.apache.ibatis.annotations.Mapper;

/**
 * @className: cn.xyr.lottery.infrastructure.dao.IStrategyDao
 * @description: TODO
 * @author: xyr
 * @github: https://github.com/1999p
 * @create: 2023-04-03 20:14
 */
@Mapper
public interface IStrategyDao {

    /**
     * 查询策略配置
     * @param strategyId 策略id
     * @return 策略信息
     */
    Strategy queryStrategy(Long strategyId);

    /**
     * 插入策略配置
     * @param req 策略配置
     */
    void insert(Strategy req);

}
