package cn.xyr.lottery.domain.strategy.service.algorithm.impl;

import cn.xyr.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.List;

/**
 * 抽奖算法实现类
 *
 * @className: cn.xyr.lottery.domain.strategy.service.algorithm.impl.SingleRateRandomDrawAlgorithm
 * @description: TODO 【推荐】单项随机概率抽奖，抽到一个已经排掉的奖品则未中奖
 * 单项概率算法不涉及奖品概率重新计算的问题，分配好的概率结果是固定的
 * @author: xyr
 * @create: 2023-04-03 22:31
 */
@Component("singleRateRandomDrawAlgorithm")
public class SingleRateRandomDrawAlgorithm extends BaseAlgorithm {

    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {

        // 获取策略对应的元祖
        String[] rateTuple = super.rateTupleMap.get(strategyId);
        assert rateTuple != null;

        // 随机索引
        int randomVal = this.generateSecureRandomIntCode(100);
        int idx = super.hashIdx(randomVal);

        //根据索引找到数据元祖中的奖品信息，返回结果
        String awardId = rateTuple[idx];
        // 如果中奖ID命中排除奖品列表，则返回NULL
        if (excludeAwardIds.contains(awardId)) {
            return null;
        }

        return awardId;
    }

}