package cn.xyr.lottery.infrastructure.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import cn.xyr.lottery.infrastructure.po.UserTakeActivityCount;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: TODO 用户活动参与次数表Dao
 * @author: xyr
 * @date: 2023-04-19 22:35
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
@Mapper
public interface IUserTakeActivityCountDao {

    /**
     * 查询用户领取次数信息
     * @param userTakeActivityCountReq 请求入参【活动号、用户ID】
     * @return 领取结果
     */
    @DBRouter
    UserTakeActivityCount queryUserTakeActivityCount(UserTakeActivityCount userTakeActivityCountReq);

    /**
     * 插入领取次数信息
     * @param userTakeActivityCount 请求入参
     */
    // @DBRouter
    void insert(UserTakeActivityCount userTakeActivityCount);

    /**
     * 更新领取次数信息
     * @param userTakeActivityCount 请求入参
     * @return 更新数量
     */
    // @DBRouter
    int updateLeftCount(UserTakeActivityCount userTakeActivityCount);
}
