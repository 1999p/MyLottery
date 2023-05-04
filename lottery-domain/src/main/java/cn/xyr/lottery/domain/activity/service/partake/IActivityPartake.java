package cn.xyr.lottery.domain.activity.service.partake;

import cn.xyr.lottery.common.Result;
import cn.xyr.lottery.domain.activity.model.req.PartakeReq;
import cn.xyr.lottery.domain.activity.model.res.PartakeResult;
import cn.xyr.lottery.domain.activity.model.vo.DrawOrderVO;

/**
 * @description: TODO 抽奖活动参与接口
 * @author: xyr
 * @date: 2023-04-13 19:41
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
public interface IActivityPartake {

    /**
     *
     * 参与活动
     * @param req 入参
     * @return    领取结果
     */
    PartakeResult doPartake(PartakeReq req);

    /**
     * 保存奖品单
     * @param drawOrder 奖品单
     * @return          保存结果
     */
    Result recordDrawOrder(DrawOrderVO drawOrder);
}
