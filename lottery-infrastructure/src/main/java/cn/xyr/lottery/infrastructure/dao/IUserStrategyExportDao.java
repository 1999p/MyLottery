package cn.xyr.lottery.infrastructure.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import cn.bugstack.middleware.db.router.annotation.DBRouterStrategy;
import cn.xyr.lottery.infrastructure.po.UserStrategyExport;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: TODO 用户策略计算结果表DAO
 * @author: xyr
 * @date: 2023-04-18 22:44
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
@Mapper
@DBRouterStrategy(splitTable = true)
public interface IUserStrategyExportDao {

    /**
     * 新增数据
     * @param userStrategyExportDao 用户策略
     */
    @DBRouter(key = "uId")
    void insert(UserStrategyExport userStrategyExportDao);

    /**
     * 查询数据
     * @param uId 用户ID
     * @return 用户策略
     */
    @DBRouter
    UserStrategyExport queryUserStrategyExportByUId(String uId);
}
