package cn.xyr.lottery.infrastructure.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import cn.xyr.lottery.infrastructure.po.UserTakeActivity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: TODO 用户领取活动表DAO
 * @author: xyr
 * @date: 2023-04-18 23:00
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
@Mapper
public interface IUserTakeActivityDao {

    /**
     * 插入用户领取活动信息
     *
     * @param userTakeActivity 入参
     */
    // @DBRouter(key = "uId")
    void insert(UserTakeActivity userTakeActivity);
}
