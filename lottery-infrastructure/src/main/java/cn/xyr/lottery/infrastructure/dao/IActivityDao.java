package cn.xyr.lottery.infrastructure.dao;

import cn.xyr.lottery.infrastructure.po.Activity;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @className: cn.xyr.lottery.infrastructure.dao.IActivityDao
 * @description: TODO 创建活动表Dao信息，由interfaces模块调用infrastructure模块，完成CRUD操作
 * @author: xyr
 * @create: 2023-03-30 15:58
 */
@Mapper
public interface IActivityDao {

    /**
     * 插入活动信息
     * @param req
     */
    void insert(Activity req);

    /**
     * 查询活动信息
     * @param activityId
     * @return 活动信息
     */
    Activity queryActivityById(Long activityId);

}
