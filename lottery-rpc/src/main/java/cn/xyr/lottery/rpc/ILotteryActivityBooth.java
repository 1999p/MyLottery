package cn.xyr.lottery.rpc;

import cn.xyr.lottery.rpc.req.DrawReq;
import cn.xyr.lottery.rpc.req.QuantificationDrawReq;
import cn.xyr.lottery.rpc.res.DrawRes;

/**
 * @description: 抽奖活动展台接口
 * @author: xyr
 * @date: 2023-05-06 10:30
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
public interface ILotteryActivityBooth {


    /**
     * 指定活动抽奖
     * @param drawReq 请求参数
     * @return        抽奖结果
     */
    DrawRes doDraw(DrawReq drawReq);

    /**
     * 量化人群抽奖
     * @param quantificationDrawReq 请求参数
     * @return                      抽奖结果
     */
    DrawRes doQuantificationDraw(QuantificationDrawReq quantificationDrawReq);
}
