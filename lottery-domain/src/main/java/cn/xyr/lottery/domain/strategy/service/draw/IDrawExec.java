package cn.xyr.lottery.domain.strategy.service.draw;


import cn.xyr.lottery.domain.strategy.model.req.DrawReq;
import cn.xyr.lottery.domain.strategy.model.res.DrawResult;

/**
 *
 * service，是具体的业务领域逻辑实现层，在这个包下定义了
 * algorithm抽奖算法实现
 * 和具体的抽奖策略包装 draw 层，
 *
 * @className: cn.xyr.lottery.domain.strategy.service.draw.IDrawExec
 * @description: TODO 对外提供抽奖接口
 * @author: xyr
 * @create: 2023-04-03 22:39
 */
public interface IDrawExec {

    /**
     * 抽奖方法
     * @param req 抽奖参数：用户id,策略id
     * @return 中奖结果
     */
    DrawResult doDrawExec(DrawReq req);
}
