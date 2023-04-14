package cn.xyr.lottery.infrastructure.dao;

import cn.xyr.lottery.infrastructure.po.Award;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @className: cn.xyr.lottery.infrastructure.dao.IAwardDao
 * @description: TODO
 * @author: xyr
 * @github: https://github.com/1999p
 * @create: 2023-04-03 16:53
 */
@Mapper
public interface IAwardDao {

    /**
     * 查询奖品信息
     * @param awardId 奖品Id
     * @return 获奖信息
     */
    Award queryAwardInfo(String awardId);

    /**
     * 插入奖品配置
     * @param list 奖品配置
     */
    void insertList(List<Award> list);
}
