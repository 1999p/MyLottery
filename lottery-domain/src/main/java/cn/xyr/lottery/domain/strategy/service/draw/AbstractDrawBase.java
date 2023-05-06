package cn.xyr.lottery.domain.strategy.service.draw;

import cn.xyr.lottery.common.Constants;
import cn.xyr.lottery.domain.strategy.model.aggregates.StrategyRich;
import cn.xyr.lottery.domain.strategy.model.req.DrawReq;
import cn.xyr.lottery.domain.strategy.model.res.DrawResult;
import cn.xyr.lottery.domain.strategy.model.vo.*;
import cn.xyr.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: cn.xyr.lottery.domain.strategy.service.draw.AbstractDrawBase
 * @description: TODO 定义抽象抽奖过程，目标模式 提供标准的执行流程
 * @author: xyr
 * @create: 2023-04-05 20:14
 */
public abstract class AbstractDrawBase extends DrawStrategySupport implements IDrawExec {


    private Logger logger = LoggerFactory.getLogger(AbstractDrawBase.class);

    @Override
    public DrawResult doDrawExec(DrawReq req) {
        // 1.获取抽奖策略
        //调用父类的标准的方法以获取公用的数据服务
        StrategyRich strategyRich = super.queryStrategyRich(req.getStrategyId());
        StrategyBriefVO strategy = strategyRich.getStrategy();

        // 2.校验抽奖策略是否已经初始化到内存（单项概率需要初始化）
        this.checkAndInitRateData(req.getStrategyId(), strategy.getStrategyMode(), strategyRich.getStrategyDetailList());

        // 3.获取不在抽奖范围内的列表：包括：奖品库存为空，风控策略、临时调整
        List<String> excludeAwardIds = this.queryExcludeAwardIds(req.getStrategyId());

        //4.执行抽奖算法
        String awardId = this.drawAlgorithm(req.getStrategyId(), drawAlgorithmMapGroup.get(strategy.getStrategyMode()), excludeAwardIds);

        //5.包装中奖结果（公用的 入参和出参是要锁定的）
        return buildDrawResult(req.getuId(), req.getStrategyId(),awardId,strategy);

    }

    /**
     * 获取不在抽奖范围内的列表，包括：
     * 奖品库存为空、风控策略、临时调整等，这类数据是含有业务逻辑的，所以需要由具体的实现方决定
     *
     * @param strategyId 策略Id
     * @return 排除的奖品ID集合
     */
    protected abstract List<String> queryExcludeAwardIds(Long strategyId);

    /**
     * 执行抽奖算法
     *
     * @param strategyId        策略ID
     * @param drawAlgorithm     抽奖算法模型
     * @param excludeAwardIds   排除的抽奖ID集合
     * @return 中奖奖品Id
     */
    protected abstract String drawAlgorithm(Long strategyId,IDrawAlgorithm drawAlgorithm,List<String> excludeAwardIds);

    /**
     * 校验抽奖策略是否已经初始化到内存
     *
     * @param strategyId         抽奖策略ID
     * @param strategyMode       抽奖策略模式
     * @param strategyDetailList 抽奖策略详情
     */
    private void checkAndInitRateData(Long strategyId, Integer strategyMode, List<StrategyDetailBriefVO> strategyDetailList) {

        //根据抽奖策略模式，获取对应的抽奖服务
        IDrawAlgorithm drawAlgorithm = drawAlgorithmMapGroup.get(strategyMode);

        // 判断已处理过的的数据
        if (drawAlgorithm.isExist(strategyId)) {
            return;
        }

        // 解析并初始化中奖概率数据到散列表
        List<AwardRateVO> awardRateInfoList = new ArrayList<>(strategyDetailList.size());
        for (StrategyDetailBriefVO strategyDetail : strategyDetailList) {
            awardRateInfoList.add(new AwardRateVO(strategyDetail.getAwardId(), strategyDetail.getAwardRate()));
        }

        drawAlgorithm.initRateTuple(strategyId,strategyMode , awardRateInfoList);
    }

    /**
     * 包装抽奖结果
     *
     * @param uId        用户ID
     * @param strategyId 策略ID
     * @param awardId    奖品ID，null 情况：并发抽奖情况下，库存临界值1 -> 0，会有用户中奖结果为 null
     * @return  中奖结果
     */
    private DrawResult buildDrawResult(String uId,Long strategyId,String awardId,StrategyBriefVO strategy){
        if (null == awardId){
            logger.info("执行策略抽奖完成【未中奖】，用户：{} 策略ID：{}",uId,strategyId);
            return new DrawResult(uId,strategyId,Constants.DrawState.FAIL.getCode());
        }

        AwardBriefVO award = super.queryAwardInfoByAwardId(awardId);
        DrawAwardVO drawAwardInfo = new DrawAwardVO(uId,award.getAwardId(), award.getAwardType(),award.getAwardName(),award.getAwardContent());
        drawAwardInfo.setStrategyMode(strategy.getStrategyMode());
        drawAwardInfo.setGrantType(strategy.getGrantType());
        drawAwardInfo.setGrantDate(strategy.getGrantDate());
        logger.info("执行策略抽奖完成【已中奖】，用户：{} 策略ID：{} 奖品ID：{} 奖品名称：{}", uId, strategyId, awardId, award.getAwardName());

        return new DrawResult(uId,strategyId,Constants.DrawState.SUCCESS.getCode(),drawAwardInfo);
    }
}
