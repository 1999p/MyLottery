package cn.xyr.lottery.domain.strategy.service.algorithm.impl;

import cn.xyr.lottery.domain.strategy.model.vo.AwardRateVO;
import cn.xyr.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: TODO 必中奖策略抽奖，排掉已经中奖的概率，重新计算中奖范围
 * @author: xyr
 * @date: 2023-04-06 10:02
 * @Copyright: https://xxydnx.cn
 */
@Component("entiretyRateRandomDrawAlgorithm")
public class EntiretyRateRandomDrawAlgorithm extends BaseAlgorithm {
    /**
     * SecureRandom 生成随机数，索引到对应的奖品信息返回结果
     *
     * @param strategyId      策略ID
     * @param excludeAwardIds 排除掉已经不能作为抽奖的奖品ID，留给风控和空库存使用
     * @return 中奖结果
     */
    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
        BigDecimal differenceDenominator = BigDecimal.ZERO;

        //排除掉不在抽奖范围内的奖品Id集合
        ArrayList<AwardRateVO> differenceAwardRateList = new ArrayList<>(excludeAwardIds.size());
        List<AwardRateVO> awardRateIntervalValList = awardRateInfoMap.get(strategyId);
        for (AwardRateVO awardRateInfo : awardRateIntervalValList){
            String awardId = awardRateInfo.getAwardId();
            if (excludeAwardIds.contains(awardId)){
                continue;
            }
            differenceAwardRateList.add(awardRateInfo);
            differenceDenominator = differenceDenominator.add(awardRateInfo.getAwardRate());
        }

        //前置判断：奖品列表为0，返回null
        if (differenceAwardRateList.size() == 0){
            return null;
        }

        //前置判断：奖品列表为1，直接返回
        if (differenceAwardRateList.size() == 1){
            return differenceAwardRateList.get(0).getAwardId();
        }

        //获取随机概率值
        int randomVal = this.generateSecureRandomIntCode(100);


        //循环获取奖品
        String awardId = null;
        int cursorVal = 0;
        for (AwardRateVO awardRateInfo : differenceAwardRateList){
            int rateVal = awardRateInfo.getAwardRate().divide(differenceDenominator,2,BigDecimal.ROUND_UP)
                    .multiply(new BigDecimal(100)).intValue();
            if (randomVal <= (cursorVal + rateVal)){
                awardId = awardRateInfo.getAwardId();
                break;
            }
            cursorVal += rateVal;
        }

        //返回中奖结果
        return awardId;
    }
}
