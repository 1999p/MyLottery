package cn.xyr.lottery.rpc;

import cn.xyr.lottery.rpc.req.ActivityReq;
import cn.xyr.lottery.rpc.res.ActivityRes;

/**
 * @className: cn.xyr.lottery.rpc.IActivityBooth
 * @description:
 *  活动展台
 *  1. 创建活动
 *  2. 更新活动
 *  3. 查询活动
 * @author: xyr
 * @create: 2023-03-30 21:02
 */
public interface IActivityBooth {

    ActivityRes queryActivityById(ActivityReq req);
}
