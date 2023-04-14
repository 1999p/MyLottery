package cn.xyr.lottery.domain.activity.service.deploy;

import cn.xyr.lottery.domain.activity.model.req.ActivityConfigReq;

/**
 * @description: TODO 部署活动配置接口
 * @author: xyr
 * @date: 2023-04-13 19:21
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
public interface IActivityDeploy {

    /**
     * 创建活动信息
     * @param req 活动配置请求对象信息
     */
    void createActivity(ActivityConfigReq req);

    /**
     * 修改活动信息
     * @param req 活动配置请求对象信息
     */
    void updateActivity(ActivityConfigReq req);
}
