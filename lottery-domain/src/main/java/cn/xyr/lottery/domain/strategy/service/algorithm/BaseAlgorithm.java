package cn.xyr.lottery.domain.strategy.service.algorithm;

import cn.xyr.lottery.domain.strategy.model.vo.AwardRateInfo;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @className: cn.xyr.lottery.domain.strategy.service.algorithm.BaseAlgorithm
 * @description: TODO 公用的算法逻辑
 * @author: xyr
 * @create: 2023-04-03 21:39
 */
public abstract class BaseAlgorithm implements IDrawAlgorithm {

    /**
     * 斐波那契散列增量，逻辑：黄金分割点：(√5 - 1) / 2 = 0.6180339887，
     * Math.pow(2, 32) * 0.6180339887 = 0x61c88647
     */
    private final int HASH_INCREMENT = 0x61c88647;

    /**
     * 数组初始化长度 128，保证数据填充时不发生碰撞的最小初始化值
     */
    private final int RATE_TUPLE_LENGTH = 128;

    /**
     * 存放概率与奖品对应的散列结果，strategyId -> rateTuple
     */
    protected Map<Long, String[]> rateTupleMap = new ConcurrentHashMap<>();

    /**
     * 奖品区间概率值 strategyId -> [awardId->begin、awardId->end]
     */
    protected Map<Long, List<AwardRateInfo>> awardRateInfoMap = new ConcurrentHashMap<>();

    /**
     * 初始化数组
     *
     * @param strategyId        策略ID
     * @param awardRateInfoList 奖品概率配置集合 「值示例：AwardRateInfo.awardRate = 0.04」
     */
    @Override
    public void initRateTuple(Long strategyId, List<AwardRateInfo> awardRateInfoList) {

        //保存奖品概率信息
        awardRateInfoMap.put(strategyId,awardRateInfoList);

        //computeIfAbsent() 方法对 hashMap 中指定 key 的值进行重新计算，如果不存在这个 key，则添加到 hashMap 中
        String[] rateTuple = rateTupleMap.computeIfAbsent(strategyId, k -> new String[RATE_TUPLE_LENGTH]);

        int cursorVal = 0;
        for (AwardRateInfo awardRateInfo: awardRateInfoList){
            int rateVal = awardRateInfo.getAwardRate().multiply(new BigDecimal(100)).intValue();

            //循环填充概率范围值
            for (int i = cursorVal; i <= (rateVal + cursorVal) ; i++) {
                rateTuple[hashIdx(i)] = awardRateInfo.getAwardId();

            }

            cursorVal += rateVal;
        }



    }

    @Override
    public boolean isExistRateTuple(Long strategyId) {
        return rateTupleMap.containsKey(strategyId);
    }

    /**
     * 斐波那契散列法
     * 计算哈希索引下标值
     * @param val 值
     * @return 索引
     */
    protected int hashIdx(int val){
        int hashCode = val * HASH_INCREMENT + HASH_INCREMENT;
        return hashCode & (RATE_TUPLE_LENGTH -1 );
    }

    /**
     *
     * 生成百位随机抽奖码
     * @param bound
     * @return 随机值
     */
    protected int generateSecureRandomIntCode(int bound) {
        return new SecureRandom().nextInt(bound) + 1;
    }
}
