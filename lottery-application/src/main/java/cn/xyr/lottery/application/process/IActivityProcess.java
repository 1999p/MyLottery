package cn.xyr.lottery.application.process;

import cn.xyr.lottery.application.process.req.DrawProcessReq;
import cn.xyr.lottery.application.process.res.DrawProcessResult;

/**
 * @description: TODO
 * @author: xyr
 * @date: 2023-05-03 22:15
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
public interface IActivityProcess {

    /**
     * 执行抽奖流程
     * @param req 抽奖请求
     * @return    抽奖结果
     */
    DrawProcessResult doDrawProcessResult(DrawProcessReq req);

}
