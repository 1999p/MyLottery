package cn.xyr.lottery.domain.strategy.service.draw;

import cn.xyr.lottery.common.Constants;
import cn.xyr.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @className: cn.xyr.lottery.domain.strategy.service.draw.DrawConfig
 * @description: TODO 抽奖统一配置信息类
 * @author: xyr
 * @create: 2023-04-03 22:41
 */
public class DrawConfig {


    /**
     * 整体概率
     */
    @Resource
    private IDrawAlgorithm entiretyRateRandomDrawAlgorithm;

    /**
     * 单项概率
     */
    @Resource
    private IDrawAlgorithm singleRateRandomDrawAlgorithm;

    /**
     * 抽奖策略组
     */
    protected static Map<Integer, IDrawAlgorithm> drawAlgorithmMapGroup = new ConcurrentHashMap<>();

    /**
     * 将两种抽奖策略配置到map中去
     */
    @PostConstruct
    public void init() {
        drawAlgorithmMapGroup.put(Constants.StrategyMode.ENTIRETY.getCode(),entiretyRateRandomDrawAlgorithm);
        drawAlgorithmMapGroup.put(Constants.StrategyMode.SINGLE.getCode(), singleRateRandomDrawAlgorithm);
    }
}
